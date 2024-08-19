import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class test
{
	
	/*	Compares the contents of the two specified files.
	 *  Returns the status of the file comparision.
	 */
	public static String compareTwoFiles(String firstFilePath,String secondFilePath) throws IOException
	{
		String status="equal";
		File firstFile = new File(firstFilePath);
		File secondFile = new File(secondFilePath);
		/*
		if(! firstFile.exists())
		{
			status="File "+firstFile.getAbsolutePath()+"does not exist";
			return status;
		}
		if(! secondFile.exists())
		{
			status="File "+secondFile.getAbsolutePath()+"does not exist";
			return status;
		}
		
		if(firstFile.length() != secondFile.length())
		{
			status="Files lengths are different."+firstFile.getName()+" length is "+firstFile.length()+" and "+secondFile.getName()+" length is "+secondFile.length();
			return status;
		}
		*/
		FileInputStream firstFileStream = new FileInputStream(firstFile);
		FileInputStream secondFileStream = new FileInputStream(secondFile);
		
		int bufOne;
		int bufTwo;		
		while ( ((bufOne=firstFileStream.read()) != -1) && ((bufTwo=secondFileStream.read()) != -1))
		{
			if( bufOne != bufTwo)
			{
				status="File "+firstFile.getAbsolutePath()+" has different content compared to the File "+secondFile.getAbsolutePath()+" : "+bufOne+" : "+bufTwo;
				return status;
			}
		}		
		firstFileStream.close();
		secondFileStream.close();
		return status;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{	
		BufferedReader reader = new BufferedReader(new FileReader("C:\\AtoZResponse-PRD.xml"));
		
		String str  = "";
		
		while((str=reader.readLine())!=null)
		{
			if(str!=null) System.out.println(str.length());
		
			System.out.println(str.charAt(458755));
		}
		
		
		System.out.println(compareTwoFiles("C:\\AtoZResponse-PRD.xml","C:\\AtoZResponse.xml"));
	}
	
	 


}
