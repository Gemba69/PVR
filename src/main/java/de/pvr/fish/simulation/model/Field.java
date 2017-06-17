package de.pvr.fish.simulation.model;

import static de.pvr.fish.simulation.util.WatchAreaType.*;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.pvr.fish.simulation.algorithm.task.CalculatePositionTask;
import de.pvr.fish.simulation.algorithm.task.FishTask;
import de.pvr.fish.simulation.algorithm.task.SetNewPositionTask;
import de.pvr.fish.simulation.util.MeasureUtil;

public class Field {

	private ArrayList<Fish> fishes;
	private int length;
	private int height;

	private int threads;
	private int fishNumber;

	private ExecutorService executorService;
	private ArrayList<FishTask> calcTasks;
	private ArrayList<FishTask> newPositionTasks;
	
	public static MeasureUtil measureUtil;

	private static final Logger LOG = LogManager.getLogger(Field.class);

	public Field(int length, int height, int fishNumber, int threads) {
		this.length = length;
		this.height = height;
		this.fishNumber = fishNumber;
		this.threads = threads;
		fishes = new ArrayList<Fish>();
		
		//Overhead
		MeasureUtil.startWatch(KAPPA);
		this.executorService = Executors.newFixedThreadPool(threads);
		this.calcTasks = new ArrayList<FishTask>();
		this.newPositionTasks = new ArrayList<FishTask>();
		MeasureUtil.suspend(KAPPA);

	}

	public boolean addNewFishToField(Fish fish) {
		if (fishes.contains(fish)) {
			return false;
		} else {
			fishes.add(fish);
			return true;
		}
	}

	public void nextInteration() {
		LOG.info("Starting overall Iteration");
		// Execution
		try {
			this.executorService.invokeAll(this.calcTasks);
		} catch (InterruptedException e) {
			LOG.error(e.getMessage());
		}
		// 2. Execution
		try {
			this.executorService.invokeAll(this.newPositionTasks);
		} catch (InterruptedException e) {
			LOG.error(e.getMessage());
		}
		logFishes(); // only fpr debugging
	}

	public void prepareTaskLists() {
		this.calcTasks.clear();
		this.newPositionTasks.clear();
		// split Task
		int startPosition = 0;
		ArrayList<Integer> positions = splitTasks();
		for (Integer endPosition : positions) {
			this.calcTasks.add(new CalculatePositionTask(this.fishes,
					new ArrayList<Fish>(this.fishes.subList(startPosition, endPosition)), startPosition, endPosition));
			startPosition = endPosition;
		}
		startPosition = 0;
		for (Integer endPosition : positions) {
			this.newPositionTasks.add(new SetNewPositionTask(this.fishes,
					new ArrayList<Fish>(this.fishes.subList(startPosition, endPosition)), startPosition, endPosition));
			startPosition = endPosition;
		}
	}

	public ArrayList<Integer> splitTasks() {
		ArrayList<Integer> positions = new ArrayList<Integer>();
		for (int i = 0; i < threads - 1; i++) {
			positions.add(fishNumber / threads * (i + 1));
		}
		positions.add(fishNumber);
		return positions;

	}

	private void logFishes() {
		for (Fish fish : this.fishes) {
			LOG.debug("Fish at Position " + fish.getPosition().getCoordinateX() + "/"
					+ fish.getPosition().getCoordinateY() + " with Angle " + fish.getAngle() + " and Speed "
					+ fish.getNewSpeed());
		}
	}

	public ArrayList<Fish> getFishes() {
		return fishes;
	}

	public void setFishes(ArrayList<Fish> fishes) {
		this.fishes = fishes;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public ExecutorService getExecutorService() {
		return executorService;
	}

	public void setExecutorService(ExecutorService executorService) {
		this.executorService = executorService;
	}

	public ArrayList<FishTask> getCalcTasks() {
		return calcTasks;
	}

	public void setCalcTasks(ArrayList<FishTask> tasks) {
		this.calcTasks = tasks;
	}

	public ArrayList<FishTask> getNewPositionTasks() {
		return newPositionTasks;
	}

	public void setNewPositionTasks(ArrayList<FishTask> tasks) {
		this.newPositionTasks = tasks;
	}

	public static Logger getLog() {
		return LOG;
	}

	public int getThreads() {
		return threads;
	}

	public void setThreads(int threads) {
		this.threads = threads;
	}

	public int getFishNumber() {
		return fishNumber;
	}

	public void setFishNumber(int fishNumber) {
		this.fishNumber = fishNumber;
	}
}
