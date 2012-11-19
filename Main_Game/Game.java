/////// CS12130 Project 2011 ////////
//////'Cross-the-Square Game' ///////
//// Author: Connor Luke Goddard ////
/////// Student No: 110024253 ///////
////////// Aber ID: clg11 ///////////

import java.util.Random;
import java.util.ArrayList;
import java.awt.Point;
import java.util.Scanner;

public class Game  {
	
	//Create instances of classes required to run the game
	private Player playerControl = new Player();
	private Grid drawGrid;
	private Menu gameMenu;
	private Highscore setHighScore;
	
	//Create an array of 5 instances of the Hunter class (for controlling hunters)
	private Hunter[] hunterArray = new Hunter[5];
	
	private Random rand = new Random();
	
	//Create variables required by methods in 'Game' class and other sub-classes
	private int hunterCount = 0;
	private int choice;
	private int score = 0;
	private int life = 5;
	private int level = 1;
	private int noLevels;
	private String playerInput;
	
	public Game(Menu m) throws Exception {
	drawGrid = new Grid(this);
	setHighScore = new Highscore(this);
	gameMenu = m;
	}
	
	public Hunter[] getHunterArray() {
		return hunterArray;
	}
	
	public void runMenu() throws Exception {
		gameMenu.mainMenu();
	}
	
	public int getScore() {
		return score;
	}
	public int getChoice() {
		return choice;
	}
	
	public int getHunterCount() {
		return hunterCount;
	}
	
	public int getPlayerX() {
		return playerControl.getPlayerPointX();
	}
	
	public int getPlayerY() {
		return playerControl.getPlayerPointY();
	}
	
	public int getLastMoveX() {
		return playerControl.getLastX();
	}
	
