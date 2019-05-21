package backend;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

public class Order {
    private int orderNr;
    private boolean packed;
    private Article[] articles;

    public Order(int Orderid) {
        orderNr = Orderid;
        packed = false;
        Database.openConnection();
        Database.createStatement();
        ResultSet rs = Database.executeQuery("SELECT StockItemID, Quantity FROM orderlines WHERE OrderID = " + orderNr);
        ArrayList<Integer> _id = new ArrayList<>();
        try {
            while (rs.next()) {
                _id.add(rs.getInt(1));
            }
            articles = new Article[_id.size()];
            int j = 0;
            for (Integer i : _id) {
                articles[j] = new Article(i);
                j++;
            }
        } catch (java.sql.SQLException e) {
            e.getStackTrace();
        }
        Database.closeStatement();
        Database.openConnection();

    }

    @Override
    public String toString() {
        return "backend.Order{" +
                "orderNr=" + orderNr +
                ", packed=" + packed +
                '}';
    }

    public void print() {
        System.out.println("backend.Order nummer: " + orderNr);
        for (Article a : articles) {
            System.out.println(a);
        }
    }

    public ArrayList<ArrayList> getOrderInfo() {
        ArrayList<ArrayList> items = new ArrayList<>();
        Database.openConnection();
        Database.createStatement();
        ResultSet rs = Database.executeQuery("SELECT OrderLineID, Quantity, size FROM orderlines " +
                "JOIN stockitems ON stockitems.StockItemID = orderlines.StockItemID" +
                " WHERE OrderID = " + orderNr);


        try {
            while (rs.next()) {

                ArrayList<Integer> item = new ArrayList<>();
                item.add(rs.getInt(1));
                item.add(rs.getInt(2));
                item.add(rs.getInt(3));
                items.add(item);
            }
        } catch (java.sql.SQLException e) {
            e.getStackTrace();
        }
        Database.closeStatement();
        Database.closeConnection();
        return items;
    }
}
