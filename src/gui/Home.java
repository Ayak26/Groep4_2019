package gui;


import backend.PackingRobot;
import backend.SortingRobot;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;


public class Home implements Initializable {
    private boolean cameraView = false, runThread = true;
    private Thread updater;

    @FXML
    private Button Detecteren;

    @FXML
    private ImageView detectimage;

    @FXML
    private Rectangle SortServo1, SortServo2, Pack1Servo1, Pack1Servo2, Pack2Servo1, Pack2Servo2, Laser;

    /*
    Camera view openen
     */
    @FXML
    private void detecteren() {
        if (!cameraView) {
            Detecteren.setText("Sluit cam");
            cameraView = true;
            detectimage.setVisible(true);
        } else {
            Detecteren.setText("Open cam");
            cameraView = false;
            detectimage.setVisible(false);
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        detectimage.setVisible(false);
        updater = new Thread(this::run);
        updater.start();
    }

    /*
    Update the UI

    Called in updater thread
    Checks within first if the robots are connected to prevent errors
     */
    public void updateUI() {
        if (cameraView) {
            detectimage.setImage(new Image("file:assets/camera.jpg"));
        }

        SortingRobot sr;
        if (Main.getData().getSorteerrobot() != null) {
            sr = Main.getData().getSorteerrobot();

            if (sr.isLaser()) {
                Laser.setFill(Color.GREEN);
            } else {
                Laser.setFill(Color.RED);
            }

            if (sr.isServo1()) {
                SortServo1.setFill(Color.GREEN);
            } else {
                SortServo1.setFill(Color.RED);

            }

            if (sr.isServo2()) {
                SortServo2.setFill(Color.GREEN);
            } else {
                SortServo2.setFill(Color.RED);
            }
            PackingRobot pr1;
            PackingRobot pr2;
            if(!sr.getPackingRobots().isEmpty()) {
                if(sr.getPackingRobots().get(0) != null) {
                    pr1 = sr.getPackingRobots().get(0);
                    if (pr1.isServo1()) {
                        Pack1Servo1.setFill(Color.GREEN);
                    } else {
                        Pack1Servo1.setFill(Color.RED);
                    }

                    if (pr1.isServo2()) {
                        Pack1Servo2.setFill(Color.GREEN);
                    } else {
                        Pack1Servo2.setFill(Color.RED);
                    }
                }
                if (sr.getPackingRobots().get(1) != null) {
                    pr2 = sr.getPackingRobots().get(1);
                    if (pr2.isServo1()) {
                        Pack2Servo1.setFill(Color.GREEN);
                    } else {
                        Pack2Servo1.setFill(Color.RED);
                    }

                    if (pr2.isServo2()) {
                        Pack2Servo2.setFill(Color.GREEN);
                    } else {
                        Pack2Servo2.setFill(Color.RED);
                    }
                }
            }
        }
    }


    public void run() {
        while (runThread) {
            Platform.runLater(() -> updateUI());

            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopRunThread() {
        runThread = false;
    }
}
