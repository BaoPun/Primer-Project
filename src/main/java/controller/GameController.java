package controller;

import java.io.IOException;
import java.util.Scanner;

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
		introduction();
		
		// Game loop: play the game while we still have questions to answer (WIN) and/or we still have life (LOSE).
		while(game.getLife() > 0 && !game.checkIfWin()) {
			
			// Step 1: interact with the monster based on our current position
			interactWithMonster();
			
		}
		
		if(game.checkIfWin())
			displayWinMessage();
	}
	
	public void interactWithMonster() {
		
		// Print what level we're on.
		System.out.println("\nYou are on Level " + (game.getX() + 1) + ".  You have " + game.getLife() + " life left.");
		
		// If we haven't answered a question from the current monster, then prompt their question.
		if(!game.getOneQuestion().isAnsweredCorrectly()) {
			
			System.out.println("\nAs you enter the maze you encounter a strange Creature.\r");
			System.out.println("RRRR! who goes there? You shall not pass unless you can answer my question correctly.\n");
			
			// Print out the current question we're on.
			game.printOneQuestion();
			
			// Answer the question
			game.answerQuestion();
	
		}
		
		// Assuming we answer the question correctly, we get to move on
		System.out.println("\n");
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