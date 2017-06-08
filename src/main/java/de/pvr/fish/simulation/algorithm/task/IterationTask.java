package de.pvr.fish.simulation.algorithm.task;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.pvr.fish.simulation.config.FishParameter;
import de.pvr.fish.simulation.model.Fish;
import de.pvr.fish.simulation.model.Position;
import de.pvr.fish.simulation.util.Radius;
import de.pvr.fish.simulation.util.RandomGenerator;

import org.apache.commons.lang3.tuple.Pair;

public class IterationTask implements Callable<Void>{
	
	private Fish[][] fishes;
	private Position startPosition;
	private Position endPosition;
	
	private static final Logger LOG = LogManager.getLogger(IterationTask.class);
	
	
	public IterationTask( Fish[][] fishes, Position startPosition, Position endPosition) {
		this.fishes = fishes;
		this.startPosition = startPosition;
		this.endPosition = endPosition;
	}
	

	@Override
	public Void call() throws Exception {
		LOG.info("Starting with Task iteration");
		for ( int i = (int) startPosition.getCoordinateX() ; i < endPosition.getCoordinateX(); i++) {
			for (int j = (int) startPosition.getCoordinateY() ; j < endPosition.getCoordinateY(); i++) {
				if (fishes[i][j] != null) {
					Fish fish = fishes[i][j];
					setToNewPlace(fish, findNeighbours(fish));
				}
			}
		}
		return null;
	}
	
	
	
	public ArrayList<Pair<Fish, Radius>> findNeighbours(Fish fish) {
		ArrayList<Pair<Fish, Radius>> neighbourFishes = new ArrayList<Pair<Fish, Radius>>();
		//R1
		neighbourFishes.addAll(searchInR1(fish, FishParameter.NUMBER_OF_NEIGHBOURS));
		//R2
		if (neighbourFishes.size() < FishParameter.NUMBER_OF_NEIGHBOURS) {
			neighbourFishes.addAll(searchInR2(fish, FishParameter.NUMBER_OF_NEIGHBOURS - neighbourFishes.size()));
		}
		//R3
		if (neighbourFishes.size() < FishParameter.NUMBER_OF_NEIGHBOURS) {
			neighbourFishes.addAll(searchInR3(fish, FishParameter.NUMBER_OF_NEIGHBOURS - neighbourFishes.size()));
		}
		
		return neighbourFishes;
	}
	
	public void setToNewPlace(Fish fish, ArrayList<Pair<Fish, Radius>> neighbourFishes) {
		if (neighbourFishes.isEmpty()) {
			fish.turnAt(RandomGenerator.getRandomAngle());
		} else {
			this.fishes[(int) fish.getPosition().getCoordinateX()][(int) fish.getPosition().getCoordinateY()] = null;
			
			int newAngle = 0;
			int newPositionX = 0;
			int newPositionY = 0;
			int R3counter = 0; 
			
			for (Pair<Fish, Radius> pair : neighbourFishes) {
				switch (pair.getRight()) {
				case R1:
					newAngle = newAngle + ((fish.getAngle() + 90) % 360);
				case R2:
					newAngle = newAngle + (pair.getLeft().getAngle());
				case R3:
					newAngle = newAngle + (pair.getLeft().getAngle());
					R3counter++;
				break;
			}
			newAngle = ( newAngle / neighbourFishes.size());
			//fish.setNextPosition(fish.);
			fish.turnAt(newAngle);
			//this.fishes[fish.getPosition().getCoordinateX()][fish.getPosition().getCoordinateY()] = fish;
			//TODO fish wirklich auf neue Position legen
			//TODO Speed
		}
		}
		
	}
	
	private ArrayList<Pair<Fish, Radius>> searchInR1(Fish fish, int freeCapacity) {
		return searchInSpecificRadius(fish, freeCapacity, 0, FishParameter.RADIUS1, Radius.R1);
	}
	
	private ArrayList<Pair<Fish, Radius>> searchInR2(Fish fish, int freeCapacity) {
		return searchInSpecificRadius(fish, freeCapacity, FishParameter.RADIUS1, FishParameter.RADIUS2, Radius.R2);
	}
	
	private ArrayList<Pair<Fish, Radius>> searchInR3(Fish fish, int freeCapacity) {
		return searchInSpecificRadius(fish, freeCapacity, FishParameter.RADIUS2, FishParameter.RADIUS3, Radius.R3);
	}
	
	private ArrayList<Pair<Fish, Radius>> searchInSpecificRadius(Fish fish, int freeCapacity, int minRadusLength, int radiusLength, Radius radius) {
		ArrayList<Pair<Fish, Radius>> neighbourFishes = new ArrayList<Pair<Fish, Radius>>();
		Position startPosition = fish.getPosition().getRadiusStartPosition(radiusLength * FishParameter.FISH_BODY_LENGTH);
		Position endPosition =  fish.getPosition().getRadiusEndPosition(radiusLength * FishParameter.FISH_BODY_LENGTH);
		
		Fish potencialNeighbour;
		for (int i = (int) startPosition.getCoordinateX(); i < endPosition.getCoordinateX(); i++) {
			for ( int j = (int) startPosition.getCoordinateY(); j < endPosition.getCoordinateY(); j++) {
				if (fishes[i][j] != null) {
					potencialNeighbour = fishes[i][j];
					if (potencialNeighbour != fish && fish.getPosition().getDiffBetweenPositions(potencialNeighbour.getPosition()).getLength() >= minRadusLength && !fish.isInDeathAngle(potencialNeighbour.getPosition())) {
						neighbourFishes.add(Pair.of(fishes[i][j], radius));
					}					
				}
			}
		}
		
		while (neighbourFishes.size() > freeCapacity) {
			neighbourFishes.remove(0);
			//TODO eleganter machen
		}
		
		return neighbourFishes;
	}
	
	private void setNewSpeed() {
		
	}

}
