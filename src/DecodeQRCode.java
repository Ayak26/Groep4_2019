import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DecodeQRCode {
    Articles one = new Articles();
    ArrayList getArticle = one.getArticles();

    public String DecodeQRCode(File qrCodeimage) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(qrCodeimage);
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        try {
            Result result = new MultiFormatReader().decode(bitmap);
            System.out.println(result.getText());
            for (int i = 0; i < getArticle.size(); i++) {
                if (result.getText().equals(getArticle.get(i))) {
                    return result.getText();
                }
            }
        } catch (NotFoundException e) {
            System.out.println("There is no QR code in the image");
            return null;
        }
        return null;
    }
}
