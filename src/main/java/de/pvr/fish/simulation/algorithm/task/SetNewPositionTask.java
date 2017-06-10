package de.pvr.fish.simulation.algorithm.task;

import java.util.concurrent.Callable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.pvr.fish.simulation.model.Fish;
import de.pvr.fish.simulation.model.Position;

public class SetNewPositionTask extends FishTask{
	
	private Fish[][] newFishes;
	
	private static final Logger LOG = LogManager.getLogger(SetNewPositionTask.class);
	
	
	public SetNewPositionTask( Fish[][] oldFishes,Fish[][] newFishes, Position startPosition, Position endPosition) {
		super(oldFishes, startPosition, endPosition);
		this.newFishes = newFishes;
	}
	

	@Override
	public Void call() throws Exception {
		LOG.info("Starting with SetNewPosition Task from " + this.startPosition + " to " + this.endPosition);
		Fish fish;
		for ( int i = (int) startPosition.getCoordinateX() ; i < endPosition.getCoordinateX(); i++) {
			for (int j = (int) startPosition.getCoordinateY() ; j < endPosition.getCoordinateY(); i++) {
				if (fishes[i][j] != null) {
					fish = fishes[i][j];
					fish.goToNextPosition();
					newFishes[(int) fish.getPosition().getCoordinateX()][(int) fish.getPosition().getCoordinateY()] = fish;
				}
			}
		}
		
		return null;
	}

}
