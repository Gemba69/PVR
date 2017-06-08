package de.pvr.fish.simulation.util;

import de.pvr.fish.simulation.model.Position;

public class CommonUtil {

	
	public static int getAngle(Position source, Position target) {
	    int angle = (int) Math.toDegrees(Math.atan2(target.getCoordinateY() - source.getCoordinateY(), target.getCoordinateX() - source.getCoordinateX()));

	    if(angle < 0){
	        angle += 360;
	    }
	    
	    return angle;
	}
}
