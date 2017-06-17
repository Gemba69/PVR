package de.pvr.fish.simulation.performancetest;

import de.pvr.fish.simulation.application.SimulationApp;
import de.pvr.fish.simulation.util.MeasureUtil;
import static de.pvr.fish.simulation.util.WatchAreaType.*;

import org.junit.Test;

public class TestPerformance {

	@Test
	public void TestPerformance() {

		//MeasureUtil.startWatch(SIGMA);

		SimulationApp appTest = new SimulationApp(600, 600, 1100, 8, 3, 4, 30, 2, 4, 6, 5);
		appTest.createField(600, 600, 1100, 8);
		//MeasureUtil.suspend(SIGMA);
		appTest.startIterations();
	}

	// TODO implement Measuring test
}
