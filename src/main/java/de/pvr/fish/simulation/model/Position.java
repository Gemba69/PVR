package de.pvr.fish.simulation.model;

import java.util.Objects;

import de.pvr.fish.simulation.config.FishParameter;
import de.pvr.fish.simulation.util.CommonUtil;

public class Position {

	
	private double coordinateX;
	private double coordinateY;
	
	public Position (double coordinateX, double coordinateY) {
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
	}
	
	
	public double getCoordinateX() {
		return this.coordinateX;
	}
	
	public void setCoordinateX(int coordinateX) {
		this.coordinateX = coordinateX;
	}
	
	public double getCoordinateY() {
		return this.coordinateY;
	}
	
	public void setCoordinateY(int coordinateY) {
		this.coordinateY = coordinateY;
	}
	
	public void nextPosition() {
		if (FishParameter.FIELD_HEIGHT - 1 > this.coordinateY) {
			this.coordinateY++;
		} else {
			if (FishParameter.FIELD_LENGTH - 1 > this.coordinateX) {
				this.coordinateY = 0;
				this.coordinateX++;
			}
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
	
	public void addPosition(Position p) {
		this.coordinateX += p.getCoordinateX();
		this.coordinateY += p.getCoordinateY();
	}
	
	public void addLength(double length) {
		this.coordinateX += length;
	}
	
	public int getAngle(Position target) {
		return CommonUtil.getAngle(this, target);
	}
	
	public Position getDiffBetweenPositions(Position target) {
		return new Position(target.coordinateX - this.coordinateX, target.coordinateY - this.coordinateY);
	}
	
	public void addSpecificAngle(int angle) {
		angle = angle * -1;
		double coordX = this.coordinateX * Math.cos(Math.toRadians(angle)) + this.coordinateY * - Math.sin(Math.toRadians(angle));
		double coordY = this.coordinateX * Math.sin(Math.toRadians(angle)) + this.coordinateY * Math.cos(Math.toRadians(angle));
		
		this.coordinateX = (Math.round(coordX * 100)/100);
		this.coordinateY = (Math.round(coordY * 100)/100);
	}
	
	public double getLength() {
		return Math.sqrt(Math.abs(coordinateX) * Math.abs(coordinateX) + Math.abs(coordinateY * Math.abs(coordinateY)));
	}
	
}
