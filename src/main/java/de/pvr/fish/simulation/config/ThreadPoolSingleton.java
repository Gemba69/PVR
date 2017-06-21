package de.pvr.fish.simulation.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.pvr.fish.simulation.util.MeasureUtil;
import de.pvr.fish.simulation.util.WatchAreaType;

public class ThreadPoolSingleton {

	private static final Logger LOG = LogManager.getLogger(ThreadPoolSingleton.class);

	private static int threads = FishParameter.DEFAULT_THREADS;
	private static ExecutorService executorService = Executors.newFixedThreadPool(threads);

	public static ExecutorService getExecutorService() {
		return executorService;
	}

	public static void createNewExecutorService(int newThreads) {
		// if (threads != newThreads) { -- would be the efficient way, but for
		// meausring it disabled
		threads = newThreads;
		FishParameter.THREADS = newThreads;
		executorService = Executors.newFixedThreadPool(threads);
		// }
	}

	public static int getThreads() {
		return threads;
	}

	public static void reset() {
		threads = FishParameter.DEFAULT_THREADS;
		executorService = Executors.newFixedThreadPool(threads);
	}

}
