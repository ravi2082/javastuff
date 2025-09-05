import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

/**
 * Utility class to compare two files and find lines that exist in the second file
 * but not in the first file. Writes the differences to an output file.
 */
public class FileCompare {

    private static final String CDS_FILE = "uniquelogins-2012-cds-found.txt";
    private static final String MWP_FILE = "uniquelogins-2012-mwp-found.txt";
    private static final String OUTPUT_FILE = "uniqueloginsbetwMWP-CDS-2012.txt";

    /**
     * Main method to compare files and generate difference report.
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            Set<String> cdsLines = loadLinesFromFile(CDS_FILE);
            int cdsSize = cdsLines.size();
            System.out.println("Initial CDS Data Size: " + cdsSize);

            int uniqueCount = compareAndWriteDifferences(cdsLines, MWP_FILE, OUTPUT_FILE);
            System.out.println("Unique lines found in MWP but not in CDS: " + uniqueCount);
            
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Loads all lines from a file into a Set for efficient lookup.
     * @param filename the name of the file to load
     * @return a Set containing all lines from the file
     * @throws IOException if file cannot be read
     */
    private static Set<String> loadLinesFromFile(String filename) throws IOException {
        Set<String> lines = new TreeSet<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        
        return lines;
    }

    /**
     * Compares lines from the comparison file against the reference set and writes
     * unique lines to the output file.
     * @param referenceLines the set of reference lines to compare against
     * @param comparisonFile the file to compare
     * @param outputFile the file to write unique lines to
     * @return the number of unique lines found
     * @throws IOException if files cannot be read or written
     */
    private static int compareAndWriteDifferences(Set<String> referenceLines, 
                                                 String comparisonFile, 
                                                 String outputFile) throws IOException {
        int uniqueCount = 0;
        
        try (BufferedReader comparisonReader = new BufferedReader(new FileReader(comparisonFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            
            String line;
            while ((line = comparisonReader.readLine()) != null) {
                if (!referenceLines.contains(line)) {
                    System.out.println("Unique line: " + line);
                    writer.write(line + "\n");
                    writer.flush();
                    uniqueCount++;
                }
            }
        }
        
        return uniqueCount;
    }
}
