import java.sql.ResultSet;
import java.sql.SQLException;

public class DBtest {
    public DBtest() {

        ResultSet rs;

        Database.openConnection();

        Database.createStatement();
        rs = Database.executeQuery("SELECT ColorID, ColorName FROM colors");
        try {
            while (rs.next()) {
                int id = rs.getInt(1);
                String naam = rs.getString(2);
                System.out.println(id + " " + naam);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Database.closeStatement();
        Database.createStatement();
        rs = Database.executeQuery("SELECT StockGroupID, StockGroupName FROM stockgroups");
        try {
            while (rs.next()) {
                int id = rs.getInt(1);
                String naam = rs.getString(2);
                System.out.println(id + " " + naam);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Database.closeConnection();
    }
}
