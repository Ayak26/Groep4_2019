import com.fazecast.jSerialComm.SerialPort;

import java.io.IOException;

public class Test {
    public static void main(String[] args) {

        SerialPort sp = SerialPort.getCommPort("COM7");
        sp.setComPortParameters(9600, 8, 1, 0);
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);


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

        for (Integer i = 2; i < 7; ++i) {
            try {
                sp.getOutputStream().write(i.byteValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                sp.getOutputStream().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Sent number: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (sp.closePort()) {
            System.out.println("Port is closed :)");
        } else {
            System.out.println("Failed to close port :(");
            return;
        }
    }
}
