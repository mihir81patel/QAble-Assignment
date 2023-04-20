package examples;
public class RemoveWhiteSpaces {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] originalStr = {"hello	\tworld\n", "This is a test", "  The  quick  brown  fox  jumps  over  the  lazy  dog  "};
		for(int i=0; i<originalStr.length; i++) {
			System.out.println(originalStr[i].replaceAll("\\s",""));
		}
	}

}
