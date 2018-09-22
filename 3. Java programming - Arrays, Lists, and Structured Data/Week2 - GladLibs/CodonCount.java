
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import java.util.*;
import edu.duke.*;
import org.apache.commons.csv.*;

public class CodonCount {
    private HashMap<String, Integer> map;
    
    public CodonCount () {
        map = new HashMap<String, Integer>();
    }
    
    public void buildCodonMap (int start, String dna) {
        map.clear();
        for (int i = start; i <= dna.length() - 3; i += 3) {
            String curGene = dna.substring(i, i + 3);
            if (map.containsKey(curGene)) {
                map.put(curGene, map.get(curGene) + 1);
            }
            else {
                map.put(curGene, 1);
            }
        }
    }
    
    public String getMostCommonCodon () {
        int maxFreq = 0;
        String result = "";
        for (String k: map.keySet()) {
            if (map.get(k) > maxFreq) {
                maxFreq = map.get(k);
                result = k;
            }
        }
        System.out.println(result + " occurs the most: " + map.get(result));
        return result;
    }
    
    public void printCodonCounts (int start, int end) {
        for (String k:map.keySet()) {
            int count = map.get(k);
            if (count >= start && count <= end) {
                System.out.println(k + "\t" + count);
            }
        }
    }
    
    public void testBuildCodonMap () {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        //System.out.print(dna);
        for (int i = 0; i <= 2; i ++) {
            CodonCount cc = new CodonCount();
            cc.buildCodonMap(i, dna);
            int unqiueCount = cc.map.size();
            //System.out.println(cc.map);
            System.out.println("number of unique codons " + unqiueCount);
            //cc.printCodonCounts(1, 5); 
        }
    }
    
    public void testGetMostCommonCodon () {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        CodonCount cc = new CodonCount();
        cc.buildCodonMap(2, dna);
        String mostFreq = cc.getMostCommonCodon();
    }
    
    public void testPrintCodonCounts () {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        CodonCount cc = new CodonCount();
        cc.buildCodonMap(0, dna);
        cc.printCodonCounts(7,7);
    }
}
