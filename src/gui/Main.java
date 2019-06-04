package gui;

import backend.Camera;
import backend.DataModel;
import backend.Database;
import backend.PackingRobot;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {
    private Home homeController;

    private static BorderPane root;

    private static DataModel data;

    private static Pane home, orders, orderaanmaken;

    private Camera cameraThread;

    @Override
    public void start(Stage primaryStage) throws Exception {
        if (Database.openConnection()) {
            System.out.println("database connectie open");
        }
        cameraThread = new Camera();
        cameraThread.start();

        data = new DataModel();


        FXMLLoader sidebar = new FXMLLoader(getClass().getResource("Sidebar.fxml"));
        root = sidebar.load();
        SidebarController sc = sidebar.getController();

        FXMLLoader home = new FXMLLoader(getClass().getResource("Home.fxml"));
        this.home = home.load();
        root.setCenter(this.home);
        homeController = home.getController();
        FXMLLoader orders = new FXMLLoader(getClass().getResource("Orders.fxml"));
        this.orders = orders.load();
        FXMLLoader orderaanmaken = new FXMLLoader(getClass().getResource("Orderaanmaken.fxml"));
        this.orderaanmaken = orderaanmaken.load();


        primaryStage.setTitle("HMI");
        primaryStage.setScene(new Scene(root, 800, 480));
        primaryStage.show();

    }


    @Override
    public void stop() throws Exception {
        super.stop();
        Database.closeConnection();
        homeController.stopRunThread();
        cameraThread.stopRunThread();
        if (getData().getSorteerrobot() != null) {
            getData().getSorteerrobot().closeConnection();
            if (!getData().getSorteerrobot().getPackingRobots().isEmpty()) {
                for (PackingRobot pr : getData().getSorteerrobot().getPackingRobots()) {
                    pr.closeConnection();
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static BorderPane getRoot() {
        return root;
    }

    public static DataModel getData() {
        return data;
    }

    public static Pane getHome() {
        return home;
    }

    public static Pane getOrders() {
        return orders;
    }

    public static Pane getOrderaanmaken() {
        return orderaanmaken;
    }
}
