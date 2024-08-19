import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import matrix.util.StringList;

public class TestSplit {

	/**
	 * @param args
	 */
	private static String getString(HashMap relMap, String delimiter, String internalDelimiter)
	{
		StringBuffer strBuffer = new StringBuffer();
		String str = "";
		Set set = relMap.keySet();
		Iterator itr = set.iterator();
		String key = "";
		while (itr.hasNext())
		{
			key = (String) itr.next();
			strBuffer.append(key + internalDelimiter + (String) relMap.get(key) + delimiter);
		}
		str = strBuffer.toString();
		str = str.substring(0, str.length() - 1);
		return str;
	}
	public static HashMap getHashMap(String relAttributesMapStr, String delimiter,
			String internalDelimiter)
	{
		HashMap map = new HashMap();
		String[] strArray = relAttributesMapStr.split(delimiter,-1);
		String[] valArray = new String[2];
		for (int i = 0; i < strArray.length; i++)
		{
			
			valArray = strArray[i].split(internalDelimiter,-1);
			System.out.print(valArray[0]);
			System.out.print("  "+valArray[1]);
			System.out.println();
			map.put(valArray[0], valArray[1]);
		}

		return map;
	}
	public static StringList getStringList(String str, String delimiter)
	{
		StringList strList = new StringList();
		String[] strArray = str.split(delimiter,-1);
		for (int i = 0, sz = strArray.length; i < sz; i++)
		{
			strList.add(strArray[i]);
		}
		return strList;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap relMap = new HashMap();
		relMap.put("date","20");
		relMap.put("date1","");
		relMap.put("key","some");
		String str = getString(relMap,"~","=");
		System.out.println("relmap str here is "+str);
		System.out.println("relmap from str is "+getHashMap(str,"~","="));
		
		//System.out.println("Vector "+getStringList("ravi,sd",","));
	}

}
