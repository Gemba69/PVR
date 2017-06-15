package de.pvr.fish.simulation.application;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import de.pvr.fish.simulation.model.Field;
import de.pvr.fish.simulation.model.Fish;
import de.pvr.fish.simulation.testdata.TestdataGenerator;

public class TestSimulationApp {

	
	@Test
	public void testCreateRandomFishes() {
		SimulationApp app = new SimulationApp(600, 600, 1100, 8, 3, 4, 30, 2, 4,6,1);
		
		app.createField(600, 600, 1100, 8);
		
	}
	
	@Test
	public void testThreeIterations() {
		SimulationApp app = new SimulationApp(600, 600, 10, 8, 3, 4, 30, 2, 4,6,1);
		app.setField(TestdataGenerator.getFieldWithSpecific10Fishes());
		ArrayList<Fish> fishes = TestdataGenerator.getFieldWithSpecific10Fishes().getFishes();
		app.startIteration();
		assertNotEquals(fishes, app.getField().getFishes());
		Field field2 = TestdataGenerator.getFieldWithSpecific10Fishes();
		field2.nextInteration();
		fishes = field2.getFishes();
		app.startIteration();
		assertNotEquals(fishes, app.getField().getFishes());
	}
	
	@Test
	public void testSimulation10RandomFishes() {
		Field field = TestdataGenerator.getFieldWith10Fishes();
		
		field.nextInteration();
		field.nextInteration();
		field.nextInteration();
		field.nextInteration();
	}
	
	@Test
	public void testSimulation10SpecificFishes() {
		Field field = TestdataGenerator.getFieldWithSpecific10Fishes2();
		
		field.nextInteration();
		field.nextInteration();
		field.nextInteration();
		field.nextInteration();
	}
}
