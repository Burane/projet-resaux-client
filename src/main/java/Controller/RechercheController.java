package Controller;

import event.EventBus;
import event.interfaces.SearchEventInterface;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import request.receive.ImageResponse;
import request.receive.SearchResponse;
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

	private void loadImages(ArrayList<ImageResponse> images) {
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
						Image image = new Image(inputstream);
						imageView.setImage(image);

					} else {
						imageView.setImage(null);
					}
				}
			}
		});
	}
}
