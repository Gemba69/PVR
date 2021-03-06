package de.pvr.fish.simulation.model;

import static org.junit.Assert.*;

import org.junit.Test;

import de.pvr.fish.simulation.config.FishParameter;




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
		
		fish.turnAt(72);
		assertEquals(163, fish.getAngle());
	}
	
	@Test
	public void testGetAngel() {
		Fish fish = new Fish(new Position(100, 100), new Position(102, 102));
		
		assertEquals(225, fish.getAngle());
		
		fish = new Fish(new Position(100, 100), new Position(100, 102));
		
		assertEquals(270, fish.getAngle());
		
		fish.turnAt(90);
		assertEquals(0, fish.getAngle());
	}
	
	@Test
	public void testGetCalcAngel() {
		Fish fish = new Fish(new Position(100, 100), new Position(102, 102));
		assertEquals(225, fish.getCalcAngle());
		
		fish = new Fish(new Position(98, 102), new Position(96, 102));
		assertEquals(0, fish.getCalcAngle());
		
		fish = new Fish(new Position(98, 102), new Position(96, 100));
		assertEquals(45, fish.getCalcAngle());
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
		
		fish.turnAtCalc(90);
		targetFish = new Fish(new Position (98, 102), new Position(95, 102));
		fish.goToNextPosition(3);
		assertEquals(targetFish, fish);
		
		fish.turnAtCalc(45);
		targetFish = new Fish(new Position (95.8787, 99.8787), new Position(93.7574, 97.7574));
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
	
	@Test
	public void testGetDiffPosition() {
		Fish fish = new Fish(new Position(100, 100), new Position(100, 102));
		Position targetPosition = new Position (0, 2);
		assertEquals(targetPosition, fish.getDiffPosition());
		
		fish = new Fish(new Position(100, 100), new Position(104, 102));
		targetPosition = new Position (4, 2);
		assertEquals(targetPosition, fish.getDiffPosition());
		
		fish = new Fish(new Position(100, 100), new Position(98, 96));
		targetPosition = new Position (-2, -4);
		assertEquals(targetPosition, fish.getDiffPosition());
		
		fish = new Fish(new Position(100, 100), new Position(100, 102));
		targetPosition = new Position (0, 2);
		assertEquals(targetPosition, fish.getDiffPosition());
		
		fish = new Fish(new Position(99,99), new Position(100, 102));
		targetPosition = new Position (1, 3);
		assertEquals(targetPosition, fish.getDiffPosition());
	}
	
	@Test
	public void testGetLengthPosition() {
		FishParameter.FISH_BODY_LENGTH = 6;
		Fish fish = new Fish(new Position(100, 100), new Position(100, 102));
		Position targetPosition = new Position(100, 94);
		assertEquals(targetPosition, fish.getLengthPosition());
		
		fish = new Fish(new Position(100, 100), new Position(102, 100));
		targetPosition = new Position(94, 100);
		assertEquals(targetPosition, fish.getLengthPosition());
		
		fish = new Fish(new Position(100, 100), new Position(102, 100));
		targetPosition = new Position(94, 100);
		assertEquals(targetPosition, fish.getLengthPosition());
		
		fish = new Fish(new Position(100, 100), new Position(102, 103));
		targetPosition = new Position(96.6448, 95.0258);
		assertEquals(targetPosition, fish.getLengthPosition());
		
		fish = new Fish(new Position(100, 100), new Position(98, 98));
		targetPosition = new Position(104.2426, 104.2426);
		assertEquals(targetPosition, fish.getLengthPosition());

		FishParameter.FISH_BODY_LENGTH = FishParameter.DEFAULT_FISH_BODY_LENGTH;
	}
	
	@Test
	public void testGetSpeed() {
		Fish fish = new Fish(new Position(100, 100), new Position(100, 102));
		assertTrue(fish.getNewSpeed() >= 1 * FishParameter.FISH_BODY_LENGTH);
		assertTrue(fish.getNewSpeed() >= 1 * FishParameter.FISH_BODY_LENGTH);
		assertTrue(fish.getNewSpeed() >= 1 * FishParameter.FISH_BODY_LENGTH);
		assertTrue(fish.getNewSpeed() >= 1 * FishParameter.FISH_BODY_LENGTH);
		assertTrue(fish.getNewSpeed() <= 2 * FishParameter.FISH_BODY_LENGTH);
		assertTrue(fish.getNewSpeed() <= 2 * FishParameter.FISH_BODY_LENGTH);
		assertTrue(fish.getNewSpeed() <= 2 * FishParameter.FISH_BODY_LENGTH);
		assertTrue(fish.getNewSpeed() <= 2 * FishParameter.FISH_BODY_LENGTH);
	}
	
	
	@Test
	public void testIsFishOutOfAquariumX() {
		Fish fish = new Fish(new Position(100, 100), new Position (100, 98));
		assertFalse(fish.isFishOutOfAquariumX());
		
		fish = new Fish(new Position(1202, 400), new Position (298, 298));
		assertTrue(fish.isFishOutOfAquariumX());
	}
	
	@Test
	public void testIsFishOutOfAquariumY() {
		Fish fish = new Fish(new Position(100, 100), new Position (100, 98));
		assertFalse(fish.isFishOutOfAquariumY());
		
		fish = new Fish(new Position(1200, 400), new Position (298, 298));
		assertTrue(fish.isFishOutOfAquariumY());
	}
	
	@Test
	public void testSetFishFromOutInAquariumX() {
		Fish fish = new Fish(new Position(1202, 399), new Position (298, 298));
		Fish targetFish = new Fish(new Position(1191, 399), new Position (298, 298));
		fish.setFishFromOutInAquariumX();
		assertEquals(targetFish, fish);
		
		fish = new Fish(new Position(-2, 291), new Position (298, 298));
		targetFish = new Fish(new Position(8, 291), new Position (298, 298));
		fish.setFishFromOutInAquariumX();
		assertEquals(targetFish, fish);
	}
	
	@Test
	public void testSetFishFromOutInAquariumY() {
		Fish fish = new Fish(new Position(1199, 403), new Position (298, 298));
		Fish targetFish = new Fish(new Position(1199, 391), new Position (298, 298));
		fish.setFishFromOutInAquariumY();
		assertEquals(targetFish, fish);
		
		fish = new Fish(new Position(0, -3), new Position (298, 298));
		targetFish = new Fish(new Position(0, 8), new Position (298, 298));
		fish.setFishFromOutInAquariumY();
		assertEquals(targetFish, fish);
	}
	
	@Test
	public void testSetFishFromOutInAquariumXY() {
		FishParameter.FIELD_LENGTH = 300;
		FishParameter.FIELD_HEIGHT = 300;
		Fish fish = new Fish(new Position(302, 302), new Position (298, 298));
		Fish targetFish = new Fish(new Position(291, 291), new Position (298, 298));
		fish.setFishFromOutInAquariumX();
		fish.setFishFromOutInAquariumY();
		assertEquals(targetFish, fish);
		
		fish = new Fish(new Position(-2, 302), new Position (298, 298));
		targetFish = new Fish(new Position(8, 291), new Position (298, 298));
		fish.setFishFromOutInAquariumX();
		fish.setFishFromOutInAquariumY();
		assertEquals(targetFish, fish);
		
		fish = new Fish(new Position(302, -1), new Position (298, 298));
		targetFish = new Fish(new Position(291, 8), new Position (298, 298));
		fish.setFishFromOutInAquariumX();
		fish.setFishFromOutInAquariumY();
		assertEquals(targetFish, fish);
		
		fish = new Fish(new Position(302, -1), new Position (298, 298));
		targetFish = new Fish(new Position(291, 8), new Position (298, 298));
		fish.setFishFromOutInAquariumX();
		fish.setFishFromOutInAquariumY();
		assertEquals(targetFish, fish);
		
		fish = new Fish(new Position(310, 310), new Position (298, 298));
		targetFish = new Fish(new Position(291, 291), new Position (298, 298));
		fish.setFishFromOutInAquariumX();
		fish.setFishFromOutInAquariumY();
		assertEquals(targetFish, fish);
		
		FishParameter.resetValuesToDefault();
	}
	

}
