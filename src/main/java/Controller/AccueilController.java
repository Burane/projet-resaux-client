package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class AccueilController {

	@FXML AnchorPane rootPane;
	@FXML BorderPane scene;

	@FXML
	public void initialize() {
		setRechercheView();
	}

	private void setRechercheView() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Recherche.fxml"));

		loader.setControllerFactory((Class<?> controllerType) -> {
			if (controllerType == ImagePreviewController.class) {
				return new RechercheController();
			} else {
				try {
					return controllerType.getDeclaredConstructor().newInstance();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});

		try {
			scene.setCenter(loader.load());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void onAjouter(ActionEvent actionEvent) {
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

	public void onMyImages(ActionEvent actionEvent) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MesImages.fxml"));

		loader.setControllerFactory((Class<?> controllerType) -> {
			if (controllerType == ImagePreviewController.class) {
				return new MesImagesController();
			} else {
				try {
					return controllerType.getDeclaredConstructor().newInstance();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});

		try {
			scene.setCenter(loader.load());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onRechercheParJour(ActionEvent actionEvent) {
		Parent menu = null;
		try {
			menu = FXMLLoader.load(getClass().getResource("RechercheParJours.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		scene.setCenter(menu);
	}
}