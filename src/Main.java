import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgcodecs.Imgcodecs;

import java.util.concurrent.TimeUnit;
import java.io.File;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        CameraThread cameraThread = new CameraThread();
        cameraThread.start();

    }
}
