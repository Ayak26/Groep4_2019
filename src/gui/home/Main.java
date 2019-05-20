package gui.home;


import backend.*;
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
        Database.openConnection();
        cameraThread = new CameraThread();
        cameraThread.start();
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        primaryStage.setTitle("HMI");
        primaryStage.setScene(new Scene(root, 800, 480));
        primaryStage.setResizable(false);
        primaryStage.show();
        DBtest test = new DBtest();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        cameraThread.stop();
        Database.closeConnection();
    }
}
