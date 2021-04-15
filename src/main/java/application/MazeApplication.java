package application;

import java.io.IOException;

import model.Game;

public class MazeApplication {

	public static void main(String[] args) throws IOException {
		System.out.println("Welcome to our Maze :)\n");
		
		Game game = new Game("src/main/resources/mazeInput.txt");
	}
}
