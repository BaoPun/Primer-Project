package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Creature;

/**
 * This class contains the player's life, and the generated levels of the maze.
 * @author baoph
 *
 */
public class Game {
	private int life;	// Expert = 1, Novice = 2, Beginner = 3
	private int x, y;	// row, col position of where we are
	
	// MVP: 3 creatures per level
	private List<Creature> levelOne = new ArrayList<Creature>();			
	private List<Creature> levelTwo = new ArrayList<Creature>();
	private List<Creature> levelThree = new ArrayList<Creature>();
	// private List<List<Creature>> levels;
	
	public Game(String filename) throws IOException {
		
		this.life = 30;
		this.x = this.y = 0;
		this.readFromFile(filename);
		
	}
	
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	// Check if we won the game
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
	
	public void playGame() {
		
		// Game loop: play the game while we still have questions to answer (WIN) and/or we still have life (LOSE).
		while(life > 0 && !checkIfWin()) {
			
			// Step 1: interact with the monster based on our current position
			interactWithMonster();
			
			// Set up UI to output questions and answers.
			
			break;
		}
	}
	
	public void interactWithMonster() {
		// First, check what level we're currently on.
		if(this.x == 0) {
			System.out.println("Level 1");
			
			
		}
		else if(this.x == 1) {
			System.out.println("Level 2");
			
			
		}
		else {
			System.out.println("Level 3");
			
			
		}
	}
	
	// Ugly method to read from the file
	private void readFromFile(String filename) throws IOException {
		
		// Use a combination of InputStream and BufferedReader to read from the file on each line.
		InputStream inputStream = new FileInputStream(new File(filename));
	    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
	        String line;
	        
	        // Current level.
	        // THIS LOGIC FAILS IF WE HAVE MULTIPLE LEVELS
	        int currentReadLevel = 1;
	        
	        while ((line = br.readLine()) != null) {
	        	
	        	line = line.trim();
	        	
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
	        			int correctIdx = Integer.parseInt(br.readLine().trim()) - 1;		// convert from 1-based to 0-based
	        			
	        			
	        			// Which list are we adding this to? 
	        			if(currentReadLevel == 1)
	        				levelOne.add(new Creature(question, answerOne, answerTwo, answerThree, answerFour, correctIdx));
	        				
	        			else if(currentReadLevel == 2)
	        				levelTwo.add(new Creature(question, answerOne, answerTwo, answerThree, answerFour, correctIdx));
	        				
	        			else
	        				levelThree.add(new Creature(question, answerOne, answerTwo, answerThree, answerFour, correctIdx));
	        			
	        		}
	        		
	        		// After reading all questions from a specific level, increment this to move onto the next level to read
	        		currentReadLevel++;
	        	}
	        	
	        
	        	
	        }
	    }
	}

	
	
	
	
	
}
