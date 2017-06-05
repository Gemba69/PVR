package de.pvr.fish.simulation.model;

import de.pvr.fish.simulation.util.Radius;

public class Fish {

	
	private Position position;
	private int angle;
	
	private Fish neighborFish;
	
	public Fish (int angle, Position position) {
		this.angle = angle;
		this.position = position;
	}
	
	public void move(Radius radius) {
		switch (radius) {
		case R1:
			this.angle = (this.angle + 90) % 360;
		case R2:
			this.angle = this.neighborFish.getAngle();
		case R3:
			this.angle = this.neighborFish.getAngle();
			this.position = new Position (this.neighborFish.getPosition().getCoordinateX() - this.position.getCoordinateX(), this.neighborFish.getPosition().getCoordinateY() - this.position.getCoordinateY());
		case R4:
			this.angle = (this.angle + 180) % 360;
		break;
					
		}		
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


	public Fish getNeighborFish() {
		return neighborFish;
	}


	public void setNeighborFish(Fish neighborFish) {
		this.neighborFish = neighborFish;
	}
}
