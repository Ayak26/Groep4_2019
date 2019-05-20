package gui.home;

import backend.CameraThread;
import backend.RBtest;
import backend.Robot;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    protected static boolean on = false;
    CameraThread cameraThread;

    @Override
    public void start(Stage primaryStage) throws Exception{

        cameraThread = new CameraThread();
        cameraThread.start();
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        primaryStage.setTitle("HMI");
        primaryStage.setScene(new Scene(root, 800, 480));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
       CameraThread cameraThread = new CameraThread();
       RBtest robot = new RBtest();
        cameraThread.start();
        launch(args);
        cameraThread.stop();


    }

    @Override
    public void stop() throws Exception {
        super.stop();
        cameraThread.stop();
    }
}
