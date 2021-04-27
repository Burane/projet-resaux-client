package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AcceuilController {

    @FXML
    public AnchorPane rootPane;

    @FXML
    public Button ajouter;

    @FXML
    public Button buton;

    @FXML
    public GridPane grille;

    @FXML
    public TextArea text;

    @FXML
    public ImageView image;


    public  void onAjouter (ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Connexion.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void onTest(ActionEvent actionEvent) {
//        String data = text.getText();
//        String base64Image = data.split(",")[1];
//        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
//        InputStream inputstream = new ByteArrayInputStream(imageBytes);

        ArrayList<File> files = new ArrayList<>();

        //files.add(new File("C:\\Users\\Propriétaire\\Pictures\\Hinata.png"));


        grille.setHgap(100); //horizontal gap in pixels => that's what you are asking for
        grille.setVgap(100); //vertical gap in pixels
        grille.setPadding(new Insets(10, 10, 10, 10));

        buton.setVisible(false);

        try {


           // files.add(new File(String.valueOf(getClass().getClassLoader().getResourceAsStream("Hinata.png"))));
            List<Node> children = grille.getChildren();
            for(int i=0; i< children.size(); ++i)
            {
                InputStream in = getClass().getClassLoader().getResourceAsStream("Hinata.png");
                //InputStream inputstream = new ByteArrayInputStream(new FileInputStream(files.get(i%files.size())).readAllBytes());
                if (children.get(i)instanceof ImageView)
                {
                    ImageView image = (ImageView) children.get(i);
                    image.setImage(new Image(in));
                    GridPane.setMargin(image, new Insets(400, 10, 400, 10));
                    image.fitWidthProperty().bind(image.getParent().getScene().widthProperty());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Parent root;
//        try {
//            root = FXMLLoader.load(getClass().getClassLoader().getResource("Main.fxml"));
//            Stage stage = new Stage();
//            stage.setTitle("My New Stage Title");
//            stage.setScene(new Scene(root, 450, 450));
//            stage.show();
//            // Hide this current window (if this is what you want)
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
