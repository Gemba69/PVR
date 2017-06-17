package de.pvr.fish.simulation.util;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MeasureUtil {

	// TODO 3 implement this class
	private static final Logger LOG = LogManager.getLogger(MeasureUtil.class);

	private static StopWatch runtime = new StopWatch();
	private static StopWatch sigma = new StopWatch();
	private static StopWatch kappa = new StopWatch();
	private static StopWatch phi = new StopWatch();
	private static StopWatch simulation = new StopWatch();
	private static StopWatch thread = new StopWatch();
	private static StopWatch iteration = new StopWatch();

	public static void resetAllWatches() {
		runtime = new StopWatch();
		sigma = new StopWatch();
		kappa = new StopWatch();
		phi = new StopWatch();
		simulation = new StopWatch();
		thread = new StopWatch();
		iteration = new StopWatch();
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
	
	public static void logAllWatches() {
		LOG.info("Time of Runtime is: " + runtime.getTime());
		LOG.info("Time of Sigma is: " + sigma.getTime());
		LOG.info("Time of Kappa is: " + kappa.getTime());
		LOG.info("Time of Phi is: " + phi.getTime());
	}

	public static StopWatch getRuntime() {
		return runtime;
	}

	public static void setRuntime(StopWatch runtime) {
		MeasureUtil.runtime = runtime;
	}

	public static StopWatch getSigma() {
		return sigma;
	}

	public static void setSigma(StopWatch sigma) {
		MeasureUtil.sigma = sigma;
	}

	public static StopWatch getKappa() {
		return kappa;
	}

	public static void setKappa(StopWatch kappa) {
		MeasureUtil.kappa = kappa;
	}

	public static StopWatch getPhi() {
		return phi;
	}

	public static void setPhi(StopWatch phi) {
		MeasureUtil.phi = phi;
	}

	public static StopWatch getSimulation() {
		return simulation;
	}

	public static void setSimulation(StopWatch simulation) {
		MeasureUtil.simulation = simulation;
	}

	public static StopWatch getThread() {
		return thread;
	}

	public static void setThread(StopWatch thread) {
		MeasureUtil.thread = thread;
	}

	public static StopWatch getIteration() {
		return iteration;
	}

	public static void setIteration(StopWatch iteration) {
		MeasureUtil.iteration = iteration;
	}

	public static Logger getLog() {
		return LOG;
	}
	
    public static long getMeasuredTimeFor(WatchAreaType type) {
        StopWatch watch = getWatch(type);
        return watch.getNanoTime() / 1000000;
    }
}
