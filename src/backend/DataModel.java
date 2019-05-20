package backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataModel {
    boolean turned_on;
    Robot inpakrobot1;
    Robot inpakrobot2;
    Robot sorteerrobot1;
    ArrayList<Integer> orders;

    private DataModel() {
        turned_on = false;
        inpakrobot1 = new Robot("com5");
        inpakrobot2 = new Robot("com1");
        sorteerrobot1 = new Robot("COM4");
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

    public Robot getInpakrobot1() {
        return inpakrobot1;
    }

    public Robot getInpakrobot2() {
        return inpakrobot2;
    }

    public Robot getSorteerrobot1() {
        return sorteerrobot1;
    }

}
