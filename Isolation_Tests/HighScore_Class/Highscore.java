/////// CS12130 Project 2011 ////////
//////'Cross-the-Square Game' ///////
//// Author: Connor Luke Goddard ////
/////// Student No: 110024253 ///////
////////// Aber ID: clg11 ///////////

import java.util.*;
import java.text.*;

// *** This class contains portions of code that have been modified from: http://www.javablogging.com/read-and-write-xml/ and http://www.mkyong.com/java/how-to-modify-xml-file-in-java-jdom/***
public class Highscore {

// Set variables to retrieve current date information
private Date today;
private SimpleDateFormat dateFormatter;

// Set instances of classes to allow information to be received from 'Game' class, and use methods in 'XML' class
private XML xmlClass;

// Set variables to retrieve user input
private String playerInput;
private Scanner scan = new Scanner(System.in);

public Highscore() {
	xmlClass = new XML(this);
}

	public void setScore(int testScore) throws Exception {
		
		// Set variable 'today' with current date in format "dd/mm/yy"
		dateFormatter = new SimpleDateFormat("dd/MM/yy");
		today = new Date();
		
		// Retrieve the current number of high score records contained in 'HighScore.xml'
		xmlClass.readNo();
		
		// Retrieve current player's name from using user input
		System.out.print("Please enter your name: ");
		playerInput = scan.next();
		
		// Set XML input variable 'scoreData' to current player's inputted name, their score, and the current date in pre-defined format
		//xmlClass.scoreData = playerInput + "," + gameControl.getScore() + "," + dateFormatter.format(today);
		xmlClass.setScoreData(playerInput + "," + testScore + "," + dateFormatter.format(today));
		
		// Set XML sorting variable 'playerScore' to current player's score to allow bubble sort to take place (if neccessary)
		xmlClass.setPlayerScore(testScore);
		// Run method in 'XML' class to allow new highscore to be added to the 'HighScores.xml' file if score is eligable
		xmlClass.readXML();
		
		// Check 'scoreSwitch' variable returned from 'readXML()' method in 'XML' class
		switch (xmlClass.getScoreSwitch()) {
			// If 'scoreSwitch' = 0 then after sorting, player has not had a high enough score to make the high scores board..
			case 0:	System.out.println("\nSorry " + playerInput + ", you have not made the highscore this time!\n");
					break;
			// If 'scoreSwitch' = 1 then after sorting, player has had a high enough score to make the board and so has been added to the 'HighScores.xml' file
			case 1:	System.out.println("\nCongradulations " + playerInput + ", you have made it onto the highscore board! :D\n");
		}
	}
	
	// Print highscore data from 'HighScores.xml' file
	public void readScore() throws Exception {
		
		System.out.println("\n****Highscores****\n");
		
		// Run 'getHighScores' method in 'XML' class to retrieve and display all the highscore records currently in the 'HighScores.xml' file
		xmlClass.getHighScores();
		
		// Prompt user to enter a key to re-load main menu (gives user time to view highscore information before system prints main menu again)
		System.out.print("\nPress any key to continue: ");
		playerInput = scan.next();
		
	}

}
		
