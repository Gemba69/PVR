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
	
	@Test
	public void testIsInDeathAngle() {
		Fish fish = new Fish(new Position(100, 100), new Position(100, 102));
		
		assertEquals(true, fish.isInDeathAngle(new Position(100, 98)));
		
		assertEquals(false, fish.isInDeathAngle(new Position(103, 103)));
	}
	
	@Test
	public void testGoToNextPosition() {
		Fish fish = new Fish(new Position(100, 100), new Position(100, 102));
		Fish targetFish = new Fish(new Position(100, 102), new Position(100, 104));
		fish.goToNextPosition(2);
		assertEquals(targetFish, fish);
		
		fish.turnAt(45);
		targetFish = new Fish(new Position (99, 103), new Position(97, 105));
		fish.goToNextPosition(3);
		assertEquals(targetFish, fish);
	}
	
	@Test
	public void testFishEquals() {
		Fish fish = new Fish(new Position(100, 100), new Position(100, 102));
		Fish targetFish = new Fish(new Position(100, 102), new Position(100, 104));
		
		assertFalse(fish.equals(targetFish));
		targetFish = new Fish(new Position(100, 100), new Position(100, 102));
		assertTrue(fish.equals(targetFish));
	}

}
