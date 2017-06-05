package de.pvr.fish.simulaton.testdata;

import de.pvr.fish.simulation.model.Fish;
import de.pvr.fish.simulation.model.Position;
import de.pvr.fish.simulation.util.RandomGenerator;
import de.pvr.sosh.simulation.application.Field;

public class TestdataGenerator {

	
	public static Fish generateRandomFish() {
		Fish fish = new Fish(RandomGenerator.getRandomAngle(), RandomGenerator.getRandomPosition());
		return fish;
	}
	
	public static Field getEmptyField() {
		Field field = new Field(1000, 1000);
		return field;
	}
	
	public static Field getFieldWithFishes(int maxFishes) {
		Field field = getEmptyField();
		
		for (int i = 0; i < maxFishes; i++) {
			field.addNewFishToField(generateRandomFish());
		}	
		return field;
	}
	
	public static Field getFieldWith10Fishes() {
		return getFieldWithFishes(10);
	}
	
	public static Field getFieldWithSpecific10Fishes() {
		Field field = getEmptyField();
		
		field.addNewFishToField(new Fish(180, new Position(100,100)));
		field.addNewFishToField(new Fish(180, new Position(102,102)));
		field.addNewFishToField(new Fish(0, new Position(104,104)));
		field.addNewFishToField(new Fish(90, new Position(101,102)));
		field.addNewFishToField(new Fish(270, new Position(10,10)));
		field.addNewFishToField(new Fish(180, new Position(105,103)));
		field.addNewFishToField(new Fish(0, new Position(103,107)));
		field.addNewFishToField(new Fish(90, new Position(99,99)));
		field.addNewFishToField(new Fish(90, new Position(100,102)));
		field.addNewFishToField(new Fish(180, new Position(500,500))); //far away
		
		return field;
	}
	
}
