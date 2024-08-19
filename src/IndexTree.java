import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import matrix.db.BusinessObject;
import matrix.db.Context;
import matrix.db.ExpansionWithSelect;
import matrix.db.RelationshipWithSelect;
import matrix.db.RelationshipWithSelectItr;
import matrix.db.RelationshipWithSelectList;
import matrix.util.MatrixException;
import matrix.util.StringList;

public class IndexTree
{

    public static StringList objectSelectList = new StringList();
    public static List excludeTypeList =  new ArrayList();
    
    static
    {    	
    	objectSelectList.addElement("id");
		objectSelectList.addElement("type");	
		objectSelectList.addElement("name");
		objectSelectList.addElement("revision");

		excludeTypeList.add("Product Family (A-Z)");
		excludeTypeList.add("Product Type");
		excludeTypeList.add("Product Sub-Type");
		excludeTypeList.add("General Catalog");
		excludeTypeList.add("Change Request");
    }
	public static void main(String args[]) throws Exception
	{
		IndexTree indexFamilyTree = new IndexTree();
        Context context = getContext("mwp2dpkg.appl.ge.com:4847", "creator", "tmplvl10");
        //String input[] = {"43482.46832.25247.31169", "MktUIData"};// Prd type microprocessors
        String input[] = {"43482.46832.2896.36612", "MktUIData"};//Family Drives
        System.out.println("Obtained context");
        long startTime = System.currentTimeMillis();
        indexFamilyTree.connectFamilyTree(context, input);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken to complete the program is "+(endTime-startTime)/1000+" secs");
	}
	
    private static Context getContext(String rmiconnectstring, String userName, String password) throws MatrixException
    {
        Context context = new matrix.db.Context("bos",rmiconnectstring);
        context.setUser(userName);
        context.setPassword(password);
        return context;
    }

	public int connectFamilyTree(Context context, String[] args) throws Exception 
	{
   		String objectID =  args[0];
   		String collectionName = args[1];
   		String type = "";
   		String name = "";
   		String id = "";
   		String revision = "";
   		HashSet pubSet = new HashSet();
   		
   		String relWhereExpression = "attribute[Category Key]=='Marketing UI' || attribute[Category Key]=='OTIS Library' || name=='PartReferencing' ";
   		String busWhereExpression = "(type!='Change Request') && (current=='Released' || current=='Active')";
   		BusinessObject busObject = new BusinessObject(objectID);
   		
   		ExpansionWithSelect expandSelect = busObject.expandSelect(context, "Category,Categorize,PartReferencing", "*", objectSelectList, new StringList(), false, true, (short) 0, busWhereExpression, relWhereExpression, false);
   		
   		RelationshipWithSelectList relSelectList = expandSelect.getRelationships();
   		RelationshipWithSelectItr relItr = new RelationshipWithSelectItr(relSelectList);
   		

   		if(relSelectList.size()==0)
   		{
   			return 0;
   		}
   		
   		while( relItr.next() )
        {
            RelationshipWithSelect relSelect = relItr.obj();
            type = relSelect.getTargetSelectData("type");
            name = relSelect.getTargetSelectData("name");
            id = relSelect.getTargetSelectData("id");
            revision = relSelect.getTargetSelectData("revision");
            if((!excludeTypeList.contains(type)))
        	{
            	pubSet.add(type+"\t"+name+"\t"+revision);
        		//System.out.println(type+"\t"+name+"\t"+revision);
        	}
        }
   		System.out.println(pubSet.size());
   		Iterator it = pubSet.iterator();
   		while(it.hasNext())
   		{
   			System.out.println(it.next());
   		}
   		
   		return 0;
	}
}