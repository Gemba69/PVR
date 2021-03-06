package de.pvr.fish.simulation.algorithm.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.pvr.fish.simulation.config.FishParameter;
import de.pvr.fish.simulation.model.Fish;
import de.pvr.fish.simulation.util.CommonUtil;
import de.pvr.fish.simulation.util.Radius;
import de.pvr.fish.simulation.util.RandomGenerator;

import org.apache.commons.lang3.tuple.Pair;

public class CalculateNewPositionTask extends FishTask {

	private static final Logger LOG = LogManager.getLogger(CalculateNewPositionTask.class);

	public CalculateNewPositionTask(ArrayList<Fish> fishes, ArrayList<Fish> subFishes, int startPositon,
			int endPosition) {
		super(fishes, subFishes, startPositon, endPosition);
	}

	@Override
	public Void call() throws Exception {
		LOG.info("Starting with CalculatePosition Task from " + this.startPosition + " to " + this.endPosition);
		for (Fish fish : subFishes) {
			LOG.debug("Calculate new Position for fish with Position " + fish.getPosition());
			calculateNewPlace(fish, findNeighbours(fish));
		}
		return null;
	}

	public ArrayList<Pair<Fish, Radius>> findNeighbours(Fish fish) {
		ArrayList<Pair<Fish, Radius>> neighbourFishes = new ArrayList<Pair<Fish, Radius>>();
		// R1
		neighbourFishes.addAll(searchInR1(fish, FishParameter.NUMBER_OF_NEIGHBOURS));
		// R2
		if (neighbourFishes.size() < FishParameter.NUMBER_OF_NEIGHBOURS) {
			neighbourFishes.addAll(searchInR2(fish, FishParameter.NUMBER_OF_NEIGHBOURS - neighbourFishes.size()));
		}
		// R3
		if (neighbourFishes.size() < FishParameter.NUMBER_OF_NEIGHBOURS) {
			neighbourFishes.addAll(searchInR3(fish, FishParameter.NUMBER_OF_NEIGHBOURS - neighbourFishes.size()));
		}
		LOG.debug("Number of Neigbours is: " + neighbourFishes.size() + " and List is " + neighbourFishes);
		return neighbourFishes;
	}

	public void calculateNewPlace(Fish fish, ArrayList<Pair<Fish, Radius>> neighbourFishes) {
		fish.turnAtCalc(calculateNewAngle(fish, neighbourFishes));

	}

	private ArrayList<Pair<Fish, Radius>> searchInR1(Fish fish, int freeCapacity) {
		return searchInSpecificRadius(fish, freeCapacity, 0, FishParameter.RADIUS1 * FishParameter.FISH_BODY_LENGTH,
				Radius.R1);
	}

	private ArrayList<Pair<Fish, Radius>> searchInR2(Fish fish, int freeCapacity) {
		return searchInSpecificRadius(fish, freeCapacity, FishParameter.RADIUS1 * FishParameter.FISH_BODY_LENGTH,
				FishParameter.RADIUS2 * FishParameter.FISH_BODY_LENGTH, Radius.R2);
	}

	private ArrayList<Pair<Fish, Radius>> searchInR3(Fish fish, int freeCapacity) {
		return searchInSpecificRadius(fish, freeCapacity, FishParameter.RADIUS2 * FishParameter.FISH_BODY_LENGTH,
				FishParameter.RADIUS3 * FishParameter.FISH_BODY_LENGTH, Radius.R3);
	}

	private ArrayList<Pair<Fish, Radius>> searchInSpecificRadius(Fish fish, int freeCapacity, double minRadusLength,
			double radiusLength, Radius radius) {
		LOG.debug("Searchin for Radous " + radius + " from " + minRadusLength + " to " + radiusLength
				+ " with freeCapacity: " + freeCapacity);
		ArrayList<Pair<Fish, Radius>> neighbourFishes = new ArrayList<Pair<Fish, Radius>>();
		double distance;
		for (Fish potencialNeighbour : fishes) {
			distance = fish.getPosition().getDiffBetweenPositions(potencialNeighbour.getPosition()).getLength();
			if (potencialNeighbour != fish && distance >= minRadusLength && distance < radiusLength
					&& !fish.isInDeathAngle(potencialNeighbour.getPosition())) {
				neighbourFishes.add(Pair.of(potencialNeighbour, radius));
			}

		}
		if (neighbourFishes.size() > freeCapacity) {
			Collections.sort(neighbourFishes, new Comparator<Pair<Fish, Radius>>() {
				@Override
				public int compare(Pair<Fish, Radius> p1, Pair<Fish, Radius> p2) {
					return (int) (p1.getLeft().getPosition().getDiffBetweenPositions(fish.getPosition()).getLength()
							- p2.getLeft().getPosition().getDiffBetweenPositions(fish.getPosition()).getLength());
				}
			});
			neighbourFishes = new ArrayList<Pair<Fish, Radius>>(neighbourFishes.subList(0, freeCapacity));
		}
		return neighbourFishes;
	}

	public int calculateNewAngle(Fish fish, ArrayList<Pair<Fish, Radius>> neighbourFishes) {
		int newAngle = 0;
		if (neighbourFishes.isEmpty()) {
			newAngle = RandomGenerator.getRandomAngle();
		} else {
			for (Pair<Fish, Radius> pair : neighbourFishes) {
				switch (pair.getRight()) {
				case R1:
					newAngle = newAngle + (Math.min(
							(CommonUtil.getAngle(fish.getDiffPosition(), pair.getLeft().getDiffPosition()) + 90),
							(CommonUtil.getAngle(fish.getDiffPosition(), pair.getLeft().getDiffPosition()) - 90)));
					break;
				case R2:
					newAngle = newAngle
							+ (CommonUtil.getAngle(fish.getDiffPosition(), pair.getLeft().getDiffPosition()));
					break;
				case R3:
					newAngle = newAngle + (CommonUtil.getAngle(fish.getDiffPosition(),
							pair.getLeft().getPosition().getDiffBetweenPositions(fish.getPosition())));
					break;
				}
			}
			newAngle = (newAngle / neighbourFishes.size());
		}
		LOG.debug("The fish turns at: " + newAngle);
		return newAngle;
	}

}
