package de.pvr.fish.simulation.application;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.pvr.fish.simulation.model.Fish;
import de.pvr.fish.simulation.model.Position;

public class TestSimulationApp {

	
	@Test
	public void testCreateRandomFishes() {
		SimulationApp app = new SimulationApp(600, 600, 1100, 8, 3, 4, 30, 2, 4,6,1);
		
		app.createField();
		
	}
}
