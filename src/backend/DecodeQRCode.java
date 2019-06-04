package backend;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import gui.Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DecodeQRCode {

    public String DecodeQRCode(File qrCodeimage) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(qrCodeimage);
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result = null;
        try {
            result = new MultiFormatReader().decode(bitmap);
            SortingRobot sr;
            if(Main.getData().getSorteerrobot() != null) {
                sr = Main.getData().getSorteerrobot();

                PackingRobot pr1;
                PackingRobot pr2;
                if (sr.getPackingRobots() != null) {
                    if (sr.getPackingRobots().get(0) != null) {
                        pr1 = sr.getPackingRobots().get(0);
                        for (int j = 0; j < pr1.getBoxes().length; j++) {
                            for (Article a : pr1.getBoxes()[j].content) {
                                if (a.getId() == Integer.parseInt(result.getText()) && !a.isPacked()) {
                                    if (j == 0) {
                                        sr.sendCommand("S1");
                                        pr1.sendCommand("S1");
                                        a.setPacked(true);
                                        return result.getText();
                                    } else if (j == 1) {
                                        sr.sendCommand("S1");
                                        pr1.sendCommand("S2");
                                        a.setPacked(true);
                                        return result.getText();
                                    } else {
                                        sr.sendCommand("S1");
                                        a.setPacked(true);
                                        return result.getText();
                                    }
                                }
                            }
                        }
                    }
                    if (sr.getPackingRobots().get(1) != null) {
                        pr2 = sr.getPackingRobots().get(1);
                        for (int j = 0; j < pr2.getBoxes().length; j++) {
                            for (Article a : pr2.getBoxes()[j].content) {
                                if (a.getId() == Integer.parseInt(result.getText()) && !a.isPacked()) {
                                    if (j == 0) {
                                        sr.sendCommand("S2");
                                        pr2.sendCommand("S1");
                                        a.setPacked(true);
                                        return result.getText();
                                    } else if (j == 1) {
                                        sr.sendCommand("S2");
                                        pr2.sendCommand("S2");
                                        a.setPacked(true);
                                        return result.getText();
                                    } else {
                                        sr.sendCommand("S2");
                                        a.setPacked(true);
                                        return result.getText();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (NotFoundException e) {
            return null;
        }
        return result.getText();
    }
}
