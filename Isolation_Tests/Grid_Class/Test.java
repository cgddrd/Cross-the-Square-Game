import java.util.Scanner;

public class Test {
    
    public static void main (String[] args) {
    
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Please enter a X value: ");
		int testX = scan.nextInt();
        
        System.out.print("Please enter a Y value: ");
		int testY = scan.nextInt();
        
        Grid testGrid = new Grid();
		testGrid.drawXY(testX,testY);
    }
}