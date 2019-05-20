package gui.home;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Orderspecifiek  implements Initializable {
    @FXML
    private Button home, back, SelectProduct;

    @FXML
    private ImageView start_stop;


    @FXML
    private void goHome() throws Exception {
        Stage stage = (Stage)home.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        stage.setScene(new Scene(root));
    }

    @FXML
    private void goBack() throws Exception {
        Stage stage = (Stage)back.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Orders.fxml"));
        stage.setScene(new Scene(root));
    }

    @FXML
    private void goSelectProduct() throws Exception {
        Stage stage = (Stage)SelectProduct.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("SelectProduct.fxml"));
        stage.setScene(new Scene(root));
    }

    @FXML
    private void start_stop() {
        Image image;
        if(Main.on) {
            image = new Image("file:assets/start.png");
            start_stop.setImage(image);
            Main.on =false;
        } else {
            image = new Image("file:assets/stop.png");
            start_stop.setImage(image);
            Main.on = true;
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(Main.on) {
            start_stop.setImage(new Image("file:assets/stop.png"));
        } else {
            start_stop.setImage(new Image("file:assets/start.png"));
        }
    }


}
