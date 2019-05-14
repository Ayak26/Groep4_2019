import java.sql.*;

public class Database {
    private static String url = "jdbc:mysql://localhost/wideworldimporters";
    private static String username = "root", password = "";

    private static Connection c;
    private static Statement s;

    public static boolean openConnection() {
        try {
            c = DriverManager.getConnection(url, username, password);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean closeConnection() {
        try {
            c.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void createStatement() {
        try {
            s = c.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet executeQuery(String Query) {
        try {
            ResultSet rs = s.executeQuery(Query);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Er is iets mis met de database connectie");
            return null;
        }
    }

    public static int executeUpdate(String Query) {
        try {
            int succes = s.executeUpdate(Query);
            return succes;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void closeStatement() {
        try {
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isClosed() {
        try {
            return c.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }
}
