package gui.home;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Voorraad implements Initializable {

    private boolean sorted;

    @FXML
    private Button home;

    @FXML
    private ImageView sort, start_stop;

    @FXML
    private void start_stop() {
        StandardGuiMethods.start_stop(start_stop);
    }

    @FXML
    private void sort() {
        Image image;
        if(sorted) {
            sorted = false;
        } else if (!sorted) {
            sorted = true;
        } else {
            sorted = true;
        }
    }

    @FXML
    private void goHome() throws Exception {
        Stage stage = (Stage)home.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        stage.setScene(new Scene(root));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(Main.on) {
            start_stop.setImage(new Image("file:assets/stop.png"));
        } else {
            start_stop.setImage(new Image("file:assets/start.png"));
        }
        sort.setImage(new Image("file:assets/sort_a-z.png"));
    }
}
