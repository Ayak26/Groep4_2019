package backend;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DecodeQRCode {
    Order one = new Order();
    Article articles[] = one.getArticles();
    Box boxes1[] = DataModel.getInpakrobot1().getBoxes();
    Box boxes2[] = DataModel.getInpakrobot2().getBoxes();

    public String DecodeQRCode(File qrCodeimage) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(qrCodeimage);
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        try {
            Result result = new MultiFormatReader().decode(bitmap);
            System.out.println(result.getText());
            for (int j = 0; j < 3; j++) {
                for (Article a : boxes1[j].content) {
                    if (a.getId() == Integer.parseInt(result.getText())) {
                        if (j == 0) {
                            DataModel.getSorteerrobot1().sendCommand("S1");
                            DataModel.getInpakrobot1().sendCommand("S1");
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
