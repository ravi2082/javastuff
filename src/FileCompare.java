import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Set;
import java.util.TreeSet;

public class FileCompare
{

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception
	{
		BufferedReader cdsReader = new BufferedReader(new FileReader("uniquelogins-2012-cds-found.txt"));
		BufferedReader osbReader = new BufferedReader(new FileReader("uniquelogins-2012-mwp-found.txt"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("uniqueloginsbetwMWP-CDS-2012.txt"));
		String osbStr = ""; 
		String cdsStr = "";
		Set s = new TreeSet();
		while((cdsStr=cdsReader.readLine())!=null)
		{
			//String[] cdsArr = cdsStr.split("\t");
			s.add(cdsStr);			
		}
		int cdsSize = s.size();
		System.out.println("Initial CDS Data Size is "+cdsSize);
		int i =0;
		while((osbStr=osbReader.readLine())!=null)
		{
			//System.out.println(osbStr);
			//String[] osbArr = osbStr.split("\t");
			//String osbInvNum = osbArr[2];
			if(!s.contains(osbStr))
			{
				System.out.println(osbStr);
				i++;
				
				writer.write(osbStr+"\n");
				writer.flush();
			}			
		}
		System.out.println(i);
		//System.out.println("Final CDS Data Size "+cdsSize);
		writer.close();
		cdsReader.close();
		osbReader.close();
	}

}
