package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
	private int life;	// Expert = 10, Novice = 20, Beginner = 30
	private int x, y;	// row, col position of where we are
	public final static int loss = 10;	// always losing 10 life points.
	
	// MVP: 3 creatures per level
	/*
		List<Creature> levelOne = new ArrayList<Creature>(), levelTwo = new ArrayList<Creature>(), levelThree = new ArrayList<Creature>();
	*/
	
	// Stretch goal: convert to list of lists for arbitrary number of levels
	private List<List<Creature>> levels = new ArrayList<List<Creature>>();
	
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
	
	public int getNumLevels() {
		return this.levels.size();
	}
	
	public int getNumQuestionsOnLevel() {
		return this.levels.get(this.x).size();
	}
	
	// Check if we won the game
	public boolean checkIfWin() {
		
		for(int i = 0; i < levels.size(); i++) {
			for(int j = 0; j < levels.get(i).size(); j++) {
				if(!levels.get(i).get(j).isAnsweredCorrectly())
					return false;
			}
		}
		
		return true;
	}
	
	public void printOneQuestion() {
		System.out.println(levels.get(this.x).get(this.y));
	}
	
	public Creature getOneQuestion() {
		return levels.get(this.x).get(this.y);
	}
	
	/**
	 * If we are finished with the last question on the current level, move onto the next
	 */
	public void moveOntoNextLevel() {
		
		if(this.y >= levels.get(this.x).size()) {
			this.y = 0;
			this.x++;
		}
	}
	
	
	// Ugly method to read from the file
	private void readFromFile(String filename) throws IOException {
		
		// Use a combination of InputStream and BufferedReader to read from the file on each line.
		InputStream inputStream = new FileInputStream(new File(filename));
	    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
	    	
        	// Initial number of levels
        	int level = Integer.parseInt(br.readLine().trim());
        	
        	// iterates through each level
        	for(int i = 0; i < level; i++) {
        		
        		// Get the total # of questions
        		int totalQuestions = Integer.parseInt(br.readLine().trim());
        		
        		// Create a new level to store the questions onto.
        		levels.add(new ArrayList<Creature>());
        		
        		// Iterate through each question
        		for(int j = 0; j < totalQuestions; j++) {
        			
        			// Read the type of question, the question, the 4 answers, and the index of the correct answer
        			String type = br.readLine().trim();
        			String question = br.readLine().trim();
        			String answerOne = br.readLine().trim();
        			String answerTwo = br.readLine().trim();
        			String answerThree = br.readLine().trim();
        			String answerFour = br.readLine().trim();
        			int correctIdx = Integer.parseInt(br.readLine().trim());		
        			
        			// Add to the current list, depending on the question type
        			if(type.equals("Java"))
        				levels.get(i).add(new JavaCreature(question, answerOne, answerTwo, answerThree, answerFour, correctIdx, "Java"));
        			else if(type.equals("Data Structures"))
        				levels.get(i).add(new DataStructuresCreature(question, answerOne, answerTwo, answerThree, answerFour, correctIdx, "Data Structures"));
        			else if(type.equals("SQL"))
        				levels.get(i).add(new SQLCreature(question, answerOne, answerTwo, answerThree, answerFour, correctIdx, "SQL"));
        		}
	        }
	    }
	    catch(NumberFormatException e) {
	    	System.out.println("Sadge, a formatting error occured.  The program will now exit.");
	    	System.exit(1);
	    }
	}
	
}