package de.pvr.fish.simulation.algorithm;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import de.pvr.fish.simulation.algorithm.task.CalculatePositionTask;
import de.pvr.fish.simulation.algorithm.task.SetNewPositionTask;
import de.pvr.fish.simulation.model.Field;
import de.pvr.fish.simulation.model.Fish;
import de.pvr.fish.simulation.model.Position;
import de.pvr.fish.simulation.testdata.TestdataGenerator;
import de.pvr.fish.simulation.util.Radius;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class TestCalculatePositionTask {
	
	@Test
	public void testCall() {
	
		Field field = TestdataGenerator.getFieldWithSpecific10Fishes2();
		ArrayList<Fish> fishes = field.getFishes();
		CalculatePositionTask task = new CalculatePositionTask(fishes, new ArrayList<Fish>(fishes.subList(0, 5)), 0, 5);
		try {
			task.call();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindNeighbours() {
		Field field = TestdataGenerator.getFieldWithSpecific10Fishes();
		
		CalculatePositionTask iterationTask = new CalculatePositionTask(field.getFishes(), field.getFishes(), 0, 600);

		ArrayList<Pair<Fish, Radius>> neighbourhood = iterationTask.findNeighbours(field.getFishes().get(0));
		
		ArrayList<Pair<Fish, Radius>> targetNeighbourhood = new ArrayList<Pair<Fish, Radius>>();
		targetNeighbourhood.add(Pair.of(field.getFishes().get(7), Radius.R1));
		targetNeighbourhood.add(Pair.of(field.getFishes().get(1), Radius.R2));
		targetNeighbourhood.add(Pair.of(field.getFishes().get(3), Radius.R2));
		targetNeighbourhood.add(Pair.of(field.getFishes().get(8), Radius.R2));
		
		assertEquals(targetNeighbourhood, neighbourhood);
	}
	
	@Test
	public void testCalculateNewPlace() {
		
		Field field = TestdataGenerator.getFieldWithSpecific10Fishes();
		
		CalculatePositionTask iterationTask = new CalculatePositionTask(field.getFishes(), field.getFishes(), 0, 600);
		Fish fish = field.getFishes().get(0);
		Fish targetFish = new Fish(new Position (100, 100), new Position(100, 102), new Position(98,101));

		iterationTask.calculateNewPlace(fish, iterationTask.findNeighbours(fish));
		
		assertEquals(targetFish, fish);
	}
	
	@Test
	public void testCalculateNewAngle() {
		Field field = TestdataGenerator.getFieldWithSpecific10Fishes();
		CalculatePositionTask iterationTask = new CalculatePositionTask(field.getFishes(), field.getFishes(), 0, 600);
		
		Fish fish = field.getFishes().get(0);
		ArrayList<Pair<Fish, Radius>> neighbourFishes = new ArrayList<Pair<Fish, Radius>>();
		neighbourFishes.add(Pair.of(field.getFishes().get(7), Radius.R1));
		neighbourFishes.add(Pair.of(field.getFishes().get(8), Radius.R2));
		neighbourFishes.add(Pair.of(field.getFishes().get(3), Radius.R2));
		neighbourFishes.add(Pair.of(field.getFishes().get(1), Radius.R2));
		
		assertEquals(41 , iterationTask.calculateNewAngle(fish, neighbourFishes));
	}
}
