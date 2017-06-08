package de.pvr.fish.simulation.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPosition {

	@Test
	public void testNextPosition() {
		Position p = new Position(100, 100);
		Position targetPosition = new Position (100, 101);
		p.nextPosition();
		
		assertEquals(targetPosition, p);

		p = new Position(100, 999);
		targetPosition = new Position(101, 0);
		p.nextPosition();
		
		assertEquals(targetPosition, p);
		
	}
	
	@Test
	public void testgetRadiusStartPosition() {
		Position p = new Position(100, 100);
		Position targetPosition = new Position (98, 98);
		Position startPosition = p.getRadiusStartPosition(2);
		
		assertEquals(targetPosition, startPosition);

		p = new Position(100, 0);
		targetPosition = new Position(98, 0);
		startPosition = p.getRadiusStartPosition(2);
		
		assertEquals(targetPosition, startPosition);
	}
	
	@Test
	public void testgetRadiusEndPosition() {
		Position p = new Position(100, 100);
		Position targetPosition = new Position (102, 102);
		Position endPosition = p.getRadiusEndPosition(2);
		
		assertEquals(targetPosition, endPosition);

		p = new Position(100, 999);
		targetPosition = new Position(102, 999);
		 endPosition = p.getRadiusEndPosition(2);
		
			assertEquals(targetPosition, endPosition);
	}
	
	@Test
	public void testAddPosition() {
		Position p = new Position(100, 100);
		Position addPosition = new Position(3, 3);
		Position targetPosition = new Position (103, 103);
		
		p.addPosition(addPosition);
		
		assertEquals(targetPosition, p);
	}
	
	@Test
	public void testGetAngle() {
		Position p = new Position(100, 100);
		Position targetPosition = new Position (99, 101);
		
		assertEquals(315, p.getAngle(targetPosition));
		
		targetPosition = new Position (98, 98);
		
		assertEquals(45, p.getAngle(targetPosition));
		
		targetPosition = new Position (102, 98);
		
		assertEquals(135, p.getAngle(targetPosition));
		
		targetPosition = new Position (98, 100);
		
		assertEquals(0, p.getAngle(targetPosition));
		
		targetPosition = new Position (100, 98);
		
		assertEquals(90, p.getAngle(targetPosition));
	}
	
	@Test
	public void testGetDiffBetweenPositions() {
		Position p = new Position(100, 100);
		Position p2 = new Position(102, 102);
		Position targetPosition = new Position (2, 2);
		
		assertEquals(p.getDiffBetweenPositions(p2), targetPosition);
	}
	
	@Test
	public void testAddSpecificAngle() {
		Position p = new Position(2, 2);
		Position targetPosition = new Position (2, 0);
		
		p.addSpecificAngle(45);
		
		assertEquals(targetPosition, p);
		
		p = new Position(2, 0);
		targetPosition = new Position(-2, 0);
		p.addSpecificAngle(180);
		
		assertEquals(targetPosition, p);
		
		p = new Position(-2, 0);
		targetPosition = new Position(0, 2);
		p.addSpecificAngle(90);
		
		assertEquals(targetPosition, p);
	}
	
	@Test
	public void testGetLength() {
		Position p = new Position (2, 3);
		
		assertEquals(3.6, p.getLength(), 0.1);
		
		p = new Position (2, 2);
		
		assertEquals(2.8, p.getLength(), 0.1);
		
		p = new Position (3, 2);
		
		assertEquals(3.6, p.getLength(), 0.1);
		
		p = new Position (-3, -2);
		//FIXME to double and phytagoras
		assertEquals(3.6, p.getLength(), 0.1);
	}
	
}
