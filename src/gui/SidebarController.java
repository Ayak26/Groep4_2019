package gui;

import backend.PackingRobot;
import backend.SortingRobot;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class SidebarController implements Initializable {

    private boolean on = false;


    @FXML
    private ImageView start_stop, home, orders;

    @FXML
    private void goHome() {
        Main.getRoot().setCenter(Main.getHome());
    }

    @FXML
    private void goOrders() {
        Main.getRoot().setCenter(Main.getOrders());
    }

    @FXML
    private void start_stop() {
        SortingRobot sr = Main.getData().getSorteerrobot();
        if (on) {
            on = false;
            if(sr != null) {
                sr.sendCommand("OFF");
                if(!sr.getPackingRobots().isEmpty()) {
                    for (PackingRobot pr: sr.getPackingRobots()) {
                        pr.sendCommand("OFF");
                    }
                }
            }
            start_stop.setImage(new Image("file:assets/start.PNG"));
        } else {
            on = true;
            if(sr != null) {
                sr.sendCommand("ON");
                if(!sr.getPackingRobots().isEmpty()) {
                    for (PackingRobot pr: sr.getPackingRobots()) {
                        pr.sendCommand("ON");
                    }
                }
            }
            start_stop.setImage(new Image("file:assets/stop.PNG"));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (on) {
            start_stop.setImage(new Image("file:assets/stop.PNG"));
        } else {
            start_stop.setImage(new Image("file:assets/start.PNG"));
        }
        home.setImage(new Image("file:assets/home.png"));
        orders.setImage(new Image("file:assets/orders.png"));
    }

}
