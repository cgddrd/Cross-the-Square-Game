/////// CS12130 Project 2011 ////////
//////'Cross-the-Square Game' ///////
//// Author: Connor Luke Goddard ////
/////// Student No: 110024253 ///////
////////// Aber ID: clg11 ///////////

import java.util.Scanner;

public class Menu {
	
	//Creates an instance of the 'Game' class to allow the game to be run from the menu
	private Game gameControl;

	//Creates object to allow user input
	private Scanner scan = new Scanner(System.in);

public Menu() {
}
	
	public void mainMenu() throws Exception {
		
		// Display the game main menu
		System.out.println();
		System.out.println("Welcome to 'Cross-the-Square'");
		System.out.println("Created by Connor Goddard");
		System.out.println();
		System.out.println("** ** ** ** ** ** ** ** **");
		System.out.println("1) Play Level 1 Only ");
		System.out.println("2) Play Level 1 & Level 2");
		System.out.println("3) View Highscores");
		System.out.println("4) Exit the Game");
		System.out.println("** ** ** ** ** ** ** ** **");
		System.out.println();
		System.out.println("Please select an option: ");
		
		// Prompt for a menu option to be inputted from the user
		// **'.charAt' is used to prevent an IllegalArgumentException being thrown if any other type of input is used
		char playerInput = scan.next().charAt(0);
		
		// Run the appropiate commands depending on the inputted character from the user
		menuSelect(playerInput);
	}
	
	private void menuSelect(char playerInput) throws Exception {
		
		Player playerControl = new Player();
		gameControl = new Game(this);

		// Check if the input from the user is one of the permitted menu options
		if (playerInput == '1' || playerInput == '2' || playerInput == '3' || playerInput == '4') {
			
			//If it is then run the appropiate command for the menu selection
			switch (playerInput) {
				//Run the game at level 2
				case '1': gameControl.setNoLevels(1);
						  gameControl.initialiseGame();
						  break;
				//Run the game at level 1
				case '2': gameControl.setNoLevels(2);
						  gameControl.initialiseGame();
						  break;
				//Display the highscores board
				case '3': gameControl.readScore();
						  break;
				//Exit the game 
				case '4': System.out.println("Thankyou for Playing :)\n");
				          break;
			}
		
		//Otherwise if the input was not a permitted menu option
		} else {
			
			//Display a message informing the user
			System.out.println("\n\n\n\n\n\n\n\nInformation: Key Not Permitted - Please try again:\n");
			
			//Re-load the main menu for them to try again
			mainMenu();
		}
	}
}	
		
		
	
		
	
	
	
