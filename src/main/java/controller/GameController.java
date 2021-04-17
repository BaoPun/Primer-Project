package controller;

import java.io.IOException;
import java.util.Scanner;

import exceptions.InvalidAnswerException;
import exceptions.InvalidDirectionException;
import exceptions.InvalidMovementException;
import exceptions.InvalidStartException;
import view.Game;

public class GameController {
	
	private Game game;
	
	// Handles all inputs
	public static Scanner scanner;
	
	public GameController(String filename) throws IOException {
		game = new Game(filename);
		scanner = new Scanner(System.in);
	}
	
	/**
	 * Outputs the introduction to the game, as well as a confirmation to start or quit the game
	 * @throws InvalidStartException if an invalid confirmation input was provided
	 */
	public void introduction() {
		System.out.println("Welcome to the MazeRunner.");
		System.out.println("If you dare to enter you will be tested on your knowledge of all things Java.");
		System.out.println("Otherwise leave now and save yourself the embarrassment. To get out of the Maze successfully, answer each question correctly.");
		System.out.println("For this game, you will start out with 30 life points.");
		System.out.println("Every time you answer correctly, your life will be deducted by 10. If your life hits 0 you lose. Good Luck!\n");
		
		boolean invalid = true;
		
		// Even if we have invalid input, error handle so that we always eventually get correct input.
		do {
			try {
				// Start the game or quit
				System.out.print("To start the game, enter 1, To quit the game, enter 0:\n> ");
				String input = scanner.next();
				
				// If input is not 1 and not 0, throw an exception and try again.
				if(!input.equals("1") && !input.equals("0"))
					throw new InvalidStartException(input);
				
				// If 0, quit.  Otherwise, proceed as normal.
				if(input.equals("0")) {
					System.out.println("\nGame has ended.  Come back in 100 years when you're ready.");
					System.exit(1);
				}
				
				invalid = false;
			} 
			catch(InvalidStartException e) {
				System.out.println(e.getMessage());
			}
		} while(invalid);
	}
	
	/**
	 * Game loop to interact with the Game object
	 */
	public void play() {
		
		// Introduce the user to the game
		introduction();
		
		// Game loop: play the game while we still have questions to answer (WIN) and/or we still have life (LOSE).
		while(game.getLife() > 0 && !game.checkIfWin()) {
			
			// Step 1: interact with the monster based on our current position
			interactWithMonster();
			
			// Step 2 Stretch goal: show the current maze location
			
			
			// Step 3 Stretch goal: implement move function
			move();
			
			break;
			
		}
		
		if(game.checkIfWin())
			displayWinMessage();
	}
	
	public void interactWithMonster() {
		
		// Print what level we're on.
		System.out.println("\nYou are on Level " + (game.getX() + 1) + ".  You have " + game.getLife() + " life left.");
		
		// If we haven't answered a question from the current monster, then prompt their question.
		if(!game.getOneQuestion().isAnsweredCorrectly()) {
			
			System.out.println("\nAs you enter the maze you encounter a strange Creature.");
			System.out.println("RRRR! who goes there? You shall not pass unless you can answer my question correctly.\n");
			
			// Print out the current question we're on.
			game.printOneQuestion();
			
			// Answer the question
			answerQuestion();
		}
		
		// Assuming we answer the question correctly, we get to move on
		System.out.println("\n");
	}
	
	public void answerQuestion() {
		
		boolean invalid = true;
		do {
			try {
				// Choose an answer (1-4) or 0 (quit)
				System.out.print("Please select an answer from 1-4 or select 0 to quit\n> ");
				String input = scanner.next();
				
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
				if(answer != game.getOneQuestion().getCorrectAnswerIdx()) {
					// Incorrect answer, print message and deduct life.
					System.out.println("GRRR, the creature is disappointed in your lack of knowledge.  As penalty, you have been shaved off 10 life points.");
					game.setLife(game.getLife() - Game.loss);
					System.out.println("You now have " + game.getLife() + " life left.");
					
					// If we hit 0, we are a wee bit dead. :(
					if(game.getLife() <= 0) {
						this.displayLoseMessage();
						System.exit(1);
					}
					else
						System.out.println("However, you still cannot move on, so you have to answer the question again.\n");
					
				}
				else {
					
					// Print a successful message
					System.out.println("The creature looks happy, as if it received a lot of head pats.  You may now proceed.");
					
					// Change the boolean to be true for the current position
					game.getOneQuestion().setAnsweredCorrectly(true);
					
					// MVP: move onto next question on our level
					// game.setY(game.getY() + 1);
					
					// But if we finished the last question on the current level, move onto the next.  Another function :)
					// game.moveOntoNextLevel();
					
					// Stretch goal: comment out 2 lines above
					
					// Don't answer the question again.
					invalid = false;
				}
			} 
			catch(InvalidAnswerException e) {
				System.out.println(e.getMessage());
			}
		} while(invalid);
	}
	
	public void showMaze() {
		
	}
	
	public void move() {
		boolean invalid = true;
		do {
			try {
				System.out.print("Please choose to move up (1), down (2), left (3), or right (4):\n> ");
				String input = scanner.next();
				
				if(!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4"))
					throw new InvalidMovementException(input);
				
				// Which direction?
				if(input.equals("1")) {
					// Check if we are able to move in this direction
					if(game.getX() <= 0)
						throw new InvalidDirectionException("UP");
					
					// Move up
					game.setX(game.getX() - 1);
				}
				else if(input.equals("2")) {
					// Check if we are able to move in this direction
					if(game.getX() >= game.getNumLevels())
						throw new InvalidDirectionException("DOWN");
					
					// Move down
					game.setX(game.getX() + 1);
				}
				else if(input.equals("3")) {
					// Check if we are able to move in this direction
					if(game.getY() <= 0)
						throw new InvalidDirectionException("LEFT");
					
					// Move left
					game.setY(game.getY() - 1);
				}
				else {
					// Check if we are able to move in this direction
					if(game.getY() >= game.getNumQuestionsOnLevel())
						throw new InvalidDirectionException("RIGHT");
					
					// Move right
					game.setY(game.getY() + 1);
				}
				
				invalid = false;
				
			}
			catch(InvalidMovementException e) {
				System.out.println(e.getMessage());
			}
			catch(InvalidDirectionException e) {
				System.out.println(e.getMessage());
			}
			
		} while(invalid);
	}
	
	/**
	 * If we win the game, display this message
	 */
	public void displayWinMessage() {
		System.out.println("Wow Congratulations! You really know your stuff.");
		System.out.println("Now go into the world with your head held high knowing you beat the maze and that you are a true Java Developer!");
	}
	
	/**
	 * If we lose the game, display this message
	 */
	public void displayLoseMessage() {
		System.out.println("Well, well, well… and you call yourself a Java Developer.");
		System.out.println("Jokes on you. It’s clear that you need some help, so here’s a resource that will whip you into shape:");
		System.out.println("\t\t\thttps://www.w3schools.com/java/");
	}
}