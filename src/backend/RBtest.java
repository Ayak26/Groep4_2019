package backend;

import java.sql.SQLOutput;

public class RBtest {
    public RBtest() {

        Robot inpakrobot1 = new Robot("COM3");

        if (inpakrobot1.openConnection()) {

            System.out.println("Geconnect :P");
            inpakrobot1.sendCommand("CONNECTED\r");
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            inpakrobot1.closeConnection();
        } else {
            System.out.println("pech gehad");
        }
    }
}
