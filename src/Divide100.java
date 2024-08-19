import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Divide100
{
	

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		int[] range = new int[]{6,7,8,9,10,11,12,13,14};
		ArrayList a = new ArrayList();
		ArrayList temp = new ArrayList();
		HashSet set = new HashSet();
		int sum = 0;
		int count = 0;
		String key = "";
		for(int it=0;it<10000000;it++)
		{
			temp = new ArrayList();
			loop:			
			for(int i=0;i<11;i++)
			{
				int index = (int)(Math.random()*10);
				
				if(!(index>=0 && index<range.length))
				{
					if(i!=0)i--;
					continue loop;
				}
				sum = sum + range[index];
				temp.add(range[index]);
				
			}
			if(sum==100)
			{
				
				buildKey(temp,set);
				count++;			
			}
			sum=0;
		}
		
		System.out.println(set.size());
		
	}

	private static void buildKey(ArrayList temp, HashSet set)
	{
		if(temp.size()!=11) return;
		String key = "";
		Collections.sort(temp);
		for(int i=0;i<temp.size();i++)
		{
			key = key + "~" + temp.get(i);
		}
		set.add(key);
		
	}

}
