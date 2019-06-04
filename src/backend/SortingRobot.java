package backend;

import java.util.ArrayList;

public class SortingRobot extends Robot {
    private ArrayList<PackingRobot> packingRobots;
    private boolean laser = false, servo1 = false, servo2 = false;

    public SortingRobot(String port) {
        super(port);
    }

    @Override
    protected void readCommand(String command) {
        if (command.equals("AMON\r")) {
            System.out.println("Robot is aan");
        } else if (command.equals("AMOFF\r")) {
            System.out.println("Robot is uit");
        } else if (command.equals("S1:OPEN\r")) {
            servo1 = true;
            System.out.println("Servo 1 is open");
        } else if (command.equals("S1:CLOSED\r")) {
            servo1 = false;
            System.out.println("Servo 1 is dicht");
        } else if (command.equals("S2:OPEN\r")) {
            servo2 = true;
            System.out.println("Servo 2 is open");
        } else if (command.equals("S2:CLOSED\r")) {
            servo2 = false;
            System.out.println("Servo 2 is dicht");
        } else if (command.equals("DETECTED\r")) {
            laser = true;
            System.out.println("Item gezien op band");
        }
    }

    public ArrayList<PackingRobot> getPackingRobots() {
        return packingRobots;
    }

    public void setPackingRobots(ArrayList<PackingRobot> packingRobots) {
        this.packingRobots = packingRobots;
    }

    public boolean isLaser() {
        return laser;
    }

    public void setLaser(boolean laser) {
        this.laser = laser;
    }

    public boolean isServo1() {
        return servo1;
    }

    public void setServo1(boolean servo1) {
        this.servo1 = servo1;
    }

    public boolean isServo2() {
        return servo2;
    }

    public void setServo2(boolean servo2) {
        this.servo2 = servo2;
    }
}
