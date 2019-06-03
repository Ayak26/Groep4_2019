package gui.home;

import backend.Database;
import backend.OrderInfo;
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
    private Button home, back, SelectProduct;

    @FXML
    private ImageView start_stop;
    @FXML
    private TableView<OrderInfo> createtable;
    @FXML
    private TableColumn<OrderInfo, String> articlenr;

    @FXML
    private void goHome() throws Exception {
        Stage stage = (Stage)home.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        stage.setScene(new Scene(root));
    }

    @FXML
    private void goBack() throws Exception {
        Stage stage = (Stage)back.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Orders.fxml"));
        stage.setScene(new Scene(root));
    }

    @FXML
    private void goSelectProduct() throws Exception {
        Stage stage = (Stage)SelectProduct.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("SelectProduct.fxml"));
        stage.setScene(new Scene(root));
    }

    @FXML
    private void start_stop() {
        StandardGuiMethods.start_stop(start_stop);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(Main.on) {
            start_stop.setImage(new Image("file:assets/stop.png"));
        } else {
            start_stop.setImage(new Image("file:assets/start.png"));
        }
        ObservableList<OrderInfo> article = FXCollections.observableArrayList();
        Database.createStatement();
        ResultSet rs = Database.executeQuery("SELECT StockItemID FROM orderlines WHERE OrderID =" + table.getSelectionModel().getSelectedItem().getId()) ;
        try {
            while(rs.next()){
                article.add(new OrderInfo(rs.getString("StockItemID")));
                articlenr.setCellValueFactory(new PropertyValueFactory<OrderInfo, String>("id"));
            }
            articletable.setItems(article);
        }catch(Exception f){
            System.out.println("ERROR");

    }
}
