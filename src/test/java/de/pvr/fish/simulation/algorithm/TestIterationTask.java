package de.pvr.fish.simulation.algorithm;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import de.pvr.fish.simulation.algorithm.task.IterationTask;
import de.pvr.fish.simulation.application.Field;
import de.pvr.fish.simulation.model.Fish;
import de.pvr.fish.simulation.model.Position;
import de.pvr.fish.simulation.testdata.TestdataGenerator;
import de.pvr.fish.simulation.util.Radius;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class TestIterationTask {
	
	@Test
	public void testFindNeighbours() {
		Field field = TestdataGenerator.getFieldWithSpecific10Fishes();
		
		IterationTask iterationTask = new IterationTask(field.getFishes(), new Position(0, 0), new Position(0, 0));

		ArrayList<Pair<Fish, Radius>> neighbourhood = iterationTask.findNeighbours(field.getFishes()[100][100]);
		
		ArrayList<Pair<Fish, Radius>> targetNeighbourhood = new ArrayList<Pair<Fish, Radius>>();
		targetNeighbourhood.add(Pair.of(field.getFishes()[99][99], Radius.R1));
		targetNeighbourhood.add(Pair.of(field.getFishes()[100][102], Radius.R1));
		targetNeighbourhood.add(Pair.of(field.getFishes()[101][102], Radius.R2));
		targetNeighbourhood.add(Pair.of(field.getFishes()[102][102], Radius.R2));
		
		//assertEquals(targetNeighbourhood, neighbourhood);
	}
	
	@Test
	public void testSetToNewPlace() {
		
		Field field = TestdataGenerator.getFieldWithSpecific10Fishes();
		
		IterationTask iterationTask = new IterationTask(field.getFishes(), new Position(0, 0), new Position(0, 0));
		Fish fish = field.getFishes()[100][100];
		Fish targetFish = new Fish(new Position (101, 101), new Position(103, 103));

		//iterationTask.setToNewPlace(fish, iterationTask.findNeighbours(fish));
		
		//assertEquals(targetFish, fish);
		
	}
}
