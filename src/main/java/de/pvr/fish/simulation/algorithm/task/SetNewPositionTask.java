package de.pvr.fish.simulation.algorithm.task;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.pvr.fish.simulation.model.Fish;

public class SetNewPositionTask extends FishTask{
	
	public SetNewPositionTask(ArrayList<Fish> fishes, ArrayList<Fish> subFishes, int startPositon, int endPosition) {
		super(fishes, subFishes, startPositon, endPosition);
	}

	private static final Logger LOG = LogManager.getLogger(SetNewPositionTask.class);

	@Override
	public Void call() throws Exception {
		LOG.info("Starting with SetNewPosition Task from " + this.startPosition + " to " + this.endPosition);
		for (Fish fish : subFishes) {
			LOG.debug("Setting fish from Position " + fish.getPosition() + " to Position " + fish.getCalcNextPosition());
			fish.goToNextPosition();
		}
		return null;
	}
}

