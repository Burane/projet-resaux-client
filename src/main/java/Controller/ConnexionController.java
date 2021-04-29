package Controller;

import event.EventBus;
import event.interfaces.AuthentificationEventInterface;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import request.receive.AuthentificationResponse;
import request.send.LoginRequest;
import server.Client;

import java.io.*;

public class ConnexionController implements AuthentificationEventInterface {
	@FXML public TextField username;
	@FXML public TextField password;
	@FXML private AnchorPane rootPane;

	@FXML
	public void initialize() {
		EventBus.getInstance().subscribeToAuthentificationEvent(this);
	}

	public void OnConnexion(ActionEvent actionEvent) {
		Client client = Client.getInstance();
		client.send(new LoginRequest(username.getText(), password.getText()));
	}

	@Override
	public void onAuthentificationResponse(AuthentificationResponse authentificationResponse) {
		System.out.println("AUTH CONTROLERS " + authentificationResponse);

		if (authentificationResponse.isSuccess()) {
			EventBus.getInstance().unSubscribeToAuthentificationEvent(this);
			setAccueilScene();
		}
	}

	public void setAccueilScene() {
		Platform.runLater(() -> {
			Parent root = null;
			try {
				root = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			Scene scene = new Scene(root);
			Stage stage = (Stage) rootPane.getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		});

	}

	public void onRetour(ActionEvent actionEvent) {
	}

	public void onConnexion(ActionEvent actionEvent) {
		Client client = Client.getInstance();
		client.send(new LoginRequest(username.getText(), password.getText()));
	}
}