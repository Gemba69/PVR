package de.pvr.fish.simulation.application;

import java.util.Vector;

import de.pvr.fish.simulation.config.FishParameter;
import de.pvr.fish.simulation.model.Fish;
import de.pvr.fish.simulation.model.Position;
import de.pvr.fish.simulation.util.RandomGenerator;

public class Application {

	
	public static void main(String[] args) {
		//TODO Parameter auslesen und validieren (nicht mehr Fische als auf Feld passen
		
		Field field = new Field(FishParameter.FIELD_LENGTH, FishParameter.FIELD_HEIGHT, FishParameter.NUMBER_FISH, FishParameter.THREADS);
		
		field.setFishes(createRandomFishes(FishParameter.NUMBER_FISH, field.getLength(), field.getHeight()));
		
		for (int i = 0; i < FishParameter.ITERATIONS; i++) {
			field.nextInteration();
		}
	}
	
	private static Fish[][] createRandomFishes(int fishNumber, int fieldLength, int fieldHeight) {
		Fish[][] fishes = new Fish[fieldLength][fieldHeight];
		Fish fish;
		for (int i = 0; i > fishNumber; i++) {
			fish = createRandomFish(fieldLength, fieldHeight);
			if (fishes[(int) fish.getPosition().getCoordinateX()][(int) fish.getPosition().getCoordinateX()] == null) {
				fishes[(int) fish.getPosition().getCoordinateX()][(int) fish.getPosition().getCoordinateX()] = fish;
			} else {
				i--;
			}
		}
		
		return fishes;
		
	}
	
	private static Fish createRandomFish(int fieldLength, int fieldHeight) {
		return RandomGenerator.getRandomFish(fieldLength, fieldHeight);
	}

}
