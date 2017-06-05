package de.pvr.fish.simulation.model;

import java.util.Objects;

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
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fish fish = (Fish) o;
        if (this.angle == fish.angle && this.position.getCoordinateX() == fish.position.getCoordinateX() && this.position.getCoordinateY() == fish.position.getCoordinateY()) {
        	return true;
        } else {
        	return false;
        }
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.position.getCoordinateX() + this.position.getCoordinateY() + this.angle);
    }

    @Override
    public String toString() {
        return "Fish{" +
                "coordinateX=" + this.position.getCoordinateX() +
                ", coordinateY=" + this.position.getCoordinateY() +
                ", angle=" + this.angle +
                "}";
    }
}
