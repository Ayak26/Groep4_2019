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
    //Test
    public Order() {
        orderNr = 0;
        packed = false;
        articles = new Article[3];
        articles[0] = new Article(1);
        articles[1] = new Article(2);
        articles[2] = new Article(3);
    }

    @Override
    public String toString() {
        return "backend.Order{" +
                "orderNr=" + orderNr +
                ", packed=" + packed +
                '}';
    }

    public Article[] getArticles() {
        return articles;
    }

    public void print() {
        System.out.println("backend.Order nummer: " + orderNr);
        for (Article a : articles) {
            System.out.println(a);
        }
    }
}
