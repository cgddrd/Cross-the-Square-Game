public class Test {

	public static void main (String[] args) {

        for (int i = 0; i < 10; i++) {
            Player testPlayer = new Player();
            
            testPlayer.getInput();
            
            System.out.println(testPlayer.getPlayerPointX() + "," + testPlayer.getPlayerPointY());
        }
    }
}