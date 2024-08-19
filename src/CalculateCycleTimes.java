import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CalculateCycleTimes
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		ArrayList list = new ArrayList();
		try
		{
			BufferedReader reader = new BufferedReader( new FileReader("CreatedLastUpdated.txt"));
			String line = null;
			int total = 0;
			int count = 0;
			while((line=reader.readLine())!=null)
			{
				String[] arr = line.split("\t");
				
				if(arr.length!=2)continue;
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
				Date createdDate = sdf.parse(arr[0]);
				Date lastUpdated = sdf.parse(arr[1]);
				int difInDays = (int) ((lastUpdated.getTime() - createdDate.getTime())/(1000*60*60*24));
				System.out.println(difInDays);
				total = total + difInDays;
				count++;
			}
			
			System.out.println("Count is "+count);
			System.out.println("Total is "+total);
			System.out.println("Avg is "+(total/count));
			
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
