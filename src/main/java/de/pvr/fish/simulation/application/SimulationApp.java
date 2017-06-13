package de.pvr.fish.simulation.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.pvr.fish.simulation.config.FishParameter;
import de.pvr.fish.simulation.model.Fish;
import de.pvr.fish.simulation.util.RandomGenerator;

public class SimulationApp {
	
	private int fieldLength;
	private int fieldHeight;
	private int fishNumber;
	private int threads;
	private int iterations;
	
	private Field field;
	
	private static final Logger LOG = LogManager.getLogger(SimulationApp.class);

	public static void main(String[] args) {
		//TODO 4 Parameter validieren
		
		//SimulationApp app = new SimulationApp(FishParameter.FIELD_LENGTH, FishParameter.FIELD_HEIGHT, FishParameter.NUMBER_FISH, FishParameter.THREADS, FishParameter.ITERATIONS);
		SimulationApp app = new SimulationApp(600, 600, 1100, 8, 3, 4, 30, 2, 4,6,1);
		app.startIterations();
	}
	
	public SimulationApp(int fieldLength, int fieldHeight, int fishNumber, int threads, int iterations) {
		this.fieldLength = fieldLength;
		this.fieldHeight = fieldHeight;
		this.fishNumber = fishNumber;
		this.threads = threads;
		this.iterations = iterations;
	}
	
	public SimulationApp(int fieldLength, int fieldHeight, int fishNumber, int threads, int iterations, int neighbours, int deathAngle, int r1, int r2, int r3, int bodyLength) {
		this.fieldLength = fieldLength;
		this.fieldHeight = fieldHeight;
		this.fishNumber = fishNumber;
		this.threads = threads;
		this.iterations = iterations;
		
		setParametersInFischParameters(neighbours, deathAngle, r1, r2, r3, bodyLength);
		createField();
		
		LOG.info("Creating new Field " + this.fieldLength + " x " + this.fieldHeight + " with " + this.fishNumber + " Fishes and " + this.threads + " threads and "+ this.iterations + "iterations." );
		LOG.info("Specific Fish Parameters: Body Length: " + bodyLength + ", Neigbours " + neighbours + ", Death Angel: " + deathAngle + " and Radius: " + r1 + " " + r2 + " "+ r3);
	}
	
	public void createField() {
		this.field = new Field(this.fieldLength, this.fieldHeight, this.fishNumber, this.threads);
		createRandomFishes(this.fishNumber, field.getLength(), field.getHeight());
	}
	
	public void startIterations() {
		for (int i = 0; i < this.iterations; i++) {
			this.field.nextInteration();
		}
	}
	
	public void startIteration() {
		this.field.nextInteration();
	}
	
	public void setParametersInFischParameters(int neighbours, int deathAngle, int r1, int r2, int r3, int bodyLength) {
		FishParameter.ITERATIONS = this.iterations;
		FishParameter.THREADS = this.threads;
		FishParameter.NUMBER_FISH = this.fishNumber;
		FishParameter.FIELD_LENGTH = this.fieldLength;
		FishParameter.FIELD_HEIGHT = this.fieldLength;
		FishParameter.NUMBER_OF_NEIGHBOURS = neighbours;
		FishParameter.DEATH_ANGLE = deathAngle;
		FishParameter.RADIUS1 = r1;
		FishParameter.RADIUS2 = r2;
		FishParameter.RADIUS3 = r3;
		FishParameter.FISH_BODY_LENGTH = bodyLength;
	}

	private void createRandomFishes(int fishNumber, int fieldLength, int fieldHeight) {
		for (int i = 0; i < fishNumber; i++) {
			if (!field.addNewFishToField(createRandomFish(fieldLength - 1, fieldHeight - 1))) {
				i--;
			}
		}
	}
	
	private Fish createRandomFish(int fieldLength, int fieldHeight) {
		return RandomGenerator.getRandomFish(fieldLength, fieldHeight);
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}
	
	public int getFieldLength() {
		return fieldLength;
	}

	public void setFieldLength(int fieldLength) {
		this.fieldLength = fieldLength;
	}

	public int getFieldHeight() {
		return fieldHeight;
	}

	public void setFieldHeight(int fieldHeight) {
		this.fieldHeight = fieldHeight;
	}

	public int getFishNumber() {
		return fishNumber;
	}

	public void setFishNumber(int fishNumber) {
		this.fishNumber = fishNumber;
	}

	public int getThreads() {
		return threads;
	}

	public void setThreads(int threads) {
		this.threads = threads;
	}

	public int getIterations() {
		return iterations;
	}

	public void setIterations(int iterations) {
		this.iterations = iterations;
	}
}
