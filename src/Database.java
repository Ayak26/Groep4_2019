//import java.sql.*;
//
//public class Database {
//    private String url = "jdbc:mysql://localhost/wideworldimporters";
//    private String username = "root", password = "";
//    public static Connection c;
//
//    public void openConnection() {
//        c = DriverManager.getConnection(url, username, password);
//    }
//
//    public void closeConnection() {
//        c.close();
//    }
//}
import java.sql.*;

public class Database {
    private static String url = "jdbc:mysql://localhost/wideworldimporters";
    private static String username = "root", password = "";
    public static Connection c;

    public static void openConnection() {
        try {
            c = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
