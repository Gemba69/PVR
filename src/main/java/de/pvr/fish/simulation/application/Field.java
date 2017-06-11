package de.pvr.fish.simulation.application;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.pvr.fish.simulation.algorithm.task.FishTask;
import de.pvr.fish.simulation.algorithm.task.CalculatePositionTask;
import de.pvr.fish.simulation.algorithm.task.SetNewPositionTask;
import de.pvr.fish.simulation.model.Fish;
import de.pvr.fish.simulation.model.Position;

public class Field {
	
	private ArrayList<Fish> fishes;
	private int length;
	private int height;
	
	private int threads;
	private int fishNumber;
	
	private ExecutorService executorService;
	private ArrayList<FishTask> tasks;
	
	private static final Logger LOG = LogManager.getLogger(Field.class);
	
	
	public Field(int length, int height, int fishNumber, int threads) {
		this.length = length;
		this.height = height;
		this.fishNumber = fishNumber;
		this.threads = threads;
		fishes = new ArrayList<Fish>();
		
		this.executorService = Executors.newFixedThreadPool(threads);
		this.tasks = new ArrayList<FishTask>();
		
	}
	
	public boolean addNewFishToField(Fish fish) {
		//if (this.fishes[(int) fish.getPosition().getCoordinateX()][(int) fish.getPosition().getCoordinateY()] == null) {
			//this.fishes[(int) fish.getPosition().getCoordinateX()][(int) fish.getPosition().getCoordinateY()] = fish;
			fishes.add(fish);
			return true;
		//}
		//return false;
	}
	
	public void nextInteration() {
		LOG.info("Starting overall Iteration");
		
		tasks.clear();
		//split Task 
		int startPosition = 0;
		int endPosition = 0;
		for (int i = 0; i < threads - 1; i++) {
			endPosition = fishNumber/threads * (i + 1);
			this.tasks.add(new CalculatePositionTask(this.fishes, this.fishes.subList(startPosition, endPosition), startPosition, endPosition));
			startPosition = endPosition + 1;
		}
		endPosition = fishNumber - 1;
		this.tasks.add(new CalculatePositionTask(this.fishes, this.fishes.subList(startPosition, endPosition), startPosition, endPosition));
		//Execution
		try {
			this.executorService.invokeAll(this.tasks);
		} catch (InterruptedException e) {
			LOG.error(e.getMessage());
		}
		//2.
		ArrayList<Position> positions = splitTasks();
		for (Position p : positions) {
			this.tasks.add(new SetNewPositionTask(this.fishes, this.fishes.subList(p.getCoordinateX(), endPosition), startPosition, endPosition));
		}
		endPosition = fishNumber - 1;
		this.tasks.add(new SetNewPositionTask(this.fishes, this.fishes.subList(startPosition, endPosition), startPosition, endPosition));
		//Execution
		try {
			this.executorService.invokeAll(this.tasks);
		} catch (InterruptedException e) {
			LOG.error(e.getMessage());
		}
	}
	
	public ArrayList<Integer, Integer> splitTasks() {
		ArrayList<Position> positions = new ArrayList<Position>();
		int startPosition = 0;
		int endPosition = 0;
		for (int i = 0; i < threads - 1; i++) {
			endPosition = fishNumber/threads * (i + 1);
			positions.add(new Position (startPosition, endPosition));
			startPosition = endPosition + 1;
		}
		endPosition = fishNumber - 1;
		positions.add(new Position (startPosition, endPosition));
		return positions;

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

	public ArrayList<FishTask> getTasks() {
		return tasks;
	}

	public void setTasks(ArrayList<FishTask> tasks) {
		this.tasks = tasks;
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
