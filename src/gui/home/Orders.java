package gui.home;

import backend.Box;
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
    private Button home, createorder;
    @FXML
    private ImageView start_stop;
    @FXML
   private TableView<OrderInfo> table;
    @FXML
    private TableColumn<OrderInfo, String> ordernr;


    @FXML
    private Label articletext;


    @FXML
    private Label idtext;

    @FXML
    private Label betaline;

    @FXML
    private Label alphaline;

 private static String Number;
 private static String Number2;


    @FXML
    private void goHome() throws Exception {
        Stage stage = (Stage)home.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        stage.setScene(new Scene(root));
    }
    @FXML
    private void goEmptyRow() throws Exception {
        Box[] d = DataModel.getInpakrobot1().getBoxes();
        Box[] b = DataModel.getInpakrobot2().getBoxes();
        d[0].content.clear();
        d[1].content.clear();
        d[2].content.clear();
        b[0].content.clear();
        b[1].content.clear();
        b[2].content.clear();
        alphaline.setText("");
        betaline.setText("");
    }
    @FXML
    private void goAlpha() throws Exception{
        int parsing = Integer.parseInt(table.getSelectionModel().getSelectedItem().getId());
        DataModel.getInpakrobot1().setOrder(parsing);

        if(DataModel.getInpakrobot1().getOrder() != null){
            Number = table.getSelectionModel().getSelectedItem().getId();
            alphaline.setText(Number);
        } else{
            alphaline.setText("");
        }



   }
    @FXML
    private void goBeta() throws Exception{
        int parsing = Integer.parseInt(table.getSelectionModel().getSelectedItem().getId());
        DataModel.getInpakrobot2().setOrder(parsing);
        if(DataModel.getInpakrobot2().getOrder() != null) {
            Number2 = table.getSelectionModel().getSelectedItem().getId();
            betaline.setText(Number2);
        } else{
            betaline.setText("");

        }
    }

    @FXML
    private void goDelete() throws Exception {
        try {
            Database.createStatement();
            Database.executeUpdate("DELETE FROM orders WHERE OrderID = " + table.getSelectionModel().getSelectedItem().getId());
            Database.closeStatement();
        } catch (Exception e){
            System.out.println("NOPE: " + e);
        }
        Stage stage = (Stage)home.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Orders.fxml"));
        stage.setScene(new Scene(root));
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
               ArrayList<String> articlesarray = new ArrayList<String>();
              while(rs.next()){
                  articlesarray.add(rs.getString("StockItemID"));
                }
              String textfield = "";
              for(int i = 0; i < articlesarray.size(); i++){
                  System.out.println(articlesarray.get(i));
                  textfield += articlesarray.get(i) + "\n";
              }
              articletext.setText(textfield);
              Database.closeStatement();
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

        alphaline.setText(Number);
        betaline.setText(Number2);

        if(Main.on) {
            start_stop.setImage(new Image("file:assets/stop.png"));
        } else {
            start_stop.setImage(new Image("file:assets/start.png"));
        }


        try{
            Database.createStatement();
            ResultSet rs = Database.executeQuery("SELECT OrderID from orders WHERE PickingCompletedWhen is null");
           while(rs.next()){
                items.add(new OrderInfo(rs.getString("OrderID")));
                ordernr.setCellValueFactory(new PropertyValueFactory<OrderInfo, String>("id"));
            }
            table.setItems(items);
           Database.closeStatement();



                


        }catch (Exception ex){
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }



    }
}
