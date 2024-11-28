package Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class IOHandler {

    public static BufferedImage loadImage(String path) {
        InputStream is = IOHandler.class.getResourceAsStream(path);
        try {
            return ImageIO.read(is);
        } catch (IOException e) {
        }
        return null;
    }
}
