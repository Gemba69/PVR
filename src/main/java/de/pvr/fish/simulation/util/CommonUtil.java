package de.pvr.fish.simulation.util;

import de.pvr.fish.simulation.model.Position;

public class CommonUtil {
	
	private static int ROUNDING_PRECISION = 10000;

	
	public static int getAngle(Position source, Position target) {
		double angle2 = Math.atan2(target.getCoordinateY() - source.getCoordinateY() , target.getCoordinateX() - source.getCoordinateX());
		
		int angle = (int) Math.toDegrees(angle2);

		angle = (angle + 180)%360;

	    return angle;
	}
	
	public static double roundDouble(double value) {
		value = Math.round(value * ROUNDING_PRECISION);
		value = value / ROUNDING_PRECISION;
		return value;
	}
}
