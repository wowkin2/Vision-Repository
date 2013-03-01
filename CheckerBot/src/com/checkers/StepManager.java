package com.checkers;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.IOException;

import com.models.Move;
import com.models.Point;

public class StepManager {

	private Robot robot;
	//private static final long stepDelay = 2000;
	

	public StepManager() throws AWTException, IOException {
		robot = new Robot();
	}

	public void move(Move availableMove, String[][] board) throws InterruptedException {
		Point start = availableMove.getStartPoint();
		Point finish = availableMove.getFinishPoint();
		int startPosX = Field.startX + Field.plateSize * start.getX()
				+ Field.plateSize / 2;
		int startPosY = Field.startY + Field.plateSize * start.getY()
				+ Field.plateSize / 2;
		int finishPosX = Field.startX + Field.plateSize * finish.getX()
				+ Field.plateSize / 2;
		int finishPosY = Field.startY + Field.plateSize * finish.getY()
				+ Field.plateSize / 2;
		robot.mouseMove(startPosX, startPosY);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		Thread.sleep(1000);
		robot.mouseMove(finishPosX, finishPosY);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);

		board[finish.getX()][finish.getY()] = board[start.getX()][start.getY()];
		board[start.getX()][start.getY()] = Field.getNameByNumber[0];
		Thread.sleep(2000);
	}
}
