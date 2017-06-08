package de.pvr.fish.simulation.model;

import static org.junit.Assert.*;

import org.junit.Test;


public class TestFish {
	
	@Test
	public void testTurnAround() {
		Fish fish = new Fish(new Position(100, 100), new Position(100, 102));
		int targetAngel = 270;
		
		assertEquals(targetAngel, fish.getAngle());
		
		targetAngel = 90;
		fish.turnAround();
		
		assertEquals(targetAngel, fish.getAngle() );
		
		targetAngel = 270;
		fish.turnAround();
		
		assertEquals(targetAngel, fish.getAngle() );
		
		targetAngel = 0;
		fish.turnAt(-90);
		fish.turnAround();
		
		assertEquals(targetAngel, fish.getAngle() );
	}
	
	@Test
	public void testTurnAt() {
		Fish fish = new Fish(new Position(100, 100), new Position(102, 100));
		assertEquals(180, fish.getAngle() );
		
		int targetAngel = 225;
		fish.turnAt(45);
		
		assertEquals(targetAngel, fish.getAngle() );
		
		targetAngel = 90;
		fish.turnAt(-135);
		
		assertEquals(targetAngel, fish.getAngle() );
		
	}
	
	@Test
	public void testGetAngel() {
		Fish fish = new Fish(new Position(100, 100), new Position(102, 102));
		
		assertEquals(225, fish.getAngle());
		
		fish = new Fish(new Position(100, 100), new Position(100, 102));
		
		assertEquals(270, fish.getAngle());
	}

}
