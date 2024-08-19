import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;

import com.codas.xml.xmlAppProperties.AttributeType;
import com.codas.xml.xmlAppProperties.AttributesType;
import com.codas.xml.xmlAppProperties.FooterAttributeType;
import com.codas.xml.xmlAppProperties.HeaderAttributeType;

public class AppXMLParser  {
	String xmlName = null;
	public AppXMLParser(String xmlName)
	{this.xmlName = xmlName;
		
	}
	
	
	
	public static void main(String args[]) throws Exception
	{
		JAXBContext jc = JAXBContext.newInstance("com.codas.xml.xmlAppProperties");
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		JAXBElement<?> jaxElement = (JAXBElement<?>) unmarshaller.unmarshal(new FileInputStream( "Alchemy.xml" ));
		AttributesType attrType = (AttributesType)jaxElement.getValue();
		
		List attrList = attrType.getAttribute();
		
		Iterator i = attrList.iterator();
		System.out.println("Printing Basic Body Attributes ::");
		System.out.println();
		while(i.hasNext())
		{
			AttributeType attr = (AttributeType)i.next();
			System.out.println("Attribute Name : "+attr.getName());
			System.out.println("Attribute Group : "+attr.getAttrName());
			System.out.println("Attribute Description : "+attr.getDescription());
			System.out.println("Attribute Type : "+attr.getType());
			System.out.println("Attribute Size : "+attr.getSize());
			System.out.println("Attribute isSortOption : "+attr.isIsSortOption());
			System.out.println("Attribute isSortCriterion : "+attr.isIsSortCriterion());
			System.out.println();
			System.out.println("==============================");
			
		}
		
		List headerAttrList = attrType.getHeaderAttribute();
		i = headerAttrList.iterator();
		System.out.println("Printing Basic Header Attributes ::");
		System.out.println();
		while(i.hasNext())
		{
			HeaderAttributeType attr = (HeaderAttributeType)i.next();
			System.out.println("Attribute Name : "+attr.getName());
			System.out.println("Attribute Description : "+attr.getDescription());
			System.out.println("Attribute Type : "+attr.getType());
			System.out.println("Attribute Size : "+attr.getSize());
			System.out.println("Attribute RowNum : "+attr.getRowNum());
			System.out.println("Attribute Values : "+attr.getValues());
			System.out.println("Attribute Default : "+attr.getDefault());
			System.out.println("Attribute Border : "+attr.getBorder());
			System.out.println("Attribute Action : "+attr.getAction());
			System.out.println("Attribute isSortOption : "+attr.isIsSortOption());
			System.out.println("Attribute isSortCriterion : "+attr.isIsSortCriterion());
			System.out.println();
			System.out.println("==============================");
			
		}
	
		List footerAttrList = attrType.getFooterAttribute();
		i = footerAttrList.iterator();
		System.out.println("Printing Basic Footer Attributes ::");
		System.out.println();
		while(i.hasNext())
		{
			FooterAttributeType attr = (FooterAttributeType)i.next();
			System.out.println("Attribute Name : "+attr.getName());
			System.out.println("Attribute Description : "+attr.getDescription());
			System.out.println("Attribute Type : "+attr.getType());
			System.out.println("Attribute Size : "+attr.getSize());
			System.out.println("Attribute RowNum : "+attr.getRowNum());
			System.out.println("Attribute Values : "+attr.getValues());
			System.out.println("Attribute Default : "+attr.getDefault());
			System.out.println("Attribute Border : "+attr.getBorder());
			System.out.println("Attribute Action : "+attr.getAction());
			System.out.println("Attribute isSortOption : "+attr.isIsSortOption());
			System.out.println("Attribute isSortCriterion : "+attr.isIsSortCriterion());
			System.out.println();
			System.out.println("==============================");
			
		}
		
	}
	
}
