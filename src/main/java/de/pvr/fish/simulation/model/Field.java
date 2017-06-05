package de.pvr.fish.simulation.model;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.pvr.fish.simulation.algorithm.task.IterationTask;
import de.pvr.fish.simulation.config.FishConstants;

public class Field {
	
	private Fish[][] fishes;
	private int length;
	private int height;
	private int numberOfSquares;
	
	private ExecutorService executorService;
	private ArrayList<IterationTask> tasks;
	
	private static final Logger LOG = LogManager.getLogger(Field.class);
	
	
	public Field(int length, int height) {
		this.length = length;
		this.height = height;
		fishes = new Fish[length][height];
		
		this.executorService = Executors.newFixedThreadPool(FishConstants.THREADS);
		this.tasks = new ArrayList<IterationTask>();
		
	}
	
	public void addNewFishToField(Fish fish) {
		this.fishes[fish.getPosition().getCoordinateX()][fish.getPosition().getCoordinateY()] = fish;
	}
	
	public void nextInteration() {
		LOG.info("Starting Iteration");
		
		//split Task 
		Position startPosition = new Position (0, 0);
		for (Position position : splitTasks()) {
			this.tasks.add(new IterationTask(this.fishes, startPosition, position));
			position.nextPosition();
			startPosition = position;
		}
		
		//Executtion
		try {
			this.executorService.invokeAll(this.tasks);
		} catch (InterruptedException e) {
			LOG.error(e.getMessage());
		}
		
		// for....
		
		//setNeigbourFish
		//fish.move()
	}
	
	private ArrayList<Position> splitTasks() {
		ArrayList<Position> borderPositions = new ArrayList<Position>();
		int fishcounter = 0;
		for (int i = 0; i < this.length - 1; i++) {
			for( int j = 0; j < this.height - 1; j++) {
				if (this.fishes[i][j] != null) {
					fishcounter++;
					if (fishcounter%(FishConstants.NUMBER_FISH/FishConstants.THREADS) == 0) {
						borderPositions.add(new Position(i, j));
					}
				}
			}
		}
		return borderPositions;
	}
	
	


	
	
}
