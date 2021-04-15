package model;

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

/**
 * This class contains the player's life, and the generated levels of the maze.
 * @author baoph
 *
 */
public class Game {
	private int life;	// Expert = 1, Novice = 2, Beginner = 3
	
	// MVP: 3 creatures per level
	private List<Creature> levelOne = new ArrayList<Creature>();			
	private List<Creature> levelTwo = new ArrayList<Creature>();
	private List<Creature> levelThree = new ArrayList<Creature>();
	// private List<List<Creature>> levels;
	
	// Handles all inputs
	private Scanner scanner;
	
	
	public Game(String filename) throws IOException {
		
		this.life = 30;
		this.readFromFile(filename);
		
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
