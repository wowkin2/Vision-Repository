package com.checkers;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;
import java.util.Random;
import com.models.Move;
import com.models.Point;

public class Logic {
	public LinkedList<Move> availableMoves; // All available moves
	private LinkedList<Move> priorityMoves; // Contain order if there are many
											// moves(beats)
	private LinkedList<Move> bestMoveOrder; // Most priority moves
	private static Random rand = new Random();
	public String[][] board = new String[8][8];

	public static String playerSide = "yellow"; // yellow is default
	private String enemySide = "pink"; // pink is default

	Logic() throws IOException, AWTException {
		if (playerSide.equals("yellow")) {
			enemySide = "pink";
		} else {
			enemySide = "yellow";
		}
		availableMoves = new LinkedList<Move>();
		priorityMoves = new LinkedList<Move>();
		bestMoveOrder = new LinkedList<Move>();
	}

	// Main Logic Method
	// Check all available diagonals for simple figures and Queens
	private void addMovesByCoords(int i, int j, boolean isQueen, int priority) {
		if (isQueen) { // Method to check Queen Moves
			int tmpX, tmpY;
			int tmpBeatX, tmpBeatY;
			// ++
			tmpX = i;
			tmpY = j;
			while (true) {
				tmpBeatX = 1;
				tmpBeatY = 1;
				tmpX++;
				tmpY++;
				if ((tmpX >= 0) && (tmpX < 8) && (tmpY >= 0) && (tmpY < 8)) {
					if (board[tmpX][tmpY].equals("dark")) {
						availableMoves.add(new Move(new Point(i, j), new Point(
								tmpX, tmpY)));
					} else if (board[tmpX][tmpY].startsWith(enemySide)) {
						while (true) {
							if ((tmpX + tmpBeatX >= 0) && (tmpX + tmpBeatX < 8)
									&& (tmpY + tmpBeatY >= 0)
									&& (tmpY + tmpBeatY < 8)) {
								if (board[tmpX + tmpBeatX][tmpY + tmpBeatY]
										.equals("dark")) {
									availableMoves.add(new Move(
											new Point(i, j),
											new Point(tmpX + tmpBeatX, tmpY
													+ tmpBeatY), priority + 1));
								}
								tmpBeatX++;
								tmpBeatY++;
							} else {
								break;
							}
						}
					}
				} else {
					break;
				}
			}
			// +-
			tmpX = i;
			tmpY = j;
			while (true) {
				tmpBeatX = 1;
				tmpBeatY = 1;
				tmpX++;
				tmpY--;
				if ((tmpX >= 0) && (tmpX < 8) && (tmpY >= 0) && (tmpY < 8)) {
					if (board[tmpX][tmpY].equals("dark")) {
						availableMoves.add(new Move(new Point(i, j), new Point(
								tmpX, tmpY)));
					} else if (board[tmpX][tmpY].startsWith(enemySide)) {
						if ((tmpX + tmpBeatX >= 0) && (tmpX + tmpBeatX < 8)
								&& (tmpY + tmpBeatY >= 0)
								&& (tmpY + tmpBeatY < 8)) {
							while (true) {
								if (board[tmpX + tmpBeatX][tmpY + tmpBeatY]
										.equals("dark")) {
									availableMoves.add(new Move(
											new Point(i, j),
											new Point(tmpX + tmpBeatX, tmpY
													- tmpBeatY), priority + 1));
								} else {
									break;
								}
								tmpBeatX++;
								tmpBeatY--;
							}
						}
					}
				} else {
					break;
				}
			}
			// -+
			tmpX = i;
			tmpY = j;
			while (true) {
				tmpBeatX = 1;
				tmpBeatY = 1;
				tmpX--;
				tmpY++;
				if ((tmpX >= 0) && (tmpX < 8) && (tmpY >= 0) && (tmpY < 8)) {
					if (board[tmpX][tmpY].equals("dark")) {
						availableMoves.add(new Move(new Point(i, j), new Point(
								tmpX, tmpY)));
					} else if (board[tmpX][tmpY].startsWith(enemySide)) {
						if ((tmpX + tmpBeatX >= 0) && (tmpX + tmpBeatX < 8)
								&& (tmpY + tmpBeatY >= 0)
								&& (tmpY + tmpBeatY < 8)) {
							while (true) {
								if (board[tmpX + tmpBeatX][tmpY + tmpBeatY]
										.equals("dark")) {
									availableMoves.add(new Move(
											new Point(i, j),
											new Point(tmpX - tmpBeatX, tmpY
													+ tmpBeatY), priority + 1));
								} else {
									break;
								}
								tmpBeatX--;
								tmpBeatY++;
							}
						}
					}
				} else {
					break;
				}
			}
			// --
			tmpX = i;
			tmpY = j;
			while (true) {
				tmpBeatX = 1;
				tmpBeatY = 1;
				tmpX--;
				tmpY--;
				if ((tmpX >= 0) && (tmpX < 8) && (tmpY >= 0) && (tmpY < 8)) {
					if (board[tmpX][tmpY].equals("dark")) {
						availableMoves.add(new Move(new Point(i, j), new Point(
								tmpX, tmpY)));
					} else if (board[tmpX][tmpY].startsWith(enemySide)) {
						if ((tmpX + tmpBeatX >= 0) && (tmpX + tmpBeatX < 8)
								&& (tmpY + tmpBeatY >= 0)
								&& (tmpY + tmpBeatY < 8)) {
							while (true) {
								if (board[tmpX + tmpBeatX][tmpY + tmpBeatY]
										.equals("dark")) {
									availableMoves.add(new Move(
											new Point(i, j),
											new Point(tmpX - tmpBeatX, tmpY
													- tmpBeatY), priority + 1));
								} else {
									break;
								}
								tmpBeatX--;
								tmpBeatY--;
							}
						}
					}
				} else {
					break;
				}
			}
		} else {
			// Coords ++
			if ((i + 1 >= 0) && (i + 1 < 8) && (j + 1 >= 0) && (j + 1 < 8)) {
				if (board[i + 1][j + 1].equals("dark")) {
					// availableMoves.add(new Move(new Point(i, j), new Point(i
					// + 1,
					// j + 1)));
				} else if (board[i + 1][j + 1].startsWith(enemySide)) {
					if ((i + 2 >= 0) && (i + 2 < 8) && (j + 2 >= 0)
							&& (j + 2 < 8)) {
						if (board[i + 2][j + 2].equals("dark")) {
							availableMoves.add(new Move(new Point(i, j),
									new Point(i + 2, j + 2), priority + 1));
						}
					}
				}
			}
			// +-
			if ((i + 1 >= 0) && (i + 1 < 8) && (j - 1 >= 0) && (j - 1 < 8)) {
				if (board[i + 1][j - 1].equals("dark")) {
					availableMoves.add(new Move(new Point(i, j), new Point(
							i + 1, j - 1)));
				} else if (board[i + 1][j - 1].startsWith(enemySide)) {
					if ((i + 2 >= 0) && (i + 2 < 8) && (j - 2 >= 0)
							&& (j - 2 < 8)) {
						if (board[i + 2][j - 2].equals("dark")) {
							availableMoves.add(new Move(new Point(i, j),
									new Point(i + 2, j - 2), priority + 1));
						}
					}
				}
			}
			// -+
			if ((i - 1 >= 0) && (i - 1 < 8) && (j + 1 >= 0) && (j + 1 < 8)) {
				if (board[i - 1][j + 1].equals("dark")) {
					// availableMoves.add(new Move(new Point(i, j), new Point(i
					// - 1,
					// j + 1)));
				} else if (board[i - 1][j + 1].startsWith(enemySide)) {
					if ((i - 2 >= 0) && (i - 2 < 8) && (j + 2 >= 0)
							&& (j + 2 < 8)) {
						if (board[i - 2][j + 2].equals("dark")) {
							availableMoves.add(new Move(new Point(i, j),
									new Point(i - 2, j + 2), priority + 1));
						}
					}
				}
			}
			// --
			if ((i - 1 >= 0) && (i - 1 < 8) && (j - 1 >= 0) && (j - 1 < 8)) {
				if (board[i - 1][j - 1].equals("dark")) {
					availableMoves.add(new Move(new Point(i, j), new Point(
							i - 1, j - 1)));
				} else if (board[i - 1][j - 1].startsWith(enemySide)) {
					if ((i - 2 >= 0) && (i - 2 < 8) && (j - 2 >= 0)
							&& (j - 2 < 8)) {
						if (board[i - 2][j - 2].equals("dark")) {
							availableMoves.add(new Move(new Point(i, j),
									new Point(i - 2, j - 2), priority + 1));
						}
					}
				}
			}
		}

	}

