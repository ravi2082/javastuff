
/**
 * Demonstration class for dynamic class loading and interface usage.
 * Shows how to load a class at runtime and use it through an interface.
 */
public class TestDate {

    /**
     * Main method demonstrating dynamic class loading.
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            String className = "test1";
            
            // Load class dynamically at runtime
            Class<?> classObj = Class.forName(className);
            
            // Create instance and cast to interface
            TestInterface instance = (TestInterface) classObj.getDeclaredConstructor().newInstance();
            
            // Test the escape sequences method
            String testString = "ra'vi";
            String result = instance.putEscapeSequences(testString);
            
            System.out.println("Original string: " + testString);
            System.out.println("Escaped string: " + result);
            
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + e.getMessage());
            System.err.println("Make sure the class 'test1' exists and implements TestInterface");
        } catch (Exception e) {
            System.err.println("Error during dynamic class loading: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
