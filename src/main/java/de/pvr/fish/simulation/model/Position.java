package de.pvr.fish.simulation.model;

import java.util.Objects;

import de.pvr.fish.simulation.config.FishConstants;

public class Position {

	
	private int coordinateX;
	private int coordinateY;
	
	public Position (int coordinateX, int coordinateY) {
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
	}
	
	
	public int getCoordinateX() {
		return this.coordinateX;
	}
	
	public void setCoordinateX(int coordinateX) {
		this.coordinateX = coordinateX;
	}
	
	public int getCoordinateY() {
		return this.coordinateY;
	}
	
	public void setCoordinateY(int coordinateY) {
		this.coordinateY = coordinateY;
	}
	
	public void nextPosition() {
		if (FishConstants.FIELD_LENGTH - 1 > this.coordinateY) {
			this.coordinateY++;
		} else {
			this.coordinateY = 0;
			this.coordinateX++;
			//TODO Randbedingung Ende des Feldes - auch weiter unten die beiden und als Testabdeckung
		}
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        if (this.coordinateX == position.coordinateX && this.coordinateY == position.coordinateY) {
        	return true;
        } else {
        	return false;
        }
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.coordinateX + this.coordinateY);
    }

    @Override
    public String toString() {
        return "Position{" +
                "coordinateX=" + this.coordinateX +
                ", coordinateY=" + this.coordinateY +
                "}";
    }
	
	public Position getRadiusStartPosition(int radius) {
		int positionX = this.coordinateX - radius;
		if (positionX < 0) {
			positionX = 0;
		}
		int positionY = this.coordinateY - radius;
		if (positionY < 0) {
			positionY = 0;
		}
		return new Position(positionX, positionY);
	}
	
	public Position getRadiusEndPosition(int radius) {
		int positionX = this.coordinateX + radius;
		if (positionX > FishConstants.FIELD_LENGTH) {
			positionX = FishConstants.FIELD_LENGTH;
		}
		int positionY = this.coordinateY + radius;
		if (positionY > FishConstants.FIELD_HEIGHT - 1) {
			positionY = FishConstants.FIELD_HEIGHT - 1;
		}
		return new Position(positionX, positionY);
	}
	
}
