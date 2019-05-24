package backend;

import java.sql.*;

public class Article {
    private int id;
    private String name;
    private String colour;
    private int size;


    public Article(int id) {
        this.id = id;

        Database.createStatement();
        ResultSet rs = Database.executeQuery("SELECT StockItemName, Size, ColorName FROM stockitems JOIN colors " +
                "ON stockitems.ColorID = colors.ColorID WHERE StockItemID = " + id);

        if (rs != null) {
            try {
                while (rs.next()) {
                    name = rs.getString(1);
                    size = rs.getInt(2);
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

    /**
     * @return an int contaioning the id of this article
     */
    public int getId() {
        return id;
    }

    /**
     * @return a string composed of the id, name, colour and size of the article
     */
    @Override
    public String toString() {
        return "backend.Article{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", colour='" + colour + '\'' +
                ", size='" + size + '\'' +
                '}';
    }

    /**
     * @return a string containing the name of the current article
     */
    public String getName() {
        return name;
    }

    /**
     * @return a string containing the colour of the article
     */
    public String getColour() {
        return colour;
    }

    /**
     * @return an int containing the size of the article
     */
    public int getSize() {
        return size;
    }
}
