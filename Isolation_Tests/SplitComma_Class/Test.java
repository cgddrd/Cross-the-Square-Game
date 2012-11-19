import java.util.Scanner;

public class Test {

    public static void main (String[] args) throws Exception{
	
        Scanner scan = new Scanner(System.in);
		SplitComma split = new SplitComma();
		
		System.out.print("Please enter a split string value: ");
		String testInput = scan.next();
    
		split.setDisplayString(testInput);
		split.splitString();
		
		for (int i = 0; i < split.getArray().length; i++) {
			
		System.out.println("Split Output: " + split.getArray()[i]);
		
        }
		
	}
}
	
	
