package de.pvr.fish.simulation.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPosition {

	@Test
	public void testNextPosition() {
		Position p = new Position(100, 100);
		Position targetPosition = new Position (100, 101);
		p.nextPosition();
		
		assertEquals(p, targetPosition);

		p = new Position(100, 999);
		targetPosition = new Position(101, 0);
		p.nextPosition();
		
		assertEquals(p, targetPosition);
		
	}
	
	@Test
	public void testgetRadiusStartPosition() {
		Position p = new Position(100, 100);
		Position targetPosition = new Position (98, 98);
		Position startPosition = p.getRadiusStartPosition(2);
		
		assertEquals(startPosition, targetPosition);

		p = new Position(100, 0);
		targetPosition = new Position(98, 0);
		startPosition = p.getRadiusStartPosition(2);
		
		assertEquals(startPosition, targetPosition);
	}
	
	@Test
	public void testgetRadiusEndPosition() {
		Position p = new Position(100, 100);
		Position targetPosition = new Position (102, 102);
		Position endPosition = p.getRadiusEndPosition(2);
		
		assertEquals(endPosition, targetPosition);

		p = new Position(100, 999);
		targetPosition = new Position(102, 999);
		 endPosition = p.getRadiusEndPosition(2);
		
		assertEquals(endPosition, targetPosition);
	}
	
	
	
}
