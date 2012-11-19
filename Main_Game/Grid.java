/////// CS12130 Project 2011 ////////
//////'Cross-the-Square Game' ///////
//// Author: Connor Luke Goddard ////
/////// Student No: 110024253 ///////
////////// Aber ID: clg11 ///////////

public class Grid {
	
	// Create object of 'Menu' class
	private Game gameControl;
	// Create new 2D array that will act as the game playing board
	private String[][] boardGrid = new String[12][12];
	
public Grid(Game b) {
	// Create object of currently running 'Game' class to allow player and hunter coordinates to be plotted on grid
	gameControl = b;
}

// *** This portion of code has been modified from: http://www.dreamincode.net/forums/topic/233382-2-d-array-grid-design/ ***

	public void drawMove() throws NullPointerException {
	
		try {
		
			// Loop through the first 'layer' (row) of the array..
			for(int row = 0; row < 12; row++) {
				
				// ..and add a '|' to the left edge of the board..
				System.out.print("|" + "  ");

				// ..then loop the second 'layer' (column) of the array..
				for (int column = 0; column < 12; column++) {
					
					// ..then populate the entire 2D array with full stops to create the board 'grid'
					boardGrid[row][column] = ".  ";
					
					//Draw the player's current position on the grid with 'P'
					boardGrid[gameControl.getPlayerX()][gameControl.getPlayerY()] = "P  ";
					
					// Loop through the number of hunters selected to be drawn on the board this go
					for (int k = 0; k < gameControl.getChoice(); k++)  {

						Hunter currentHunter = gameControl.getHunterArray()[k]; 
					
						
						// Draw a 'H' on the grid position that matches the hunter's current coordinate
						boardGrid[currentHunter.getHunterPath().get(gameControl.getHunterCount()).x][currentHunter.getHunterPath().get(gameControl.getHunterCount()).y] = "H  ";
					}			
					
				// Print the entire grid out on screen (including hunter and player positions)
				System.out.print(boardGrid[row][column]);
				}
				
			//Print an empty line at the end of the grid to separate grid from player statistics	
			System.out.println();
				
			}
		}
		
		//If the method cannot access the current hunter's path arraylist (and so does not know its next coordinate)
		catch (NullPointerException np) { 
			System.out.println("Error: Cannot retrieve Hunter position - Hunter path generation failed");
		}
	}
	
	//Set and draw an initial grid apon a new game start to display player's position at (0,0) ready for the player to have their first move
	public void setGrid() {
		
		System.out.println("\n\n");
		
			for(int row = 0; row < 12; row++) {

				System.out.print("|" + "  ");

				for (int column = 0; column < 12; column++) {
				
					boardGrid[row][column] = ".  ";
					boardGrid[gameControl.getPlayerX()][gameControl.getPlayerY()] = "P  ";
								
					
					System.out.print(boardGrid[row][column]);

				}
				System.out.println();
				}
				System.out.println("\n");
				
	}
	
// *** END OF REFERENCED CODE ***
	
	// Changes a hunter's current position on the board from 'H' to '.' if the hunter has collided with the player
	public void resetHunter(int hunterx, int huntery) {
		boardGrid[hunterx][huntery] = ".  ";
	}
	
	public String[][] getBoard() {
		return boardGrid;
	}
}
