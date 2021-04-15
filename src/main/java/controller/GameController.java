package controller;

import java.io.IOException;
import java.util.Scanner;

import view.Game;

public class GameController {
	
	private Game game;
	
	// Handles all inputs
	private Scanner scanner;
	
	public GameController(String filename) throws IOException {
		game = new Game(filename);
	}
	
	public void introduction() {
		System.out.println("Welcome to the MazeRunner.");
		System.out.println("If you dare to enter you will be tested on your knowledge of all things Java.");
		System.out.println("Otherwise leave now and save yourself the embarrassment. To get out of the Maze successfully, answer each question correctly.");
		System.out.println("Every time you answer correctly, your life will be deducted. If your life hits 0 you lose. Good Luck!\n");
	}
	
	public void play() {
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
