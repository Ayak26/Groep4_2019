import java.sql.*;

public class Artikel {
    private int _id;
    private String _naam;
    private String _kleur;
    private String _size;


    public Artikel(int id) {
        _id = id;
        Database.createStatement();
        ResultSet rs = Database.executeQuery("SELECT StockItemName, Size, ColorName FROM stockitems JOIN colors " +
                "ON stockitems.ColorID = colors.ColorID WHERE StockItemID = " + _id);
        if (rs != null) {
            try {
                while(rs.next()) {
                    _naam = rs.getString(1);
                    _size = rs.getString(2);
                    _kleur = rs.getString(3);
                }
            } catch (java.sql.SQLException e) {
                e.getStackTrace();
            }
        } else {
            System.out.println("Er is iets mis gegaan");
        }
        Database.closeStatement();
    }

    @Override
    public String toString() {
        return "Artikel{" +
                "_id=" + _id +
                ", _naam='" + _naam + '\'' +
                ", _kleur='" + _kleur + '\'' +
                ", _size='" + _size + '\'' +
                '}';
    }
}
