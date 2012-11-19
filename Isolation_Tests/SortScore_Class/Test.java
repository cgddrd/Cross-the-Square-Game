import java.util.*;
public class Test {

	public static void main (String[] args) throws Exception{
		
		ArrayList testSort = new ArrayList();
		Scanner scan = new Scanner(System.in);
		
		for (int i = 0; i < 5; i++) {
		
			System.out.print("Please enter a value to sort: ");
			int testInt = scan.nextInt();
            testSort.add(new SortScore (testInt));
		}
		
		Collections.sort(testSort);
		Collections.reverse(testSort);
		
		System.out.println("\nSorted Values:");
		
		for (int i = 0; i < testSort.size(); i++) {
			System.out.println(testSort.get(i));
        }
    }
}
	
	
