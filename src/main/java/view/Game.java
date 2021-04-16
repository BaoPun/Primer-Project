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

/**
 * This class contains the player's life, and the generated levels of the maze.
 * @author baoph
 *
 */
public class Game {
	private int life;	// Expert = 1, Novice = 2, Beginner = 3
	private int x, y;	// row, col position of where we are
	private final int loss = 10;	// always losing 10 life points.
	
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
			
		
		}
		
		// If we won the game (REFACTOR)
		if(checkIfWin()) {
			System.out.println("Wow Congratulations! You really know your stuff.");
			System.out.println("Now go into the world with your head held high knowing you beat the maze and that you are a true Java Developer!");
		}
	}
	
	public void interactWithMonster() {
		// First, check what level we're currently on.
		if(this.x == 0) {
			System.out.println("\nYou are on Level 1.  You have " + life + " life left.");
			
			// If we haven't answered a question from the current monster, then prompt their question.
			if(!this.levelOne.get(y).isAnsweredCorrectly()) {
				
				System.out.println("\nAs you enter the maze you encounter a strange Creature.\r");
				System.out.println("RRRR! who goes there? You shall not pass unless you can answer my question correctly.\n");
				
				// Print out the current question we're on.
				printOneQuestion();
				
				// Answer the question
				answerQuestion();
				
			}
			
			// Assuming we answer the question correctly, we get to move on
			
		}
		else if(this.x == 1) {
			System.out.println("\nYou are on Level 2.  You have " + life + " life left.");
			
			// If we haven't answered a question from the current monster, then prompt their question.
			if(!this.levelTwo.get(y).isAnsweredCorrectly()) {
				
				System.out.println("\nAs you enter the maze you encounter a strange Creature.\r");
				System.out.println("RRRR! who goes there? You shall not pass unless you can answer my question correctly.\n");
				
				// Print out the current question we're on.
				printOneQuestion();
				
				// Answer the question
				answerQuestion();
				
			}
			
			// Assuming we answer the question correctly, we get to move on
		}
		else {
			System.out.println("\nYou are on Level 3.  You have " + life + " life left.");
			
			// If we haven't answered a question from the current monster, then prompt their question.
			if(!this.levelThree.get(y).isAnsweredCorrectly()) {
				
				System.out.println("\nAs you enter the maze you encounter a strange Creature.\r");
				System.out.println("RRRR! who goes there? You shall not pass unless you can answer my question correctly.\n");
				
				// Print out the current question we're on.
				printOneQuestion();
				
				// Answer the question
				answerQuestion();
				
			}
			
			// Assuming we answer the question correctly, we get to move on
			
			
		}
		System.out.println("\n");
	}
	
	// DEBUG: print all questions/answers
	public void printAll() {
		System.out.println("Level 1 Questions");
		for(int i = 0; i < levelOne.size(); i++)
			System.out.println(levelOne.get(i));
		
		System.out.println("Level 2 Questions");
		for(int i = 0; i < levelTwo.size(); i++)
			System.out.println(levelTwo.get(i));
		
		System.out.println("Level 3 Questions");
		for(int i = 0; i < levelThree.size(); i++)
			System.out.println(levelThree.get(i));
	}
	
	public void printOneQuestion() {
		if(this.x == 0) 
			System.out.println(levelOne.get(y));
		
		else if(this.x == 1)
			System.out.println(levelTwo.get(y));
		
		else 
			System.out.println(levelThree.get(y));
	}
	
	public Creature getOneQuestion() {
		if(this.x == 0) 
			return levelOne.get(y);
		
		else if(this.x == 1)
			return levelTwo.get(y);
		
		else 
			return levelThree.get(y);
	}
	
	public void answerQuestion() {
		
		boolean invalid = true;
		do {
			try {
				// Choose an answer (1-4) or 0 (quit)
				System.out.print("Please select an answer from 1-4 or select 0 to quit\n> ");
				String input = GameController.scanner.next();
				
				// If input is not 1 and not 0, throw an exception and try again.
				if(!input.equals("0") && !input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4"))
					throw new InvalidAnswerException(input);
				
				// If 0, quit.  Otherwise, proceed as normal.
				if(input.equals("0")) {
					System.out.println("\nGame has ended.  Come back in 100 years when you're ready.");
					System.exit(1);
				}
				
				// otherwise, compare the input with the correct answer on the question we're on.
				int answer = Integer.parseInt(input);
				
				// If the answer is incorrect
				if(answer != this.getOneQuestion().getCorrectAnswerIdx()) {
					// Incorrect answer, print message and deduct life.
					System.out.println("GRRR, the creature is dissapointed in your lack of knowledge.  As penalty, you have been shaved off 10 life points.");
					this.life -= this.loss;
					System.out.println("You now have " + this.life + " life left.");
					
					// If we hit 0, we are a wee bit dead. :(
					if(this.life <= 0) {
						System.out.println("\nWell, well, well… and you call yourself a Java Developer.");
						System.out.println("Jokes on you. It’s clear that you need some help, so here’s a resource that will whip you into shape:");
						System.out.println("\t\t\thttps://www.w3schools.com/java/");
						return;
					}
					else
						System.out.println("However, you still cannot move on, so you have to answer the question again.\n");
					
				}
				else {
					
					// Print a successful message
					System.out.println("The creature looks happy, as if it received a lot of head pats.  You may now proceed.");
					
					// Change the boolean to be true for the current position
					this.getOneQuestion().setAnsweredCorrectly(true);
					
					// MVP: move onto next question on our level
					this.y++;
					
					// But if we finished the last question on the current level, move onto the next.  Another function :)
					moveOntoNextLevel();
					
					// Don't answer the question again.
					invalid = false;
				}
				
				
				
			} 
			catch(InvalidAnswerException e) {
				System.out.println(e.getMessage());
			}
		} while(invalid);
	}
	
	/**
	 * If we are finished with the last question on the current level, move onto the next
	 */
	private void moveOntoNextLevel() {
		
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
	        			int correctIdx = Integer.parseInt(br.readLine().trim());		
	        			
	        			
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
