import java.util.Scanner;

public class Test {

	public static void main (String[] args) {

        Scanner scan = new Scanner(System.in);
        
        System.out.print("Please enter a X value: ");
		int testX = scan.nextInt();
        
        System.out.print("Please enter a Y value: ");
		int testY = scan.nextInt();
        
		Hunter testHunter = new Hunter(testX,testY);
	
		for (int i = 0; i < testHunter.getHunterPath().size(); i++) {
			System.out.println("Hunter Coord: " + testHunter.getHunterPath().get(i));
		}
    }
}