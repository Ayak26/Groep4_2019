package backend;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import java.io.File;

public class Camera extends Thread {
    private boolean runThread = true;
    VideoCapture camera;
    public void run() {
        try {
            System.load("C:/Users/Jeroen/Documents/Javalibraries/opencv/build/java/x64/opencv_java410.dll");
        } catch (Exception es) {
        }

        try {
            camera = new VideoCapture(0);

        } catch (Exception es) {
            camera = new VideoCapture(1);
        }
        if (!camera.isOpened()) {
        } else {
            Mat frame = new Mat();
            while (runThread) {
                if (camera.read(frame)) {

                    Imgcodecs.imwrite("assets/camera.jpg", frame);
                    try {
                        Thread.sleep(25);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {

                        File file = new File("assets/camera.jpg");

                        DecodeQRCode newQRCode = new DecodeQRCode();
                        String decodedText = newQRCode.DecodeQRCode(file);
                        if (decodedText != null) {
                            System.out.println("Decoded text = " + decodedText);
                            Imgcodecs.imwrite("assets/qrcode.jpg", frame);
                            Thread.sleep(5000);
                        }
                    } catch (Exception e) {
                    }
                }


            }
        }
    }

    public void stopRunThread() {
        this.runThread = false;
        camera.release();
    }
}
