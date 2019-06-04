package gui;

import backend.*;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Orders implements Initializable {


    @FXML
    private TableView<OrderInfo> table;
    @FXML
    private TableColumn<OrderInfo, String> ordernr;


    @FXML
    private TableView<OrderInfo> articletable;
    @FXML
    private TableColumn<OrderInfo, String> articlenr;

    @FXML
    private Label idtext, betaline, alphaline, articletext;
    ;

    private static String Number, Number2;

    @FXML
    private void goAlpha() {
        int parsing = Integer.parseInt(table.getSelectionModel().getSelectedItem().getId());
        if (!Main.getData().getSorteerrobot().getPackingRobots().isEmpty()) {
            PackingRobot pr = Main.getData().getSorteerrobot().getPackingRobots().get(0);
            if (pr != null) {
                pr.setOrder(parsing);

                if (pr.getOrder() != null) {
                    Number = table.getSelectionModel().getSelectedItem().getId();
                    alphaline.setText(Number);
                }
            } else {
                alphaline.setText("");
            }
        } else {
            System.out.println("No robot to set to");
        }
    }

    @FXML
    private void goBeta() {
        int parsing = Integer.parseInt(table.getSelectionModel().getSelectedItem().getId());
        if (!Main.getData().getSorteerrobot().getPackingRobots().isEmpty()) {
            PackingRobot pr = Main.getData().getSorteerrobot().getPackingRobots().get(1);
            if (pr != null) {
                pr.setOrder(parsing);

                if (pr.getOrder() != null) {
                    Number2 = table.getSelectionModel().getSelectedItem().getId();
                    betaline.setText(Number2);
                }
            } else {
                betaline.setText("");
            }
        } else {
            System.out.println("No robot to set to");
        }
    }

    @FXML
    private void goCreateorder() {
        Main.getRoot().setCenter(Main.getOrderaanmaken());
    }

    @FXML
    private void goEmptyRow() {
        Box[] d = Main.getData().getSorteerrobot().getPackingRobots().get(0).getBoxes();
        Box[] b = Main.getData().getSorteerrobot().getPackingRobots().get(1).getBoxes();
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
    private void goDelete() throws Exception {
        try {
            Database.createStatement();
            Database.executeUpdate("DELETE FROM orderlines WHERE OrderID = " + table.getSelectionModel().getSelectedItem().getId());
            Database.closeStatement();
            Database.createStatement();
            Database.executeUpdate("DELETE FROM orders WHERE OrderID = " + table.getSelectionModel().getSelectedItem().getId());
            Database.closeStatement();
        } catch (Exception e){
            System.out.println("NOPE: " + e);
        }
        refresh();
    }
    @FXML
    public void clickItem(MouseEvent event) {
        if (event.getClickCount() == 2) //Checking double click
        {
            System.out.println(table.getSelectionModel().getSelectedItem().getId());
            idtext.setText("Order ID: " + table.getSelectionModel().getSelectedItem().getId());
            idtext.setVisible(true);
            ObservableList<OrderInfo> article = FXCollections.observableArrayList();
            Database.createStatement();
            ResultSet rs = Database.executeQuery("SELECT StockItemID FROM orderlines WHERE OrderID =" + table.getSelectionModel().getSelectedItem().getId());
            try {
                ArrayList<String> articlesarray = new ArrayList<String>();
                while (rs.next()) {
                    articlesarray.add(rs.getString("StockItemID"));
                }
                String textfield = "";
                for (int i = 0; i < articlesarray.size(); i++) {
                    System.out.println(articlesarray.get(i));
                    textfield += articlesarray.get(i) + "\n";
                }
                articletext.setText(textfield);
                Database.closeStatement();
            } catch (Exception f) {
                System.out.println("ERROR");
            }

        }
    }

    @FXML
    private void refresh() {
        fillTable();
    }

    private void fillTable() {
        try {
            ObservableList<OrderInfo> items = FXCollections.observableArrayList();
            Database.createStatement();
            ResultSet rs = Database.executeQuery("SELECT OrderID from orders WHERE PickingCompletedWhen is null");
            while (rs.next()) {
                items.add(new OrderInfo(rs.getString("OrderID")));
                ordernr.setCellValueFactory(new PropertyValueFactory<>("id"));
            }
            Database.closeStatement();
            table.setItems(items);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        alphaline.setText(Number);
        betaline.setText(Number2);

        fillTable();
    }

}
