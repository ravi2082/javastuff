import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

import com.matrixone.framework.util.eMatrixDateFormat;

public class FileParse
{

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception
	{
		String str = "";
		String[] arr = null;
		String familyName = null;
		String productTypeName = null;
		String pubType = null;
		String pubTitle = null;
		String pubNo = null;
		String pubDesc = null;
		String pubPrice = null;
		String productSubTypeName = null;
		String pubCreationDate = null;
		String pubSensitivity = null;
		String pubCreator = null;
		String Source = null;
		String SourceContactInfo = null;
		HashMap docAttributes = null;
		HashMap relAttributes = null;
		String gCatName = null;
		boolean isGCatSupplied = false;
		BufferedReader in = new BufferedReader(new FileReader("pubLoad.txt"));
		BufferedWriter out = new BufferedWriter(new FileWriter("pubLoadTest122.txt"));
		while((str=in.readLine())!=null)
		{
		
			if (str != null)
			{
				arr = str.split("\t");
				// assign the values

				familyName = arr[0];
				productTypeName = arr[1];
				productSubTypeName = arr[2];
				gCatName = arr[3];

				pubType = arr[4].trim(); // Publication Type

				pubTitle = arr[6];// description
				pubNo = arr[13]; // object name
				pubDesc = arr[14]; // extended description

				
				// Relationship attributes
				pubPrice = arr[5];
				//pubCreationDate = getMatrixDate(arr[6]);
				pubCreationDate = arr[7];
				pubSensitivity = arr[9];
				pubCreator = arr[10];
				Source = arr[11];
				SourceContactInfo = arr[12];
				
				out.write(familyName+"\t"+productTypeName+"\t"+productSubTypeName+"\t"+gCatName+"\t"+pubType+"\t"+pubPrice+"\t"+pubTitle+"\t"+pubCreationDate+"\t"+arr[8]+"\t"+pubSensitivity+"\t"+pubCreator+"\t"+Source+"\t"+SourceContactInfo+"\t"+pubNo+"\t"+pubDesc+"\n");
				out.flush();


			}
		}
		in.close();
		out.close();
	}

}
