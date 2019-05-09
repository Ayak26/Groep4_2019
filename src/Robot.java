import com.fazecast.jSerialComm.SerialPort;

import java.io.IOException;

public class Robot {
    private SerialPort sp;

    public Robot(String port) {
        sp = SerialPort.getCommPort(port);
        sp.setComPortParameters(9600, 8, 1, 0);
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);
    }

    public void openConnection() {
        if (sp.openPort()) {
            System.out.println("Port is open :)");
        } else {
            System.out.println("Failed to open port :(");
            return;
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (sp.closePort()) {
            System.out.println("Port is closed :)");
        } else {
            System.out.println("Failed to close port :(");
        }
    }

    public void sendCommand(String command) {
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
}