import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

	@Override
	public void start(Stage stage) {
		AnchorPane anchorPane = null;
		try {
			anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource("Main.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		stage.setScene(new Scene(anchorPane,1024,576));
		stage.setTitle("ImageBank");
		stage.setResizable(false);
		stage.show();
		stage.setOnCloseRequest(evt -> {
			System.exit(0);
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

}