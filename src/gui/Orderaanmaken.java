package gui;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Orderaanmaken implements Initializable {

    @FXML
    private Button addarticle, complete;
    @FXML
    private TableView<OrderInfo> createtable;
    @FXML
    private TableColumn<OrderInfo, String> articlenr;
    @FXML
    private TableView<String> selectarticletable;
    @FXML
    private TableColumn<Orderaanmaken, String> selectarticlenr;

    @FXML
    private Label articletext;

    private ArrayList<String> newarticles = new ArrayList<>();

    ObservableList<OrderInfo> items = FXCollections.observableArrayList();

    @FXML
    private void addArticlebtn() throws Exception {
        newarticles.add(createtable.getSelectionModel().getSelectedItem().getId());
        String textfield = "\n";

        for (int i = 0; i < newarticles.size(); i++) {
            System.out.println(newarticles.get(i));
            textfield += newarticles.get(i) + "\n";
        }
        articletext.setVisible(true);
        articletext.setText(textfield);
        complete.setOnAction(actionEvent -> insertToDatabase(newarticles));
    }

    private void insertToDatabase(ArrayList textfield) {
        try {
            Database.createStatement();
            int maxID = 0;
            ResultSet rs2 = Database.executeQuery("SELECT OrderID from orders Order By OrderID DESC");
            if (rs2.next()) {
                maxID += rs2.getInt("OrderID");
            }

            int maxIDplus = maxID + 1;
            Database.executeUpdate("INSERT INTO orders (OrderID, CustomerID, SalespersonPersonID, PickedByPersonID, ContactPersonID, BackorderOrderID, OrderDate, ExpectedDeliveryDate, CustomerPurchaseOrderNumber, IsUndersupplyBackordered, Comments, DeliveryInstructions, InternalComments, PickingCompletedWhen, LastEditedBy, LastEditedWhen) VALUES ('" + maxIDplus + "', '804',  '1033', '1033', '1033', NULL, '2019-05-26', '2019-05-19',  NULL, '1', NULL, NULL, NULL, NULL, '1033', '2019-05-19 00:00:00')");


            ResultSet rs3 = Database.executeQuery("SELECT OrderLineID from orderlines Order By OrderID DESC");
            int MaxIDlines = 0;
            if (rs3.next()) {
                MaxIDlines += rs3.getInt("OrderLineID");
            }

            int MaxIDcount = MaxIDlines + 1;
            System.out.println("LINES: " + MaxIDcount);
            for (int i = 0; i < textfield.size(); i++) {
                Database.executeUpdate("INSERT INTO orderlines (OrderLineID, OrderID, StockItemID, Description, PackageTypeID, Quantity, UnitPrice, TaxRate, PickedQuantity, PickingCompletedWhen, LastEditedBy, LastEditedWhen) VALUES ('" + MaxIDcount + "', '" + maxIDplus + "', '" + textfield.get(i) + "', 'n/a', '2', '1', NULL, '15.000', '2', NULL, '1033', '2019-05-19 00:00:00')");
                MaxIDcount++;
                System.out.println(MaxIDcount);
            }

            Database.closeStatement();
        } catch (Exception e) {
            System.out.println("ERROR " + e);
        }
        Main.getRoot().setCenter(Main.getOrders());

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Database.createStatement();
            ResultSet rs = Database.executeQuery("SELECT StockItemID from stockitems");
            while (rs.next()) {
                items.add(new OrderInfo(rs.getString("StockItemId")));
                articlenr.setCellValueFactory(new PropertyValueFactory<>("id"));
            }
            Database.closeStatement();
            createtable.setItems(items);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
