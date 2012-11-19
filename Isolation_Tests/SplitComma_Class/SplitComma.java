/////// CS12130 Project 2011 ////////
//////'Cross-the-Square Game' ///////
//// Author: Connor Luke Goddard ////
/////// Student No: 110024253 ///////
////////// Aber ID: clg11 ///////////

public class SplitComma {
	
	//Create public string split arrays and string that are used by 'XML' class
	private String[] results;
	private String[] player;
	private String[] displayArray;
	private String displayString;
	
public SplitComma() {
}
   public void splitString() {
	  
		displayArray = displayString.split( ",\\s*" ); 
		
	}
	
	public String[] getArray() {
		return displayArray;
	}
	
	public void setDisplayString(String display) {
		this.displayString = display;
	}
}	
