import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
// https://docs.oracle.com/javase/tutorial/2d/images/loadimage.html
// https://docs.oracle.com/javase/tutorial/2d/images/saveimage.html
// https://www.baeldung.com/java-resize-image

// Transformation program that takes input image, desired dimensions, and returns new image.
// Current workflow designed to take image from an existing folder, and output to same folder
public class image_resizer {


    // Variables will get values from parameters
    private static int targetWidth = 100;
    private static int targetHeight = 80;
    private static String targetName = "sphere.jpg";

    // Main function reads file from relative location
    public static void main(String args[]) throws IOException {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(targetName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage newImage = null;

        newImage = resizeImage(img, targetWidth, targetHeight);

        try {
            File outputfile = new File("imageResult2.jpg");
            ImageIO.write(newImage, "jpg", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }
}
