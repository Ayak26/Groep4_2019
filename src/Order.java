import java.sql.*;

public class Order {
    private int _ordernr;
    private boolean _ingepakt;
    private Artikel[] _artikelen;

    public Order(int Orderid) {
        _ordernr = Orderid;
        _ingepakt = false;
        Database.createStatement();
        ResultSet rs = Database.executeQuery("SELECT StockItemID FROM orderlines WHERE OrderID = " + _ordernr);
        int _id[];
        try {
            _id = new int[rs.getFetchSize()];
            int i = 0;
            while (rs.next()) {
                _id[i] = rs.getInt(1);
                i++;
            }
            for (int j = 0; j < _id.length; j++) {
                _artikelen = new Artikel[_id[j]];
            }
        } catch (java.sql.SQLException e) {
            e.getStackTrace();
        }
        Database.closeStatement();

    }
}
