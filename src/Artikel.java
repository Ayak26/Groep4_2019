import java.sql.*;

public class Artikel {
    private int _id;
    private String _naam;
    private String _kleur;

    public Artikel(int id) {
        _id = id;
        Statement s = Database.c.prepareStatement("SELECT StockItemName, ColorName From stockitems s " +
                "JOIN colors c ON c.ColorID=s.ColorID WHERE StockItemID = 'id'");
    }
}
