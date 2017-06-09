package de.pvr.fish.simulation.model;

import java.util.Objects;

import de.pvr.fish.simulation.config.FishParameter;
import de.pvr.fish.simulation.util.RandomGenerator;

public class Fish {

	
	private Position position;
	private Position nextPosition;
	
	public Fish (Position position, Position nextPosition) {
		this.position = position;
		this.nextPosition = nextPosition;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	public Position getNextPosition() {
		return this.nextPosition;
	}
	
	public void setNextPosition(Position nextPosition) {
		this.nextPosition = nextPosition;
	}
	
	public void turnAround() {
		turnAt(-180);
	}
	
	public void turnAt(int angle) {
		angle *= -1;
		Position p = this.position.getDiffBetweenPositions(this.nextPosition);
		Position newNextPosition = new Position(this.position.getCoordinateX(), this.position.getCoordinateY());
		
		p.addSpecificAngle(angle);
		newNextPosition.addPosition(p);
		
		this.nextPosition = newNextPosition;
	}
	
	public boolean isInDeathAngle(Position positon) {
		int angleBetween = this.position.getAngle(positon);
		
		if (getAngle() - Math.abs(angleBetween) > 180 + FishParameter.DEATH_ANGLE / 2 || getAngle() - Math.abs(angleBetween) < 180 - FishParameter.DEATH_ANGLE / 2 ) {
			return false;
		} else {
			return true;
		}
	}
	
	public int getAngle() {
		return this.position.getAngle(this.nextPosition);
	}
	
	public double getNewSpeed() {
		// TODO 2 eleganter machen
		return RandomGenerator.getRandomSpeed(3);
	}
	
	public void goToNextPosition(double speed) {
		Position newPosition = new Position(this.nextPosition.getCoordinateX(), this.nextPosition.getCoordinateY());
		newPosition.addLength(speed);
		int angle = getAngle();
		this.position = this.nextPosition;
		this.nextPosition = newPosition;
		turnAt(angle - 180);
	}
	
	public void goToNextPosition() {
		goToNextPosition(getNewSpeed());
	}
	
	public Position getDiffPosition() {
		return this.position.getDiffBetweenPositions(nextPosition);
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fish fish = (Fish) o;
        if (this.position.getCoordinateX() == fish.position.getCoordinateX() && this.position.getCoordinateY() == fish.position.getCoordinateY() && this.nextPosition.getCoordinateX() == fish.nextPosition.getCoordinateX() && this.nextPosition.getCoordinateY() == fish.nextPosition.getCoordinateY()) {
        	return true;
        } else {
        	return false;
        }
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.position.getCoordinateX() + this.position.getCoordinateY() + this.nextPosition.getCoordinateX() + this.nextPosition.getCoordinateY());
    }

    @Override
    public String toString() {
        return "Fish{" +
                "coordinateX=" + this.position.getCoordinateX() +
                ", coordinateY=" + this.position.getCoordinateY() +
                ", nextCoordinateX=" + this.nextPosition.getCoordinateX() +
                ", nextCoordinateY=" + this.nextPosition.getCoordinateY() +
                "}";
    }
    
}