	// Recursively find double-beats & and set +1 priority to each beats-thread
	private void recursiveMovesCheck(int priorityIndex) {
		boolean isDoubles = false;
		try {
			for (Move m : availableMoves) {
				if (m.getPriority() > priorityIndex) {
					addMovesByCoords(m.getFinishPoint().getX(), m
							.getFinishPoint().getY(), false, priorityIndex + 1);
					isDoubles = true;
				}
			}
			if (isDoubles) {
				recursiveMovesCheck(priorityIndex + 1);
			}
		} catch (ConcurrentModificationException e) {
			// do nothing
		}
	}

	// Return all available moves
	public LinkedList<Move> getAvailableMoves(Eyes eyes) throws Exception {
		board = eyes.getCurrentField();

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j].startsWith(playerSide)) {
					addMovesByCoords(i, j, // Coords of each players figures
							board[i][j].endsWith("_q"),// return true if queen
							0);// 0 - Start move priority
				}
			}
		}
		recursiveMovesCheck(0);
		return availableMoves;
	}

	public boolean isEndOfGame() {
		boolean player1 = false; // player
		boolean player2 = false; // enemy
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (!player1) {
					if (board[i][j].startsWith(playerSide)) {
						player1 = true;
					}
				}
				if (!player2) {
					if (board[i][j].startsWith(enemySide)) {
						player2 = true;
					}
				}
				if (player1 && player2) {
					return false;
				}
			}
		}
		if (player1) {
			System.out.println("******************" + "\n*** You Win ! ***");
		} else {
			System.out.println("******************" + "\n*** You lose! ***");
		}
		return true;
	}

	private int getMaxPriority(LinkedList<Move> ListMoves) {
		int startPriority = 0;
		for (Move m : ListMoves) {
			if (m.getPriority() > startPriority) {
				startPriority = m.getPriority();
			}
		}
		return startPriority;
	}

	public LinkedList<Move> getBestMove(LinkedList<Move> ListMoves) {
		int maxPriority = getMaxPriority(ListMoves);
		priorityMoves.clear();
		bestMoveOrder.clear();
		// Find max priority move
		for (Move m : ListMoves) {
			System.out.print(m + ", ");
			if (m.getPriority() == maxPriority) {
				priorityMoves.add(m);
			}
		}
		System.out.println();
		int r = rand.nextInt(priorityMoves.size());
		Move maxPriorityMove = priorityMoves.get(r);
		int minPriority = maxPriority;
		// Find the way to maxPriorityMove
		bestMoveOrder.addFirst(maxPriorityMove);
		while (maxPriorityMove.getPriority() == minPriority && minPriority > 1) {
			for (Move m : ListMoves) {
				if (maxPriorityMove.getStartPoint().equals(m.getFinishPoint())) {
					bestMoveOrder.addFirst(m);
					maxPriorityMove = m;
					minPriority--;
					break;
				}
			}
			minPriority--;
		}

		return bestMoveOrder;
	}
}