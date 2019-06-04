package gui.home;

import backend.DataModel;
import backend.Database;
//import backend.Order;
import backend.OrderInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.swing.UIManager.getString;

public class Orders implements Initializable {

    ObservableList<OrderInfo> items = FXCollections.observableArrayList();
    ObservableList<String> stringOrders = FXCollections.observableArrayList();
    @FXML
    private Button home, createorder, alphabtn, betabtn;
    @FXML
    private ImageView start_stop;
    @FXML
   private TableView<OrderInfo> table;
    @FXML
    private TableColumn<OrderInfo, String> ordernr;

    @FXML
    private TableColumn<OrderInfo, String> linerow;


    @FXML
    private TableView<OrderInfo> articletable;
    @FXML
    private TableColumn<OrderInfo, String> articlenr;

    @FXML
    private Label idtext;
 //   @FXML
//    private TableView<OrderInfo> table;
    //@FXML
   // private TableColumn<OrderInfo, String> ordernr;



    @FXML
    private void goHome() throws Exception {
        Stage stage = (Stage)home.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        stage.setScene(new Scene(root));
    }
    @FXML
    private void goAlpha() throws Exception{

        int parsing = Integer.parseInt(table.getSelectionModel().getSelectedItem().getId());
        DataModel.getInpakrobot1().setOrder(parsing);



   }
    @FXML
    private void goBeta() throws Exception{
        int parsing = Integer.parseInt(table.getSelectionModel().getSelectedItem().getId());
        DataModel.getInpakrobot2().setOrder(parsing);
    }

    @FXML
    private void goCreateorder() throws Exception {
        Stage stage = (Stage)createorder.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Orderaanmaken.fxml"));
        stage.setScene(new Scene(root));
    }

    @FXML
    public void clickItem(MouseEvent event)
    {
        if (event.getClickCount() == 2) //Checking double click
        {
            System.out.println(table.getSelectionModel().getSelectedItem().getId());
            idtext.setText("Order ID: " + table.getSelectionModel().getSelectedItem().getId());
            idtext.setVisible(true);
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


        try{
            ResultSet rs = Database.executeQuery("SELECT OrderID from orders WHERE PickingCompletedWhen is null");
           while(rs.next()){
                items.add(new OrderInfo(rs.getString("OrderID")));
                ordernr.setCellValueFactory(new PropertyValueFactory<OrderInfo, String>("id"));
            }
            table.setItems(items);

//            for (ModelTable m: ModelTable){
//                col_id.setCellValueFactory(new PropertyValueFactory<>(m.id));
//
//            }

                


        }catch (Exception ex){
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }


        //col_id.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
        //table.setItems(data);

    }
}
