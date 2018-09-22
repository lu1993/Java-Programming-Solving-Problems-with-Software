
/**
 * Write a description of ImageSaver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import org.apache.commons.csv.*;
import edu.duke.*;

public class ImageSaver {
    public void doSave () {
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            ImageResource ir = new ImageResource(f);
            String name = ir.getFileName();
            String newName = "copy-" + name;
            ir.setFileName(newName);
            ir.draw(); // show image in screen
            ir.save();
        }
    }
}
