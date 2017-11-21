package com.trashmelody.beatmap.parser.utils;

public class Vector2 {

	private double positionX, positionY;
	
	public Vector2(double positionX, double positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
	}
	
	public Vector2 add(Vector2 position) {
		return new Vector2(positionX + position.getX(), positionY + position.getY());
	}
	
	public Vector2 subtract(Vector2 position) {
		return new Vector2(positionX - position.getX(), positionY - position.getY());
	}
	
	public Vector2 scale(double scale) {
		return new Vector2(positionX*scale, positionY*scale);
	}
	
	public double length() {
		return Math.sqrt(positionX*positionX + positionY*positionY);
	}
	
	public double distance(Vector2 position) {
		return position.subtract(this).length();
	}

	public double getX() {
		return positionX;
	}

	public double getY() {
		return positionY;
	}
	
	public void setX(double x) {
		this.positionX = x;
	}

	public void setY(double y) {
		this.positionY = y;
	}
	
	public Vector2 clone() {
		return new Vector2(positionX, positionY);
	}

	public com.badlogic.gdx.math.Vector2 toGdxVector() {
		return new com.badlogic.gdx.math.Vector2((float) positionX, (float) positionY);
	}

	@Override
	public String toString() {
		return String.format("(x: %.2f, y: %.2f)", positionX, positionY);
	}
}
