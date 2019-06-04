package backend;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import javafx.application.Platform;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataModel implements Runnable{
    private SortingRobot sorteerrobot;


    public DataModel() {
        searchRobots();
        sorteerrobot.openConnection();
        if(!sorteerrobot.getPackingRobots().isEmpty()){
            for (PackingRobot pr: sorteerrobot.getPackingRobots()) {
                pr.openConnection();
            }
        }
    }

    /*
    Dynamically searches for robots at start and loads them in
     */
    private void searchRobots() {
        ArrayList<PackingRobot> packingrobots = new ArrayList<>();
        String port;
        for (SerialPort sp: SerialPort.getCommPorts()) {
            Robot robot = new Robot(sp.getSystemPortName());
            robot.openConnection();
            try {
                Thread.sleep(1700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            robot.sendCommand("CONNECT");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (robot.getType() == null) {
                robot.closeConnection();
                System.out.println("Not a robot");
            } else if(robot.getType().equals("Sorting")) {
                port = robot.getSp().getSystemPortName();
                robot.closeConnection();
                sorteerrobot = new SortingRobot(port);
            } else if (robot.getType().equals("Packing")) {
                port = robot.getSp().getSystemPortName();
                robot.closeConnection();
                packingrobots.add(new PackingRobot(port));
            }
        }
        if(sorteerrobot!=null) {
            sorteerrobot.setPackingRobots(packingrobots);
        } else {
            System.out.println("No main robot");
        }
    }

    public SortingRobot getSorteerrobot() {
        return sorteerrobot;
    }

    @Override
    public void run() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
