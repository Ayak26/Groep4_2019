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

public class Home implements Initializable {



    @FXML
    private Button products, packing_robots, orders, sorting_robot;

    @FXML
    private ImageView start_stop;

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


    @FXML
    private void goStock() throws Exception {
        Stage stage = (Stage)products.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Voorraad.fxml"));
        stage.setScene(new Scene(root));
    }

    @FXML
    private void goSelectPackingRobots() throws Exception {
        Stage stage = (Stage)packing_robots.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("SelectPackingRobot.fxml"));
        stage.setScene(new Scene(root));
    }

    @FXML
    private void goSortingRobot() throws Exception {
        Stage stage = (Stage)packing_robots.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("SortRobot.fxml"));
        stage.setScene(new Scene(root));
    }

    @FXML
    private void goOrders() throws Exception {
        Stage stage = (Stage)packing_robots.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Orders.fxml"));
        stage.setScene(new Scene(root));
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
