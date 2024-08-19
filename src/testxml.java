import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class testxml
{

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception
	{
		String requestXml = "<CheckOutLog><Status>REJECTED</Status><WaitTime>0</WaitTime><RequestSrc>speedi</RequestSrc><Action>INSERT</Action><PubType>Drawings-Outline and Dimensional</PubType><Pub>GEM-2878</Pub></CheckOutLog>";

		Document xmlDoc = DocumentHelper.parseText(requestXml);
		Element rootElement = xmlDoc.getRootElement();
		System.out.println(rootElement.elementText("Status"));
		System.out.println(rootElement.elementText("WaitTime"));
		System.out.println(rootElement.elementText("RequestSrc"));
		System.out.println(rootElement.elementText("Action"));
		System.out.println(rootElement.elementText("PubType"));
		System.out.println(rootElement.elementText("Pub"));

	}

}
