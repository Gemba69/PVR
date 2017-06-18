package de.pvr.fish.simulation.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.pvr.fish.simulation.model.Position;

public class TestCommonUtil {

	@Test
	public void testGetAngle() {
		Position p1 = new Position (50, 50);
		Position p2 = new Position(60, 60);
		
		assertEquals(225, CommonUtil.getAngle(p1, p2));
		assertEquals(45, CommonUtil.getAngle(p2, p1));
		
		p1 = new Position (2, 0);
		p2 = new Position(-3, -7);
		
		assertEquals(55, CommonUtil.getAngle(p1, p2));
		assertEquals(234, CommonUtil.getAngle(p2, p1));
		
		p1 = new Position (2, 0);
		p2 = new Position(1, 0);
		
		assertEquals(0, CommonUtil.getAngle(p1, p2));
		assertEquals(180, CommonUtil.getAngle(p2, p1));
		
		p1 = new Position (2, 0);
		p2 = new Position(1, 3);
		
		assertEquals(288, CommonUtil.getAngle(p1, p2));
		assertEquals(109, CommonUtil.getAngle(p2, p1));
		
		p1 = new Position (0, 2);
		p2 = new Position(1, 3);
		
		assertEquals(225, CommonUtil.getAngle(p1, p2));
		assertEquals(45, CommonUtil.getAngle(p2, p1));
		
		p1 = new Position (0, 2);
		p2 = new Position(1, 0);
		
		assertEquals(117, CommonUtil.getAngle(p1, p2));
		assertEquals(296, CommonUtil.getAngle(p2, p1));
		
		p1 = new Position (0, 2);
		p2 = new Position(1, 3);
		
		assertEquals(225, CommonUtil.getAngle(p1, p2));
		assertEquals(45, CommonUtil.getAngle(p2, p1));
	}
	
	@Test
	public void testRoundDouble() {
		assertEquals(3.4457, CommonUtil.roundDouble(3.445678), 0.0000000001 );
		
	}
}
