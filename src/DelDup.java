import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DelDup
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		String str = "";
		Set s = new HashSet();
		try
		{
			BufferedReader reader =  new BufferedReader(new FileReader("list1.txt"));
			BufferedWriter writer =  new BufferedWriter(new FileWriter("newlist1.txt"));
			while((str=reader.readLine())!=null)
			{
				if(!s.contains(str))
				{
					s.add(str);
				}
				else
				{
					System.out.println(str);
				}
			}
			System.out.println(s.size());
			reader.close();
			Iterator itr = s.iterator();
			while(itr.hasNext())
			{
				writer.write((String)itr.next()+"\n");
			}
			writer.close();			
			
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
