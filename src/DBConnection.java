import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		 try
		 {
		 Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		 con=DriverManager.getConnection("jdbc:odbc:ravi");
		 st=con.createStatement();
		 rs=st.executeQuery("select * from gets");
		 System.out.println(" Emp Number");
		 while(rs.next())
		 {
		 System.out.println(" example of getting ");
//		 out.println("<br>" + rs.getString("id") + " " + rs.getString("empname"));
		 //System.out.println("<br>" + rs.getInt(1) + " " + rs.getString(2));
		 }

		 rs.close();
		 con.close();
		 st.close();

		 } catch(Exception e) { System.out.println(" Errors are "+ e);}


	}

}
