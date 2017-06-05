package de.pvr.fish.simulation.model;

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
	
}
