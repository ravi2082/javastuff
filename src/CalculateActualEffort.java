import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to calculate actual effort from time tracking data.
 * Reads data from "DaysMonMin.txt" file with format: days\thours\tminutes
 */
public class CalculateActualEffort {

    private static final String INPUT_FILE = "DaysMonMin.txt";
    private static final int HOURS_PER_DAY = 24;
    private static final int WORK_HOURS_PER_DAY = 8; // Assuming 8 hour work day
    private static final int OFF_HOURS_PER_DAY = 16; // Non-work hours per day
    private static final int MINUTES_PER_HOUR = 60;

    /**
     * Main method to calculate actual effort statistics.
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        List<TimeEntry> timeEntries = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE))) {
            String line;
            int totalHours = 0;
            int entryCount = 0;
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                
                if (parts.length != 3) {
                    System.err.println("Skipping malformed line: " + line);
                    continue;
                }
                
                try {
                    int days = Integer.parseInt(parts[0]);
                    int hours = Integer.parseInt(parts[1]);
                    int minutes = Integer.parseInt(parts[2]);
                    
                    TimeEntry entry = new TimeEntry(days, hours, minutes);
                    timeEntries.add(entry);
                    
                    int entryTotalHours = calculateTotalHours(entry);
                    totalHours += entryTotalHours;
                    entryCount++;
                    
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number format in line: " + line);
                }
            }
            
            printStatistics(entryCount, totalHours);
            
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Calculates total hours for a time entry.
     * @param entry the time entry to calculate hours for
     * @return total hours for the entry
     */
    private static int calculateTotalHours(TimeEntry entry) {
        int totalHours = 0;
        
        // Convert days to working hours (excluding off-hours)
        if (entry.getDays() > 0) {
            int totalDayHours = entry.getDays() * HOURS_PER_DAY;
            int offHours = OFF_HOURS_PER_DAY * entry.getDays();
            totalHours += (totalDayHours - offHours);
        }
        
        // Add hours
        if (entry.getHours() > 0) {
            totalHours += entry.getHours();
        }
        
        // Convert minutes to hours (note: original code had a bug checking hours instead of minutes)
        if (entry.getMinutes() > 0) {
            totalHours += (entry.getMinutes() / MINUTES_PER_HOUR);
        }
        
        return totalHours;
    }

    /**
     * Prints effort statistics.
     * @param count number of entries processed
     * @param totalHours total hours calculated
     */
    private static void printStatistics(int count, int totalHours) {
        if (count == 0) {
            System.out.println("No valid entries found.");
            return;
        }
        
        double avgHours = (double) totalHours / count;
        double avgDays = avgHours / WORK_HOURS_PER_DAY;
        
        System.out.println("Count: " + count);
        System.out.println("Total hours: " + totalHours);
        System.out.printf("Average hours: %.2f%n", avgHours);
        System.out.printf("Average work days: %.2f%n", avgDays);
    }

    /**
     * Inner class to represent a time entry.
     */
    private static class TimeEntry {
        private final int days;
        private final int hours;
        private final int minutes;

        public TimeEntry(int days, int hours, int minutes) {
            this.days = days;
            this.hours = hours;
            this.minutes = minutes;
        }

        public int getDays() { return days; }
        public int getHours() { return hours; }
        public int getMinutes() { return minutes; }
    }
}
