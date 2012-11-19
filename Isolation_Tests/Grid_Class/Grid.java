/////// CS12130 Project 2011 ////////
//////'Cross-the-Square Game' ///////
//// Author: Connor Luke Goddard ////
/////// Student No: 110024253 ///////
////////// Aber ID: clg11 ///////////

public class Grid {
	
	// Create new 2D array that will act as the game playing board
	private String[][] boardGrid = new String[12][12];

public Grid() {
}

// *** This portion of code has been modified from: http://www.dreamincode.net/forums/topic/233382-2-d-array-grid-design/ ***

	private void drawMove(int testX, int testY) throws NullPointerException {
	
		// Loop through the first 'layer' (row) of the array..
			for(int row = 0; row < 12; row++) {
				
				// ..and add a '|' to the left edge of the board..
				System.out.print("|" + "  ");

				// ..then loop the second 'layer' (column) of the array..
				for (int column = 0; column < 12; column++) {
					
					// ..then populate the entire 2D array with full stops to create the board 'grid'
					boardGrid[row][column] = ".  ";
					
					//Draw the player's current position on the grid with 'P'
					boardGrid[testX][testY] = "P  ";
								
					
				// Print the entire grid out on screen (including hunter and player positions)
				System.out.print(boardGrid[row][column]);
				}
				
			//Print an empty line at the end of the grid to separate grid from player statistics	
			System.out.println();
		}
	}
		
	public void drawXY (int X, int Y) {
		drawMove(X,Y);
	}
		
}


