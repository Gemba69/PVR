package de.pvr.fish.simulation.model;

import static org.junit.Assert.*;

import org.junit.Test;


public class TestFish {
	
	@Test
	public void testTurnAround() {
		Fish fish = new Fish(90, new Position(100, 100));
		int targetAngel = 270;
		fish.turnAround();
		
		assertEquals(fish.getAngle(), targetAngel);
		
		targetAngel = 90;
		fish.turnAround();
		
		assertEquals(fish.getAngle(), targetAngel);
		
		targetAngel = 0;
		fish.setAngle(180);
		fish.turnAround();
		
		assertEquals(fish.getAngle(), targetAngel);
	}

}
