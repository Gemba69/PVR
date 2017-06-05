package de.pvr.fish.simulation.algorithm.task;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.pvr.fish.simulation.config.FishConstants;
import de.pvr.fish.simulation.model.Fish;
import de.pvr.fish.simulation.model.Position;
import de.pvr.fish.simulation.util.Radius;

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
		for (int i = startPosition.getCoordinateX() ; i < endPosition.getCoordinateX(); i++) {
			for (int j = startPosition.getCoordinateY() ; j < endPosition.getCoordinateY(); i++) {
				if (fishes[i][j] != null) {
					Fish fish = fishes[i][j];
					setToNewPlace(fish, findNeighbours(fish));
				}
			}
		}
		return null;
	}
	
	
	
	private ArrayList<Pair<Fish, Radius>> findNeighbours(Fish fish) {
		ArrayList<Pair<Fish, Radius>> neighbourFishes = new ArrayList<Pair<Fish, Radius>>();
		//R1
		neighbourFishes.addAll(searchInR1(fish, FishConstants.NUMBER_OF_NEIGHBOURS));
		//R2
		if (neighbourFishes.size() < FishConstants.NUMBER_OF_NEIGHBOURS) {
			neighbourFishes.addAll(searchInR2(fish, FishConstants.NUMBER_OF_NEIGHBOURS - neighbourFishes.size()));
		}
		//R3
		if (neighbourFishes.size() < FishConstants.NUMBER_OF_NEIGHBOURS) {
			neighbourFishes.addAll(searchInR3(fish, FishConstants.NUMBER_OF_NEIGHBOURS - neighbourFishes.size()));
		}
		
		return neighbourFishes;
	}
	
	private void setToNewPlace(Fish fish, ArrayList<Pair<Fish, Radius>> neighbourFishes) {
		if (neighbourFishes.isEmpty()) {
			fish.turnAround();
		} else {
			this.fishes[fish.getPosition().getCoordinateX()][fish.getPosition().getCoordinateY()] = null;
			
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
					newPositionX = newPositionX + (pair.getLeft().getPosition().getCoordinateX() - fish.getPosition().getCoordinateX());
					newPositionY = newPositionY + (pair.getLeft().getPosition().getCoordinateY() - fish.getPosition().getCoordinateY());
				break;
			}
			fish.setAngle( newAngle / neighbourFishes.size());
			if (R3counter > 1) {
				fish.setPosition(new Position (newPositionX / R3counter, newPositionY / R3counter));
			}
			
			this.fishes[fish.getPosition().getCoordinateX()][fish.getPosition().getCoordinateY()] = fish;
		}
		}
		
	}
	
	private ArrayList<Pair<Fish, Radius>> searchInR1(Fish fish, int freeCapacity) {
		ArrayList<Pair<Fish, Radius>> neighbourFishes = new ArrayList<Pair<Fish, Radius>>();
		Position startPosition = fish.getPosition().getRadiusStartPosition(FishConstants.RADIUS1 * FishConstants.FISH_BODY_LENGTH);
		Position endPosition =  fish.getPosition().getRadiusEndPosition(FishConstants.RADIUS1 * FishConstants.FISH_BODY_LENGTH);
		
		for (int i = startPosition.getCoordinateX(); i < endPosition.getCoordinateX(); i++) {
			for ( int j = startPosition.getCoordinateY(); j < endPosition.getCoordinateY(); j++) {
				if (fishes[i][j] != null) {
					neighbourFishes.add(Pair.of(fishes[i][j], Radius.R1));
				}
			}
		}
		//TODO Einschränkung im Totenwinkel
		
		//TODO reduceToFour
		if (neighbourFishes.size() > freeCapacity) {
			
		}
		
		return neighbourFishes;
	}
	
	private ArrayList<Pair<Fish, Radius>> searchInR2(Fish fish, int freeCapacity) {
		ArrayList<Pair<Fish, Radius>> neighbourFishes = new ArrayList<Pair<Fish, Radius>>();
		Position startPosition = fish.getPosition().getRadiusStartPosition(FishConstants.RADIUS2 * FishConstants.FISH_BODY_LENGTH);
		Position endPosition =  fish.getPosition().getRadiusEndPosition(FishConstants.RADIUS2 * FishConstants.FISH_BODY_LENGTH);
		
		for (int i = startPosition.getCoordinateX(); i < endPosition.getCoordinateX(); i++) {
			for ( int j = startPosition.getCoordinateY(); j < endPosition.getCoordinateY(); j++) {
				if (fishes[i][j] != null) {
					neighbourFishes.add(Pair.of(fishes[i][j], Radius.R1));
				}
			}
		}
		//TODO Einschränkung im Totenwinkel
		
		//TODO reduceToFour
		if (neighbourFishes.size() > freeCapacity) {
			
		}
		
		return neighbourFishes;
	}
	
	private ArrayList<Pair<Fish, Radius>> searchInR3(Fish fish, int freeCapacity) {
		ArrayList<Pair<Fish, Radius>> neighbourFishes = new ArrayList<Pair<Fish, Radius>>();
		Position startPosition = fish.getPosition().getRadiusStartPosition(FishConstants.RADIUS3 * FishConstants.FISH_BODY_LENGTH);
		Position endPosition =  fish.getPosition().getRadiusEndPosition(FishConstants.RADIUS3 * FishConstants.FISH_BODY_LENGTH);
		
		for (int i = startPosition.getCoordinateX(); i < endPosition.getCoordinateX(); i++) {
			for ( int j = startPosition.getCoordinateY(); j < endPosition.getCoordinateY(); j++) {
				if (fishes[i][j] != null) {
					neighbourFishes.add(Pair.of(fishes[i][j], Radius.R1));
				}
			}
		}
		//TODO Einschränkung im Totenwinkel
		
		//TODO reduceToFour
		if (neighbourFishes.size() > freeCapacity) {
			
		}
		
		return neighbourFishes;
	}

}
