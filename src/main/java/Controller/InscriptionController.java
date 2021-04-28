package Controller;

import event.EventBus;
import event.interfaces.ErrorEventInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import request.receive.ErrorResponse;
import request.send.RegisterRequest;
import server.Client;
import javafx.scene.control.TextField;


import java.io.IOException;

public class InscriptionController implements ErrorEventInterface {
	@FXML private AnchorPane rootPane;
	@FXML public TextField passwordConfirmation;
	@FXML public TextField password;
	@FXML public TextField username;
	@FXML
	public void initialize() {
		EventBus.getInstance().subscribeToErrorEvent(this);
	}

	public void onInscription(ActionEvent actionEvent) throws IOException {
		if(!password.getText().equals(passwordConfirmation.getText())) {
			System.out.println("les deux mdp ne sont pas pareils");
			return;
		}

		Client.getInstance().send(new RegisterRequest(username.getText(),password.getText()));

		Parent root = FXMLLoader.load(getClass().getResource("Connexion.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) rootPane.getScene().getWindow();
		stage.setScene(scene);
		stage.show();


	}

	@Override
	public void onErrorResponse(ErrorResponse errorResponse) {
		System.out.println(errorResponse);
	}
}
