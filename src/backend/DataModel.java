package backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataModel {
    private static boolean turned_on;
    private static PackingRobot inpakrobot1;
    private static Robot inpakrobot2;
    private static Robot sorteerrobot1;
    ArrayList<Integer> orders;

    public DataModel() {
        turned_on = false;
        inpakrobot1 = new PackingRobot("COM4");
        inpakrobot1.setBoxes(5);
        inpakrobot1.setOrder(100000);


        Database.openConnection();
        Database.createStatement();
        orders = new ArrayList<Integer>();
        ResultSet rs = Database.executeQuery("SELECT OrderID FROM orders WHERE PickingCompletedWhen is NULL");
        try {
            while (rs.next()) {
                int x = rs.getInt(1);
                orders.add(x);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static boolean isTurned_on() {
        return turned_on;
    }

    public static Robot getInpakrobot1() {
        return inpakrobot1;
    }

    public static Robot getInpakrobot2() {
        return inpakrobot2;
    }

    public static Robot getSorteerrobot1() {
        return sorteerrobot1;
    }

}
