package gui.home;

import backend.Database;
import backend.OrderInfo;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Orderaanmaken  implements Initializable {
    @FXML
    private Button home, back, addarticle, complete;

    @FXML
    private ImageView start_stop;
    @FXML
    private TableView<OrderInfo> createtable;
    @FXML
    private TableColumn<OrderInfo, String> articlenr;
    @FXML
    private TableView<String> selectarticletable;
    @FXML
    private TableColumn<Orderaanmaken, String> selectarticlenr;

    @FXML
    private void goHome() throws Exception {
        Stage stage = (Stage)home.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        stage.setScene(new Scene(root));
    }

    ObservableList<String> newarticles = FXCollections.observableArrayList();

    ObservableList<OrderInfo> items = FXCollections.observableArrayList();

    @FXML
    private void addArticlebtn() throws Exception{
        newarticles.add(createtable.getSelectionModel().getSelectedItem().getId());
        for (int i = 0; i < newarticles.size(); i++) {
            System.out.println(newarticles.get(i));
         //   selectarticletable.setItems(newarticles);
         //   selectarticlenr.setCellValueFactory(new PropertyValueFactory<Orderaanmaken, String >("articlenr"));
        }



    }
    @FXML
    private void goBack() throws Exception {
        Stage stage = (Stage)back.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Orders.fxml"));
        stage.setScene(new Scene(root));
    }

    private void addArticle() throws Exception{
        System.out.println("SD");
    }

    @FXML
    private void start_stop() {
        StandardGuiMethods.start_stop(start_stop);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (Main.on) {
            start_stop.setImage(new Image("file:assets/stop.png"));
        } else {
            start_stop.setImage(new Image("file:assets/start.png"));
        }
        try {
            ResultSet rs = Database.executeQuery("SELECT StockItemID from stockitems");
            while (rs.next()) {
                items.add(new OrderInfo(rs.getString("StockItemId")));
                articlenr.setCellValueFactory(new PropertyValueFactory<OrderInfo, String>("id"));
            }
            createtable.setItems(items);

        } catch( Exception E) {
            System.out.println("byebye");
        }
    }}
