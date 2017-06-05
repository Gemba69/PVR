package de.pvr.fish.simulation.model;

import de.pvr.fish.simulation.util.Radius;

public class Fish {

	
	private Position position;
	private int angle;
	
	public Fish (int angle, Position position) {
		this.angle = angle;
		this.position = position;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public int getAngle() {
		return angle;
	}


	public void setAngle(int angle) {
		this.angle = angle;
	}
	
	public void turnAround() {
		this.angle = (this.angle + 180) % 360;
	}
}
