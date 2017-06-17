package de.pvr.fish.simulation.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.pvr.fish.simulation.config.FishParameter;

public class ThreadPoolSingleton {

	private static int threads = FishParameter.DEFAULT_THREADS;
	private static ExecutorService executorService = Executors.newFixedThreadPool(threads);

	public static ExecutorService getExecutorService() {
		return executorService;
	}

	public static void createNewExecutorService(int newThreads) {
		if (threads != newThreads) {
			threads = newThreads;
			FishParameter.THREADS = newThreads;
			executorService = Executors.newFixedThreadPool(threads);
		}
	}

	public static int getThreads() {
		return threads;
	}

	public static void reset() {
		threads = FishParameter.DEFAULT_THREADS;
		executorService = Executors.newFixedThreadPool(threads);
	}

}
