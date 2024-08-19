import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.security.PrivateKey;


import iaik.pkcs.pkcs8.EncryptedPrivateKeyInfo;

public class MyPrivateKey
{
	
	 public static byte[] getFileAsBytes(String fileName)
	  {
	    try
	    {
	      FileInputStream fileInStream = new FileInputStream(fileName);

	      ByteArrayOutputStream byteArrayOutStream = new ByteArrayOutputStream();

	      byte[] readBytes = new byte[1024];

	      int i = fileInStream.read(readBytes);
	      while (i > 0)
	      {
	        byteArrayOutStream.write(readBytes, 0, i);
	        i = fileInStream.read(readBytes);
	      }

	      fileInStream.close();

	      return byteArrayOutStream.toByteArray();
	    }
	    catch (Exception exception)
	    {
	      System.out
	        .println("Error in getFileAsBytes" + exception.toString());
	      exception.printStackTrace();
	    }return null;
	  }

	public PrivateKey getEncryptedPrivateKey(String pFileLocation, String pPassword)
	  {
	    EncryptedPrivateKeyInfo localEncryptedPrivateKey = null;
	    PrivateKey localPrivateKey = null;
	    try
	    {
	      byte[] localPrivateKeyArray =   getFileAsBytes(pFileLocation);

	      if (localPrivateKeyArray != null)
	      {
	        localPrivateKey = getEncryptedPrivateKey(localPrivateKeyArray, 
	          pPassword);
	      }

	    }
	    catch (Exception e)
	    {
	      System.out.println("Exception in getEncryptedPrivateKey()" + 
	        e.toString());
	    }
	    return localPrivateKey;
	  }

	  public PrivateKey getEncryptedPrivateKey(byte[] pPrivateKeyArray, String pPassword)
	  {
	    EncryptedPrivateKeyInfo localEncryptedPrivateKey = null;
	    PrivateKey localPrivateKeyInfo = null;
	    PrivateKey localPrivateKey = null;
	    try
	    {
	      localEncryptedPrivateKey = new EncryptedPrivateKeyInfo(
	        pPrivateKeyArray);
	      if (pPassword != null)
	      {
	        localPrivateKey =  localEncryptedPrivateKey.decrypt(pPassword);
	      }

	    }
	    catch (Exception e)
	    {
	      System.out.println("Exception in getEncryptedPrivateKey()" + 
	        e.toString());
	    }
	    return localPrivateKey;
	  }
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		MyPrivateKey pKey = new MyPrivateKey();
		System.out.println("Private Key: " +pKey.getEncryptedPrivateKey("D:\\myv10logs\\jpmckeys\\ravi-privkey.pk8","Matrix-One!").getAlgorithm());

	}

}
