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
	
	public void play() {
		introduction();
		game.playGame();
		
	}
	
	public void displayWinMessage() {
		System.out.println("Wow Congratulations! You really know your stuff.");
		System.out.println("Now go into the world with your head held high knowing you beat the maze and that you are a true Java Developer!");
	}
	
	public void displayLoseMessage() {
		System.out.println("Well, well, well… and you call yourself a Java Developer.");
		System.out.println("Jokes on you. It’s clear that you need some help, so here’s a resource that will whip you into shape:");
		System.out.println("\t\t\thttps://www.w3schools.com/java/");
	}
}
