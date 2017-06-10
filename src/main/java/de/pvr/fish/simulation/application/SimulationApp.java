package de.pvr.fish.simulation.application;

import java.util.Vector;

import de.pvr.fish.simulation.config.FishParameter;
import de.pvr.fish.simulation.model.Fish;
import de.pvr.fish.simulation.model.Position;
import de.pvr.fish.simulation.util.RandomGenerator;

public class SimulationApp {
	
	private int fieldLength;
	private int fieldHeight;
	private int fishNumber;
	private int threads;
	private int iterations;

	
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

	public static void main(String[] args) {
		//TODO 4 Parameter validieren
		
		SimulationApp app = new SimulationApp(FishParameter.FIELD_LENGTH, FishParameter.FIELD_HEIGHT, FishParameter.NUMBER_FISH, FishParameter.THREADS, FishParameter.ITERATIONS);
		
		app.createFieldAndIterate();
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
	}
	
	public void createFieldAndIterate() {
		Field field = new Field(this.fieldLength, this.fieldHeight, this.fishNumber, this.threads);
		
		field.setFishes(createRandomFishes(this.fishNumber, field.getLength(), field.getHeight()));
		
		for (int i = 0; i < this.iterations; i++) {
			field.nextInteration();
		}
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

	private static Fish[][] createRandomFishes(int fishNumber, int fieldLength, int fieldHeight) {
		Fish[][] fishes = new Fish[fieldLength][fieldHeight];
		Fish fish;
		for (int i = 0; i > fishNumber; i++) {
			fish = createRandomFish(fieldLength, fieldHeight);
			if (fishes[(int) fish.getPosition().getCoordinateX()][(int) fish.getPosition().getCoordinateX()] == null) {
				fishes[(int) fish.getPosition().getCoordinateX()][(int) fish.getPosition().getCoordinateX()] = fish;
			} else {
				i--;
			}
		}
		
		return fishes;
		
	}
	
	private static Fish createRandomFish(int fieldLength, int fieldHeight) {
		return RandomGenerator.getRandomFish(fieldLength, fieldHeight);
	}
	
	

}
