/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgcodecs.Imgcodecs;
import java.util.concurrent.TimeUnit;

import java.io.File;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
        
public class OpenCV {
    
    
//     private static String decodeQRCode(File qrCodeimage) throws IOException {
//
//    }
    


    public static void main (String args[]){
        
        
        VideoCapture camera;

    try{


    System.load("C:/opencv/build/java/x64/opencv_java410.dll");
    System.load("C:/opencv/build/x64/vc14/bin/opencv_ffmpeg410_64.dll");
    System.load("C:/opencv/build/x64/vc14/bin/opencv_world410.dll");

    } catch(Exception es){
        //   JOptionPane.showMessageDialog(null, "ERROR: " + es);
    }
          //      JOptionPane.showMessageDialog(null, "ERROR1");

        try{
         camera = new VideoCapture(0);

        } catch(Exception es){
           // JOptionPane.showMessageDialog(null, "ERRORSS: " + es);
            camera = new VideoCapture(1);
        }
      //JOptionPane.showMessageDialog(null, "ERROR4");
    	        Scherm h = new Scherm();
        h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	if(!camera.isOpened()){
        //JOptionPane.showMessageDialog(null, "ERROR");
    	}
    	else {
          //          JOptionPane.showMessageDialog(null, "ERROR2");

    		Mat frame = new Mat();
    	    while(true){
    	    	if (camera.read(frame)){
    	    		//System.out.println("Frame Obtained");
    	    		//System.out.println("Captured Frame Width " + 
    	    		//frame.width() + " Height " + frame.height());
    	    		Imgcodecs.imwrite("camera.jpg", frame);
    	    		//System.out.println("OK");
                        
                        
                                   try {
            File file = new File("camera.jpg");
            DecodeQRCode nieuw = new DecodeQRCode();
            String decodedText = nieuw.DecodeQRCode(file);
            if(decodedText == null) {
             //   System.out.println("No QR Code found in the image");
            } else {
               System.out.println("Decoded text = " + decodedText);
            //    JOptionPane.showMessageDialog(null, decodedText);

                 TimeUnit.SECONDS.sleep(1);
            }
        } catch (Exception e) {
          //  System.out.println("Could not decode QR Code, IOException :: " + e.getMessage());
        }


                        //TimeUnit.SECONDS.sleep(1);
    	    		//break;

    	    	}
    	    }	
    	}
        
    	camera.release();

    }
}   
