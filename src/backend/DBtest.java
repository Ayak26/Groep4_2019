package backend;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBtest {
    public DBtest() {

        ResultSet rs;

        Database.createStatement();
        rs = Database.executeQuery("SELECT ColorID, ColorName FROM colors");
        try {
            while (rs.next()) {
                int id = rs.getInt(1);
                String naam = rs.getString(2);
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Database.closeStatement();
    }
}
