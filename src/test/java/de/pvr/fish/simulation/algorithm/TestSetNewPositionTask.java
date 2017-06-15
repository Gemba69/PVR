package de.pvr.fish.simulation.algorithm;

import java.util.ArrayList;

import org.junit.Test;

import de.pvr.fish.simulation.algorithm.task.SetNewPositionTask;
import de.pvr.fish.simulation.model.Field;
import de.pvr.fish.simulation.model.Fish;
import de.pvr.fish.simulation.testdata.TestdataGenerator;

public class TestSetNewPositionTask {
	
	@Test
	public void testCall() {
	
		Field field = TestdataGenerator.getFieldWithSpecific10Fishes2();
		ArrayList<Fish> fishes = field.getFishes();
		SetNewPositionTask task = new SetNewPositionTask(fishes, new ArrayList<Fish>(fishes.subList(0, 5)), 0, 5);
		try {
			task.call();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
