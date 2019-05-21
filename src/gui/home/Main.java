package gui.home;

import backend.DataModel;
import backend.Order;
import backend.PackingRobot;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    protected static boolean on = false;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        primaryStage.setTitle("HMI");
        primaryStage.setScene(new Scene(root, 800, 480));
        primaryStage.setResizable(false);
        primaryStage.show();

        DataModel datamodel = new DataModel();
        DataModel.getInpakrobot1();
        Order guan = new Order(100000);
        guan.print();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
