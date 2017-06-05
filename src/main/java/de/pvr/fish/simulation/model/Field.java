package de.pvr.fish.simulation.model;



public class Field {
	
	private Fish[][] fishes;
	private int length;
	private int height;
	private int numberOfSquares;
	
	
	public Field(int length, int height) {
		this.length = length;
		this.height = height;
	}
	
	public void addFish(Fish fish) {
		this.fishes[fish.getPosition().getCoordinateX()][fish.getPosition().getCoordinateY()] = fish;
	}
	
	private void setNeigbourFish(Fish fish) {
		Fish neighbourFish = new Fish();
		
		
		fish.setNeighborFish(neighbourFish);
	}
	
	
	public void nextInteration() {
		
		// for....
		
		//setNeigbourFish
		//fish.move()
	}
	
	


	
	
}
