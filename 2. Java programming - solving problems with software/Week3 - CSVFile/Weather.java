
/**
 * Write a description of Weather here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;

public class Weather {
   public CSVRecord coldestOfTwo (CSVRecord coldestTemp, CSVRecord record, String variable) {
        if (coldestTemp == null) {
            coldestTemp = record;
        }
        else {
            double curTemp = Double.parseDouble(coldestTemp.get(variable));
            double newTemp = Double.parseDouble(record.get(variable));
            if (curTemp > newTemp && newTemp > -50) {
                coldestTemp = record;
            }
        }
        return coldestTemp;
    }
    
    public CSVRecord lowestOfTwo (CSVRecord lowestHumidity, CSVRecord record, String variable) {
        if (lowestHumidity == null) {
            lowestHumidity = record;
        }
        else {
            if (!record.get("Humidity").equals("N/A") && !lowestHumidity.get("Humidity").equals("N/A")) {
               int currHumd = Integer.parseInt(record.get("Humidity"));
               int lowestHumd = Integer.parseInt(lowestHumidity.get("Humidity"));
               if (currHumd < lowestHumd) {
                   lowestHumidity = record;
               }
            }
        }
        return lowestHumidity;
    }
    
    public CSVRecord coldestHourInFile (CSVParser parser) {
        CSVRecord coldestTemp = null;
        for (CSVRecord record: parser) {
            coldestTemp = coldestOfTwo(coldestTemp, record, "TemperatureF");
        }
        return coldestTemp;
    }
    
    public void testColdestHourInFile () {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestTemp = coldestHourInFile(parser);
        System.out.println("Coldest Temperature on this day " + Double.parseDouble(coldestTemp.get("TemperatureF")));
    }
    
    public File fileWithColdestTemperature () {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestTemp = null;
        File coldestFile = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord record = coldestHourInFile(parser);
            coldestTemp = coldestOfTwo(coldestTemp, record, "TemperatureF");
            if (coldestTemp == record) {
                coldestFile = f;
            }
        }
        return coldestFile;
    }
        
    public void testFileWithColdestTemperture () {
        File coldestFile = fileWithColdestTemperature();
        FileResource fr = new FileResource(coldestFile);
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestTemp = coldestHourInFile(parser);
        System.out.println("Coldest day was in file " + coldestFile.getAbsolutePath());
        System.out.println("Coldest temperature on that day was " + Double.parseDouble(coldestTemp.get("TemperatureF")));
        System.out.println("All the temperatures on the coldest day were:");
        CSVParser coldestRecords = new FileResource(coldestFile).getCSVParser();
        for (CSVRecord record: coldestRecords) {
            System.out.println(record.get("TemperatureF"));
        }
    }
      
    public CSVRecord lowestHumidityInFile (CSVParser parser) {
        CSVRecord lowestHumidity = null;
        for (CSVRecord record: parser) {
            lowestHumidity = lowestOfTwo(lowestHumidity, record, "Humidity");
        }
        return lowestHumidity;
    }
    
    public CSVRecord lowestHumidityInManyFiles () {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestHumidity = null;
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord record = lowestHumidityInFile(parser);
            lowestHumidity = lowestOfTwo(lowestHumidity, record, "Humidity");
        }
        return lowestHumidity;
    }
    
    public void testLowestHumidityInFile () {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestHumidity = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + 
                            Integer.parseInt(lowestHumidity.get("Humidity")) + 
                           " at " + lowestHumidity.get("DateUTC"));
    }
    
    public void testLowestHumidityInManyFiles () {
        CSVRecord lowestHumidity = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + 
                    Integer.parseInt(lowestHumidity.get("Humidity")) + 
                   " at " + lowestHumidity.get("DateUTC"));
    }
    
    public double averageTemperatureInFile (CSVParser parser) {
        double total = 0.0;
        int count = 0;
        double avg = 0.0;
        for (CSVRecord record: parser) {
            total += Double.parseDouble(record.get("TemperatureF"));
            count += 1;
        }
        avg = total / count;
        return avg;
    }
    
    public void testAverageTempertureInFile () {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + avg);
    }
    
    public double averageTemperatureWithHighHumidityInFile (CSVParser parser, int value) {
        double total = 0.0;
        double count = 0.0;
        double avg = 0.0;
        for (CSVRecord record: parser) {
            if (Integer.parseInt(record.get("Humidity")) >= value) {
                total += Double.parseDouble(record.get("TemperatureF"));
                count += 1.0;
            }
        }
        if (count > 0.0) {
            avg = total / count;
            System.out.println("Average Temp when high Humidity is " + avg);
            return avg;
        }
        else {
            System.out.println("No temperatures with that humidity");
            return avg;
        }
    }
    
    public void testAverageTemperatureWithHighHumidityInFile () {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureWithHighHumidityInFile(parser, 80);
    }
}
