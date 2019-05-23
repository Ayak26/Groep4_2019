package backend;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DecodeQRCode {
    Box boxes1[] = DataModel.getInpakrobot1().getBoxes();
    Box boxes2[] = DataModel.getInpakrobot2().getBoxes();

    public String DecodeQRCode(File qrCodeimage) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(qrCodeimage);
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        try {
            boolean found = false;
            Result result = new MultiFormatReader().decode(bitmap);
            for (int j = 0; j < boxes1.length; j++) {
                System.out.println("we");
                if (!found) {
                    System.out.println("hebben");
                    for (Article a : boxes1[j].content) {
                        System.out.println("veel");
                        if (a.getId() == Integer.parseInt(result.getText())) {
                            System.out.println("plezier");
                            if (j == 0) {
                                DataModel.getSorteerrobot1().sendCommand("S1");
                                DataModel.getInpakrobot1().sendCommand("S1");
                                System.out.println("0");
                                return result.getText();
                            } else if (j == 1) {

                                DataModel.getSorteerrobot1().sendCommand("S1");
                                DataModel.getInpakrobot1().sendCommand("S2");
                                return result.getText();
                            } else {
                                DataModel.getSorteerrobot1().sendCommand("S1");
                                return result.getText();
                            }
                        }
                    }
                    for (Article a : boxes2[j].content) {
                        if (a.getId() == Integer.parseInt(result.getText())) {
                            if (j == 0) {
                                DataModel.getSorteerrobot1().sendCommand("S2");
                                DataModel.getInpakrobot1().sendCommand("S1");
                                return result.getText();
                            } else if (j == 1) {
                                DataModel.getSorteerrobot1().sendCommand("S2");
                                DataModel.getInpakrobot1().sendCommand("S2");
                                return result.getText();
                            } else {
                                DataModel.getSorteerrobot1().sendCommand("S2");
                                return result.getText();
                            }
                        }
                    }
                } else {
                    return result.getText();
                }
            }
//            for (int i = 0; i < articles.length - 1; i++) {
//                if (Integer.parseInt(result.getText()) == articles[i].getId()) {
//
//
//                    //QRcode found, artikel zoeken in order.
//                    return result.getText();
//                }
//            }
        } catch (NotFoundException e) {
            return null;
        }
        return null;
    }
}
