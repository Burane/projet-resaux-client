import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import request.send.DisconnectRequest;
import server.Client;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main extends Application {

	@Override
	public void start(Stage stage) {
		AnchorPane anchorPane = null;
		try {
			anchorPane = FXMLLoader.load(getClass().getResource("Main.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		assert anchorPane != null;
		stage.setScene(new Scene(anchorPane,1024,576));
		stage.setTitle("ImageBank");
		stage.setResizable(false);
		stage.show();
		stage.setOnCloseRequest(evt -> System.exit(0));
	}

	public static void main(String[] args) {
		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, StandardCharsets.UTF_8));
		try{
			Thread t = new Thread(Client.getInstance());
			t.start();
			launch(args);
		} catch (Exception e){
			Client.getInstance().send(new DisconnectRequest());
			System.exit(0);
		}
	}

}