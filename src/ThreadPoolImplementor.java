import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

public class ThreadPoolImplementor extends ThreadPoolExecutor
{
    //Core pool size
    private static int poolSize = 2;
    //Max pool size
    private static int maxPoolSize = 2;
    //Keep Alive time of a running thread if its idle. 
    private static long keepAliveTime = 100;    
    //Capacity of the Queue 
    private static final int CAPACITY=5;    
    //Queue to store all requests. At any point there will be CAPACITY tasks in the Queue.
    //Additional tasks will be in processing.
    private static final BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(CAPACITY);
    
    private final ThreadLocal<Long> startTime = new ThreadLocal<Long>();
    private final AtomicLong numTasks = new AtomicLong();
    private final AtomicLong totalTime = new AtomicLong();
    //Logger
    private final Logger log = Logger.getLogger(ThreadPoolImplementor.class.getName());
    
    private static ThreadPoolImplementor instance = null;
    private static int threads = 0;
    /**
     * Private Constructor
     */
    private ThreadPoolImplementor()
    {    super(poolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS,queue);
    }
    
    /**
     * To get a singleton instance of the class
     * @return
     */
    public static ThreadPoolImplementor getInstance()
    {    return (instance==null)?(instance=new ThreadPoolImplementor()):instance;
    }
    
    /* (non-Javadoc)
     * @see java.util.concurrent.ThreadPoolExecutor#beforeExecute(java.lang.Thread, java.lang.Runnable)
     * Executed before a task is executed. Can be used for logging
     */
    protected void beforeExecute(Thread t, Runnable r)    
    {    System.out.println("Before Execute :: Queue size " + queue.size() );
        log.info(String.format("No. of Active Threads Before Execution...."+this.getActiveCount()));
        log.info(String.format("Thread %s: start %s", t, r));
        startTime.set(System.nanoTime());
        //Java API says to call the super class method at the end of the overridden method
        super.beforeExecute(t, r);
    }
    
    /**
     * This method takes an object of Callable. 
     * The task is submitted to the Queue and executed by the ThreadPoolExecutor
     * If the Queue is full it throws a RejectedExecutionException
     * The call method of the Callable implementation is executed by the Executor
     * @param task the task to be executed
     * @return Future representing the task result, or null if rejected
     */    
    public <T> Future<T> submit(Callable<T> task) {
        try {
            return super.submit(task);
        } catch (RejectedExecutionException e) {
            System.out.println("Rejected execution of a task since Queue is Full :: Queue Size :: " + 
                             queue.size() + " PoolSize :: " + this.getPoolSize());
            //e.printStackTrace();            
        }
        return null;
    }
    
    /* (non-Javadoc)
     * @see java.util.concurrent.ThreadPoolExecutor#afterExecute(java.lang.Runnable, java.lang.Throwable)
     * After execution of a task. Can be used for logging.
     */
    protected void afterExecute(Runnable r, Throwable t)
    {    //Java API says to call the super class method at the top in overridden methods
        super.afterExecute(r, t);        
        long endTime = System.nanoTime();
        long taskTime = endTime - startTime.get();
        numTasks.incrementAndGet();
        totalTime.addAndGet(taskTime);
       
        try
        {    log.info(String.format("Thread %s: err %s, time=%dns",((Future)r).get().toString(), t, taskTime));            
        }
        catch (InterruptedException e)
        {    e.printStackTrace();
        }
        catch (ExecutionException e)
        {    e.printStackTrace();
        }
        log.info(String.format("Total tasks Executed so far...."+numTasks));
        log.info(String.format("No. of Active Threads After Execution...."+this.getActiveCount()));
    }
    

    /* (non-Javadoc)
     * @see java.util.concurrent.ExecutorService#shutdownNow()
     */
    public List<Runnable> shutdownNow()
    {
        return super.shutdownNow();
    }
    
    /**
     * A sample class implementing Callable for tasks to be executed
     */
    private class MyCallable implements Callable<String> {    
        private String name = null;
        
        /**
         * Executes the callable task.
         * @return the name of the thread that executed the task
         * @throws Exception if an error occurs during execution
         */
        @Override
        public String call() throws Exception {
            //Write business logic. Call other implementations
            if (name != null) Thread.currentThread().setName(this.name);
            log.info(Thread.currentThread().getName() + " called.");
            
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " :: iteration :: " + i);
                Thread.sleep(1000);
            }
            return Thread.currentThread().getName();
        }
        
        /**
         * Sets the name for this callable task.
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }
    }
    
    
    /**
     * SpawnThreads is used to create a thread to hit the implementor service concurrently
     *
     */
    private class SpawnThreads extends Thread
    {
        private String name = null;
        SpawnThreads(String name)
        {    this.name=name;
        }
    
        /* (non-Javadoc)
         * @see java.lang.Runnable#run()
         * This class implements Runnable to only simulate an environment
         * where a many threads concurrently hit a service
         */
        public void run() {
            ThreadPoolImplementor executor = ThreadPoolImplementor.getInstance();
            MyCallable task = executor.new MyCallable();
            if (name != null) task.setName(name);
            Future<String> ftask = executor.submit(task);
            try {
                if (ftask != null) {
                    System.out.println(ftask.get() + " completed");                
                } else {
                    System.out.println(Thread.currentThread().getName() + " ---- rejected");                
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) throws Exception
    {
        if(args.length!=1)
        {
            System.out.println("USAGE: java ThreadPoolImplementor IntThreadstoCreate");
            return;
        }
        System.out.println("At any point the max processing threads size is "+maxPoolSize);
        System.out.println("At any point the max Queue size is "+CAPACITY);
        threads=Integer.parseInt(args[0]);        
        ThreadPoolImplementor executor = ThreadPoolImplementor.getInstance();
        //Spawn Thread to simulate a an env to hit a service concurrently
        for(int i=0;i<threads;i++)            
        {    SpawnThreads st = executor.new SpawnThreads("Task "+i);
            st.start();                
        }        
    }
}
