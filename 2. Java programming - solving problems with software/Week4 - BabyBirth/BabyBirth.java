
/**
 * Write a description of BabyBirth here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirth {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord record: fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(record.get(2));
            if (numBorn < 100) {
                System.out.println("Name " + record.get(0) + 
                    " Gender " + record.get(1) + 
                    " Num Born " + record.get(2));
            }
        }
    }
    
    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int totalNames = 0;
        int totalBoyNames = 0;
        int totalGirlNames = 0;
        for (CSVRecord record: fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(record.get(2));
            totalBirths += numBorn;
            totalNames += 1;
            if (record.get(1).equals("M")) {
                totalBoys += numBorn;
                totalBoyNames += 1;
            }
            else {
                totalGirls += numBorn;
                totalGirlNames += 1;
            }
        }
        System.out.println("Total number of births = " + totalBirths);
        System.out.println("Total number of boys births = " + totalBoys);
        System.out.println("Total number of girls births = " + totalGirls);
        System.out.println("Total number of names = " + totalNames);
        System.out.println("Total number of boys names = " + totalBoyNames);
        System.out.println("Total number of girls names = " + totalGirlNames);
    }
    
    public int getGirlName (int year, FileResource fr) {
        if (fr == null) {
            fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
        }
        CSVParser parser = fr.getCSVParser(false);
        int totalGirlNames = 0;
        for (CSVRecord record: parser) {
            if (record.get(1).equals("F")) {
                totalGirlNames += 1;
            }
        }
        return totalGirlNames;
    }
    
    public void testTotalBirths () {
        FileResource fr1 = new FileResource("us_babynames/us_babynames_by_year/yob"+1900+".csv");
        totalBirths(fr1);
        FileResource fr2 = new FileResource("us_babynames/us_babynames_by_year/yob"+1905+".csv");
        totalBirths(fr2);
    }
    
    public long getRank (int year, String name, String gender, FileResource fr) {
        if (fr == null) {
            fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
        }
        CSVParser parser = fr.getCSVParser(false);
        long rank = -1;
        for (CSVRecord record: parser) {
            if (record.get(0).equals(name) && record.get(1).equals(gender)) {
                rank = record.getRecordNumber();
                break;
            }
        }
        if (gender.equals("M")) {
            int totalGirlNames = getGirlName(year, fr);
            rank -= totalGirlNames;
        }
        return rank;
    }
    
    public void testGetRank () {
        long rank = 0;
        //rank = getRank(1960, "Emily", "F", null);
        //System.out.println("rank of the girl’s name “Emily” in 1960 is " + rank);
        rank = getRank(1971, "Frank", "M", null);
        System.out.println("rank of the boy’s name “Frank” in 1971 is " + rank);
    }
    
    public String getName (int year, long rank, String gender) {
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
        CSVParser parser = fr.getCSVParser(false);
        String name = "NO NAME";
        if (gender.equals("M")) {
            int totalGirlNames = getGirlName(year, fr);
            rank += totalGirlNames;
        }
        for (CSVRecord record: parser) {
            if (record.getRecordNumber() == rank && record.get(1).equals(gender)) {
                name = record.get(0);
            }
        }
        return name;
    }
    
    public void testGetName () {
        String name = "";
        //name = getName(1980, 350, "F");
        //System.out.println("The girl's name of rank 350 in 1980 is " + name);
        name = getName(1982, 450, "M");
        System.out.println("The boy's name of rank 450 in 1982 is " + name);
    }
    
    public void whatIsNameInYear (String name, int year, int newYear, String gender) {
        long rank = -1;
        rank = getRank(year, name, gender, null);
        //System.out.println("Rank of " + name + " in " + year + " is "+ rank);
        String newName = "";
        newName = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + " would be " + newName + " if she/he was born in " + newYear);
    }
    
    public void testWhatIsNameInYear () {
        //whatIsNameInYear("Susan", 1972, 2014, "F");
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    
    public double getAverageRank (String name, String gender) {
        double avgRank = -1.0;
        double totalRank = 0;
        int count = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            for (CSVRecord record: parser) {
                if (record.get(0).equals(name) && record.get(1).equals(gender)) {
                    double curRank = getRank(0, name, gender, fr);
                    //System.out.println("Current rank is " + curRank);
                    totalRank += curRank;
                    count += 1;
                    break;
                }
            }
        }
        if (count > 0) {
            avgRank = totalRank / count;
        }
        return avgRank;
    }
    
    public void testGetAverageRank () {
        //double avgRank = getAverageRank("Susan", "F");
        //System.out.println("average rank of girl's name Susan " + avgRank); 
        double avgRank = getAverageRank("Robert", "M");
        System.out.println("average rank of boy's name Robert " + avgRank); 
    }
    
    public long getTotalBirthsRankedHigher (int year, String name, String gender) {
        int total = 0;
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord record: parser) {
            if (record.get(1).equals(gender)) {
                if (!record.get(0).equals(name)) {
                    System.out.println(record.get(0) + " has higher rank than " + name);
                    System.out.println(record.get(0) + " has  " + record.get(2));
                    total += Integer.parseInt(record.get(2));
                } 
                else {
                    break;
                }
            }
        }
        return total;
    }
    
    public void testGetTotalBirthsRankedHigher () {
        //long total = getTotalBirthsRankedHigher(1990, "Emily", "F");
        //System.out.println("the total number of girls born in 1990 with names ranked higher than the girl's name Emily " + total);
        long total = getTotalBirthsRankedHigher(1990, "Drew", "M");
        System.out.println("the total number of boys born in 1990 with names ranked higher than the boy's name Drew " + total);

    }
    
    public int getHighestRankYear (String name, String gender, int startYear, int endYear) {
        long curRank = 0;
        long highestRank = 0;
        int highestYear = 0;
        for (int year = startYear; year <= endYear; year++) {
            FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
            CSVParser parser = fr.getCSVParser(false);
            for (CSVRecord record: parser) {
                if (record.get(0).equals(name) && record.get(1).equals(gender)) {
                    curRank = record.getRecordNumber();
                    if (highestRank == 0) {
                        highestRank = curRank;
                        highestYear = year;
                    }
                    else {
                        if (curRank < highestRank) {
                            highestRank = curRank;
                            highestYear = year;
                        }
                    } 
                }
            }   
        }
        return highestYear;
    }
    
    public void testGetHighestRankYear () {
        int year = getHighestRankYear("Genevieve", "F", 1880, 2014); 
        System.out.println("The year with the highest rank of girl's name Genevieve " + year);
        //int year = getHighestRankYear("Mich", "M", 1880, 2014); 
        //System.out.println("The year with the highest rank of boy's name Mich " + year);
    }
}
