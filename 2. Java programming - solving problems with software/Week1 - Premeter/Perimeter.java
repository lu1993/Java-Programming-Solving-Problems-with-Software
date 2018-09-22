
/**
 * Write a description of Premeter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class Perimeter {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int totalPoint = 0;
        for (Point currPt : s.getPoints()) {
            totalPoint += 1;
        }
        return totalPoint;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double perimeter = getPerimeter(s);
        int point = getNumPoints(s);
        return perimeter / point;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double largestDist = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            if (currDist > largestDist) {
                largestDist = currDist;
            }
        }
        // totalPerim is the answer
        return largestDist;
    }

    public double getLargestX(Shape s) {
        // Put code here
        return 0.0;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = 0.0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perimeter = getPerimeter(s);
            if (perimeter > largestPerimeter) {
                largestPerimeter = perimeter;
            }
        }
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        String name = "";
        double largestPerimeter = 0.0; 
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perimeter = getPerimeter(s);
            if (perimeter > largestPerimeter) {
                largestPerimeter = perimeter;
                name = f.getName();
            }
        }
        return name;
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        double avglength = getAverageLength(s);
        System.out.println("average length = " + avglength);
        double largestlength = getLargestSide(s);
        System.out.println("largest length = " + largestlength);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getLargestPerimeterMultipleFiles();
        System.out.println("largest perimeter = " + length);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        String length = getFileWithLargestPerimeter();
        System.out.println("largest perimeter file = " + length);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        Perimeter pr = new Perimeter();
        pr.testPerimeter();
        // pr.testPerimeterMultipleFiles();
        // pr.testFileWithLargestPerimeter();
    }
}

