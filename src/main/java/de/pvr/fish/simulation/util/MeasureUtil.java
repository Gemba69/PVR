package de.pvr.fish.simulation.util;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MeasureUtil {

	// TODO 3 implement this class
	private static final Logger LOG = LogManager.getLogger(MeasureUtil.class);

	public static StopWatch runtime = new StopWatch();
	public static StopWatch sigma = new StopWatch();
	public static StopWatch kappa = new StopWatch();
	public static StopWatch phi = new StopWatch();
	public static StopWatch simulation = new StopWatch();
	public static StopWatch thread = new StopWatch();
	public static StopWatch iteration = new StopWatch();

	public void resetAllWatches() {
		this.runtime = new StopWatch();
		this.sigma = new StopWatch();
		this.kappa = new StopWatch();
		this.phi = new StopWatch();
		this.simulation = new StopWatch();
		this.thread = new StopWatch();
		this.iteration = new StopWatch();
	}

	public static void startWatch(WatchAreaType type) {
		if (!getWatch(type).isStarted()) {
			getWatch(type).start();
		} else {
			getWatch(type).resume();
		}
	}

	public static void suspend(WatchAreaType type) {
		StopWatch watch = getWatch(type);
		if (watch.isStarted()) {
			watch.suspend();
			LOG.debug("Time of watch is: " + watch + watch.getTime());
		}

	}

	public static StopWatch getWatch(WatchAreaType type) {
		switch (type) {
		case RUNTIME:
			return runtime;
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
