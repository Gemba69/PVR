package de.pvr.fish.simulation.util;

import de.pvr.fish.simulation.model.Position;

public class CommonUtil {

	
	public static int getAngle(Position source, Position target) {
		double angle2 = Math.atan2(target.getCoordinateY() - source.getCoordinateY() , target.getCoordinateX() - source.getCoordinateX());
		
		int angle = (int) Math.toDegrees(angle2);

		angle = (angle + 180)%360;

	    return angle;
	}
}
