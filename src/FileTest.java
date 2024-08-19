import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileTest
{

	private static final CharSequence PGP = "PGP";


	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws Exception
	{
		/*
		 * 
		 
		File file1 = new File("C:\\DET-542.pdf.gpg");
		FileTest ftest = new FileTest();
		FileInputStream is = new FileInputStream("C:\\DET-542.pdf.gpg");
		byte[] bytes = new byte[1024];
		int byteRead;
		while((byteRead=is.read(bytes))>0)
		{
			
		}
		*/
		
		BufferedReader reader = new BufferedReader(new FileReader("InvoiceTest.txt"));
		String line="";
		
		while((line=reader.readLine())!=null)			
		{
			System.out.println(line.length());
			if(line.length()<3) continue; 
			if ((int) line.charAt(2) >= 65 && (int) line.charAt(2) <= 90)
			{
				System.out.println(line);
			}
		}
	}

	
	public void splitMergedFiles(File file1)
	{

		BufferedReader in = null;
		BufferedWriter out = null;
		
		String str = "";
		File temp = null;
		try
		{
			in = new BufferedReader(new InputStreamReader(new FileInputStream(file1.getAbsolutePath()),"ISO8859_1"));
			// start reading the original feed line by line
			System.out.println("Reading the feed");
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\out.pgp"),"ISO8859_1"));
			while((str = in.readLine())!=null)
			{
				
				out.write(str);		
				out.flush();	
			}			
			in.close();			
			out.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
		// Check
		// Now the new listing is available
		
	}
}
