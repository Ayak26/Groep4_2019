import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Order {
    private int _ordernr;
    private boolean _ingepakt;
    private Artikel[] _artikelen;

    public Order(int Orderid) {
        _ordernr = Orderid;
        _ingepakt = false;
        Database.createStatement();
        ResultSet rs = Database.executeQuery("SELECT StockItemID FROM orderlines WHERE OrderID = " + _ordernr);
        ArrayList<Integer> _id = new ArrayList<>();
        try {
            while (rs.next()) {
                _id.add(rs.getInt(1));
            }
            _artikelen = new Artikel[_id.size()];
            int j = 0;
            for (Integer i: _id) {
                _artikelen[j] = new Artikel(i);
                j++;
            }
        } catch (java.sql.SQLException e) {
            e.getStackTrace();
        }
        Database.closeStatement();

    }

    @Override
    public String toString() {
        return "Order{" +
                "_ordernr=" + _ordernr +
                ", _ingepakt=" + _ingepakt +
                '}';
    }
    public void print() {
        System.out.println("Order nummer: "+_ordernr);
        for (Artikel a: _artikelen) {
            System.out.println(a);
        }
    }
}
