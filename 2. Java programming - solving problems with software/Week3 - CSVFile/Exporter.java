
/**
 * Write a description of Exporter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class Exporter {
    public void countryInfo (CSVParser parser, String country) {
        for (CSVRecord record: parser) {
            if (record.get("Country").equals(country)) {
                String export = record.get("Exports");
                String value = record.get("Value (dollars)");
                System.out.println(country + ": " + export + ": " + value);
            }
        }
    }
    
    public void listExportersTwoProducts (CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record: parser) {
            String export = record.get("Exports");
            String country = record.get("Country");
            if (export.contains(exportItem1) && export.contains(exportItem2)) {
                System.out.println(country);
            }
        }
    }
   
    public void numberOfExporters (CSVParser parser, String exportItem1) {
        int count = 0;
        for (CSVRecord record: parser) {
            String export = record.get("Exports");
            // String country = record.get("Country");
            if (export.contains(exportItem1)) {
                count += 1;
            }
        }
        System.out.println("number of countries that export " + exportItem1 + ":"+ count);
        // return count;
    }
    
    public void bigExporters (CSVParser parser, String value) {
        for (CSVRecord record: parser) {
            String v = record.get("Value (dollars)");
            String country = record.get("Country");
            if (v.length() > value.length()) {
                System.out.println(country + " " + v);
            }
        }
    }
    
    public void test () {
        FileResource fr = new FileResource();
        CSVParser records = fr.getCSVParser();
        listExportersTwoProducts(records, "cotton", "flowers");
        numberOfExporters(records, "cocoa");
        // countryInfo(records, "Nauru");
        bigExporters(records, "$999,999,999,999");
    }
}
