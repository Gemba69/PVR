package de.pvr.fish.simulation.util;

import de.pvr.fish.simulation.config.FishParameter;
import de.pvr.fish.simulation.model.Fish;
import de.pvr.fish.simulation.model.Position;

public class RandomGenerator {

	
	public static int getRandomCoordinate(int number) {
		return getRandomNumber(number);
	}
	
	public static int getRandomNumber(int area) {
		return (int) (Math.random() * area + 1);
	}
	
	public static Position getRandomPosition() {
		return getRandomPosition(1000, 1000);
	}
	
	public static Position getRandomPosition(int length, int height) {
		return new Position(getRandomCoordinate(length), getRandomCoordinate(height));
	}
	
	public static Position getRandomNextPosition(Position p) {
		double coordX = p.getCoordinateX() + getRandomRangeNumber();
		double coordY = p.getCoordinateX() + getRandomRangeNumber();
		while (coordX > FishParameter.FIELD_LENGTH || coordX < 0) {
			coordX = p.getCoordinateX() + getRandomRangeNumber();
		}
		while (coordY > FishParameter.FIELD_HEIGHT || coordY < 0) {
			coordY = p.getCoordinateY() + getRandomRangeNumber();
		}
		return new Position(coordX, coordY);
	}
	
	public static int getRandomAngle() {
		return (int) (Math.random() * 360 + 1);
	}
	
	public static Fish getRandomFish(int length, int height) {
		Position p = getRandomPosition(length, height);
		return new Fish(p, getRandomNextPosition(p));
	}
	
	public static int getRandomSpeed() {
		return getRandomNumber(3);
		//TODO 2 eleganter machen mit den anderen auf Speed mappen
	}
	
	public static int getRandomSpeed(int maxSpeed) {
		return getRandomNumber(maxSpeed);
	}
	
	public static int getRandomRangeNumber() {
		return getRandomRangeNumber(3);
	}
	
	public static int getRandomRangeNumber(int number) {
		return getRandomNumber(2) * FishParameter.FISH_BODY_LENGTH;
	}

}
