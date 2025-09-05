import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Utility class to remove duplicates from a text file.
 * Reads from "list1.txt" and writes unique lines to "newlist1.txt".
 */
public class DelDup {

    private static final String INPUT_FILE = "list1.txt";
    private static final String OUTPUT_FILE = "newlist1.txt";

    /**
     * Main method to remove duplicates from file.
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Set<String> uniqueLines = new HashSet<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {
            
            String line;
            while ((line = reader.readLine()) != null) {
                if (!uniqueLines.contains(line)) {
                    uniqueLines.add(line);
                } else {
                    System.out.println("Duplicate found: " + line);
                }
            }
            
            System.out.println("Total unique lines: " + uniqueLines.size());
            
            // Write unique lines to output file
            for (String uniqueLine : uniqueLines) {
                writer.write(uniqueLine + "\n");
            }
            
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
