package de.pvr.fish.simulation.util;

import de.pvr.fish.simulation.model.Position;

public class RandomGenerator {

	
	public static int getRandomCoordinate() {
		return (int) (Math.random() * 1000 + 1);
	}
	
	public static Position getRandomPosition() {
		return new Position(getRandomCoordinate(), getRandomCoordinate());
	}
	
	public static int getRandomAngle() {
		return (int) (Math.random() * 360 + 1);
	}
}
