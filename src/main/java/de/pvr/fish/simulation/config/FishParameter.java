package de.pvr.fish.simulation.config;

public class FishParameter {
	
	//DEFAULT Parameters
	public static int DEFAULT_ITERATIONS = 10;

	public static int DEFAULT_THREADS = 4;
	
	public static int DEFAULT_NUMBER_FISH = 20;

	public static int DEFAULT_FIELD_LENGTH = 300;
	public static int DEFAULT_FIELD_HEIGHT = 300;

	public static int DEFAULT_NUMBER_OF_NEIGHBOURS = 4;
	
	public static int DEFAULT_DEATH_ANGLE = 30;
	
	public static double DEFAULT_RADIUS1 = 0.5;
	public static double DEFAULT_RADIUS2 = 2;
	public static double DEFAULT_RADIUS3 = 5;
	
	public static int DEFAULT_FISH_BODY_LENGTH = 8;
	
	public static int DEFAULT_MAX_SPEED_MULTIPLICATOR = 2;
	
	
	//Overall Parameters - changed while application run
	public static int ITERATIONS = DEFAULT_ITERATIONS;

	public static int THREADS = DEFAULT_THREADS;
	
	public static int NUMBER_FISH = DEFAULT_NUMBER_FISH;

	public static int FIELD_LENGTH = DEFAULT_FIELD_LENGTH;
	public static int FIELD_HEIGHT = DEFAULT_FIELD_HEIGHT;

	public static int NUMBER_OF_NEIGHBOURS = DEFAULT_NUMBER_OF_NEIGHBOURS;
	
	public static int DEATH_ANGLE = DEFAULT_DEATH_ANGLE;
	
	public static double RADIUS1 = DEFAULT_RADIUS1;
	public static double RADIUS2 = DEFAULT_RADIUS2;
	public static double RADIUS3 = DEFAULT_RADIUS3;
	
	public static int FISH_BODY_LENGTH = DEFAULT_FISH_BODY_LENGTH;
	
	public static int MAX_SPEED_MULTIPLICATOR = DEFAULT_MAX_SPEED_MULTIPLICATOR;
	

	public static void resetValuesToDefault() {
		ITERATIONS = DEFAULT_ITERATIONS;

		THREADS = DEFAULT_THREADS;
		
		NUMBER_FISH = DEFAULT_NUMBER_FISH;

		FIELD_LENGTH = DEFAULT_FIELD_LENGTH;
		FIELD_HEIGHT = DEFAULT_FIELD_HEIGHT;

		NUMBER_OF_NEIGHBOURS = DEFAULT_NUMBER_OF_NEIGHBOURS;
		
		DEATH_ANGLE = DEFAULT_DEATH_ANGLE;
		
		RADIUS1 = DEFAULT_RADIUS1;
		RADIUS2 = DEFAULT_RADIUS2;
		RADIUS3 = DEFAULT_RADIUS3;
		
		FISH_BODY_LENGTH = DEFAULT_FISH_BODY_LENGTH;
		
		MAX_SPEED_MULTIPLICATOR = DEFAULT_MAX_SPEED_MULTIPLICATOR;
	}
	
}
