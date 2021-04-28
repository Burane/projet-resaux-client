package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class AccueilController {

	@FXML BorderPane scene;

	@FXML
	public void initialize() {
		setRechercheView();
	}

	private void setRechercheView() {
		Parent menu = null;
		System.out.println(scene);

		try {
			menu = FXMLLoader.load(getClass().getResource("Recherche.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		scene.setCenter(menu);
	}

	public void onAjouter(ActionEvent actionEvent) {
		System.out.println("ajouter image");
		System.out.println(scene);
		Parent menu = null;
		try {
			menu = FXMLLoader.load(getClass().getResource("Upload.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		scene.setCenter(menu);
	}

	public void onRecherche(ActionEvent actionEvent) {
		setRechercheView();
	}

}