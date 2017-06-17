package de.pvr.fish.simulation.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.pvr.fish.simulation.config.FishParameter;
import de.pvr.fish.simulation.model.Fish;
import de.pvr.fish.simulation.model.Position;

public class RandomGenerator {
	
	private static final Logger LOG = LogManager.getLogger(RandomGenerator.class);

	
	public static int getRandomCoordinate(int number) {
		LOG.debug("Creating RandomCoordinate: ");
		return (int) (getRandomNumber(number - (FishParameter.FISH_BODY_LENGTH * 2)) + FishParameter.FISH_BODY_LENGTH);
	}
	
	public static double getRandomNumber(int area) {
		return (Math.random() * area );
	}
	
	public static Position getRandomPosition() {
		return getRandomPosition(FishParameter.FIELD_LENGTH, FishParameter.FIELD_HEIGHT);
	}
	
	public static Position getRandomPosition(int length, int height) {
		return new Position(getRandomCoordinate(length), getRandomCoordinate(height));
	}
	
	public static Position getRandomNextPosition(Position p) {
		double coordX = p.getCoordinateX() + getRandomRangeNumber();
		double coordY = p.getCoordinateY() + getRandomRangeNumber();
		while (coordX > FishParameter.FIELD_LENGTH - (FishParameter.FISH_BODY_LENGTH * 2) || coordX < 0 + (FishParameter.FISH_BODY_LENGTH * 2)) {
			coordX = p.getCoordinateX() + getRandomRangeNumber();
		}
		while (coordY > FishParameter.FIELD_HEIGHT - (FishParameter.FISH_BODY_LENGTH * 2) || coordY < 0 + (FishParameter.FISH_BODY_LENGTH * 2)) {
			coordY = p.getCoordinateY() + getRandomRangeNumber();
		}
		LOG.debug("NextPosition is: " + coordX + coordY);
		return new Position(coordX, coordY);
	}
	
	public static int getRandomAngle() {
		return (int) (Math.random() * 360 );
	}
	
	public static Fish getRandomFish(int length, int height) {
		Position p = getRandomPosition(length, height);
		Fish fish = new Fish(p, getRandomNextPosition(p));
		LOG.debug("Creating Random fish: " + fish);
		return fish;
	}
	
	public static double getRandomSpeed() {
		return getRandomNumber(3);
	}
	
	public static double getRandomSpeed(int maxSpeed) {
		return getRandomNumber(maxSpeed);
	}
	
	public static double getRandomRangeNumber() {
		LOG.debug("Fish body length: " + FishParameter.FISH_BODY_LENGTH);
		double speed = ((getRandomSpeed(FishParameter.MAX_SPEED_MULTIPLICATOR - 1) + 1) * FishParameter.FISH_BODY_LENGTH);
		return (getRandomRangeNumber((int) speed * 2) - speed);
	}
	
	public static int getRandomRangeNumber(int number) {
		return (int) getRandomNumber(number);
	}

}
