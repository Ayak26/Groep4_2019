import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
//        DBtest dbtest = new DBtest();
        ResultSet rs;

        Database.openConnection();

        Database.createStatement();
        rs = Database.executeQuery("SELECT OrderID FROM orders ORDER BY OrderID");
        DbScreenTest screen = new DbScreenTest(rs);
        Database.closeStatement();
        Database.closeConnection();
    }

}
