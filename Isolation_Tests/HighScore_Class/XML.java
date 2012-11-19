/////// CS12130 Project 2011 ////////
//////'Cross-the-Square Game' ///////
//// Author: Connor Luke Goddard ////
/////// Student No: 110024253 ///////
////////// Aber ID: clg11 ///////////

import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import java.util.*;


public class XML {

	// Set instances of 'SplitComma' class to allow highscore record string to be 'split' to allow sorting and re-organising to take place	
	private SplitComma split;

	// Create instances of all objects required to allow XML I/O to take place
	private DocumentBuilderFactory documentBuilderFactory;
	private DocumentBuilder documentBuilder;
	private Document parseDoc;
	private Document newDoc;
	private TransformerFactory transformerFactory;
	private Transformer transformer;
	private DOMSource source;
	private PrintStream ps;
	private StreamResult result;
	
	// Create all variables required for methods perfoming XML I/O and highscore analysis
	// **Note to Self: These cannot be placed in the constructors of methods as they are used in other classes??**
	private String scoreData;
	private int playerScore;
	private String element;
	private String numberData;
	private Element em;
	private int scorecount;	
	private Node scoreNode;	
	private Node root;
	private Node count;
	private Element rootElement;
	private String rootline;
	private ArrayList scoreSort = new ArrayList();
	private int scoreSwitch = 0;
	
public XML(Highscore h) {
	split = new SplitComma(this);
}
	public String getScoreData() {
		return scoreData;
	}
	
	public String getScoreNodeText() {
		return scoreNode.getTextContent();
	}
	
	public int getScoreSwitch() {
		return scoreSwitch;
	}
	
	public void setPlayerScore(int scoreInt) {
		this.playerScore = scoreInt;
	}
	
	public void setScoreData(String scoreString) {
		this.scoreData = scoreString;
	}
	
	
	
	
	// Initialise and set parsing of currently present 'HighScores.xml file or create a new one (overwriting the old one, or creating a completely new one)
	// This method is used by all other methods (except 'WriteXML()') in this class
	public void initialiseXML(int initSwitch) throws FileNotFoundException,ParserConfigurationException,Exception {
	
	try {	
			documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
		
			switch (initSwitch) {
				// If 'initSwitch' = 0, then parse the existing file and retrieve the root element..
				// ..and then read the 'number' element to determine how many highscore records are currently in the xml file
				case 0:	parseDoc = documentBuilder.parse("HighScores.xml");
						root = parseDoc.getFirstChild();
						count = parseDoc.getElementsByTagName("number").item(0);
						scorecount = Integer.parseInt(count.getTextContent());
						break;
				// If 'initSwitch' = 1, then create a new xml document and within that create a new root element called 'scoreroot'.. 
				// ..append this new root element to the new document ready for highscore elements to be added	
				case 1:	newDoc = documentBuilder.newDocument();
						rootline = "scoreroot";
						rootElement = newDoc.createElement(rootline);
						newDoc.appendChild(rootElement);
						break;
			}
		}
		//If the 'HighScores.xml' file cannot be located..
		catch (FileNotFoundException nf) {
			// ..print out an error message explaining this and run method to create the new 'HighScores.xml' file
			System.out.println("Error: File not found - New highscores file has been automatically generated.\n");
			createNewXML();
		}
		//If the parser cannot locate a highscore element (usually due to the XML being manually edited)..
		catch (ParserConfigurationException pc) {
			//.. print out error message asking user to try and restart the game
			System.out.println("Error: Parser cannot locate highscore element - Please exit the game and retry.\n");
		}
	}
	
	// Initialise and run objects to allow new highscore record to be written to 'HighScores.xml'
	// This method is used by all other methods (except 'WriteXML()') in this class
	public void writeXML(int endSwitch) throws Exception {
		
		transformerFactory = TransformerFactory.newInstance();
        transformer = transformerFactory.newTransformer();
        
        
        switch (endSwitch) {
			// If 'endSwitch' = 0, then parse the existing file and new high score element will be wriiten to the existing 'HighScores.xml' file
			case 0: source = new DOMSource(parseDoc);
					break;
			// If 'endSwitch' = 1, then create new xml file and new high score element will be written to this new 'HighScores.xml' file
			case 1:	source = new DOMSource(newDoc);
					break;
		}
		
		// Set XML output settings
		ps = new PrintStream("HighScores.xml");
		result =  new StreamResult(ps);
		
		// Output to file using settings
		transformer.transform(source, result);
	
	}
		
	// Search XML file and append to existing file if required
	public void amendXML() throws Exception {
		
		// Initialise I/O settings to parse existing 'HighScores.xml' file
		initialiseXML(0);
		
		// Loop through all the high score elements in the file
		for (int i = 1; i < scorecount; i++) {
				
				// Retrieve the current high score element and separate the information
				scoreNode = parseDoc.getElementsByTagName("score" + i).item(0);
				split.splitString(2);
				
				//Check if the current high score element in the xml file has a lower score than the current player's score..
				if (Integer.parseInt(split.getXMLScore()[1]) < playerScore) {
					// ..if it does, amend that high score element with the current player's score information..
					scoreNode.setTextContent(scoreData);
					// ..set the 'scoreSwitch' variable to 1 to allow the 'Highscore' clas to inform the user that they have made it onto the highscore board..
					scoreSwitch = 1;
					// ..break the loop (IMPORTANT: Otherwise the loop will continue to amend each highscore element..
					// .. that has a lower score than the player's current score, (which as it is sorted will be the remainder of the file))
					break;
				}
		}
		
			// Write the changes to the file using the settings for parsing and appending the existing 'HighScores.xml' file
			writeXML(0);
	}
	
