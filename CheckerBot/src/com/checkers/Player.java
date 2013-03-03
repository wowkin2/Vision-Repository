package com.checkers;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Player {
	private Eyes eyes; // Get board with all checker (independently) from screen
	private Hands hands; // Move the cursor, clicks "Start new game"
	private static Logic brain; // Contain all logic: find available moves,
								// chose the best move from there,
								// check for end of game and its result

	Player() throws AWTException, IOException {
		eyes = new Eyes();
		hands = new Hands();
		brain = new Logic();
	}

	public void play() throws FileNotFoundException, Exception, Exception {
		brain.availableMoves.clear();
		brain.availableMoves = brain.getAvailableMoves(eyes);
		if (!brain.isEndOfGame() && !brain.availableMoves.isEmpty()) {
			hands.move(brain.getBestMove(brain.availableMoves), brain.board);
			System.out.println("");
		} else {
			System.out.println("******************" + "\n*** Game Over! ***"
					+ "\n******************");
			hands.click4NewGame();
		}
	}

	public static void main(String[] args) throws AWTException, Exception {
		Thread.sleep(5000); // Delay before start
		Player player = new Player();
		while (!brain.isEndOfGame()) { // Play one game, if it ended exit
			player.play();
		}
	}
}