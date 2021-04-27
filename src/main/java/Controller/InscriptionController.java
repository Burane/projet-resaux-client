package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import request.send.RegisterRequest;
import server.Client;

public class InscriptionController {
	@FXML public TextField passwordConfirmation;
	@FXML public TextField password;
	@FXML public TextField username;

	public void onInscription(ActionEvent actionEvent) {

		if(!password.getText().equals(passwordConfirmation.getText())) {
			System.out.println("les deux mdp ne sont pas pareils");
			return;
		}

		Client.getInstance().send(new RegisterRequest(username.getText(),password.getText()));


		System.out.println("inscription");
	}
}
