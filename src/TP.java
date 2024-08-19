import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class TP extends Thread
{
	private static final int CAPACITY=5;    
    //Queue to store all requests based on. At any point there will 10 in the Queue.
    //An extra 5 tasks will be in Processing.
    private static final BlockingQueue queue = new LinkedBlockingQueue(CAPACITY);
	
	private static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 2, 100, TimeUnit.SECONDS, queue,new PublibRejectionHandler());
	
	String name = null;
	
	public TP(String name)
	{
		this.name = name;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		for(int i=0;i<10;i++)
		{
			TP a = new TP("Task "+i);
			a.start();
		}
		System.out.println("================All jobs created=============");
	}


	public void run()
	{
			GetFileTask task = new GetFileTask(""+this.name);
			//Future futureTask = poolExecutor.submit(task,this.name);
			poolExecutor.execute(task);
			/*
			try
			{
				Object obj = futureTask.get();
				System.out.println("obj :: "+this.name+" :: "+obj );
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (ExecutionException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		*/
		
		
	}
	
	private static class PublibRejectionHandler implements RejectedExecutionHandler
	{
		public void rejectedExecution(Runnable task, ThreadPoolExecutor executor)
		{
			
			System.out.println("****************** Task rejected **************");
			
			GetFileTask fileTask = (GetFileTask)task;
			System.out.println("Rejected :: Thread Name :: "+fileTask.name);
			System.out.println("queue size is :: "+executor.getQueue().size());
			/*
			Future futureTask = (Future)task;
			System.out.println(" cancelled "+futureTask.isCancelled());
			System.out.println(" isDone "+futureTask.isDone());
			System.out.println(" isDone "+futureTask.toString());
			
			
			try
			{
				System.out.println("Above");
				System.out.println("Rejected :: Thread Name :: "+futureTask.get().toString());
				System.out.println("Below");
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (ExecutionException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
		}
		
		
	}

}
