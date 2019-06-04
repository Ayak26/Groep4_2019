package backend;

import java.sql.*;

public class Database {
    private static String url = "jdbc:mysql://localhost/wideworldimporters?serverTimezone=UTC";
    private static String username = "root", password = "";

    private static Connection c;
    private static Statement s;

    /**
     * @return a boolean containing if a database connection could be established or not
     */
    public static boolean openConnection() {
        try {
            c = DriverManager.getConnection(url, username, password);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @return a boolean containing if the existing database connection could be closed
     */
    public static boolean closeConnection() {
        try {
            c.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * create a statement yet to be filled
     */
    public static void createStatement() {
        try {
            s = c.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * try to execute the query and return a resultset
     *
     * @param Query a string containing a SQL query
     * @return a resultset if the query was executed properly otherwise send back a null
     */
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

    /**
     * try to execute an update query and return the amount of changed rows
     *
     * @param Query a string containing a SQL query
     * @return a integer containing how many rows where updated
     */
    public static int executeUpdate(String Query) {
        try {
            int succes = s.executeUpdate(Query);
            return succes;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * close the statement
     */
    public static void closeStatement() {
        try {
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return a boolean containing if the connection is actually closed
     */
    public static boolean isClosed() {
        try {
            return c.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }
}
