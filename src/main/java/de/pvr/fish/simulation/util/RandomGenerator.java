package de.pvr.fish.simulation.util;

import de.pvr.fish.simulation.model.Fish;
import de.pvr.fish.simulation.model.Position;

public class RandomGenerator {

	
	public static int getRandomCoordinate() {
		return getRandomCoordinate(1000);
	}
	
	public static int getRandomCoordinate(int area) {
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
		return new Fish(getRandomAngle(), getRandomPosition(length, height));
	}
}
