package application;

import java.io.IOException;

import controller.GameController;

public class MazeApplication {

	public static void main(String[] args) throws IOException {
		
		GameController game = new GameController("src/main/resources/mazeInput.txt");
		game.introduction();
		
		game.play();
		
		// We lose regardless :)
		game.displayLoseMessage();
	}
}
