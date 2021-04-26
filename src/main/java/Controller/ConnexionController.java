package Controller;

import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import request.send.LoginRequest;
import server.Client;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ConnexionController {
	@FXML private AnchorPane rootPane;

	public void OnConnexion(ActionEvent actionEvent) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) rootPane.getScene().getWindow();
		stage.setScene(scene);
		stage.show();

		Client client = Client.getInstance();
		client.send(new LoginRequest("username","password"));
//		ArrayList images = new ArrayList();
//
//		for(Object image : images){
//			ImageView imgview = new ImageView();
//			imgview.setImage(new Image(InputStream.nullInputStream()));
//			rootPane.getChildren().add(imgview);
//		}
	}
}
