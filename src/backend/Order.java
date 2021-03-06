package backend;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

public class Order {
    private int orderNr;
    private boolean packed;
    private Article[] article_list;
    private ArrayList<Integer> amount_list = new ArrayList<>();

    public Order(int Orderid) {
        orderNr = Orderid;
        packed = false;
        Database.openConnection();
        Database.createStatement();
        ResultSet rs = Database.executeQuery("SELECT ols.StockItemID, Quantity FROM orderlines AS ols \n" +
                "JOIN stockitems AS sis ON sis.StockItemID = ols.StockItemID\n" +
                "WHERE OrderID = " + orderNr + "\n" +
                "ORDER BY size DESC");
        ArrayList<Integer> id_list = new ArrayList<>();

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

    /**
     * print the order and all of its articles to the console
     */
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
