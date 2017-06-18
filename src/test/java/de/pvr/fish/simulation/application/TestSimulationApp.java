package de.pvr.fish.simulation.application;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import de.pvr.fish.simulation.config.FishParameter;
import de.pvr.fish.simulation.model.Aquarium;
import de.pvr.fish.simulation.model.Fish;
import de.pvr.fish.simulation.testdata.TestdataGenerator;

public class TestSimulationApp {

	
	@Test
	public void testCreateRandomFishes() {
		SimulationApp app = new SimulationApp(600, 600, 1100, 8, 3, 4, 30, 2, 4,6,5);
		
		app.createAquariumsAndRandomFishes(600, 600, 1100);
		
		FishParameter.resetValuesToDefault();
		
	}
	
	@Test
	public void testThreeIterations() {
		SimulationApp app = new SimulationApp(600, 600, 10, 8, 3, 4, 30, 2, 4,6,5);
		app.setAquarium(TestdataGenerator.getAquariumWith10SpecificFishes());
		ArrayList<Fish> fishes = TestdataGenerator.getAquariumWith10SpecificFishes().getFishes();
		app.startIteration();
		assertNotEquals(fishes, app.getAquarium().getFishes());
		Aquarium field2 = TestdataGenerator.getAquariumWith10SpecificFishes();
		field2.nextIteration();
		fishes = field2.getFishes();
		app.startIteration();
		assertNotEquals(fishes, app.getAquarium().getFishes());
		
		FishParameter.resetValuesToDefault();
	}
	
	@Test
	public void testSimulation10RandomFishes() {
		Aquarium field = TestdataGenerator.getAquariumWith10RandomFishes();
		
		field.nextIteration();
		field.nextIteration();
		field.nextIteration();
		field.nextIteration();
	}
	
	@Test
	public void testSimulation10SpecificFishes() {
		FishParameter.resetValuesToDefault();
		Aquarium field = TestdataGenerator.getAquariumWith10SpecificFishes2();
		ArrayList<Fish> fishes = new ArrayList<Fish>(field.getFishes());
		 
		field.nextIteration();
		assertEquals(fishes, field.getFishes());
		field.nextIteration();
		field.nextIteration();
		field.nextIteration();
	}
}
