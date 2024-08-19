import ge.indsys.codas.RuntimeProcess;


public class TestDecrypt {

	public static boolean executeScript(String command)
	{
		Process process = null;
        Runtime runtime = Runtime.getRuntime();
        try
		{
			ProcessOut pout = new ProcessOut();
			ProcessOut perr = new ProcessOut();
			
			RuntimeProcess runtimeProcess = new RuntimeProcess(process=runtime.exec(command),
			            "Call Shell Script",pout,perr, null);
			  
			while(runtimeProcess.isProcessAlive())
			{   
			  	try 
			  	{
			        Thread.sleep((int)(Math.random() * 100));
			    } 
			  	catch (InterruptedException e) 
			  	{
			  	}
			}
		    //Modified by Ram on 1/13/2005
		    //--shell script completed with errors  decryption verbose is coming into error stream.
		    if(perr.getResult()!=null)
		    {
				System.out.println(":>> Following errors/warnings are reported by shell script "+perr.getResult());
		    }
			return true;
		}
		catch(Exception e)
		{
				  	 
			System.out.println(":>> Following Exception occured while executing shell script "+e);
		}
		return false;
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//sh /u01/CODAS/runtime/NCB/scripts/NCBDataTransferShellScript.sh /u01/CODAS/runtime/NCB/workspace/FRL1817E_060614073040 FRL1817E /u01/CODAS/runtime/NCB/backup/
		
		String shellScriptName = "/u01/CODAS/runtime/NCB/scripts/NCBDataTransferShellScript.sh";
		String dirName = "/u01/CODAS/runtime/NCB/workspace/FRL1822E";
		String fileName = "FRL1822E";
		String backupDir = "/u01/CODAS/runtime/NCB/backup/";
		String command = "sh "+ shellScriptName + " "+ dirName + " " + fileName + " " + backupDir;
		System.out.println(command);
        	
        if(executeScript(command))
        {
        	System.out.println("decryption process completed WITHOUT errors ");
        }
        else
        {
        	System.out.println("decryption process completed WITH errors ");
        }

	}
	public static class ProcessOut implements RuntimeProcess.ReadData
	  { 
	    String result = "";
	    public String getResult()
	    {
	    	return result;
	    }
	    public void readData (String line,boolean isStdOut)
	    {   
	    	if(line!=null)
	    	result= result + "\n"+((isStdOut?"stdout> ":"stderr> ")+line);
	    }
	  } //end of ProcessOut
}
