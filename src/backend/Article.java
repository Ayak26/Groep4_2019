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
//                    System.out.println(rs.getString(1));
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
     * return article id
     *
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * returns a string composed of the id, name, colour and size of the article
     *
     * @return String
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
     * return the name of the article
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * return the colour of the article
     *
     * @return String
     */
    public String getColour() {
        return colour;
    }

    /**
     * return the size of the article
     *
     * @return int
     */
    public int getSize() {
        return size;
    }
}
