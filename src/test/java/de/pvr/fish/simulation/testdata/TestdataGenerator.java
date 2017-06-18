package de.pvr.fish.simulation.testdata;

import de.pvr.fish.simulation.config.FishParameter;
import de.pvr.fish.simulation.model.Aquarium;
import de.pvr.fish.simulation.model.Fish;
import de.pvr.fish.simulation.model.Position;
import de.pvr.fish.simulation.util.RandomGenerator;

public class TestdataGenerator {

	public static Fish generateRandomFish() {
		return RandomGenerator.getRandomFish(FishParameter.FIELD_LENGTH, FishParameter.FIELD_HEIGHT);
	}

	public static Aquarium getEmptyAquarium() {
		Aquarium aquarium = new Aquarium(FishParameter.FIELD_LENGTH, FishParameter.FIELD_HEIGHT, 10);
		return aquarium;
	}

	public static Aquarium getAquariumWithFishes(int maxFishes) {
		Aquarium aquarium = getEmptyAquarium();

		for (int i = 0; i < maxFishes; i++) {
			if (!aquarium.addNewFishToField(generateRandomFish())) {
				i--;
			}
			;
		}
		aquarium.prepareTaskLists();
		return aquarium;
	}

	public static Aquarium getAquariumWith10RandomFishes() {
		return getAquariumWithFishes(10);
	}

	public static Aquarium getAquariumWith10SpecificFishes() {
		Aquarium aquarium = getEmptyAquarium();

		aquarium.addNewFishToField(new Fish(new Position(100, 100), new Position(100, 102)));
		aquarium.addNewFishToField(new Fish(new Position(102, 102), new Position(100, 102)));
		aquarium.addNewFishToField(new Fish(new Position(104, 104), new Position(103, 102)));
		aquarium.addNewFishToField(new Fish(new Position(101, 102), new Position(100, 102)));
		aquarium.addNewFishToField(new Fish(new Position(10, 10), new Position(12, 12)));
		aquarium.addNewFishToField(new Fish(new Position(105, 103), new Position(105, 102)));
		aquarium.addNewFishToField(new Fish(new Position(103, 107), new Position(103, 106)));
		aquarium.addNewFishToField(new Fish(new Position(99, 99), new Position(100, 102)));
		aquarium.addNewFishToField(new Fish(new Position(100, 102), new Position(101, 102)));
		aquarium.addNewFishToField(new Fish(new Position(250, 250), new Position(252, 252))); // far
																								// away
		aquarium.prepareTaskLists();
		return aquarium;
	}

	public static Aquarium getAquariumWith10SpecificFishes2() {
		Aquarium aquarium = getEmptyAquarium();

		aquarium.addNewFishToField(new Fish(new Position(289, 244), new Position(291, 288)));
		aquarium.addNewFishToField(new Fish(new Position(291, 328), new Position(293, 291)));
		aquarium.addNewFishToField(new Fish(new Position(48, 216), new Position(102, 102)));
		aquarium.addNewFishToField(new Fish(new Position(201, 102), new Position(200, 102)));
		aquarium.addNewFishToField(new Fish(new Position(84, 10), new Position(85, 11)));
		aquarium.addNewFishToField(new Fish(new Position(100, 100), new Position(98, 100)));
		aquarium.addNewFishToField(new Fish(new Position(20, 20), new Position(19, 20)));
		aquarium.addNewFishToField(new Fish(new Position(248, 104), new Position(250, 102)));
		aquarium.addNewFishToField(new Fish(new Position(188, 102), new Position(186, 102)));
		aquarium.addNewFishToField(new Fish(new Position(160, 44), new Position(81, 49)));

		aquarium.prepareTaskLists();
		return aquarium;
	}

}
