/////// CS12130 Project 2011 ////////
//////'Cross-the-Square Game' ///////
//// Author: Connor Luke Goddard ////
/////// Student No: 110024253 ///////
////////// Aber ID: clg11 ///////////

import java.util.ArrayList;
import java.awt.Point;
import java.util.Random;

public class Hunter {

//Create the Arraylist that will store the generated path of a hunter
private ArrayList<Point> path = new ArrayList<Point>();



public Hunter (int playerx, int playery) {
	// Create array using destination coordinates (playerx, playery)
	// **NOTE:- On level 1, playerx and playery will ALWAYS be (0,0)
	//		  - On level 2, playerx and playery will be player's current coordinates when method is run	
	createArray(playerx, playery);
}
	public ArrayList<Point> getHunterPath() {
		return path;
	}
	
	public void createArray(int playerx, int playery) {
	
		// Add the starting coordinate to the path (always (11,11)
		path.add(new Point(11,11)); 
		
		//Create an offset point that is also the starting point
		Point offset = new Point(11,11);
		
		//Loop while the 'offset' point doesn't equal the destination point (playerx, playery)
		while (offset.x != playerx || offset.y != playery) {
			
			//Generate random value (either 1 or 2) to determine subtraction of x or y
			int choice = ((int) (Math.random() * 2)) + 1;
		
			//If the choice = 1, and the x coord of offset is greater than playerx
			if (choice == 1 && offset.x > playerx) 
				// Subtract 1 from the x coordinate (MOVE LEFT)
				offset = new Point(offset.x - 1, offset.y);
			
			//However, if the x coord is not greater than playerx..
			//..the hunter will go past it's destination and so CANNOT move anymore left..
			//..therefore y coord must be subtracted instead
			// **Acts as a fail-safe**
			else if (choice == 1) 
			offset = new Point(offset.x, offset.y - 1);
			
			//If the choice =2, and the y coord of offset is greater than playery
			else if (choice == 2 && offset.y > playery) 
				// Subtract 1 from the x coordinate (MOVE UP)
				offset = new Point(offset.x, offset.y - 1);
			
			//However, if the y coord is not greater than playery..
			//..the hunter will go past it's destination and so CANNOT move anymore up..
			//..therefore x coord must be subtracted instead
			// **Acts as a fail-safe**
			else offset = new Point(offset.x - 1, offset.y);
			
			//Add this new 'offset' point to the 'path' arraylist
			path.add(offset);
		}

		// **This loop is ONLY used during level 2, as it generates the "second"..
		// .. half of the hunter's path from the player's current position to the final destination (0,0)**
		while (offset.x != 0 || offset.y != 0) {
	
			int choice = ((int) (Math.random() * 2)) + 1;
		
			if (choice == 1 && offset.x > 0) 
					offset = new Point(offset.x - 1, offset.y);
		
			else if (choice == 1) 
				offset = new Point(offset.x, offset.y - 1);
			
			else if (choice == 2 && offset.y > 0) 
				offset = new Point(offset.x, offset.y - 1);
			
			else offset = new Point(offset.x - 1, offset.y);
			
			path.add(offset);
		}
	}
	
	
	public void resetHunter(int playerx, int playery) {
		//Clears the hunter's old path from the arraylist
		path.clear();
		
		//Creates a new path for the hunter using new destination coordinates
		createArray(playerx, playery);
	}
}
