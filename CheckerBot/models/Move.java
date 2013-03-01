package com.models;

public class Move {
	private Point startPoint;
	private Point finishPoint;
	private boolean fight = false;

	public boolean isFight() {
		return fight;
	}

	public void setFight(boolean fight) {
		this.fight = fight;
	}
	public Move(Point startPoint, Point finishPoint) {
		super();
		this.startPoint = startPoint;
		this.finishPoint = finishPoint;
	}
	
	public Move(Point startPoint, Point finishPoint, boolean fight) {
		super();
		this.startPoint = startPoint;
		this.finishPoint = finishPoint;
		this.fight = fight;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public Point getFinishPoint() {
		return finishPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public void setFinishPoint(Point finishPoint) {
		this.finishPoint = finishPoint;
	}

	public String toString() {
		return startPoint.getX() + ":" + startPoint.getY() + " - "
				+ finishPoint.getX() + ":" + finishPoint.getY() + " " + fight;
	}

}
