package de.pvr.fish.simulation.model;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.pvr.fish.simulation.config.FishParameter;
import de.pvr.fish.simulation.util.CommonUtil;
import de.pvr.fish.simulation.util.RandomGenerator;

public class Fish {

	private static final Logger LOG = LogManager.getLogger(Fish.class);

	private Position position;
	private Position nextPosition;
	private Position calcNextPosition;

	private static int maxSpedMultiplicator = FishParameter.MAX_SPEED_MULTIPLICATOR;

	public Fish(Position position, Position nextPosition) {
		this.position = position;
		this.nextPosition = nextPosition;
		this.calcNextPosition = nextPosition;
	}

	public Fish(Position position, Position nextPosition, Position calcNextPosition) {
		this.position = position;
		this.nextPosition = nextPosition;
		this.calcNextPosition = calcNextPosition;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Position getNextPosition() {
		return this.nextPosition;
	}

	public void setNextPosition(Position nextPosition) {
		this.nextPosition = nextPosition;
	}

	public Position getCalcNextPosition() {
		return this.calcNextPosition;
	}

	public void setCalcNextPosition(Position previousPosition) {
		this.calcNextPosition = previousPosition;
	}

	public void turnAround() {
		turnAt(-180);
	}

	public void turnAt(int angle) {
		angle *= -1;
		Position p = this.position.getDiffBetweenPositions(this.nextPosition);
		Position newNextPosition = new Position(this.position.getCoordinateX(), this.position.getCoordinateY());

		p.addSpecificAngle(angle);
		newNextPosition.addPosition(p);

		this.nextPosition = newNextPosition;
	}

	public void turnAroundCalc() {
		turnAtCalc(-180);
	}

	public void turnAtCalc(int angle) {
		angle *= -1;
		Position p = this.position.getDiffBetweenPositions(this.nextPosition);
		Position newNextPosition = new Position(this.position.getCoordinateX(), this.position.getCoordinateY());

		p.addSpecificAngle(angle);
		newNextPosition.addPosition(p);

		this.calcNextPosition = newNextPosition;
	}

	public boolean isInDeathAngle(Position positon) {
		int angleBetween = this.position.getAngle(positon);

		if (getAngle() - Math.abs(angleBetween) > 180 + FishParameter.DEATH_ANGLE / 2
				|| getAngle() - Math.abs(angleBetween) < 180 - FishParameter.DEATH_ANGLE / 2) {
			return false;
		} else {
			return true;
		}
	}

	public int getAngle() {
		return this.position.getAngle(this.nextPosition);
	}

	public int getCalcAngle() {
		return this.position.getAngle(this.calcNextPosition);
	}

	public double getNewSpeed() {
		return (RandomGenerator.getRandomSpeed(maxSpedMultiplicator - 1) + 1 ) * FishParameter.FISH_BODY_LENGTH;
	}

	public void goToNextPosition(double speed) {
		LOG.debug("Starting go to next Position: " + this);
		int angle = getCalcAngle();
		LOG.debug("The angle of Fish is: " + angle);
		this.position = this.calcNextPosition;
		Position newPosition = new Position(this.position.getCoordinateX(), this.position.getCoordinateY());
		newPosition.addLength(speed);
		LOG.debug("New Position is: " + newPosition);
		this.nextPosition = newPosition;
		turnAt(angle - 180);
		// prevent that fish can not swim out of the aquarium
		if (isFishOutOfAquariumX() || isFishOutOfAquariumY()) {
			//turnAround();
		}
		this.calcNextPosition = new Position(this.nextPosition.getCoordinateX(), this.nextPosition.getCoordinateY());
		LOG.debug("Ending go to next Position: " + this);
	}

	public void goToNextPosition() {
		goToNextPosition(getNewSpeed());
	}

	public Position getDiffPosition() {
		return this.position.getDiffBetweenPositions(nextPosition);
	}

	public Position getLengthPosition() {
		Position p = new Position(0, 0);
		p.addLength(FishParameter.FISH_BODY_LENGTH);
		p.addSpecificAngle(-1 * getAngle());
		p.addPosition(this.position);
		return p;
	}

	public boolean isFishOutOfAquariumX() {
		if (this.position.getCoordinateX() < 0 || this.position.getCoordinateX() > FishParameter.FIELD_LENGTH - 1) {
			setFishFromOutInAquariumX();
			return true;
		} else {
			return false;
		}
	}

	public boolean isFishOutOfAquariumY() {
		if (this.position.getCoordinateY() < 0 || this.position.getCoordinateY() > FishParameter.FIELD_HEIGHT - 1) {
			setFishFromOutInAquariumY();
			return true;
		} else {
			return false;
		}
	}

	public void setFishFromOutInAquariumX() {
		if (this.position.getCoordinateX() < 0) {
			this.position.setCoordinateX(0);
		} else if (this.position.getCoordinateX() > FishParameter.FIELD_LENGTH - 1) {
			this.position.setCoordinateX(FishParameter.FIELD_LENGTH - 1);
		}
	}

	public void setFishFromOutInAquariumY() {
		if (this.position.getCoordinateY() < 0) {
			this.position.setCoordinateY(0);
		} else if (this.position.getCoordinateY() > FishParameter.FIELD_HEIGHT - 1) {
			this.position.setCoordinateY(FishParameter.FIELD_HEIGHT - 1);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Fish fish = (Fish) o;
		if (this.position.getCoordinateX() == fish.position.getCoordinateX()
				&& this.position.getCoordinateY() == fish.position.getCoordinateY()
				&& this.nextPosition.getCoordinateX() == fish.nextPosition.getCoordinateX()
				&& this.nextPosition.getCoordinateY() == fish.nextPosition.getCoordinateY()
				&& this.calcNextPosition.getCoordinateX() == fish.calcNextPosition.getCoordinateX()
				&& this.calcNextPosition.getCoordinateY() == fish.calcNextPosition.getCoordinateY()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.position.getCoordinateX() + this.position.getCoordinateY()
				+ this.nextPosition.getCoordinateX() + this.nextPosition.getCoordinateY());
	}

	@Override
	public String toString() {
		return "Fish{" + "coordinateX=" + this.position.getCoordinateX() + ", coordinateY="
				+ this.position.getCoordinateY() + ", nextCoordinateX=" + this.nextPosition.getCoordinateX()
				+ ", nextCoordinateY=" + this.nextPosition.getCoordinateY() + ", calcNextCoordinateX="
				+ this.calcNextPosition.getCoordinateX() + ", calcNextCoordinateY="
				+ this.calcNextPosition.getCoordinateY() + "}";
	}

}
