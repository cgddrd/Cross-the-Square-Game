/////// CS12130 Project 2011 ////////
//////'Cross-the-Square Game' ///////
//// Author: Connor Luke Goddard ////
/////// Student No: 110024253 ///////
////////// Aber ID: clg11 ///////////

import java.util.Scanner;
import java.awt.Point;

public class Player {
	
	//Creates object to allow user input
	private Scanner scan = new Scanner(System.in);
	
	//Creates the player's position variables used throughout the game
	private Point lastMove = new Point(0,0);
	private Point playerPoint = new Point(0,0);
	
public Player() {
}
	
	public int getPlayerPointX() {
		return playerPoint.x;
	}
	
	public int getPlayerPointY() {
		return playerPoint.y;
	}
	
	}
	
	public void getInput() {
		
		// Prompt for a move direction to be inputted from the user
		// **'.charAt' is used to prevent an IllegalArgumentException being thrown if any other type of input is used
		System.out.print("Please make your move (g = left, h = right, j = up, k = down): ");
		char playerInput = scan.next().charAt(0);	
		
		//Run the appropiate command for the direction input
		switch (playerInput) {
			//Move up one place
			case 'g': playerPoint = new Point(playerPoint.x, playerPoint.y - 1);
					  break;
			//Move down one place
			case 'h': playerPoint = new Point(playerPoint.x, playerPoint.y + 1);
					  break;
			//Move left one place
			case 'j': playerPoint = new Point(playerPoint.x - 1, playerPoint.y);
					  break;
			//Move right one place
			case 'k': playerPoint = new Point(playerPoint.x + 1, playerPoint.y);
					  break;
			//If the inputted character does not equal one of the accepted controls, inform the user
			default: System.out.println("Sorry, that key is not acceptable");
					 break;
		}
	}	  		
}
