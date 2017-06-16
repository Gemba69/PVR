package de.pvr.fish.simulation.util;

import de.pvr.fish.simulation.config.FishParameter;
import de.pvr.fish.simulation.model.Fish;
import de.pvr.fish.simulation.model.Position;

public class RandomGenerator {

	
	public static int getRandomCoordinate(int number) {
		return (int) getRandomNumber(number);
	}
	
	public static double getRandomNumber(int area) {
		return (Math.random() * area );
	}
	
	public static Position getRandomPosition() {
		return getRandomPosition(300, 300);
	}
	
	public static Position getRandomPosition(int length, int height) {
		return new Position(getRandomCoordinate(length), getRandomCoordinate(height));
	}
	
	public static Position getRandomNextPosition(Position p) {
		double coordX = p.getCoordinateX() + getRandomRangeNumber();
		double coordY = p.getCoordinateY() + getRandomRangeNumber();
		while (coordX > FishParameter.FIELD_LENGTH || coordX < 0) {
			coordX = p.getCoordinateX() + getRandomRangeNumber();
		}
		while (coordY > FishParameter.FIELD_HEIGHT || coordY < 0) {
			coordY = p.getCoordinateY() + getRandomRangeNumber();
		}
		return new Position(coordX, coordY);
	}
	
	public static int getRandomAngle() {
		return (int) (Math.random() * 360 );
	}
	
	public static Fish getRandomFish(int length, int height) {
		Position p = getRandomPosition(length, height);
		return new Fish(p, getRandomNextPosition(p));
	}
	
	public static double getRandomSpeed() {
		return getRandomNumber(3);
	}
	
	public static double getRandomSpeed(int maxSpeed) {
		return getRandomNumber(maxSpeed);
	}
	
	public static int getRandomRangeNumber() {
		return getRandomRangeNumber(2);
	}
	
	public static int getRandomRangeNumber(int number) {
		return (int) getRandomNumber(number);
	}

}
