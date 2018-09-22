
/**
 * Write a description of BatchInversions here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import org.apache.commons.csv.*;
import edu.duke.*;

public class BatchInversions {
    public ImageResource makeInversion (ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel: outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            pixel.setRed(255 - inPixel.getRed());
            pixel.setBlue(255 - inPixel.getBlue());
            pixel.setGreen(255 - inPixel.getGreen());
        }
        return outImage;
    }
    
    public void selectAndConvert () {
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            ImageResource ir = new ImageResource(f);
            ImageResource convertedIR = makeInversion(ir);
            String name = ir.getFileName();
            String newName = "inverted-" + name;
            convertedIR.setFileName(newName);
            convertedIR.save();
        }
    }
}
