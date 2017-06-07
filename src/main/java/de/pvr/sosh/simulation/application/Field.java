package de.pvr.sosh.simulation.application;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.pvr.fish.simulation.algorithm.task.IterationTask;
import de.pvr.fish.simulation.config.FishParameter;
import de.pvr.fish.simulation.model.Fish;
import de.pvr.fish.simulation.model.Position;

public class Field {
	
	private Fish[][] fishes;
	private int length;
	private int height;
	
	private ExecutorService executorService;
	private ArrayList<IterationTask> tasks;
	
	private static final Logger LOG = LogManager.getLogger(Field.class);
	
	
	public Field(int length, int height) {
		this.length = length;
		this.height = height;
		fishes = new Fish[length][height];
		
		this.executorService = Executors.newFixedThreadPool(FishParameter.THREADS);
		this.tasks = new ArrayList<IterationTask>();
		
	}
	
	public void addNewFishToField(Fish fish) {
		this.fishes[fish.getPosition().getCoordinateX()][fish.getPosition().getCoordinateY()] = fish;
	}
	
	public void nextInteration() {
		LOG.info("Starting overall Iteration");
		
		//split Task 
		Position startPosition = new Position (0, 0);
		for (Position position : splitTasks()) {
			this.tasks.add(new IterationTask(this.fishes, startPosition, position));
			position.nextPosition();
			startPosition = position;
		}
		
		//Execution
		try {
			this.executorService.invokeAll(this.tasks);
		} catch (InterruptedException e) {
			LOG.error(e.getMessage());
		}
		
		// for....
		
		//setNeigbourFish
		//fish.move()
	}
	
	public ArrayList<Position> splitTasks() {
		ArrayList<Position> borderPositions = new ArrayList<Position>();
		int fishcounter = 0;
		for (int i = 0; i < this.length - 1; i++) {
			for( int j = 0; j < this.height - 1; j++) {
				if (this.fishes[i][j] != null) {
					fishcounter++;
					if (fishcounter%(FishParameter.NUMBER_FISH/FishParameter.THREADS) == 0) {
						borderPositions.add(new Position(i, j));
						//FIXME: Ranbedingung, wenn Fischanzahl durch Threads nicht glatt durchgeht bzw, wenn es als letztes keine Zahl gibt
					}
				}
			}
		}
		return borderPositions;
	}

	public Fish[][] getFishes() {
		return fishes;
	}

	public void setFishes(Fish[][] fishes) {
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

	public ArrayList<IterationTask> getTasks() {
		return tasks;
	}

	public void setTasks(ArrayList<IterationTask> tasks) {
		this.tasks = tasks;
	}

	public static Logger getLog() {
		return LOG;
	}
	
	


	
	
}
