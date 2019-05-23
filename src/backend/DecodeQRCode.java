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
        Result result = null;
        try {
            result = new MultiFormatReader().decode(bitmap);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        boolean sent = sendCommands(DataModel.getInpakrobot1(), result);
        if (!sent) {
            sendCommands(DataModel.getInpakrobot2(), result);
        }
        return result.getText();
    }

    private boolean sendCommands(PackingRobot robot, Result result) {
        for (int j = 0; j < robot.getBoxes().length; j++) {
            for (Article a : robot.getBoxes()[j].content) {
                if (a.getId() == Integer.parseInt(result.getText()) && !a.isPacked()) {
                    if (j == 0) {
                        DataModel.getSorteerrobot1().sendCommand("S2");
                        robot.sendCommand("S1");
                        a.setPacked(true);
                        return true;
                    } else if (j == 1) {
                        DataModel.getSorteerrobot1().sendCommand("S2");
                        robot.sendCommand("S2");
                        a.setPacked(true);
                        return true;
                    } else {
                        DataModel.getSorteerrobot1().sendCommand("S2");
                        a.setPacked(true);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
