package Controller;

import event.EventBus;
import event.interfaces.ErrorEventInterface;
import event.interfaces.SuccessEventInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import request.receive.ErrorResponse;
import request.receive.SuccessResponse;
import request.send.UploadRequest;
import server.Client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

public class UploadController implements SuccessEventInterface, ErrorEventInterface {

	@FXML private ImageView imageArea;

	@FXML private TextField titre;

	@FXML private TextField tag;

	@FXML private Button ajouterTag;

	@FXML private TextArea tagsArea;

	private File image;
	private boolean isFirstTag = true;

	@FXML
	public void initialize() {
		EventBus.getInstance().subscribeToErrorEvent(this);
		EventBus.getInstance().subscribeToSuccessEvent(this);
	}

	@FXML
	public void onAjouterTag(ActionEvent event) {
		if (isFirstTag) {
			tagsArea.appendText(tag.getText());
			isFirstTag = false;
		} else
			tagsArea.appendText(" ; " + tag.getText());
		tag.setText("");
	}

	@FXML
	public void onChoisirImage(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();

		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png",
				"*.gif");
		fileChooser.getExtensionFilters().add(extFilter);

		image = fileChooser.showOpenDialog(null);
		try {
			imageArea.setImage(new Image(new FileInputStream(image)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void onUpload(ActionEvent event) {
		if (imageArea.getImage() == null)
			return;

		if (titre.getText() == "" || titre.getText() == null)
			return;

		byte[] data = new byte[0];
		try {
			data = Files.readAllBytes(image.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		String base64 = Base64.getEncoder().encodeToString(data);

		ArrayList<String> tags = new ArrayList<>(Arrays.asList(tagsArea.getText().split(" ; ")));

		System.out.println(titre.getText() + " " + Arrays.toString(tags.toArray()));

		Client.getInstance().send(new UploadRequest(base64, titre.getText(), tags));
	}

	@Override
	public void onErrorResponse(ErrorResponse errorResponse) {
		System.out.println(errorResponse);
	}

	@Override
	public void onSuccessResponse(SuccessResponse successResponse) {
		System.out.println(successResponse);
	}

}