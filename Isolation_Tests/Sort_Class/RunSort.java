import java.util.Scanner;
import java.util.*;

public class RunSort {

	private ArrayList scoreSort = new ArrayList();
 	private String testName;
	private int testScore;
	private String testDate;
	private Scanner scan = new Scanner(System.in);

	public void performSort() {
	
	for (int i = 0; i < 5; i++) {
	
		System.out.print("Please enter a name: ");
		testName = scan.next();
		
		System.out.print("Please enter a score: ");
		testScore = scan.nextInt();
		
		System.out.print("Please enter today's date: ");
		testDate = scan.next();
		
		scoreSort.add(new SortScore (testName, testScore, testDate));
	}
	
	Collections.sort(scoreSort);
	Collections.reverse(scoreSort);
	
	for (int i = 0; i < scoreSort.size(); i++) {
		String s = scoreSort.get(i).toString();
		System.out.println(s);
	}
}
}
	