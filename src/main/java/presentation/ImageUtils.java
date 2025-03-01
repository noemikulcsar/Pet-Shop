package presentation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Utilitar pentru manipularea imaginilor.
 */
public class ImageUtils {

    /**
     * Redimensionează o imagine la dimensiunile specificate.
     * @param imagePath Calea către imaginea de redimensionat
     * @param width Lățimea dorită a imaginii redimensionate
     * @param height Înălțimea dorită a imaginii redimensionate
     * @return Imaginea redimensionată sau null în caz de eroare
     */
    public static Image resizeImage(String imagePath, int width, int height) {
        try {
            File inputFile = new File(imagePath);
            BufferedImage originalImage = ImageIO.read(inputFile);
            return originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
