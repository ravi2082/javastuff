
public class TestDate {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		String className = "test1";
		Class classObj = Class.forName(className);
		System.out.println(((TestInterface)(classObj.newInstance())).putEscapeSequences("ra'vi"));
		
		
		
	}

}
