package de.pvr.fish.simulation.util;

import de.pvr.fish.simulation.model.Fish;
import de.pvr.fish.simulation.model.Position;

public class RandomGenerator {

	
	public static int getRandomCoordinate() {
		return getRandomNumber(1000);
	}
	
	public static int getRandomNumber(int area) {
		return (int) (Math.random() * area + 1);
	}
	
	public static Position getRandomPosition() {
		return getRandomPosition(1000, 1000);
	}
	
	public static Position getRandomPosition(int length, int height) {
		return new Position(getRandomCoordinate(), getRandomCoordinate());
	}
	
	public static int getRandomAngle() {
		return (int) (Math.random() * 360 + 1);
	}
	
	public static Fish getRandomFish(int length, int height) {
		return new Fish(getRandomPosition(length, height), getRandomPosition(length + 2, height + 2));
	}
	
	public static int getRandomSpeed() {
		return getRandomNumber(3);
	}
	
	public static int getRandomSpeed(int maxSpeed) {
		return getRandomNumber(maxSpeed);
	}
	
	public static int getRandomRangeNumber() {
		return getRandomRangeNumber(3);
	}
	
	public static int getRandomRangeNumber(int number) {
		return getRandomNumber(2 * number) - number;
	}
}
