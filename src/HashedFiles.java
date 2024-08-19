import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import matrix.db.Context;
import matrix.util.StringList;

import com.matrixone.apps.domain.DomainObject;


public class HashedFiles 
{
	private static final String VAULT_ADMINISTRATION = "eService Administration";
	//private static STRAPPLICATION = "";
	private static String str = "";
	private static final String REVISION = "1";
	private static final String TYPE ="CDS_Typ_ElectDist_BOAFileHolder";
	private static final String VAULT ="eService Administration";
	//private static final String TYPE ="CDS_Typ_ElectDist_BOAFileHolder";
	//private static final String VAULT = "CDS_Vlt_ElecDist_BOA";
    private static final String FORMATFILES = "format.file.capturedfile";

    public void getHashedNames(Context context,String str,FileWriter fw,StringList select)
	{
		try
		{	
			System.out.println("Hi ++++++++++++++++++");
			matrix.db.BusinessObject bus1 = new matrix.db.BusinessObject(TYPE,str,REVISION,VAULT);
            System.out.println("Hi 1111111111111111111");

			System.out.println("TYPE is"+TYPE+"<----");
            System.out.println("Vault is"+VAULT+"<----");
			System.out.println("FileHolderId is"+str+"<----");
			System.out.println("Revison is"+REVISION+"<----");

			bus1.open(context);
			System.out.println("Hi 2222222222222222222");
			fw.write("FileHolderid is -->"+str+"\n\n");
			DomainObject domObj1 = new DomainObject(bus1);// Domain Object created
			System.out.println("Hi 3333333333333333333");

			StringList bus1List = domObj1.getInfoList(context,"format.file.capturedfile");
			System.out.println("Hi 4444444444444444444");
			// Getting select info from dom object in a map

			for(int i=0;i<bus1List.size();i++)
			{
			fw.write(bus1List.elementAt(i)+"\n");
			fw.flush();
			}
			fw.write("\n");
			fw.flush();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
	}

	public static void main(String[] args) 
	{
		try
		{
		//STRAPPLICATION = args[0]; 
		//Connecting to Matrix Context
		Context context = new Context(":bos","//cds2ppkg.appl.ge.com:4961");
		context.resetContext("User Agent", "shadowsecret", VAULT_ADMINISTRATION);
		context.connect();
		HashedFiles hf = new HashedFiles();
		java.io.File f=new java.io.File("EDFileHolders.txt");
		FileReader fr=new FileReader(f);
		BufferedReader br=new BufferedReader(fr);
		StringList select = new StringList();// Defining a stringlist which includes the select parameters 
		select.add(0, FORMATFILES);// Filenames and filesizes select parameters added to stringlist select
        //String str=null;
		java.io.FileWriter fw = new java.io.FileWriter("EDFileHoldersHashedNames.txt");
        while((str = br.readLine()) != null)
		{
			System.out.println(" FileHolder Id is ............. "+str);
			hf.getHashedNames(context,str,fw,select);
		}
		System.exit(0);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
	}
}
