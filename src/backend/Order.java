package backend;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

public class Order {
    private int orderNr;
    private boolean packed;
    private Article[] article_list;

    public Order(int Orderid) {
        orderNr = Orderid;
        packed = false;
        Database.openConnection();
        Database.createStatement();
        ResultSet rs = Database.executeQuery("SELECT StockItemID, Quantity FROM orderlines WHERE OrderID = " + orderNr);
        ArrayList<Integer> id_list = new ArrayList<>();
        ArrayList<Integer> amount_list = new ArrayList<>();
        try {
            while (rs.next()) {
                id_list.add(rs.getInt(1));
                for (int i = 0; i < rs.getInt(2); i++) {
                    amount_list.add(rs.getInt(1));
                }
            }
            ResultSetMetaData rsmd = rs.getMetaData();
            article_list = new Article[amount_list.size()];

            int j = 0;

            for (Integer key : amount_list) {
                article_list[j] = new Article(key);
                j++;
            }

        } catch (java.sql.SQLException e) {
            e.getStackTrace();
        }
        Database.closeStatement();
        Database.openConnection();

    }
    //Test
    public Order() {
        orderNr = 0;
        packed = false;
        article_list = new Article[3];
        article_list[0] = new Article(1);
        article_list[1] = new Article(2);
        article_list[2] = new Article(3);
    }

    @Override
    public String toString() {
        return "backend.Order{" +
                "orderNr=" + orderNr +
                ", packed=" + packed +
                '}';
    }

    public Article[] getArticles() {
        return article_list;
    }

    public void print() {
        System.out.println("backend.Order nummer: " + orderNr);
        for (Article article : article_list) {
            System.out.println(article);
        }
    }

    public Article[] getArticle_list() {
        return article_list;
    }

}
