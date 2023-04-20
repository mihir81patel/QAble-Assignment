package examples;
import java.util.*;

public class LongestCommonPrefix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		List<String> lstOfStrings = new ArrayList<String>();
		int size = sc.nextInt();
		int minLength = Integer.MAX_VALUE;
		for(int i = 0; i<size; i++) {
			String inputStr = sc.next();
			lstOfStrings.add(inputStr);
			if(inputStr.length()<minLength) {
				minLength = inputStr.length();
			}
		}
		String cmnPrefix = "";
		for(int i = 0; i<minLength; i++) {
			boolean charCheck = true;
			char temp = lstOfStrings.get(0).charAt(i);
			for(String x : lstOfStrings) {
				if(x.charAt(i)!=temp) {
					charCheck = false;
				}
			}
			if(charCheck) {
				cmnPrefix += String.valueOf(temp);
			}
		}
		System.out.println(cmnPrefix);
		sc.close();
	}

}
