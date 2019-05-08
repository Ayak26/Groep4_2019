/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv;
import org.opencv.core.*;
import org.opencv.highgui.*; 
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgcodecs.Imgcodecs;
import java.util.concurrent.TimeUnit;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
        
public class OpenCV {
    
    
     private static String decodeQRCode(File qrCodeimage) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(qrCodeimage);
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        try {
            Result result = new MultiFormatReader().decode(bitmap);
            return result.getText();
        } catch (NotFoundException e) {
            System.out.println("There is no QR code in the image");
            return null;
        }
    }

    public static void main (String args[]){
        
//        for(int i = 0; i < 10; i++){

//        }
    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    	VideoCapture camera = new VideoCapture(0);
    	
    	if(!camera.isOpened()){
    	//	System.out.println("Error");
    	}
    	else {
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
            String decodedText = decodeQRCode(file);
            if(decodedText == null) {
             //   System.out.println("No QR Code found in the image");
                      
            } else {
                System.out.println("Decoded text = " + decodedText);
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