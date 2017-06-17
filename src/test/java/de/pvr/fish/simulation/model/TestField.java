package de.pvr.fish.simulation.model;

import org.junit.Test;

import de.pvr.fish.simulation.model.Fish;
import de.pvr.fish.simulation.testdata.TestdataGenerator;
import de.pvr.fish.simulation.util.ThreadPoolSingleton;

import static org.junit.Assert.*;

import java.util.ArrayList;


public class TestField {

	@Test
	public void testSplitTasks() {
		Field field = TestdataGenerator.getFieldWithSpecific10Fishes();
		
		ArrayList<Integer> targetList = new ArrayList<Integer>();
		targetList.add(5);
		targetList.add(10);
		assertEquals(targetList, field.splitTasks());
		
		field.setFishNumber(120);
		ThreadPoolSingleton.createNewExecutorService(7);
		targetList = new ArrayList<Integer>();
		targetList.add(17);
		targetList.add(34);
		targetList.add(51);
		targetList.add(68);
		targetList.add(85);
		targetList.add(102);
		targetList.add(120);
		assertEquals(targetList, field.splitTasks());
		ThreadPoolSingleton.reset();
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
