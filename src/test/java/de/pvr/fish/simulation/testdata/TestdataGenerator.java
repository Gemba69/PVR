package de.pvr.fish.simulation.testdata;

import de.pvr.fish.simulation.application.Field;
import de.pvr.fish.simulation.model.Fish;
import de.pvr.fish.simulation.model.Position;
import de.pvr.fish.simulation.util.RandomGenerator;

public class TestdataGenerator {

	
	public static Fish generateRandomFish() {
		Position p = RandomGenerator.getRandomPosition();
		return new Fish(p, RandomGenerator.getRandomNextPosition(p));
	}
	
	public static Field getEmptyField() {
		Field field = new Field(1000, 1000, 10 , 2);
		return field;
	}
	
	public static Field getFieldWithFishes(int maxFishes) {
		Field field = getEmptyField();
		
		for (int i = 0; i < maxFishes; i++) {
			if (!field.addNewFishToField(generateRandomFish())) {
				i--;
			};
		}	
		return field;
	}
	
	public static Field getFieldWith10Fishes() {
		return getFieldWithFishes(10);
	}
	
	public static Field getFieldWithSpecific10Fishes() {
		Field field = getEmptyField();
		
		field.addNewFishToField(new Fish(new Position(100,100), new Position(100, 102)));
		field.addNewFishToField(new Fish(new Position(102,102), new Position(100, 102)));
		field.addNewFishToField(new Fish(new Position(104,104), new Position(103, 102)));
		field.addNewFishToField(new Fish(new Position(101,102), new Position(100, 102)));
		field.addNewFishToField(new Fish(new Position(10,10), new Position(12, 12)));
		field.addNewFishToField(new Fish(new Position(105,103), new Position(105, 102)));
		field.addNewFishToField(new Fish(new Position(103,107), new Position(103, 106)));
		field.addNewFishToField(new Fish(new Position(99,99), new Position(100, 102)));
		field.addNewFishToField(new Fish(new Position(100,102), new Position(101, 102)));
		field.addNewFishToField(new Fish(new Position(500,500), new Position(502, 502))); //far away
		
		return field;
	}
	
}
