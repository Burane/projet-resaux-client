package Controller;

import Utils.ImageUtils;
import event.EventBus;
import event.interfaces.FullImageEventInterface;
import event.interfaces.LikeEventInterface;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import request.receive.FullImageResponse;
import request.receive.LikeResponse;
import request.send.DeleteRequest;
import request.send.FullImageRequest;
import request.send.LikeRequest;
import server.Client;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ImageController implements FullImageEventInterface, LikeEventInterface {

	private int imageId;
	private byte[] imageData;
	private FullImageResponse fullImageResponse;

	public void initialize() {
		EventBus.getInstance().subscribeToFullImageEvent(this);
		Client.getInstance().send(new FullImageRequest(imageId));
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	@FXML private Button likeButton;
	@FXML private Button downloadButton;
	@FXML private Button deleteButton;
	@FXML private Pane imageView;
	@FXML private Label imageTitre;

	@FXML
	void OnDowload(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();

		String extension = null;
		try {
			extension = ImageUtils.getImageFormat(imageData);
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				extension + " files (*." + extension + ")", "*." + extension);
		fileChooser.getExtensionFilters().add(extFilter);

		Node source = (Node) event.getSource();
		Window stage = source.getScene().getWindow();
		File file = fileChooser.showSaveDialog(stage);

		if (file != null) {
			try (FileOutputStream fos = new FileOutputStream(file.getAbsolutePath())) {
				fos.write(imageData);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	void OnLike(ActionEvent event) {
		EventBus.getInstance().subscribeToLikeEvent(this);
		Client.getInstance().send(new LikeRequest(imageId));
	}

	@Override
	public void onFullImageResponse(FullImageResponse fullImageResponse) {
		if (fullImageResponse.getImageId() != imageId) {
			return;
		}
		System.out.println(fullImageResponse);
		this.fullImageResponse = fullImageResponse;

		EventBus.getInstance().unSubscribeToFullImageEvent(this);
		imageData = Base64.getDecoder().decode(fullImageResponse.getData());
		Image image = new Image(new ByteArrayInputStream(imageData));
		Platform.runLater(() -> {
			deleteButton.setVisible(fullImageResponse.isOwnedByUser());
			likeButton.setDisable(false);
			downloadButton.setDisable(false);
			imageTitre.setText(fullImageResponse.getTitre());
			imageView.setBackground(new Background(
					new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
							BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, true, false))));
			likeButton.setText(fullImageResponse.getNbLike() + " ♥");
			likeButton.setStyle(fullImageResponse.isLikedByUser() ?
					"-fx-background-color: #008000" :
					"-fx-background-color: #FF0000");
		});

	}

	public void onSupprimer(ActionEvent actionEvent) {
		Alert alert = new Alert(Alert.AlertType.WARNING,
				"Voulez-vous vraiment supprimer " + fullImageResponse.getTitre() + " ?", ButtonType.YES, ButtonType.NO,
				ButtonType.CANCEL);
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES) {
			Client.getInstance().send(new DeleteRequest(imageId));
			Node source = (Node) actionEvent.getSource();
			Stage stage = (Stage) source.getScene().getWindow();
			stage.close();

		}
	}

	@Override
	public void onLikeResponse(LikeResponse likeResponse) {
		System.out.println(likeResponse);
		if (likeResponse.getImageId() != imageId)
			return;

		EventBus.getInstance().unSubscribeToLikeEvent(this);

		Platform.runLater(() -> {

			likeButton.setStyle(
					likeResponse.isLikedByUser() ? "-fx-background-color: #008000" : "-fx-background-color: #FF0000");

			likeButton.setText((likeResponse.isLikedByUser() ?
					fullImageResponse.getNbLike() + 1 :
					fullImageResponse.getNbLike()) + " ♥");
		});

	}
}