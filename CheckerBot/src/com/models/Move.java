package com.models;

public class Move {
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Move other = (Move) obj;
		if (finishPoint == null) {
			if (other.finishPoint != null)
				return false;
		} else if (!finishPoint.equals(other.finishPoint))
			return false;
		if (priority != other.priority)
			return false;
		if (startPoint == null) {
			if (other.startPoint != null)
				return false;
		} else if (!startPoint.equals(other.startPoint))
			return false;
		return true;
	}

	private Point startPoint;
	private Point finishPoint;
	private int priority = 0;

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Move(Point startPoint, Point finishPoint) {
		super();
		this.startPoint = startPoint;
		this.finishPoint = finishPoint;
	}

	public Move(Point startPoint, Point finishPoint, int priority) {
		super();
		this.startPoint = startPoint;
		this.finishPoint = finishPoint;
		this.priority = priority;
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
		return "" + ((char) (startPoint.getX() + 65)) + (8 - startPoint.getY())
				+ "-" + ((char) (finishPoint.getX() + 65))
				+ (8 - finishPoint.getY())
				+ (priority > 0 ? " p" + priority : "");
	}
}
