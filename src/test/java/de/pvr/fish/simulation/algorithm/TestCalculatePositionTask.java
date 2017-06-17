package de.pvr.fish.simulation.algorithm;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import de.pvr.fish.simulation.algorithm.task.CalculateNewPositionTask;
import de.pvr.fish.simulation.config.FishParameter;
import de.pvr.fish.simulation.model.Field;
import de.pvr.fish.simulation.model.Fish;
import de.pvr.fish.simulation.model.Position;
import de.pvr.fish.simulation.testdata.TestdataGenerator;
import de.pvr.fish.simulation.util.CommonUtil;
import de.pvr.fish.simulation.util.Radius;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class TestCalculatePositionTask {
	
	@Test
	public void testCall() {
	
		Field field = TestdataGenerator.getFieldWithSpecific10Fishes2();
		ArrayList<Fish> fishes = field.getFishes();
		CalculateNewPositionTask task = new CalculateNewPositionTask(fishes, new ArrayList<Fish>(fishes.subList(0, 5)), 0, 5);
		try {
			task.call();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindNeighbours() {
		FishParameter.FISH_BODY_LENGTH = 1;
		FishParameter.RADIUS2 = 3;
		Field field = TestdataGenerator.getFieldWithSpecific10Fishes();
		
		CalculateNewPositionTask iterationTask = new CalculateNewPositionTask(field.getFishes(), field.getFishes(), 0, 600);

		ArrayList<Pair<Fish, Radius>> neighbourhood = iterationTask.findNeighbours(field.getFishes().get(0));
		
		ArrayList<Pair<Fish, Radius>> targetNeighbourhood = new ArrayList<Pair<Fish, Radius>>();
		targetNeighbourhood.add(Pair.of(field.getFishes().get(1), Radius.R2));
		targetNeighbourhood.add(Pair.of(field.getFishes().get(3), Radius.R2));
		targetNeighbourhood.add(Pair.of(field.getFishes().get(7), Radius.R2));
		targetNeighbourhood.add(Pair.of(field.getFishes().get(8), Radius.R2));
		
		assertEquals(targetNeighbourhood, neighbourhood);
		FishParameter.FISH_BODY_LENGTH = FishParameter.DEFAULT_FISH_BODY_LENGTH;
		FishParameter.RADIUS2 = FishParameter.DEFAULT_RADIUS2;
	}
	
	@Test
	public void testCalculateNewPlace() {
		FishParameter.FISH_BODY_LENGTH = 1;
		Field field = TestdataGenerator.getFieldWithSpecific10Fishes();
		
		CalculateNewPositionTask iterationTask = new CalculateNewPositionTask(field.getFishes(), field.getFishes(), 0, 600);
		Fish fish = field.getFishes().get(0);
		Fish targetFish = new Fish(new Position (100, 100), new Position(100, 102), new Position(99,100));

		iterationTask.calculateNewPlace(fish, iterationTask.findNeighbours(fish));
		
		assertEquals(targetFish, fish);
		FishParameter.FISH_BODY_LENGTH = FishParameter.DEFAULT_FISH_BODY_LENGTH;
	}
	
	@Test
	public void testCalculateNewAngle() {
		Field field = TestdataGenerator.getFieldWithSpecific10Fishes();
		CalculateNewPositionTask iterationTask = new CalculateNewPositionTask(field.getFishes(), field.getFishes(), 0, 600);
		
		Fish fish = field.getFishes().get(0);
		ArrayList<Pair<Fish, Radius>> neighbourFishes = new ArrayList<Pair<Fish, Radius>>();
		neighbourFishes.add(Pair.of(field.getFishes().get(7), Radius.R1));
		neighbourFishes.add(Pair.of(field.getFishes().get(8), Radius.R2));
		neighbourFishes.add(Pair.of(field.getFishes().get(3), Radius.R2));
		neighbourFishes.add(Pair.of(field.getFishes().get(1), Radius.R2));
		
		assertEquals(90 , iterationTask.calculateNewAngle(fish, neighbourFishes));
	}
	
	@Test
	public void testMovementR1() {
		Field field = TestdataGenerator.getFieldWithSpecific10Fishes();
		CalculateNewPositionTask iterationTask = new CalculateNewPositionTask(field.getFishes(), field.getFishes(), 0, 600);
		
		Fish fish = field.getFishes().get(0);
		ArrayList<Pair<Fish, Radius>> neighbourFishes = new ArrayList<Pair<Fish, Radius>>();
		neighbourFishes.add(Pair.of(field.getFishes().get(7), Radius.R1));
		
		assertEquals(135 , iterationTask.calculateNewAngle(fish, neighbourFishes));
	}
	
	@Test
	public void testMovementR2() {
		Field field = TestdataGenerator.getFieldWithSpecific10Fishes();
		CalculateNewPositionTask iterationTask = new CalculateNewPositionTask(field.getFishes(), field.getFishes(), 0, 600);
		
		Fish fish = field.getFishes().get(0);
		ArrayList<Pair<Fish, Radius>> neighbourFishes = new ArrayList<Pair<Fish, Radius>>();
		neighbourFishes.add(Pair.of(field.getFishes().get(8), Radius.R2));
		
		assertEquals(117 , iterationTask.calculateNewAngle(fish, neighbourFishes));
	}
	
	@Test
	public void testMovementR3() {
		Field field = TestdataGenerator.getFieldWithSpecific10Fishes();
		CalculateNewPositionTask iterationTask = new CalculateNewPositionTask(field.getFishes(), field.getFishes(), 0, 600);
		
		Fish fish = field.getFishes().get(0);
		ArrayList<Pair<Fish, Radius>> neighbourFishes = new ArrayList<Pair<Fish, Radius>>();
		neighbourFishes.add(Pair.of(field.getFishes().get(6), Radius.R3));
		
		assertEquals(72 , iterationTask.calculateNewAngle(fish, neighbourFishes));
	}
	
	@Test
	public void testCommonUtilgetAngle() {
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
}
