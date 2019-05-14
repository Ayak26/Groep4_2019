import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
//        DBtest dbtest = new DBtest();



        ResultSet rs;

        Database.openConnection();

        Database.createStatement();
        rs = Database.executeQuery("SELECT OrderID, OrderDate FROM orders");
        DbScreenTest screen = new DbScreenTest(rs);
//        ArrayList table = screen.convertRsToTableData(rs);
//        System.out.println(table);
//        try {
//            while (rs.next()) {
//                int id = rs.getInt(1);
//                String naam = rs.getString(2);
//                System.out.println(rs.getString(2));
////                System.out.println(id + " " + naam);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        Database.closeStatement();
        Database.closeConnection();
    }

}
