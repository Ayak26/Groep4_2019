package backend;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import java.io.IOException;

public class Robot {
    private SerialPort sp;
    protected boolean on;
    private String type;

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
        sendCommand("CLOSE");
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

    protected void readCommand(String command) {
        if (command.equals("SORTING\r")) {
            type = "Sorting";
        }
        if (command.equals("PACKING\r")) {
            type = "Packing";
        }
    }

    public String getType() {
        return type;
    }

    public SerialPort getSp() {
        return sp;
    }
}