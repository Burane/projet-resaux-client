package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class InscriptionController {
	@FXML private AnchorPane rootPane;

	public void onInscription(ActionEvent actionEvent) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Connexion.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) rootPane.getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
}
