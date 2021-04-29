package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {


	@FXML
	AnchorPane rootPane;

	public void OnConnexion(ActionEvent actionEvent) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Connexion.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) rootPane.getScene().getWindow();
		stage.setScene(scene);
		stage.show();

		rootPane.getChildren().add(new ImageView());
	}

	public void OnInscription(ActionEvent actionEvent) throws IOException {
		System.out.println("test");
		Parent root = FXMLLoader.load(getClass().getResource("Inscription.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) rootPane.getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
}