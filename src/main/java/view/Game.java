package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import controller.GameController;
import exceptions.InvalidAnswerException;
import model.Creature;
import model.JavaCreature;
import model.DataStructuresCreature;
import model.SQLCreature;

/**
 * This class contains the player's life, and the generated levels of the maze.
 * @author baoph
 *
 */
public class Game {
	private int life;	// Expert = 1, Novice = 2, Beginner = 3
	private int x, y;	// row, col position of where we are
	public final static int loss = 10;	// always losing 10 life points.
	
	// MVP: 3 creatures per level
	private List<Creature> levelOne = new ArrayList<Creature>();			
	private List<Creature> levelTwo = new ArrayList<Creature>();
	private List<Creature> levelThree = new ArrayList<Creature>();
	
	// Stretch goal: convert to list of lists for arbitrary number of levels
	// private List<List<Creature>> levels;
	
	public Game(String filename) throws IOException {
		this.life = 30;
		this.x = this.y = 0;
		this.readFromFile(filename);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getLife() {
		return this.life;
	}
	
	public void setLife(int life) {
		this.life = life;
	}
	
	/**
	 * Checks all 3 levels to determine if we won the game.
	 * For each level, we check if each question has successfully been answered.
	 * @return True if we win, False otherwise
	 */
	public boolean checkIfWin() {
		
		// Check level 1
		for(int i = 0; i < levelOne.size(); i++) {
			if(!levelOne.get(i).isAnsweredCorrectly())
				return false;
		}
		
		
		// Check level 2
		for(int i = 0; i < levelTwo.size(); i++) {
			if(!levelTwo.get(i).isAnsweredCorrectly())
				return false;
		}
		
		
		// Check level 3
		for(int i = 0; i < levelThree.size(); i++) {
			if(!levelThree.get(i).isAnsweredCorrectly())
				return false;
		}
		
		
		return true;
	}
	
	/**
	 * Prints the current creature's question and list of answers based on where we are on the maze.
	 */
	public void printOneQuestion() {
		if(this.x == 0) 
			System.out.println(levelOne.get(y));
		
		else if(this.x == 1)
			System.out.println(levelTwo.get(y));
		
		else 
			System.out.println(levelThree.get(y));
	}
	
	/**
	 * Returns a single question based on where we are on the maze.
	 * @return our position in the maze (x, y)
	 */
	public Creature getOneQuestion() {
		if(this.x == 0) 
			return levelOne.get(y);
		
		else if(this.x == 1)
			return levelTwo.get(y);
		
		else 
			return levelThree.get(y);
	}
	
	
	
	/**
	 * If we are finished with the last question on the current level, move onto the next level.
	 */
	public void moveOntoNextLevel() {
		
		// Level 1
		if(this.x == 0) {
			if(this.y >= levelOne.size()) {
				this.y = 0;		// Reset the column
				this.x++;		// But increment the row
			}
		}
		
		// Level 2
		else if(this.x == 1) {
			if(this.y >= levelTwo.size()) {
				this.y = 0;		// Reset the column
				this.x++;		// But increment the row
			}
		}
		
		// Level 3
		else {
			// DO SOMETHING HERE
		}
	}
	
	
	/**
	 * This method takes in a file and extracts all questions and their types onto our data structures. <br>
	 * Our data structure consists of 3 different lists that represent the levels in our maze.  <br>
	 * Each level contains a different type of question: Java, Data Structures, and SQL.
	 * @param filename
	 * @throws IOException
	 */
	private void readFromFile(String filename) throws IOException {
		
		// Use a combination of InputStream and BufferedReader to read from the file on each line.
		InputStream inputStream = new FileInputStream(new File(filename));
	    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
	        
	        // Current level.
	        int currentReadLevel = 1;
	        	
        	String line = br.readLine().trim();
        	
        	// Initial number of levels
        	int level = Integer.parseInt(line);
        	
        	// iterates through each level
        	for(int i = 0; i < level; i++) {
        		
        		// Get the total # of questions
        		int totalQuestions = Integer.parseInt(br.readLine().trim());
        		
        		// Iterate through each question
        		for(int j = 0; j < totalQuestions; j++) {
        			
        			// Read the question, the 4 answers, and the index of the correct answer
        			String question = br.readLine().trim();
        			String answerOne = br.readLine().trim();
        			String answerTwo = br.readLine().trim();
        			String answerThree = br.readLine().trim();
        			String answerFour = br.readLine().trim();
        			int correctIdx = Integer.parseInt(br.readLine().trim());		
        			
        			// Which list are we adding this to? 
        			if(currentReadLevel == 1)
        				levelOne.add(new JavaCreature(question, answerOne, answerTwo, answerThree, answerFour, correctIdx, "Java"));
        				
        			else if(currentReadLevel == 2)
        				levelTwo.add(new DataStructuresCreature(question, answerOne, answerTwo, answerThree, answerFour, correctIdx, "Data Structures"));
        				
        			else
        				levelThree.add(new SQLCreature(question, answerOne, answerTwo, answerThree, answerFour, correctIdx, "SQL"));
        			
        		}
        		
        		// After reading all questions from a specific level, increment this to move onto the next level to read
        		currentReadLevel++;
	        }
	    }
	    catch(NumberFormatException e) {
	    	System.out.println("Error, something is wrong with the format.  The program will now exit.");
	    	System.exit(1);
	    }
	}
	
}