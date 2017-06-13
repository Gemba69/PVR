package de.pvr.fish.simulation.application;

import org.junit.Test;

import de.pvr.fish.simulation.application.Field;
import de.pvr.fish.simulation.model.Fish;
import de.pvr.fish.simulation.model.Position;
import de.pvr.fish.simulation.testdata.TestdataGenerator;

import static org.junit.Assert.*;

import java.util.ArrayList;


public class TestField {

	@Test
	public void testSplitTasks() {
		Field field = TestdataGenerator.getFieldWithSpecific10Fishes();
		
		ArrayList<Integer> targetList = new ArrayList<Integer>();
		targetList.add(4);
		targetList.add(9);
		assertEquals(targetList, field.splitTasks());
		
		field.setFishNumber(120);
		field.setThreads(7);
		targetList = new ArrayList<Integer>();
		targetList.add(16);
		targetList.add(33);
		targetList.add(50);
		targetList.add(67);
		targetList.add(84);
		targetList.add(101);
		targetList.add(119);
		assertEquals(targetList, field.splitTasks());
	}
	
	@Test
	public void testAddNewFishToField() {
		Field field = new Field(10, 10, 10, 2);
		Fish fish = new Fish(new Position(8, 8), new Position(10, 8));	
		assertTrue(field.addNewFishToField(fish));
		
		ArrayList<Fish> targetList = new ArrayList<Fish>();
		targetList.add(fish);
		field.addNewFishToField(fish);
		
		assertEquals(targetList, field.getFishes());
		
		assertFalse(field.addNewFishToField(fish));
	}
	
	@Test
	public void testNextIteration() {
		Field field = TestdataGenerator.getFieldWithSpecific10Fishes();
		field.nextInteration();
		assertEquals(10, field.getFishes().size());
		field.nextInteration();
		assertEquals(10, field.getFishes().size());
	}
	
}
