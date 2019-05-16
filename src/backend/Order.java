package backend;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Order {
    private int orderNr;
    private boolean packed;
    private Article[] articles;

    public Order(int Orderid) {
        orderNr = Orderid;
        packed = false;
        Database.createStatement();
        ResultSet rs = Database.executeQuery("SELECT StockItemID FROM orderlines WHERE OrderID = " + orderNr);
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
}
