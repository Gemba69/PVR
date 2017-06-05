package de.pvr.fish.simulation.algorithm.task;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.pvr.fish.simulation.config.FishConstants;
import de.pvr.fish.simulation.model.Field;
import de.pvr.fish.simulation.model.Fish;
import de.pvr.fish.simulation.model.Position;
import de.pvr.fish.simulation.util.Radius;

import org.apache.commons.lang3.tuple.Pair;

public class TestTask implements Callable<Void>{
	
	private Fish[][] fishes;
	private Position startPosition;
	private Position endPosition;
	
	private static final Logger LOG = LogManager.getLogger(TestTask.class);
	
	
	public TestTask( Fish[][] fishes, Position startPosition, Position endPosition) {
		this.fishes = fishes;
		this.startPosition = startPosition;
		this.endPosition = endPosition;
	}
	

	@Override
	public Void call() throws Exception {
		LOG.info("Starting with Task");
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
			
		} 
		
	}
	
	private ArrayList<Pair<Fish, Radius>> searchInR1(Fish fish, int FreeCapacity) {
		ArrayList<Pair<Fish, Radius>> neighbourFishes = new ArrayList<Pair<Fish, Radius>>();
		//TODO
		
		//TODO reduceToFour
		
		return neighbourFishes;
	}
	
	private ArrayList<Pair<Fish, Radius>> searchInR3(Fish fish, int FreeCapacity) {
		ArrayList<Pair<Fish, Radius>> neighbourFishes = new ArrayList<Pair<Fish, Radius>>();
		//TODO
		
		//TODO reduceToFour
		
		return neighbourFishes;
	}
	
	private ArrayList<Pair<Fish, Radius>> searchInR2(Fish fish, int FreeCapacity) {
		ArrayList<Pair<Fish, Radius>> neighbourFishes = new ArrayList<Pair<Fish, Radius>>();
		//TODO
		
		//TODO reduceToFour
		
		return neighbourFishes;
	}

}
