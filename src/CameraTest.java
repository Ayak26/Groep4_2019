import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import javax.swing.*;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class CameraTest {
    public CameraTest() {

        VideoCapture camera;

        try{


          // System.load("C:/opencv/build/java/x64/opencv_java410.dll");   //Link to these files!!!!!
          //  System.load("C:/opencv/build/x64/vc14/bin/opencv_ffmpeg410_64.dll");
          //  System.load("C:/opencv/build/x64/vc14/bin/opencv_world410.dll");

         //   System.load("/home/pi/Desktop/opencv_java410.dll");
         //   System.load("/home/pi/Desktop/opencv_ffmpeg410_64.dll");
          //  System.load("/home/pi/Desktop/opencv_world410.dll");

        } catch(Exception es){
            JOptionPane.showMessageDialog(null, "ERROR Files can't be found: " + es);
        }

        try{
            camera = new VideoCapture(0);

        } catch(Exception es){
            JOptionPane.showMessageDialog(null, "Cam can't be detected, attempt 2: " + es);
            camera = new VideoCapture(1);
        }
        if(!camera.isOpened()){
            JOptionPane.showMessageDialog(null, "Cam can't be opened");
        }
        else {
            Mat frame = new Mat();
            while(true){
                if (camera.read(frame)){
                    Imgcodecs.imwrite("camera.jpg", frame);
                    try {
                        File file = new File("camera.jpg");
                        DecodeQRCode newQRCode = new DecodeQRCode();
                        String decodedText = newQRCode.DecodeQRCode(file);
                        if(decodedText == null) {
                        } else {
                            System.out.println("Decoded text = " + decodedText);
                            TimeUnit.SECONDS.sleep(1);
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
        camera.release();
    }
}
