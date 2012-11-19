/////// CS12130 Project 2011 ////////
//////'Cross-the-Square Game' ///////
//// Author: Connor Luke Goddard ////
/////// Student No: 110024253 ///////
////////// Aber ID: clg11 ///////////

// *** This portion of code has been modified from: http://www.java-tips.org/java-se-tips/java.lang/how-to-use-comparable-interface.html ***

//  "implements Comparable" tells Java that it will be comparing some data (we can define however what it is that it will compare)
public class SortScore implements Comparable {

	//Set the variables for the three different parts of a highscore record
    private int Score;
    

    private SortScore() {
		//Initialise the three varaiables ready for comparison
        Score = 0;

    }

    public SortScore(int score) {
		
        Score = score;

    }

    public String toString() {
		//Return the compared highscore record
        return Integer.toString(Score);
    }

	//Perform a bubble sort on the records using the 'Score' of a record as the sort criteria
    public int compareTo(Object o1) {
		//If the current score is equal to the score it is being checked against, return a 0 to indicate this
        if (this.Score == ((SortScore) o1).Score)
            return 0;
            
        //If the current score is greater than the score it is being checked against, return a 1 to indicate this
        else if ((this.Score) > ((SortScore) o1).Score)
            return 1;
        
        //Otherwise if the current score is less that the other score it is being checked against, return a -1
        else
			return -1;
    }
}