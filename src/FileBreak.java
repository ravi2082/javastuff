import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class FileBreak
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			
			BufferedReader reader = new BufferedReader(new FileReader("C:\\OSBInvNumWithTabs.txt"));
			BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\DC10Extract\\OSB1.txt"));
			int fileCount=2;
			int lineCount=0;
			String str = null;
			while((str=reader.readLine())!=null)
			{
				
				lineCount++;
				if(lineCount > 10000)
				{
					writer.close();
					writer =  new BufferedWriter(new FileWriter("D:\\DC10Extract\\OSB"+fileCount+".txt"));
					fileCount++;
					lineCount=1;
				} 
				writer.write(str+"\n");
				writer.flush();
			}
			if(writer !=null)
			{
				writer.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}
