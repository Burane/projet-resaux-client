package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import request.send.LoginRequest;
import server.Client;

import java.io.IOException;

public class ConnexionController {
	@FXML public TextField username;
	@FXML public TextField password;
	@FXML private AnchorPane rootPane;

	public void OnConnexion(ActionEvent actionEvent) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) rootPane.getScene().getWindow();
		stage.setScene(scene);
		stage.show();

		Client client = Client.getInstance();
		client.send(new LoginRequest(username.getText(), password.getText()));

	}
}