	public int getLastMoveY() {
		return playerControl.getLastY();
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getNoLevels() {
		return noLevels;
	}
	
	public void setNoLevels(int levels) {
		this.noLevels = levels;
	}
	
	public void initialiseGame() throws Exception {
		
		// Generate hunter paths using the 'Hunter' class
		generateHunters();
		
		// Set how many hunters will be drawn on the board for the first wave of hunters
		generateHunterNo();
		
		// Draw the playing grid for the initial move of the player
		drawGrid.setGrid();
		
		// Run the main game method
		runGame();
	
}
	private void generateHunters() {
		
		// Loop through the 5 hunter classes contained with the 'hunterArray'
		for (int i = 0; i < 5; i++) {
			switch (level) {
				// If the level = 1, then generate a random path to (0,0)
				case 1: hunterArray[i] = new Hunter(0,0);
						break;
				// If the level = 2, then generate a random path to the player's current position (and then to (0,0))
				case 2: hunterArray[i] = new Hunter(getPlayerX(),getPlayerY());
						break;
			}
		}
	}


	private void runGrid() throws Exception {
	
		// Retrieve the player's direction input
		playerControl.getInput();
	
		//Check to see if the player is trying to move off the boundaries of the board..
		if (getPlayerX() < 0 || getPlayerY() < 0 || getPlayerX() > 11 || getPlayerY() > 11) {
		
			//	If they are then then set the next plotted point of the player to their previous position
			// (So they stay in the same place)
			Point playerPosition = new Point(getLastMoveX(),getLastMoveY());
			playerControl.setPlayerPos(playerPosition);
			System.out.println("Information: Forbidden move - you have now lost a go..\n");
		} 
	
		// Draw the game board using the hunter and player coordinates
		drawGrid.drawMove();
		
		// Check to see of the player should lose a life
		checkLife();
		
		// Check to see if the player should gain any points
		checkScore();
		
		// Display the player's game statistics		
		System.out.println("\nLevel: " + level + " | Score: " + score + " | Strength: " + life + "\n");
				
		// Set the player's current position as their last position
		Point lastPosition = new Point(getPlayerX(),getPlayerY());
		playerControl.setLastPos(lastPosition);
		//playerControl.lastMove = new Point(getPlayerX(),getPlayerY());
	}
	
	
	private void generateHunterNo() {
		// Generate a random number between 1 and 5 to determine how many hunters will be on the board in the next turn of the game
		choice = rand.nextInt(5) + 1;
	}
	
	// **This is the method that runs the entire game, and determines when the game has ended**	
	private void runGame() throws Exception {
		
		if (noLevels == 1) {
		
		// **MAIN GAME LOOP - RUNS THE ENTIRE GAME**

		while (life > 0 && score < 42) {
			//Run the game while the player's life is > 0
				runGrid();
			
				if (hunterCount < 23) {
					//If the progress of a hunter on the board is < 100% (23), progress the hunter further along the board
					hunterCount++;
				}
			}
		} else {
			while (life > 0) {
			//Run the game while the player's life is > 0
				runGrid();
			
				if (hunterCount < 23) {
					//If the progress of a hunter on the board is < 100% (23), progress the hunter further along the board
					hunterCount++;
				}
			}
		}
		
		// **If this runs then the player has run out of lives, therefore GAME OVER**
		System.out.println("***************\n--GAME OVER--\n***************\n");
		System.out.println("Your final score is: " + score);
		
		// Run the method to check for highscore			
		setHighScore.setScore();
		
		// Re-load the main menu
		gameMenu.mainMenu();
}
	
	private void checkScore() throws Exception {
	
		// Check if the hunters have reached (0,0)
		if (drawGrid.getBoard()[0][0] == "H  ") {
			
			// If they have add the numbers of hunters reached to the player's score
			// **('choice' is used due to the fact the hunters come in 'waves' - as opposed to individualy)**
			if (level == 1) { 
				score = score + choice;
			} else {
				
				//Used on level 2 for 2 point score per hunter
				score = score + (2 * choice);
			}
	
			// Loop through all the hunters on the board
			for (int k = 0; k < choice; k++) {
				Hunter currentHunter = hunterArray[k];
				
				// Check the level and set the hunter's new destination accordingly
				switch (level) {
					case 1: currentHunter.resetHunter(0,0);
							break;
					case 2: currentHunter.resetHunter(getPlayerX(), getPlayerY());
							break;
				}
			
				// Reset the hunter's progress on the board	
				hunterCount = 0;
			
				// Reset the hunter's position on the board from 'H' to '.'
				drawGrid.resetHunter(currentHunter.getHunterPath().get(hunterCount).x,currentHunter.getHunterPath().get(hunterCount).y);
			}
		// Generate a new number of hunters to generated
		generateHunterNo();
		}
		
		if (score > 42 && noLevels == 1) {
			score = 42;
		}
		
		if (score >= 42 && noLevels == 2) {
			// If the player's score is > 42, check to seeif the player is already on level 2, or on level 1
			if (level == 1) {
				// If they are on level 1, move them up to level 2, and reset thier lives 
				level = 2;
				life = 5;
				
				// Set the game settings for level 2
				generateLevelTwo();
			}
		}
	}	
	
	private void checkLife() {
		
		//Loop through all the hunters currently on the game board
		for (int k = 0; k < choice; k++) {
			Hunter currentHunter = hunterArray[k];
			
			// If a hunter and a player have the same coordinates (therefore collided)..
			if (currentHunter.getHunterPath().get(hunterCount).x == getLastMoveX() && currentHunter.getHunterPath().get(hunterCount).y == getLastMoveY()) {
				
				// ..deduct  one life from the player
				life--;
				
				// ..then remove and reset that hunter that had collided
				switch (level) {
					case 1: currentHunter.resetHunter(0,0);
							break;
					case 2: currentHunter.resetHunter(getPlayerX(), getPlayerY());
							break;
				}
				
				drawGrid.resetHunter(currentHunter.getHunterPath().get(hunterCount).x,currentHunter.getHunterPath().get(hunterCount).y);
			}
			
			// If a hunter now matches the player's last coordinates (therefore have swapped and so collided)..
			if (currentHunter.getHunterPath().get(hunterCount).x == getPlayerX() && currentHunter.getHunterPath().get(hunterCount).y == getPlayerY()) {
				
				// ..deduct  one life from the player
				life--;
				
				// ..then remove and reset that hunter that had collided
				switch (level) {
				case 1: currentHunter.resetHunter(0,0);
						break;
				case 2: currentHunter.resetHunter(getPlayerX(), getPlayerY());
						break;
				}
				
				drawGrid.resetHunter(currentHunter.getHunterPath().get(hunterCount).x,currentHunter.getHunterPath().get(hunterCount).y);
			}
			
			//Prevents the life from becoming a minus value before the end of the player's turn
			if (life < 0) {
				life = 0;
			}
		}
	}
		
	private void generateLevelTwo() throws Exception {
		
		// Loop through all 5 hunter classes in the 'hunterArray'
		for (int k = 0; k < choice; k++) {
			Hunter currentHunter = hunterArray[k];
			
			//Re-generate the path of the current hunter using the player's position as the first destination
			currentHunter.resetHunter(getPlayerX(), getPlayerY());
			
			// Reset the hunter's position on the board from 'H' to '.'
			drawGrid.resetHunter(currentHunter.getHunterPath().get(hunterCount).x,currentHunter.getHunterPath().get(hunterCount).y);
			
			// Generate a new number of hunters to generated
			generateHunterNo();
			
		}
	}
	
	// Run the method to list the high score board from the 'Highscore' class
	public void readScore() throws Exception {
		setHighScore.readScore();
	}
}



