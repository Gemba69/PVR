package de.pvr.fish.simulation.application;

import static de.pvr.fish.simulation.util.WatchAreaType.*;
import org.apache.commons.lang3.time.StopWatch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.pvr.fish.simulation.config.FishParameter;
import de.pvr.fish.simulation.config.ThreadPoolSingleton;
import de.pvr.fish.simulation.model.Aquarium;
import de.pvr.fish.simulation.model.Fish;
import de.pvr.fish.simulation.util.MeasureUtil;
import de.pvr.fish.simulation.util.RandomGenerator;

public class SimulationApp {

	private int iterations;

	private Aquarium aquarium;

	private static final Logger LOG = LogManager.getLogger(SimulationApp.class);

	public SimulationApp(int iterations) {
		this.iterations = iterations;
	}

	public SimulationApp(int fieldLength, int fieldHeight, int fishNumber, int threads, int iterations, int neighbours,
			int deathAngle, double r1, double r2, double r3, int bodyLength) {
		MeasureUtil.resetAllWatches();
		this.iterations = iterations;

		setParametersInFischParameters(fieldLength, fieldHeight, fishNumber, threads, neighbours, deathAngle, r1, r2,
				r3, bodyLength);
		createAquariumsAndRandomFishes(fieldLength, fieldHeight, fishNumber);

		LOG.info("Created new Field " + fieldLength + " x " + fieldHeight + " with " + fishNumber + " Fishes and "
				+ threads + " threads and " + this.iterations + "iterations.");
		LOG.info("Specific Fish Parameters: Body Length: " + bodyLength + ", Neigbours " + neighbours
				+ ", Death Angel: " + deathAngle + " and Radius: " + r1 + " " + r2 + " " + r3);

		prepareAquariumForThreading(threads);
	}

	public void createAquariumsAndRandomFishes(int fieldLength, int fieldHeight, int fishNumber) {
		this.aquarium = new Aquarium(fieldLength, fieldHeight, fishNumber);
		createRandomFishes(fishNumber, aquarium.getLength(), aquarium.getHeight());
	}

	public void startIterations() {
		for (int i = 0; i < this.iterations; i++) {
			startIteration();
		}
	}

	public void startIteration() {
		this.aquarium.nextIteration();
	}

	public void setParametersInFischParameters(int fieldLength, int fieldHeight, int fishNumber, int threads,
			int neighbours, int deathAngle, double r1, double r2, double r3, int bodyLength) {
		FishParameter.ITERATIONS = this.iterations;
		FishParameter.THREADS = threads;
		FishParameter.NUMBER_FISH = fishNumber;
		FishParameter.FIELD_LENGTH = fieldLength;
		FishParameter.FIELD_HEIGHT = fieldHeight;
		FishParameter.NUMBER_OF_NEIGHBOURS = neighbours;
		FishParameter.DEATH_ANGLE = deathAngle;
		FishParameter.RADIUS1 = r1;
		FishParameter.RADIUS2 = r2;
		FishParameter.RADIUS3 = r3;
		FishParameter.FISH_BODY_LENGTH = bodyLength;
	}

	private void prepareAquariumForThreading(int threads) {
		LOG.debug("Staring Measuring Kappa");
		MeasureUtil.startWatch(KAPPA);
		ThreadPoolSingleton.createNewExecutorService(threads);
		this.aquarium.prepareTaskLists();
		MeasureUtil.suspend(KAPPA);
	}

	private void createRandomFishes(int fishNumber, int fieldLength, int fieldHeight) {
		for (int i = 0; i < fishNumber; i++) {
			if (!aquarium.addNewFishToField(createRandomFish(fieldLength - 1, fieldHeight - 1))) {
				i--;
			}
		}
	}

	private Fish createRandomFish(int fieldLength, int fieldHeight) {
		return RandomGenerator.getRandomFish(fieldLength, fieldHeight);
	}

	public Aquarium getAquarium() {
		return aquarium;
	}

	public void setAquarium(Aquarium aquarium) {
		this.aquarium = aquarium;
	}

	public int getIterations() {
		return iterations;
	}

	public void setIterations(int iterations) {
		this.iterations = iterations;
	}

}
