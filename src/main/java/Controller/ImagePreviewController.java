package Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import request.receive.PreviewImageResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public abstract class ImagePreviewController {

	@FXML TextField rechercheField;
	@FXML GridPane gridPane;
	@FXML Label page;

	int imagePerPage;
	int pageNumber = 0;

	@FXML
	public void initialize() {
		imagePerPage = gridPane.getRowCount() * gridPane.getColumnCount();
	}

	public void onRecherche(ActionEvent actionEvent) {
	}


	void loadImages(ArrayList<PreviewImageResponse> images) {
		List<Node> nodes = gridPane.getChildren();
		Platform.runLater(() -> {
			System.out.println("nodes.size() " + nodes.size());
			System.out.println("images.size() " + images.size());
			for (int i = 0; i < nodes.size(); i++) {
				if (nodes.get(i) instanceof AnchorPane) {

					AnchorPane imageView = (AnchorPane) nodes.get(i);
					imageView.setOnMouseClicked(null);
					imageView.setCache(true);

					if (i < images.size()) {

						String data = images.get(i).getData();
						InputStream inputstream = new ByteArrayInputStream(Base64.getDecoder().decode(data));
						Image image = new Image(inputstream, 200, 200, true, false);
						imageView.setBackground(new Background(
								new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
										BackgroundPosition.CENTER,
										new BackgroundSize(1.0, 1.0, true, true, true, false))));
						imageView.setOnMouseClicked(getFullImage(images.get(i).getImageId()));

					} else {
						imageView.setBackground(null);
					}
				}
			}
		});
	}

	private EventHandler<? super MouseEvent> getFullImage(int imageId) {
		return (EventHandler<MouseEvent>) event -> {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("image.fxml"));

			loader.setControllerFactory((Class<?> controllerType) -> {
				if (controllerType == ImageController.class) {
					ImageController controller = new ImageController();
					controller.setImageId(imageId);
					return controller;
				} else {
					try {
						return controllerType.getDeclaredConstructor().newInstance();
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			});

			Stage stage = new Stage(StageStyle.DECORATED);
			try {
				stage.setScene(new Scene(loader.load()));
			} catch (IOException e) {
				e.printStackTrace();
			}

			ImageController controller = loader.getController();

			controller.setImageId(imageId);
			stage.setResizable(false);
			stage.show();

		};

	}

	public void onPrecedent(ActionEvent actionEvent) {
		if (pageNumber > 0) {
			pageNumber--;
			onRecherche(null);
		}
	}

	public void onSuivant(ActionEvent actionEvent) {
		pageNumber++;
		onRecherche(null);
	}
}