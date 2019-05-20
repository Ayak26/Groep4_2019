package backend;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import java.io.IOException;

public class Robot {
    private SerialPort sp;

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
        if (command.equals("Hello World\r")) {
            System.out.println("test");
            sendCommand("ON");
        }
        if (command.equals("CONNECTED\r")) {
            System.out.println("verbinding gemaakt");
            sendCommand("Go fuck yourself");
        }
        if (command.equals("AMON\r")) {
            System.out.println("Robot is aan");
            sendCommand("S1");
        }
        if (command.equals("AMOFF\r")) {
            System.out.println("Robot is uit");
        }
        if (command.equals("S1:OPEN\r")) {
            System.out.println("Servo 1 is open");
        }
        if (command.equals("S1:CLOSED\r")) {
            System.out.println("Servo 1 is dicht");
            sendCommand("S2");
        }
        if (command.equals("S2:OPEN\r")) {
            System.out.println("Servo 2 is open");
        }
        if (command.equals("S2:CLOSED\r")) {
            System.out.println("Servo 2 is dicht");
            sendCommand("OFF");
        }
    }
}