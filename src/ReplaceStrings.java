import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

public class ReplaceStrings
{

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{
		HashMap map = new HashMap();
		map.put("9t83b3378.pdf","9t83b3879.pdf");
		map.put("9t83b3388.pdf","9t83b3879g15.pdf");
		map.put("9t83b3508.pdf","9t83b3879g80.pdf");
		map.put("9t83b3518.pdf","9t83b3881.pdf");
		map.put("9t83b3528.pdf","9t83b3881g03.pdf");
		map.put("9t83b3538.pdf","9t83b3881g15.pdf");
		map.put("9t83b3548.pdf","9t83b3881g80.pdf");
		map.put("9t83b3548g03.pdf","9t83b3882.pdf");
		map.put("9t83b3558.pdf","9t83b3882g03.pdf");
		map.put("9t83b3568.pdf","9t83b3882g13.pdf");
		map.put("9t83b3588.pdf","9t83b3882g15.pdf");
		map.put("9t83b3608.pdf","9t83b3882g62.pdf");
		map.put("9t83b3638.pdf","9t83b3882g80.pdf");
		map.put("9t83b3848.pdf","9t83b3883.pdf");
		map.put("9t83b3848g03.pdf","9t83b3883g13.pdf");
		map.put("9t83b3858.pdf","9t83b3883g15.pdf");
		map.put("9t83b3858g03.pdf","9t83b3883g80.pdf");
		map.put("9t83b3878g62.pdf","9t83b3884.pdf");
		map.put("9t83b3898.pdf","9t83b3884g15.pdf");
		map.put("9t83b3567g13.pdf","9t83b3884g62.pdf");
		map.put("9t83b3607g15.pdf","9t83b3884g80.pdf");
		map.put("9t83b3847g15.pdf","9t83b3884g83.pdf");
		map.put("9t83b3857g13.pdf","9t83b3885.pdf");
		map.put("9t83b3887g15.pdf","9t83b3885g13.pdf");
		
		BufferedReader reader = new BufferedReader(new FileReader("TransformersOct21-Updated.txt"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("TransformersOct21-Updated1.txt"));
		String line="";
		int i =0;
		while((line=reader.readLine())!=null)
		{
			String[] arr = line.split("\t");
			String fileName = arr[9].split("/")[1];
			//System.out.println(fileName);
			if(fileName.equalsIgnoreCase("9t83b3378.pdf"))
			{
				System.out.println(map.get(fileName));
			}
			if(map.get(fileName)!=null)
			{
				i++;
				arr[9] = "Transformers/"+map.get(fileName);
			}
			for(int j=0;j<arr.length;j++)
			{
				if(j == arr.length-1)
				{
					//System.out.println(arr[j]);
					writer.write(arr[j]+"\n");
					writer.flush();
				}
				else
				{	
					//System.out.print(arr[j]+"\t");
					writer.write(arr[j]+"\t");
					writer.flush();
				}
			}
		}
		System.out.println("ravi "+i);

	}

}
