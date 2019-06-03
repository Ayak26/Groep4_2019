package backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataModel {
    private static boolean turned_on;
    public static PackingRobot inpakrobot1;
    public static PackingRobot inpakrobot2;
    private static SortingRobot sorteerrobot1;
    ArrayList<Integer> orders;

    public DataModel() {
        turned_on = false;
        inpakrobot1 = new PackingRobot("COM3");
        boolean space_check_inpakrobot1 = inpakrobot1.setOrder(100000);
        inpakrobot1.setBoxes(5);
        inpakrobot2 = new PackingRobot("COM5");
        boolean space_check_inpakrobot2 = inpakrobot2.setOrder(100001);
        inpakrobot2.setBoxes(5);
        sorteerrobot1 = new SortingRobot("COM4");

        if (!space_check_inpakrobot1 || !space_check_inpakrobot2){
            System.out.println("not enough space");
        }

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

    public static PackingRobot getInpakrobot1() {
        return inpakrobot1;
    }

    public static PackingRobot getInpakrobot2() {
        return inpakrobot2;
    }

    public static Robot getSorteerrobot1() {
        return sorteerrobot1;
    }

}
