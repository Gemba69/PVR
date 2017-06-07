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
		
		ArrayList<Position> borderPositions = field.splitTasks();
		
		ArrayList<Position> targetList = new ArrayList<Position>();
		targetList.add(new Position(101, 102));
		targetList.add(new Position(500, 500));
		
		assertEquals(targetList, borderPositions);
	}
	
	@Test
	public void testAddNewFishToField() {
		Field field = new Field(10, 10, 10, 2);
		Fish fish = new Fish(90, new Position(8, 8));	
		field.addNewFishToField(fish);
		
		Fish[][] targetArray = new Fish[10][10];
		targetArray[8][8] = fish;
		
		
		assertArrayEquals(targetArray, field.getFishes());
		
		//TODO: implement function if fish is already on this position
	}
	
	
}
