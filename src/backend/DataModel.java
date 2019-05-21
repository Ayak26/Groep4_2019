package backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataModel {
    boolean turned_on;
    private static Robot inpakrobot1;
    private static Robot inpakrobot2;
    private static Robot sorteerrobot1;
    ArrayList<Integer> orders;

    public DataModel() {
        turned_on = false;
        PackingRobot inpakrobot1 = new PackingRobot("COM4");
        inpakrobot1.setOrder(100000);
        inpakrobot1.setBoxes(8);


        inpakrobot2 = new PackingRobot("com1");
        sorteerrobot1 = new PackingRobot("COM5");
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

    public boolean isTurned_on() {
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
