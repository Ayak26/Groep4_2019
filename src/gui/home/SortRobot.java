package gui.home;

import backend.Robot;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;


public class SortRobot implements Initializable {

    @FXML
    protected Rectangle r1;

    @FXML
    private Button home, Detecteren, refresh;

    @FXML
    private boolean detectbool = false, refreshbool = false;

    @FXML
    private ImageView start_stop, detectimage;


    @FXML
    private Image camimage;

    @FXML
    private void goHome() throws Exception {
        Stage stage = (Stage)home.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        stage.setScene(new Scene(root));
    }


    @FXML
    private void start_stop() {
        StandardGuiMethods.start_stop(start_stop);
    }

    @FXML
    private void refresh(){
            if (!refreshbool) {
                    refresh.setVisible(true);
                    camimage = new Image("file:assets/camera.jpg");
                    detectimage.setImage(camimage);
                    detectimage.setVisible(true);
            } else {
                refresh.setVisible(false);
            }
    }

    @FXML
    private void detecteren() {
        if(!detectbool) {
                Detecteren.setText("Sluit cam");
                refreshbool = false;
            refresh();
            detectbool = true;

        } else{
                 Detecteren.setText("Detecteren");
                 refreshbool = true;
                 detectbool = false;
                 refresh();
                detectimage.setVisible(false);
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(Main.on) {
            start_stop.setImage(new Image("file:assets/stop.png"));
        } else {
            start_stop.setImage(new Image("file:assets/start.png"));
        }

       if(Main.servo1){
           System.out.println("YELLOW!");
           r1.setFill(Color.YELLOW);
       } else{
           System.out.println("BLUE");
           r1.setFill(Color.BLUE);
       }

    }

    public static void setSensor(boolean sensor) {
        Main.sensor = sensor;
    }

    public static void setServo1(boolean servo1) {
        Main.servo1 = servo1;
    }

    public static void setServo2(boolean servo2) {
        Main.servo2 = servo2;
    }

    public SortRobot(){

    }



}
