import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class CalculateNoOfTickets
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		HashMap map = new HashMap();
		try
		{
			BufferedReader reader = new BufferedReader( new FileReader("Sub-Cats.txt"));
			String line = null;
			int total = 0;
			int count = 0;
			while((line=reader.readLine())!=null)
			{
				if(map.get(line)!=null)
				{
					int a = Integer.parseInt((String)map.get(line));
					map.put(line,(a+1)+"");
				}
				else
				{
					map.put(line, 1+"");
				}
				count++;
			}
			
			System.out.println("Count is "+count);
			System.out.println("Total is "+map);
			
			
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
