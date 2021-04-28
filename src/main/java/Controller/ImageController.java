package Controller;

import Utils.ImageUtils;
import event.EventBus;
import event.interfaces.FullImageEventInterface;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import request.receive.FullImageResponse;
import request.send.FullImageRequest;
import server.Client;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ImageController implements FullImageEventInterface {

	private int imageId;
	private byte[] imageData;

	public void initialize() {
		EventBus.getInstance().subscribeToFullImageEvent(this);
		Client.getInstance().send(new FullImageRequest(imageId));
		System.out.println("controller image id " + imageId);
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public int getImageId() {
		return imageId;
	}

	@FXML private Button likeButton;
	@FXML private Button downloadButton;
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
		//		Client.getInstance().send(new);

	}

	@Override
	public void onFullImageResponse(FullImageResponse fullImageResponse) {
		System.out.println(fullImageResponse);
		if (fullImageResponse.getImageId() != imageId)
			return;

		EventBus.getInstance().unSubscribeToFullImageEvent(this);
		imageData = Base64.getDecoder().decode(fullImageResponse.getData());
		Image image = new Image(new ByteArrayInputStream(imageData));
		Platform.runLater(() -> {
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
}