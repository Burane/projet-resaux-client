package Controller;

import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class Connexion {
    @FXML
    private Button buton_connexion;
    @FXML
    public void OnConnexion(ActionEvent actionEvent) {
        System.out.println("connexion");

        Parent anchorPane = null;
        try {
            anchorPane = (AnchorPane) FXMLLoader.load ( Main.class.getResource("Acceuil.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(anchorPane);
        Scene scene= new Scene(anchorPane);
        Stage stage= (Stage) buton_connexion.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
