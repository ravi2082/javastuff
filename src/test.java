import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Utility class for file operations including file comparison and content analysis.
 */
public class FileUtil {
    
    /**
     * Compares the contents of two files byte by byte.
     * @param firstFilePath path to the first file
     * @param secondFilePath path to the second file
     * @return comparison result message
     */
    public static String compareTwoFiles(String firstFilePath, String secondFilePath) {
        Path firstFile = Paths.get(firstFilePath);
        Path secondFile = Paths.get(secondFilePath);
        
        // Check if files exist
        if (!Files.exists(firstFile)) {
            return "File " + firstFile.toAbsolutePath() + " does not exist";
        }
        if (!Files.exists(secondFile)) {
            return "File " + secondFile.toAbsolutePath() + " does not exist";
        }
        
        try {
            // Check file sizes first
            long firstSize = Files.size(firstFile);
            long secondSize = Files.size(secondFile);
            
            if (firstSize != secondSize) {
                return String.format("Files have different sizes. %s: %d bytes, %s: %d bytes",
                    firstFile.getFileName(), firstSize, 
                    secondFile.getFileName(), secondSize);
            }
            
            // Compare file contents using NIO for better performance
            byte[] firstFileBytes = Files.readAllBytes(firstFile);
            byte[] secondFileBytes = Files.readAllBytes(secondFile);
            
            for (int i = 0; i < firstFileBytes.length; i++) {
                if (firstFileBytes[i] != secondFileBytes[i]) {
                    return String.format("Files differ at byte position %d. %s: %d, %s: %d",
                        i, firstFile.getFileName(), firstFileBytes[i], 
                        secondFile.getFileName(), secondFileBytes[i]);
                }
            }
            
            return "Files are identical";
            
        } catch (IOException e) {
            return "Error reading files: " + e.getMessage();
        }
    }

    /**
     * Analyzes a text file and prints line length statistics.
     * @param filename the file to analyze
     */
    public static void analyzeTextFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 1;
            int maxLength = 0;
            int totalLines = 0;
            
            while ((line = reader.readLine()) != null) {
                int length = line.length();
                System.out.println("Line " + lineNumber + " length: " + length);
                
                maxLength = Math.max(maxLength, length);
                totalLines++;
                lineNumber++;
                
                // Show character at specific position if line is long enough
                if (length > 1000) {
                    System.out.println("Long line detected. Sample character at position 1000: " + 
                                     (length > 1000 ? line.charAt(1000) : "N/A"));
                }
            }
            
            System.out.println("Analysis complete:");
            System.out.println("Total lines: " + totalLines);
            System.out.println("Maximum line length: " + maxLength);
            
        } catch (IOException e) {
            System.err.println("Error analyzing file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Main method for testing file operations.
     * Note: Update file paths as needed for your environment.
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Example usage - update these paths as needed
        String file1 = "sample1.xml";
        String file2 = "sample2.xml";
        
        System.out.println("File comparison result:");
        System.out.println(compareTwoFiles(file1, file2));
        
        // Uncomment and update path as needed for file analysis
        // System.out.println("\nAnalyzing text file:");
        // analyzeTextFile("sample.xml");
    }
}