	// Reads current 'HighScore.xml' file (if available) and determines whether it is required to either..
	// ..just add the new high score element to the end of the file, or append to the file
	public void readXML() throws NullPointerException, Exception {
		
		try {	
		
			initialiseXML(0);
        
			// Loop through all the high score elements in the file
			for (int i = 0; i < scorecount; i++) {
				// Retrieve the current high score element and separate the information
				scoreNode = parseDoc.getElementsByTagName("score" + i).item(0);
				split.splitString(2);
					
				//Add this information to the bubble sort list 'scoreSort' to be sorted
				scoreSort.add(new SortScore (split.getXMLScore()[0],Integer.parseInt(split.getXMLScore()[1]),split.getXMLScore()[2]));
			}
			
			//Separate the current player's score information
			split.splitString(1);
			
			//Add this also to the sorting list to be sorted with the existing high score information retrieved from the xml file
			scoreSort.add(new SortScore (split.getPlayerScore()[0],Integer.parseInt(split.getPlayerScore()[1]),split.getPlayerScore()[2]));
		
			// Sort all the high score records and the curent player's score information using bubble sort (Located in SortScore class)	
			Collections.sort(scoreSort);
		
			// Reverse the sorted list to list highscores in descending order (display highest score first)
			Collections.reverse(scoreSort);
	
			// Check to see how many high score elements are in the 'HighScore.xml' file
			if (scorecount >= 10) {
				
				// If the maximum of 10 high score elements are already in the file..
				// ..then the current player's score will have to be checked against the current 10 score elements..
				// ..and amend any one that has a lower score than the player's current score
				amendXML();
				
			// otherwise, the player's score is guaranteed to get on the highscore board and so can just be added to the xml file
			} else {
				
				// Initialise I/O settings to create new 'HighScores.xml' file
				initialiseXML(1);

				//Loop through the sorted scores list 'scoreSort'
				for (int i = 0; i < scoreSort.size(); i++) {
					//Create new element for each high score record in the sorted list and append it to the new xml root element
					String s = scoreSort.get(i).toString();
					element = "score" + i;
					em = newDoc.createElement(element);
					em.appendChild(newDoc.createTextNode(s));
					rootElement.appendChild(em);
				}
				
				// Add one to the total number of high score records in the xml file
				// (As the player's current score has just been added)
				scorecount++;
		
				// Append this incremented number to the xml root element
				element = "number";
				numberData = Integer.toString(scorecount);
				em = newDoc.createElement(element);
				em.appendChild(newDoc.createTextNode(numberData));
				rootElement.appendChild(em);
		
				// Set the 'scoreSwitch' variable to 1 to allow the 'Highscore' clas to inform the user that they have made it onto the highscore board
				scoreSwitch = 1;
				
				// Write the changes to the file using the settings for creating a new 'HighScores.xml' file - (Techincally overwriting)
				writeXML(1);
			}
		}
		// If the parser cannot locate either the score or number elements from the existing xml file
		catch (NullPointerException np) {
			// Print error message informing user
			System.out.println("Error: The parser cannot locate the score node in the XML file, please delete the 'HighScore.xml' file and try again");
		}
	}
  
	// Retrieves the current number of high score elements contained within the 'HighScores.xml' file
	public void readNo() throws Exception {
		
		initialiseXML(0);
        
	}
	   
	// Retrieves and displays high score board from 'HighScores.xml' file
	public void getHighScores() throws NullPointerException, Exception {
	   
		try {
		   
				initialiseXML(0);
		
				// Loop through all the high score elements in the file
				for (int i = 0; i < scorecount; i++) {
				
					// Retrieve the current high score element and separate the information
					scoreNode = parseDoc.getElementsByTagName("score" + i).item(0);
					split.splitString(2);
					
					//Add this information to the bubble sort list 'scoreSort' to be sorted
					scoreSort.add(new SortScore (split.getXMLScore()[0],Integer.parseInt(split.getXMLScore()[1]),split.getXMLScore()[2]));
				}
			
				Collections.sort(scoreSort);
				Collections.reverse(scoreSort);
        
				// Loops through the sorted list
				for (int i = 0; i < scoreSort.size(); i++) {
					//Splits the score information...
					split.setDisplayString(scoreSort.get(i).toString());
					split.splitString(3);
			
					// ..and displays it in a list showing the high scores board
					System.out.println("Name: " + split.getScoreBoard()[0] + " - Score: " + split.getScoreBoard()[1] + " - Date: " + split.getScoreBoard()[2]);
				}
		}	
		catch (NullPointerException np) {
			System.out.println("Error: The parser cannot locate the score node in the XML file, please delete the 'HighScore.xml' file and try again");
		}
	}
	
	//Creates a new 'HighScores.xml' file if there is not one in the program directory	 
	public void createNewXML() throws Exception {
			 
		initialiseXML(1);
			
		// Creates a dummy score element neccessary for the xml file to be populated later on
		// (This element will be overwritten automatically after 10 elements are in xml file)
		element = "score0";
		em = newDoc.createElement(element);
		em.appendChild(newDoc.createTextNode("**TEMP(Ignore)**,0,N/A"));
		rootElement.appendChild(em);
	
		//Creates number element containng the number of score elements in the xml file (required for parsing)
		element = "number";
		em = newDoc.createElement(element);
		em.appendChild(newDoc.createTextNode(Integer.toString(1)));
		rootElement.appendChild(em);
		
		writeXML(1);
		 
	}
}
