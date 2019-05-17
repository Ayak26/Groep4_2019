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
import java.sql.SQLOutput;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class SortRobot implements Initializable{

    @FXML
    private Button home;

    @FXML
    private Button Detecteren;

    @FXML
    private boolean detectbool = false;

    @FXML
    private ImageView start_stop;

    @FXML
    private ImageView detectimage;


    @FXML
    private void goHome() throws Exception {
        Stage stage = (Stage)home.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
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

    @FXML
    private void detecteren(){

        if(!detectbool) {
                try {
                    Image camimage = new Image("file:assets/qrcode.jpg");
                    detectimage.setImage(camimage);
                    detectimage.setVisible(true);
                } catch(Exception f){
                    System.out.println(f);
                }
                detectbool = true;
        } else{
            detectimage.setVisible(false);
            detectbool = false;

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
