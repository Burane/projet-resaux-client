package Controller;

import event.EventBus;
import event.interfaces.SearchEventInterface;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import request.receive.PreviewImageResponse;
import request.receive.SearchResponse;
import request.send.FullImageRequest;
import request.send.SearchRequest;
import server.Client;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class RechercheController implements SearchEventInterface {

	@FXML private TextField rechercheField;
	@FXML private GridPane gridPane;

	@FXML
	public void initialize() {
		EventBus.getInstance().subscribeToSearchEvent(this);
	}

	public void onRecherche(ActionEvent actionEvent) {
		String query = rechercheField.getText();
		Client.getInstance().send(new SearchRequest(query, 0, 9));
	}

	@Override
	public void onSearchResponse(SearchResponse searchResponse) {
		System.out.println("ON cherche response");
		loadImages(searchResponse.getImages());
	}

	private void loadImages(ArrayList<PreviewImageResponse> images) {
		List<Node> nodes = gridPane.getChildren();
		Platform.runLater(() -> {
			for (int i = 0; i < nodes.size(); i++) {
				if (nodes.get(i) instanceof ImageView) {
					ImageView imageView = (ImageView) nodes.get(i);
					imageView.setCache(false);
					if (i < images.size()) {
						System.out.println(images.get(i).getTitre());
						String data = images.get(i).getData();
						InputStream inputstream = new ByteArrayInputStream(Base64.getDecoder().decode(data));
						Image image = new Image(inputstream, 200,200,true,false);
						imageView.setImage(image);
						imageView.setOnMouseClicked(getFullImage(images.get(i).getImageId()));
					} else {
						imageView.setImage(null);
					}
				}
			}
		});
	}

	private EventHandler<? super MouseEvent> getFullImage(int imageId) {
		return (EventHandler<MouseEvent>) event -> {
			Client.getInstance().send(new FullImageRequest(imageId));
		};

	}
}