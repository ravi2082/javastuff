import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedList;

public class RemoveFiles
{

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{
		String fileSeparator = System.getProperty("file.separator");
		String fileDir = "D:\\speedi redown\\Transformers";
		String fileDir1 = "D:\\speedi redown\\extra";
		String fileName = "TransformersNov10.txt";
		
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		BufferedWriter writer = new BufferedWriter(new FileWriter("TransformersNov10-Final.txt"));
		LinkedList lineList = new LinkedList();
		String line="";
		ArrayList list = new ArrayList();
		int j =0;
		while((line=reader.readLine())!=null)
		{
			j++;
			String arr[] = line.split("\t");
			boolean found = false;
			System.out.println(arr[8]);
			String pdfFile = arr[8].split("/")[1];
			File pdf = new File("D:\\speedi redown\\Transformers\\"+pdfFile);
			if(!pdf.exists()) 
			{
				
				String gcatArr[] = arr[11].split("/");
				for(int i=0;i<gcatArr.length;i++)
				{
					pdf = new File("C:\\speedi redown\\Transformers\\"+gcatArr[i].trim().toLowerCase()+".pdf");
					if(pdf.exists())
					{
						//System.out.println(j+" :: pdf file is named after GCAT "+pdfFile+ " :: "+pdf.getName());
						found = true;
						break;
					}
					
				}
				
			}
			else
			{
				found = true;
			}
			if(!found)
			{
				System.out.println("No file found for : "+pdfFile );
			}
			if(found && list.contains(pdf.getName()))
			{
				System.out.println("No file found for : "+pdfFile );
				//System.out.println(j+ "  :: "+pdfFile+" is duplicate" );				
			}
			else if(found && !list.contains(pdf.getName()))
			{
				list.add(pdf.getName());
				lineList.add(line);
			}
	
		}
		
		File dir = new File(fileDir);
		
		File[] fileArr = dir.listFiles();
		int k=0;
		for(int i=0;i<fileArr.length;i++)
		{
			if(!list.contains(fileArr[i].getName()))
			{
				System.out.println(fileArr[i].getName() + " moved");
				fileArr[i].renameTo(new File(fileDir1+fileSeparator+fileArr[i].getName()));
			}
			else
			{
				k++;
			}
		}
		System.out.println("total valid folder files "+k);
		
		System.out.println(list.size());
		System.out.println(lineList.size());
		
		for(int i=0;i<lineList.size();i++)
		{
			writer.write(lineList.get(i)+"\n");
			
		}
		writer.flush();
		writer.close();
	}

}
