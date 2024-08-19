import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestTP
{
	private static final LinkedBlockingQueue queue = new LinkedBlockingQueue(10);
	static ThreadPoolExecutor poolEx = new ThreadPoolExecutor(5,5,100,TimeUnit.SECONDS,queue);
	
	private class DownloadTask implements Runnable
	{
		private String name; 
		DownloadTask(String name)
		{
			this.name =name;
		}

		public void run()
		{
			for(int i=0;i<10;i++)
			{
				System.out.println("Thread Name "+this.name+ " :: iteration "+i);
				try
				{
					Thread.sleep(1000);
				}
				catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}		
	}
	
	 private class SimulateBrowser extends Thread
	 {
		 private String name; 
		 SimulateBrowser(String name)
		 {
			this.name =name;
		 }
		 	
		 public void run()
		 {
			 DownloadTask task = new DownloadTask("Task "+this.name);
			 try
			 {
			 	poolEx.submit(task);
			 }
			 catch(RejectedExecutionException e)
			 {
				 System.out.println(" ************* Rejected Task "+this.name +":: queue size"+queue.size());				
			 }
		 }
	 }
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		for(int i=0;i<25;i++)
		{
			SimulateBrowser browser = (new TestTP()).new SimulateBrowser(i+"");
			browser.start();
		}

	}

}
