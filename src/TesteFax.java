import java.io.FileInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
public class TesteFax {

	/**
	 * @param args
	 * @throws JAXBException 
	 */
	public static void main(String[] args) throws Exception {
		JAXBContext jc = JAXBContext.newInstance( "com.codas.jaxb.efax" );
		Unmarshaller u = jc.createUnmarshaller();
		com.codas.jaxb.efax.DocumentSheet ds = (com.codas.jaxb.efax.DocumentSheet)u.unmarshal(new FileInputStream( "Appliances.xml" ) );
		System.out.println(ds.getBatchnumber());
		System.out.println(ds.getCustcode());
		System.out.println(ds.getDatereceived());
		System.out.println(ds.getDoctype());
		System.out.println(ds.getGroupname());
		System.out.println(ds.getImagepath());
		System.out.println(ds.getTotalpages());
		
	}

}
