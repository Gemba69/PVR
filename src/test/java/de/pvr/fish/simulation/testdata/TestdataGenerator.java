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
		field.addNewFishToField(new Fish(new Position(500,500), new Position(502, 502))); //far away
		field.prepareTaskLists();
		return field;
	}
	
	public static Field getFieldWithSpecific10Fishes2() {
		Field field = getEmptyField();
		
		field.addNewFishToField(new Fish(new Position(489,444), new Position(491, 488)));
		field.addNewFishToField(new Fish(new Position(291,328), new Position(293,291)));
		field.addNewFishToField(new Fish(new Position(48,216), new Position(102, 102)));
		field.addNewFishToField(new Fish(new Position(201,102), new Position(200, 102)));
		field.addNewFishToField(new Fish(new Position(84,10), new Position(85, 11)));
		field.addNewFishToField(new Fish(new Position(100,100), new Position(98, 100)));
		field.addNewFishToField(new Fish(new Position(20,20), new Position(19, 20)));
		field.addNewFishToField(new Fish(new Position(248,104), new Position(250, 102)));
		field.addNewFishToField(new Fish(new Position(188,102), new Position(186, 102)));
		field.addNewFishToField(new Fish(new Position(160,44), new Position(81, 49)));
		
//		Fish{coordinateX=489.0, coordinateY=444.0, nextCoordinateX=491.0, nextCoordinateY=488.0, calcNextCoordinateX=479.0, calcNextCoordinateY=401.0}
//		Fish{coordinateX=291.0, coordinateY=328.0, nextCoordinateX=293.0, nextCoordinateY=291.0, calcNextCoordinateX=316.0, calcNextCoordinateY=356.0}
//		Fish{coordinateX=48.0, coordinateY=216.0, nextCoordinateX=50.0, nextCoordinateY=47.0, calcNextCoordinateX=67.0, calcNextCoordinateY=384.0}
//		Fish{coordinateX=145.0, coordinateY=448.0, nextCoordinateX=145.0, nextCoordinateY=146.0, calcNextCoordinateX=389.0, calcNextCoordinateY=626.0}
//		Fish{coordinateX=462.0, coordinateY=181.0, nextCoordinateX=461.0, nextCoordinateY=464.0, calcNextCoordinateX=181.0, calcNextCoordinateY=150.0}
//		Fish{coordinateX=477.0, coordinateY=514.0, nextCoordinateX=479.0, nextCoordinateY=476.0, calcNextCoordinateX=439.0, calcNextCoordinateY=515.0}
//		Fish{coordinateX=574.0, coordinateY=262.0, nextCoordinateX=575.0, nextCoordinateY=576.0, calcNextCoordinateX=553.0, calcNextCoordinateY=575.0}
//		Fish{coordinateX=524.0, coordinateY=35.0, nextCoordinateX=526.0, nextCoordinateY=523.0, calcNextCoordinateX=736.0, calcNextCoordinateY=-404.0}
//		Fish{coordinateX=126.0, coordinateY=53.0, nextCoordinateX=128.0, nextCoordinateY=125.0, calcNextCoordinateX=56.0, calcNextCoordinateY=38.0}
//		Fish{coordinateX=597.0, coordinateY=503.0, nextCoordinateX=597.0, nextCoordinateY=596.0, calcNextCoordinateX=568.0, calcNextCoordinateY=415.0}

		field.prepareTaskLists();
		return field;
	}
	
}
