package backend;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DecodeQRCode {
    Order one = new Order();
    Article articles[] = one.getArticles();

    public String DecodeQRCode(File qrCodeimage) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(qrCodeimage);
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        try {
            Result result = new MultiFormatReader().decode(bitmap);
            System.out.println(result.getText());
            for (int i = 0; i < articles.length-1; i++) {
                if (Integer.parseInt(result.getText()) == articles[i].getId()) {
                    qrCodeimage.renameTo(new File("artikel" +articles[i].getId()+".jpg"));
                    articles[i].setImage(new Image(qrCodeimage.getCanonicalPath()));
                    return result.getText();
                }
            }
        } catch (NotFoundException e) {
            return null;
        }
        return null;
    }
}
