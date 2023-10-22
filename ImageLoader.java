import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageLoader {
    public static BufferedImage cargarImagen(String ruta) {
        try {
            // Carga la imagen desde la ruta especificada
            return ImageIO.read(new File(ruta));
        } catch (IOException e) {
            e.printStackTrace();
            return null; // En caso de error, devuelve null
        }
    }
}