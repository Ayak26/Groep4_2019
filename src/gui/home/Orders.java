package gui.home;

import backend.Database;
//import backend.Order;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Orders implements Initializable {

    @FXML
    private Button home, createorder;

    @FXML
    private ImageView start_stop;
    @FXML
    private TableView<ModelTable> table;
    @FXML
    private TableColumn<ModelTable, String> col_id;



    @FXML
    private void goHome() throws Exception {
        Stage stage = (Stage)home.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        stage.setScene(new Scene(root));
    }

    @FXML
    private void goCreateorder() throws Exception {
        Stage stage = (Stage)createorder.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Orderaanmaken.fxml"));
        stage.setScene(new Scene(root));
    }

    @FXML
    private void start_stop() {
        StandardGuiMethods.start_stop(start_stop);
    }

    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        if(Main.on) {
            start_stop.setImage(new Image("file:assets/stop.png"));
        } else {
            start_stop.setImage(new Image("file:assets/start.png"));
        }


        try{
            ResultSet rs = Database.executeQuery("SELECT OrderID from orders");

            ArrayList<ModelTable> ModelTable = new ArrayList<>();
            while(rs.next()){
                ModelTable mt = new ModelTable();
                mt.setId(rs.getString("OrderID"));
                //oblist.add(new ModelTable(rs.getString("OrderID")));
            }
            for (ModelTable m: ModelTable){
                col_id.setCellValueFactory(new PropertyValueFactory<>(m.id));

            }

                


        }catch (SQLException ex){
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }


        //col_id.setCellValueFactory(new PropertyValueFactory<>("OrderID"));

        //table.setItems(oblist);

    }
}
