package de.pvr.fish.simulation.util;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MeasureUtil {

	// TODO 3 implement this class
	private static final Logger LOG = LogManager.getLogger(MeasureUtil.class);

	public static StopWatch speedup;;
	public static StopWatch sigma;
	public static StopWatch kappa;
	public static StopWatch phi;
	public static StopWatch simulation;
	public static StopWatch thread;
	public static StopWatch iteration;

	public MeasureUtil() {
		this.speedup = new StopWatch();
		this.sigma = new StopWatch();
		this.kappa = new StopWatch();
		this.phi = new StopWatch();
		this.simulation = new StopWatch();
		this.thread = new StopWatch();
		this.iteration = new StopWatch();
	}

	public void startWatch(WatchAreaType type) {
		if (!getWatchAreaType(type).isStarted()) {
			getWatchAreaType(type).start();
		} else {
			getWatchAreaType(type).resume();
		}
	}
	// methode zum getNanotime (double )



	/*
	 * public void saveSpecificWatch(WatchAreaType type) {
	 * 
	 * }
	 * 
	 * public void logSpecificWatch(WatchAreaType watcharea) {
	 * 
	 * }
	 * 
	 * public void logAllWatches(WatchAreaType watcharea) {
	 * 
	 * }
	 */

	public StopWatch getWatchAreaType(WatchAreaType type) {
		switch (type) {
		case SPEEDUP:
			return speedup;
		case SIGMA:
			return sigma;
		case KAPPA:
			return kappa;
		case PHI:
			return phi;
		case SIMULATION:
			return simulation;
		case THREAD:
			return thread;
		case ITERATION:
			return iteration;
		default:
			return null;
		}
	}
}
