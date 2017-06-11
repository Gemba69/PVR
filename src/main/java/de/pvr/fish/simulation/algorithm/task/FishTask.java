package de.pvr.fish.simulation.algorithm.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import de.pvr.fish.simulation.model.Fish;

public abstract class FishTask implements Callable<Void> {
	
	protected ArrayList<Fish> fishes;
	protected List<Fish> subFishes;
	protected int startPosition;
	protected int endPosition;
	
	
	public FishTask( ArrayList<Fish> fishes,  List<Fish> subFishes, int startPosition, int endPosition) {
		this.fishes = fishes;
		this.subFishes = subFishes;
		this.startPosition = startPosition;
		this.endPosition = endPosition;
	}

}
