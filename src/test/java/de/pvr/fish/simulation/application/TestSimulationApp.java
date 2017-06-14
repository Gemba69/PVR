package de.pvr.fish.simulation.application;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

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
		ArrayList<Fish> fishes = app.getField().getFishes();
		app.startIteration();
		//assertNotEquals(fishes, app.getField().getFishes());
		fishes = app.getField().getFishes();
		
		app.startIteration();
		//assertNotEquals(fishes, app.getField().getFishes());
		fishes = app.getField().getFishes();
		
		app.startIteration();
		//assertNotEquals(fishes, app.getField().getFishes());
		fishes = app.getField().getFishes();

	}
}
