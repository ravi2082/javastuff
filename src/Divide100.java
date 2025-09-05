import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Utility class to find unique combinations of numbers from a given range
 * that sum to 100 using exactly 11 numbers.
 */
public class Divide100 {
    
    private static final int[] RANGE = {6, 7, 8, 9, 10, 11, 12, 13, 14};
    private static final int TARGET_SUM = 100;
    private static final int NUMBERS_COUNT = 11;
    private static final int MAX_ITERATIONS = 10_000_000;

    /**
     * Main method to find unique combinations that sum to 100.
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Set<String> uniqueCombinations = new HashSet<>();
        int validCombinations = 0;
        
        for (int iteration = 0; iteration < MAX_ITERATIONS; iteration++) {
            List<Integer> combination = new ArrayList<>();
            int sum = 0;
            
            // Generate a combination of 11 numbers
            for (int i = 0; i < NUMBERS_COUNT; i++) {
                int randomIndex = ThreadLocalRandom.current().nextInt(RANGE.length);
                int selectedNumber = RANGE[randomIndex];
                sum += selectedNumber;
                combination.add(selectedNumber);
            }
            
            if (sum == TARGET_SUM) {
                String key = buildKey(combination);
                if (uniqueCombinations.add(key)) {
                    validCombinations++;
                }
            }
        }
        
        System.out.println("Total unique combinations found: " + uniqueCombinations.size());
        System.out.println("Total valid combinations generated: " + validCombinations);
    }

    /**
     * Builds a sorted key string from the combination for uniqueness checking.
     * @param combination the list of numbers in the combination
     * @return a string key representing the sorted combination
     */
    private static String buildKey(List<Integer> combination) {
        if (combination.size() != NUMBERS_COUNT) {
            throw new IllegalArgumentException("Combination must contain exactly " + NUMBERS_COUNT + " numbers");
        }
        
        List<Integer> sortedCombination = new ArrayList<>(combination);
        Collections.sort(sortedCombination);
        
        StringBuilder keyBuilder = new StringBuilder();
        for (Integer number : sortedCombination) {
            keyBuilder.append("~").append(number);
        }
        
        return keyBuilder.toString();
    }
}
