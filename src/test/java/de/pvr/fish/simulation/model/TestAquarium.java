package de.pvr.fish.simulation.model;

import org.junit.Test;

import de.pvr.fish.simulation.config.ThreadPoolSingleton;
import de.pvr.fish.simulation.model.Fish;
import de.pvr.fish.simulation.testdata.TestdataGenerator;

import static org.junit.Assert.*;

import java.util.ArrayList;


public class TestAquarium {

	@Test
	public void testSplitTasks() {
		Aquarium field = TestdataGenerator.getAquariumWith10SpecificFishes();
		
		ThreadPoolSingleton.createNewExecutorService(2);
		
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
		Aquarium field = new Aquarium(10, 10, 10);
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
		Aquarium field = TestdataGenerator.getAquariumWith10SpecificFishes();
		field.nextIteration();
		assertEquals(10, field.getFishes().size());
		field.nextIteration();
		assertEquals(10, field.getFishes().size());
	}
	
}
