
public class Testtest1 extends TestAbstract implements TestInterface{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Testtest1 t = new Testtest1();
		t.write();
		t.print();
		t.draw1();

	}

	public void write() {
		System.out.println("written");
		
	}

	public void draw1() {
		
		System.out.println("drawn");
	}

	public String putEscapeSequences(String str) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}
