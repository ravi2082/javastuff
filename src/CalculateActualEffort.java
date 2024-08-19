import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CalculateActualEffort
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		ArrayList list = new ArrayList();
		try
		{
			BufferedReader reader = new BufferedReader( new FileReader("DaysMonMin.txt"));
			String line = null;
			int total = 0;
			int count = 0;
			while((line=reader.readLine())!=null)
			{
				String[] arr = line.split("\t");
				
				if(arr.length!=3)continue;
				int hrs1 = 0;
				if(!arr[0].equals("0"))
				{
					int noofDays = (Integer.parseInt(arr[0]));
					int noofHrs = noofDays * 24;
					int offHrs = 16 * noofDays;
					hrs1 = noofHrs - offHrs;					
					total = total + hrs1;
				}
				if(!arr[1].equals("0"))
				{
					total = total + Integer.parseInt(arr[1]);  
				}
				if(!arr[1].equals("0"))
				{
					total = total + (Integer.parseInt(arr[2])/60);
				}
				count++;
			}
			
			System.out.println("Count is "+count);
			System.out.println("Total is "+total);
			System.out.println("Avg in Hrs "+(total/count));
			System.out.println("Avg in Days "+(total/count)/8);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
