import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ParseFile
{
	static ArrayList idList = new ArrayList();

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{
		idList.add("38652.30055.3572.10694");
		idList.add("38652.30055.30504.52937");
		idList.add("38652.30055.30136.15826");
		idList.add("38652.30055.3623.54712");
		idList.add("38652.30055.27019.16131");
		idList.add("38652.30055.31324.2099");
		idList.add("38652.30055.9117.9861");
		idList.add("38652.30055.27031.6005");
		idList.add("38652.30055.31368.1253");
		idList.add("38652.30055.9168.6026");
		idList.add("38652.30055.28802.39205");
		idList.add("38652.30055.6475.39174");
		idList.add("38652.30055.23444.46040");
		idList.add("38652.30055.28874.61403");
		idList.add("38652.30055.7661.44218");
		idList.add("38652.30055.23522.28599");
		idList.add("38652.30055.29033.53170");
		idList.add("38652.30055.29086.59968");
		idList.add("38652.30055.24929.18084");
		idList.add("38652.30055.24986.12804");
		idList.add("38652.30055.3572.10694");
		idList.add("38652.30055.30504.52937");
		idList.add("38652.30055.30136.15826");
		idList.add("38652.30055.3623.54712");
		idList.add("38652.30055.27019.16131");
		idList.add("38652.30055.31324.2099");
		idList.add("38652.30055.9117.9861");
		idList.add("38652.30055.27031.6005");
		idList.add("38652.30055.31368.1253");
		idList.add("38652.30055.9168.6026");
		idList.add("38652.30055.28802.39205");
		idList.add("38652.30055.6475.39174");
		idList.add("38652.30055.23444.46040");
		idList.add("38652.30055.28874.61403");
		idList.add("38652.30055.7661.44218");
		idList.add("38652.30055.23522.28599");
		idList.add("38652.30055.29033.53170");
		idList.add("38652.30055.29086.59968");
		idList.add("38652.30055.24929.18084");
		idList.add("38652.30055.24986.12804");
		
		BufferedReader reader = new BufferedReader(new FileReader("disconnect.mql"));
		String line = "";
		
		while((line=reader.readLine())!=null)
		{
			for(int i=0;i<idList.size();i++)
			{
				String id = (String) idList.get(i);
				if(line.indexOf(id)>0) System.out.println(line);
					
			}
		}
			

	}

}
