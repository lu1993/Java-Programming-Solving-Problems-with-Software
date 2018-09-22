
/**
 * Write a description of MakeImageGray here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;

public class MakeImageGray {
    public ImageResource makeGray(ImageResource inImage) {
        // create an empty image with same width and height with input image
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        // fill in empty image with average RBG color of input image
        for (Pixel pixel: outImage.pixels()) {
            // look for corresponding pixel in inImage
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen()) / 3;
            pixel.setRed(average);
            pixel.setBlue(average);
            pixel.setGreen(average);
        }
        return outImage;
    }
    
    public void testGray () {
        ImageResource ir = new ImageResource();
        ImageResource gray = makeGray(ir);
        gray.draw();
    }
    
    public void selectAndConvert () {
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource outImage = makeGray(inImage);
            outImage.draw();
        }
    }
}

