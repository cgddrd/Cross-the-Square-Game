/////// CS12130 Project 2011 ////////
//////'Cross-the-Square Game' ///////
//// Author: Connor Luke Goddard ////
/////// Student No: 110024253 ///////
////////// Aber ID: clg11 ///////////

public class SplitComma {
	
	//Create an instamce of the 'XML' class to allow data to be passed in
	private XML xmlParser;
	
	//Create public string split arrays and string that are used by 'XML' class
	private String[] results;
	private String[] player;
	private String[] displayArray;
	private String displayString;
	
public SplitComma(XML x) {
	xmlParser = x;
}
   public void splitString(int splitSwitch) {
	   
		//Retreive the current high score element information from the xml file via 'Highscore' class
		String scoreString = xmlParser.getScoreNodeText();
	  
		//Check which split is required from 'Highscore' class using 'splitSwitch'
		switch (splitSwitch) {
			//If 'splitSwitch = 1', retrieve the current player's score information
			case 1:	String newScore = xmlParser.getScoreData();
					//Split the string from the xml at the comma's in the string..
					//.. and add to 'results[]' array for use in 'Highscore' class
					results = scoreString.split( ",\\s*" );
					//Split the string of the current player's score information at the comma's in the string..
					//.. and add to 'player[]' array for use in 'Highscore' class
					player = newScore.split( ",\\s*" );
					break;
			case 2:	results = scoreString.split( ",\\s*" ); 
					break;
					//Split the string from the xml at the comma's in the string..
					//.. and add to 'displayArray[]' array to use in 'Highscore' class for display high score board
			case 3: displayArray = displayString.split( ",\\s*" ); 
					break;
		}
	}
	
	public String[] getXMLScore() {
		return results;
	}
	
	public String[] getPlayerScore() {
		return player;
	}
	
	public String[] getScoreBoard() {
		return displayArray;
	}
	
	public void setDisplayString(String display) {
		this.displayString = display;
	}
}	
