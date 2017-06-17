package de.pvr.fish.simulation.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolSingleton {

	private static ExecutorService executorService = Executors.newFixedThreadPool(4);
	
	public static ExecutorService getExecutorService() {
		return executorService;
	}
	
	public static void createNewExecutorService(int threads) {
		executorService = Executors.newFixedThreadPool(threads);
	}
	
}
