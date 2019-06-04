package backend;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import gui.home.SortRobot;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public abstract class Robot {
    private SerialPort sp;
    protected boolean on;
    public boolean servo1 = false;
    public boolean servo2 = false;

    @FXML
    private Rectangle r1;

    public Robot(String port) {
        sp = SerialPort.getCommPort(port);
        sp.setComPortParameters(9600, 8, 1, 0);
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);
    }

    public boolean openConnection() {
        if (sp.openPort()) {
            System.out.println("Port is open :)");

            sp.addDataListener(new SerialPortDataListener() {
                String messages = "";

                @Override
                public int getListeningEvents() {
                    return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
                }

                @Override
                public void serialEvent(SerialPortEvent event) {
                    messages += new String(event.getReceivedData());
                    while (messages.contains("\n")) {
                        String[] message = messages.split("\\n", 2);
                        messages = (message.length > 1) ? message[1] : "";
                        readCommand(message[0]);
                    }
                }
            });
            return true;
        } else {
            System.out.println("Failed to open port :(");
            return false;
        }

    }

    public void closeConnection() {
        sendCommand("OFF");
        sp.removeDataListener();
        if (sp.closePort()) {
            System.out.println("Port is closed :)");
        } else {
            System.out.println("Failed to close port :(");
        }
    }

    public void sendCommand(String command) {
        command += "\n";
        try {
            byte[] convert = command.getBytes();
            sp.getOutputStream().write(convert);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            sp.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readCommand(String command) {
        if (command.equals("CONNECTED\r")) {
            System.out.println("verbinding gemaakt");
        }
        if (command.equals("AMON\r")) {
            System.out.println("Robot is aan");
        }
        if (command.equals("AMOFF\r")) {
            System.out.println("Robot is uit");
        }
        if (command.equals("S1:OPEN\r")) {
            System.out.println("Servo 1 is open");
            r1.setFill(Color.YELLOW);
            SortRobot.setRect(r1);
        }
        if (command.equals("S1:CLOSED\r")) {
            System.out.println("Servo 1 is dicht");
            servo1 = false;
        }
        if (command.equals("S2:OPEN\r")) {

            System.out.println("Servo 2 is open");
            servo2 = true;
        }
        if (command.equals("S2:CLOSED\r")) {
            System.out.println("Servo 2 is dicht");
            servo2 = false;
        }
    }
}