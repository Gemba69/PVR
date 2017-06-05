package de.pvr.fish.simulation.algorithm.task;

import java.util.concurrent.Callable;

import de.pvr.fish.simulation.model.Fish;
import de.pvr.fish.simulation.model.Position;

public class TestTask implements Callable<Void>{
	
	private Fish[][] fishes;
	private Position startPosition;
	private Position endPosition;
	
	
	public TestTask( Fish[][] fishes, Position startPosition, Position endPosition) {
		this.fishes = fishes;
		this.startPosition = startPosition;
		this.endPosition = endPosition;
	}
	

	@Override
	public Void call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	private void testMethod() {
		
	}

}
