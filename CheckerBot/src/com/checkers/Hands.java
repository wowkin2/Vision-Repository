package com.checkers;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.util.LinkedList;

import com.models.Move;
import com.models.Point;
//Ex. "StepManager.java" class
public class Hands {

	private Robot robot;

	// private static final long stepDelay = 2000;

	public Hands() throws AWTException, IOException {
		robot = new Robot();
	}

	public void move(LinkedList<Move> availableMove, String[][] board)
			throws InterruptedException {
		System.out.print("Manager: ");
		for (Move m : availableMove) {
			System.out.print(m + ", ");
			Point start = m.getStartPoint();
			Point finish = m.getFinishPoint();
			int startPosX = Eyes.startX + Eyes.plateSize * start.getX()
					+ Eyes.plateSize / 2;
			int startPosY = Eyes.startY + Eyes.plateSize * start.getY()
					+ Eyes.plateSize / 2;
			int finishPosX = Eyes.startX + Eyes.plateSize * finish.getX()
					+ Eyes.plateSize / 2;
			int finishPosY = Eyes.startY + Eyes.plateSize * finish.getY()
					+ Eyes.plateSize / 2;

			robot.mouseMove(startPosX, startPosY);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			Thread.sleep(1000);
			robot.mouseMove(finishPosX, finishPosY);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);

			board[finish.getX()][finish.getY()] = board[start.getX()][start
					.getY()];
			board[start.getX()][start.getY()] = Eyes.getNameByNumber[0];
			Thread.sleep(1000);
		}
		System.out.println();
		Thread.sleep(1000);
	}

	public void click4NewGame() throws InterruptedException {
		System.out.println("Waiting before new game 3");
		Thread.sleep(1000);
		System.out.println("Waiting before new game 2");
		Thread.sleep(1000);
		System.out.println("Waiting before new game 1");
		Thread.sleep(1000);
		System.out.println("*** New Game ! ***");
		robot.mouseMove(500, 200); // Image of girl to play new game
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
}