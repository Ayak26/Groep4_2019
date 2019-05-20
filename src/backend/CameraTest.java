package backend;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import javax.swing.*;
import java.io.File;

public class CameraTest {
    public CameraTest() {

        VideoCapture camera;

        try {
            System.load("C:/Users/Jeroen/Documents/Javalibraries/opencv/build/java/x64/opencv_java410.dll");
        } catch (Exception es) {
            JOptionPane.showMessageDialog(null, "ERROR Files can't be found: " + es);
        }

        try {
            camera = new VideoCapture(0);

        } catch (Exception es) {
            JOptionPane.showMessageDialog(null, "Cam can't be detected, attempt 2: " + es);
            camera = new VideoCapture(1);
        }
        if (!camera.isOpened()) {
            JOptionPane.showMessageDialog(null, "Cam can't be opened");
        } else {
            //Mat frame = new Mat();
            Mat frame = new Mat();
            while (true) {
                if (camera.read(frame)) {

                    Imgcodecs.imwrite("assets/camera.jpg", frame);
                    try {

                        File file = new File("assets/camera.jpg");

                        DecodeQRCode newQRCode = new DecodeQRCode();
                        String decodedText = newQRCode.DecodeQRCode(file);
                        if (decodedText != null) {
                            Imgcodecs.imwrite("assets/qrcode.jpg", frame);
                            System.out.println("Decoded text = " + decodedText);
                        }
                    } catch (Exception e) {
                    }
                }


            }
        }
        camera.release();
    }
}
