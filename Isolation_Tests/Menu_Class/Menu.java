/////// CS12130 Project 2011 ////////
//////'Cross-the-Square Game' ///////
//// Author: Connor Luke Goddard ////
/////// Student No: 110024253 ///////
////////// Aber ID: clg11 ///////////

import java.util.Scanner;

public class Menu {
	
	
	//Creates object to allow user input
	private Scanner scan = new Scanner(System.in);

public Menu() {
}
	
	public void mainMenu() throws Exception {
		
		// Display the game main menu
		System.out.println();
		System.out.println("Test Menu Class");
		System.out.println("Created by Connor Goddard");
		System.out.println();
		System.out.println("** ** ** ** ** ** ** ** **");
		System.out.println("1) Select Option 1");
		System.out.println("2) Select Option 2");
		System.out.println("3) Select Option 3");
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
		
		// Check if the input from the user is one of the permitted menu options
		if (playerInput == '1' || playerInput == '2' || playerInput == '3' || playerInput == '4') {
			
			//If it is then run the appropiate command for the menu selection
			switch (playerInput) {
				//Run the game at level 2
				case '1': System.out.println("You have selected option 1");
						  break;
				//Run the game at level 1
				case '2': System.out.println("You have selected option 2");
						  break;
				//Display the highscores board
				case '3': System.out.println("You have selected option 3");
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
		
		
	
		
	
	
	
