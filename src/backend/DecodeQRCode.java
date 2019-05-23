package backend;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DecodeQRCode {

    public String DecodeQRCode(File qrCodeimage) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(qrCodeimage);
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        try {
            Result result = new MultiFormatReader().decode(bitmap);
            for (int j = 0; j < DataModel.getInpakrobot1().getBoxes().length; j++) {
                for (Article a : DataModel.getInpakrobot1().getBoxes()[j].content) {
                    if (a.getId() == Integer.parseInt(result.getText()) && !a.isPacked()) {
                        if (j == 0) {
                            DataModel.getSorteerrobot1().sendCommand("S1");
                            DataModel.getInpakrobot1().sendCommand("S1");
                            a.setPacked(true);
                            return result.getText();
                        } else if (j == 1) {
                            DataModel.getSorteerrobot1().sendCommand("S1");
                            DataModel.getInpakrobot1().sendCommand("S2");
                            a.setPacked(true);
                            return result.getText();
                        } else {
                            DataModel.getSorteerrobot1().sendCommand("S1");
                            a.setPacked(true);
                            return result.getText();
                        }
                    }
                }
            }
            for (int j = 0; j < DataModel.getInpakrobot2().getBoxes().length; j++) {
                for (Article a : DataModel.getInpakrobot2().getBoxes()[j].content) {
                    if (a.getId() == Integer.parseInt(result.getText()) && !a.isPacked()) {
                        if (j == 0) {
                            DataModel.getSorteerrobot1().sendCommand("S2");
                            DataModel.getInpakrobot2().sendCommand("S1");
                            a.setPacked(true);
                            return result.getText();
                        } else if (j == 1) {
                            DataModel.getSorteerrobot1().sendCommand("S2");
                            DataModel.getInpakrobot2().sendCommand("S2");
                            a.setPacked(true);
                            return result.getText();
                        } else {
                            DataModel.getSorteerrobot1().sendCommand("S2");
                            a.setPacked(true);
                            return result.getText();
                        }
                    }
                }
            }
        } catch (NotFoundException e) {
            return null;
        }
        return null;
    }
}
