
public class GetFileTask implements Runnable
{
	Exception exception = null;
	String name = null;
	long startTime;
	/**
	 * @param args
	 */
	
	public GetFileTask(String name)
	{
		this.name = name;
		this.exception = null;
		startTime = System.currentTimeMillis();
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

	public void run()
	{
		try
		{
			System.out.println(this.name+  " :: Wait time :: "+ ((System.currentTimeMillis()-startTime))/1000);			
			for(int i=0;i<10;i++)
			{
				System.out.println("Thread :: "+this.name+ " :: iteration :: "+i);
				Thread.sleep((long) (Math.random()*10000));
			}				
		}
		catch(Exception e)
		{
			this.exception = e;
		}
		finally
		{
			System.out.println(this.name+  " :: Task time :: "+ ((System.currentTimeMillis()-startTime))/1000);
		}
		
		
		
	}

}
