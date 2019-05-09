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
                                             "ON stockitems.ColorID = colors.ColorID WHERE StockItemID = "+ _id);
        try{
            _naam = rs.getString(1);
            _kleur = rs.getString(2);
            _size = rs.getString(3);
        }catch(java.sql.SQLException e){
            e.getStackTrace();
        }
        Database.closeStatement();
    }
}
