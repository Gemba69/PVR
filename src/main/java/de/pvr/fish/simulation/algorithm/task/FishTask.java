package de.pvr.fish.simulation.algorithm.task;

import java.util.concurrent.Callable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.pvr.fish.simulation.model.Fish;
import de.pvr.fish.simulation.model.Position;

public abstract class FishTask implements Callable<Void> {
	
	protected Fish[][] fishes;
	protected Position startPosition;
	protected Position endPosition;
	
	
	public FishTask( Fish[][] fishes, Position startPosition, Position endPosition) {
		this.fishes = fishes;
		this.startPosition = startPosition;
		this.endPosition = endPosition;
	}

}
