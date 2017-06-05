package de.pvr.fish.simulaton.testdata;

import de.pvr.fish.simulation.model.Field;
import de.pvr.fish.simulation.model.Fish;
import de.pvr.fish.simulation.model.Position;
import de.pvr.fish.simulation.util.RandomGenerator;

public class TestdataGenerator {

	
	public Fish generateRandomFish() {
		Fish fish = new Fish();
		fish.setPosition(RandomGenerator.getRandomPosition());
		fish.setAngle(RandomGenerator.getRandomAngle());
		return fish;
	}
	
	
	
	public Field getEmptyField() {
		Field field = new Field(1000, 1000);
		return field;
	}
	
	public Field getFieldWithFishes(int maxFishes) {
		Field field = getEmptyField();
		
		for (int i = 0; i < maxFishes; i++) {
			field.addFish(generateRandomFish());
		}
		
		return field;
	}
	
	public Field getFieldWith10Fishes() {
		return getFieldWithFishes(10);
	}
	
}
