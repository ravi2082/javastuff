import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Utility class for database connections and operations.
 * Note: This class uses deprecated JDBC-ODBC bridge and should be updated
 * to use modern JDBC drivers for production use.
 */
public class DBConnection {

    // Configuration constants - these should be externalized to properties file
    private static final String DRIVER_CLASS = "sun.jdbc.odbc.JdbcOdbcDriver";
    private static final String CONNECTION_URL = "jdbc:odbc:ravi";
    
    /**
     * Main method to demonstrate database connection and query execution.
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            // Load the JDBC driver (deprecated - consider updating to modern driver)
            Class.forName(DRIVER_CLASS);
            
            // Establish connection
            connection = DriverManager.getConnection(CONNECTION_URL);
            statement = connection.createStatement();
            
            // Execute query
            resultSet = statement.executeQuery("SELECT * FROM gets");
            
            System.out.println("Employee Information:");
            System.out.println("====================");
            
            while (resultSet.next()) {
                // Process results - commented out specific column access
                // as table structure is unknown
                System.out.println("Processing employee record...");
                // Example: System.out.println("ID: " + resultSet.getString("id") + 
                //                             ", Name: " + resultSet.getString("empname"));
            }
            
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
            System.err.println("Consider updating to a modern JDBC driver");
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Clean up resources in reverse order
            closeQuietly(resultSet);
            closeQuietly(statement);
            closeQuietly(connection);
        }
    }

    /**
     * Safely closes a ResultSet without throwing exceptions.
     * @param resultSet the ResultSet to close
     */
    private static void closeQuietly(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.err.println("Error closing ResultSet: " + e.getMessage());
            }
        }
    }

    /**
     * Safely closes a Statement without throwing exceptions.
     * @param statement the Statement to close
     */
    private static void closeQuietly(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.err.println("Error closing Statement: " + e.getMessage());
            }
        }
    }

    /**
     * Safely closes a Connection without throwing exceptions.
     * @param connection the Connection to close
     */
    private static void closeQuietly(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing Connection: " + e.getMessage());
            }
        }
    }
}
