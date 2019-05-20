package backend;

import java.sql.*;

public class Article {
    private int id;
    private String name;
    private String colour;
    private String size;


    public Article(int id) {
        this.id = id;
        Database.createStatement();
        ResultSet rs = Database.executeQuery("SELECT StockItemName, Size, ColorName FROM stockitems JOIN colors " +
                "ON stockitems.ColorID = colors.ColorID WHERE StockItemID = " + id);
        if (rs != null) {
            try {
                while (rs.next()) {
                    name = rs.getString(1);
                    size = rs.getString(2);
                    colour = rs.getString(3);
                }
            } catch (java.sql.SQLException e) {
                e.getStackTrace();
            }
        } else {
            System.out.println("Er is iets mis gegaan");
        }
        Database.closeStatement();
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "backend.Article{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", colour='" + colour + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
