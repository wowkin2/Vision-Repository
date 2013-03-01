package com.checkers;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import com.models.Move;
import com.models.Point;

public class Player {
	private static LinkedList<Move> availableMoves;
	private static LinkedList<Move> priorityMoves;

	private static int beatedFigures;
	private StepManager sm;
	private Logic lg;
	private Field field;
	
	String[][] board = new String[8][8];
	
	Player() throws AWTException, IOException {

		field = new Field();
		//availableMoves = new LinkedList<Move>();
		availableMoves = new LinkedList<Move>();
		priorityMoves = new LinkedList<Move>();
		sm = new StepManager();
		lg = new Logic();
	}

	private void addCorrectMoves(int i, int j, String playerSide) {
		String enemy;
		if (playerSide.equals("yellow")) {
			enemy = "pink";
		} else {
			enemy = "yellow";
		}
		// ij
		// ++
		if ((i + 1 >= 0) && (i + 1 < 8) && (j + 1 >= 0) && (j + 1 < 8)) {
			if (board[i + 1][j + 1].equals("dark")) {
				// availableMoves.add(new Move(new Point(i, j), new Point(i + 1,
				// j + 1)));
			} else if (board[i + 1][j + 1].startsWith(enemy)) {
				if ((i + 2 >= 0) && (i + 2 < 8) && (j + 2 >= 0) && (j + 2 < 8)) {
					if (board[i + 2][j + 2].equals("dark")) {
						availableMoves.add(new Move(new Point(i, j), new Point(
								i + 2, j + 2), true));
					}
				}
			}
		}
		// +-
		if ((i + 1 >= 0) && (i + 1 < 8) && (j - 1 >= 0) && (j - 1 < 8)) {
			if (board[i + 1][j - 1].equals("dark")) {
				availableMoves.add(new Move(new Point(i, j), new Point(i + 1,
						j - 1)));
			} else if (board[i + 1][j - 1].startsWith(enemy)) {
				if ((i + 2 >= 0) && (i + 2 < 8) && (j - 2 >= 0) && (j - 2 < 8)) {
					if (board[i + 2][j - 2].equals("dark")) {
						availableMoves.add(new Move(new Point(i, j), new Point(
								i + 2, j - 2), true));
					}
				}
			}
		}
		// -+
		if ((i - 1 >= 0) && (i - 1 < 8) && (j + 1 >= 0) && (j + 1 < 8)) {
			if (board[i - 1][j + 1].equals("dark")) {
				// availableMoves.add(new Move(new Point(i, j), new Point(i - 1,
				// j + 1)));
			} else if (board[i - 1][j + 1].startsWith(enemy)) {
				if ((i - 2 >= 0) && (i - 2 < 8) && (j + 2 >= 0) && (j + 2 < 8)) {
					if (board[i - 2][j + 2].equals("dark")) {
						availableMoves.add(new Move(new Point(i, j), new Point(
								i - 2, j + 2), true));
					}
				}
			}
		}
		// --
		if ((i - 1 >= 0) && (i - 1 < 8) && (j - 1 >= 0) && (j - 1 < 8)) {
			if (board[i - 1][j - 1].equals("dark")) {
				availableMoves.add(new Move(new Point(i, j), new Point(i - 1,
						j - 1)));
			} else if (board[i - 1][j - 1].startsWith(enemy)) {
				if ((i - 2 >= 0) && (i - 2 < 8) && (j - 2 >= 0) && (j - 2 < 8)) {
					if (board[i - 2][j - 2].equals("dark")) {
						availableMoves.add(new Move(new Point(i, j), new Point(
								i - 2, j - 2), true));
					}
				}
			}
		}

	}
	public LinkedList<Move> getAvailableMoves(String playerSide, boolean war)
			throws FileNotFoundException, IOException, Exception {
		availableMoves.clear();
		board = field.getCurrentField();
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j].startsWith(playerSide)) {
					addCorrectMoves(i, j, playerSide);
				}
			}
		}
		return availableMoves;
	}
	
	public void play(boolean mustBeat) throws FileNotFoundException, Exception,
			Exception {
		priorityMoves.clear();
		availableMoves.clear();
		availableMoves = getAvailableMoves("yellow", mustBeat);
		for (Move m : availableMoves) {
			if (m.isFight()) {
				priorityMoves.add(m);
			}
			System.out.println(m.toString());
		}

		if (!priorityMoves.isEmpty()) {
			sm.move(priorityMoves.get(lg.getBestMove(priorityMoves)), board);
			beatedFigures++;
			System.out.println("---");
			play(true);
		} else if (!mustBeat && !availableMoves.isEmpty()) {
			sm.move(availableMoves.get(lg.getBestMove(availableMoves)), board);
			System.out.println("+++");
		}
	}

	public static void main(String[] args) throws AWTException, Exception {
		// TODO Auto-generated method stub

		Thread.sleep(5000);
		Player player = new Player();
		beatedFigures = 0;
		while( beatedFigures < 12 ){
			player.play(false);
		}

	}

}
