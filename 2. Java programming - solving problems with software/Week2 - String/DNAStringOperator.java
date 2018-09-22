
/**
 * Write a description of DNAStringOperator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class DNAStringOperator {
    public StorageResource getAllGenes (String dna) {
        StorageResource sr = new StorageResource();
        int startIndex = 0;
        while (true) {
            String gene = findGene(dna, startIndex);
            if (gene == "") {
                break;
            }
            sr.add(gene);
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
        return sr;
    }
    
    public String findGene (String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1 || where == -1) {
            return "";
        }
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = 0;
        if(taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
		minIndex = tgaIndex;
	} else {
		minIndex = taaIndex;
	}

	if(minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
		minIndex = tagIndex;
	}

	if(minIndex == -1) {
		return "";
	}
		
	return dna.substring(startIndex, minIndex + 3);  
    }
    
    public int findStopCodon (String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
	while(currIndex != -1) {
		int diff = currIndex - startIndex;
		if(diff % 3 == 0) {
			return currIndex;
		} else {
			currIndex = dna.indexOf(stopCodon, currIndex + 1);
		}
	}

	return -1;
    }
    
    public double cgRatio (String dna) {
        double count = 0.0;
        double strLen = dna.length();
        for (int i = 0; i < strLen; i ++) {
            if (dna.charAt(i) == 'C' || dna.charAt(i) == 'G') {
                count += 1;
            }
        }
        double ratio = count / strLen;
        return ratio;
    }
    
    public int countCTG (String dna) {
        int startIndex = 0;
        int count = 0;
        int index = dna.indexOf("CTG", startIndex);
        while (true) {
            if (index == -1 || count > dna.length()) {
                break;
            }
            count += 1;
            index = dna.indexOf("CTG", index + 3);
        }
        return count;
    }
    
    public int ctgCount (String dna) {
        int count = 0;
        int startIndex = 0;
        while (true) {
            int curIndex = dna.indexOf("CTG", startIndex);
            if (curIndex == -1) {
                break;
            }
            count += 1;
            startIndex = curIndex + 1;
        }
        return count;
    }
    
    public void processGenes () {
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        dna = dna.toUpperCase();
        System.out.println("dna string " + dna);
        StorageResource sr = getAllGenes(dna);
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = ctgCount(dna);
        int longestLen = 0;
        for (String s: sr.data()) {
            System.out.println("genes in dna string " + s);
            count3 += 1;
            int len = s.length();
            if (len > 60) {
   
                count1 += 1;
            }
            double ratio = cgRatio(s);
            if (ratio > 0.35) {
                
                count2 += 1;
            }
            if (len > longestLen) {
                longestLen = len;
            } 
        }
        System.out.println("number of CTG in dna " + count4);
        System.out.println("number of genes in file " + count3);
        System.out.println("number of genes that are longer than 60 characters " + count1);
        System.out.println("number of genes whose C-G-ratio is higher than 0.35 " + count2);
        System.out.println("length of longest gene " + longestLen);
    }
    
    public void testProcessGenes () {
        processGenes();
    }
}
