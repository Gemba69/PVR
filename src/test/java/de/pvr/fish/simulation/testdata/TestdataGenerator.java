package de.pvr.fish.simulation.testdata;

import de.pvr.fish.simulation.model.Field;
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
		field.prepareTaskLists();
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
		field.addNewFishToField(new Fish(new Position(250,250), new Position(252, 252))); //far away
		field.prepareTaskLists();
		return field;
	}
	
	public static Field getFieldWithSpecific10Fishes2() {
		Field field = getEmptyField();
		
		field.addNewFishToField(new Fish(new Position(289,244), new Position(291, 288)));
		field.addNewFishToField(new Fish(new Position(291,328), new Position(293,291)));
		field.addNewFishToField(new Fish(new Position(48,216), new Position(102, 102)));
		field.addNewFishToField(new Fish(new Position(201,102), new Position(200, 102)));
		field.addNewFishToField(new Fish(new Position(84,10), new Position(85, 11)));
		field.addNewFishToField(new Fish(new Position(100,100), new Position(98, 100)));
		field.addNewFishToField(new Fish(new Position(20,20), new Position(19, 20)));
		field.addNewFishToField(new Fish(new Position(248,104), new Position(250, 102)));
		field.addNewFishToField(new Fish(new Position(188,102), new Position(186, 102)));
		field.addNewFishToField(new Fish(new Position(160,44), new Position(81, 49)));
		
		field.prepareTaskLists();
		return field;
	}
	
}
