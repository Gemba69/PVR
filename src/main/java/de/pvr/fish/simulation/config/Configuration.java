package de.pvr.fish.simulation.config;

public class Configuration {

	
	private int iteration;
	private int threads;
	private int fishNumber;
	
	private int fieldLength;
	private int fieldHeight;
	
	private int numberOfNeighbours;
	private int deathAngle;
	private double r1;
	private double r2;
	private double r3;
	private int bodyLength;
	
	private int maxSpeedMultiplicator;
	
	private double runtime;
	private double kappa;
	private double sigma;
	private double phi;
	
	private int proccesors = 8;
	
	private double speedup;

	public Configuration(int iteration, int threads, int fishNumber, int fieldLength, int fieldHeight,
			int numberOfNeighbours, int deathAngle, double r1, double r2, double r3, int bodyLength,
			 double runtime, double kappa, double sigma, double phi) {
		this.iteration = iteration;
		this.threads = threads;
		this.fishNumber = fishNumber;
		this.fieldLength = fieldLength;
		this.fieldHeight = fieldHeight;
		this.numberOfNeighbours = numberOfNeighbours;
		this.deathAngle = deathAngle;
		this.r1 = r1;
		this.r2 = r2;
		this.r3 = r3;
		this.bodyLength = bodyLength;
		this.runtime = runtime;
		this.kappa = kappa;
		this.sigma = sigma;
		this.phi = phi;
	}
	
	public Configuration(int iteration, int threads, int fishNumber, int fieldLength, int fieldHeight,
			int numberOfNeighbours, int deathAngle, double r1, double r2, double r3, int bodyLength) {
		this.iteration = iteration;
		this.threads = threads;
		this.fishNumber = fishNumber;
		this.fieldLength = fieldLength;
		this.fieldHeight = fieldHeight;
		this.numberOfNeighbours = numberOfNeighbours;
		this.deathAngle = deathAngle;
		this.r1 = r1;
		this.r2 = r2;
		this.r3 = r3;
		this.bodyLength = bodyLength;
		this.runtime = 0;
		this.kappa = 0;
		this.sigma = 0;
		this.phi = 0;
	}

	public int getIteration() {
		return iteration;
	}

	public void setIteration(int iteration) {
		this.iteration = iteration;
	}

	public int getThreads() {
		return threads;
	}

	public void setThreads(int threads) {
		this.threads = threads;
	}

	public int getFishNumber() {
		return fishNumber;
	}

	public void setFishNumber(int fishNumber) {
		this.fishNumber = fishNumber;
	}

	public int getFieldLength() {
		return fieldLength;
	}

	public void setFieldLength(int fieldLength) {
		this.fieldLength = fieldLength;
	}

	public int getFieldHeight() {
		return fieldHeight;
	}

	public void setFieldHeight(int fieldHeight) {
		this.fieldHeight = fieldHeight;
	}

	public int getNumberOfNeighbours() {
		return numberOfNeighbours;
	}

	public void setNumberOfNeighbours(int numberOfNeighbours) {
		this.numberOfNeighbours = numberOfNeighbours;
	}

	public int getDeathAngle() {
		return deathAngle;
	}

	public void setDeathAngle(int deathAngle) {
		this.deathAngle = deathAngle;
	}

	public double getR1() {
		return r1;
	}

	public void setR1(double r1) {
		this.r1 = r1;
	}

	public double getR2() {
		return r2;
	}

	public void setR2(double r2) {
		this.r2 = r2;
	}

	public double getR3() {
		return r3;
	}

	public void setR3(double r3) {
		this.r3 = r3;
	}

	public int getBodyLength() {
		return bodyLength;
	}

	public void setBodyLength(int bodyLength) {
		this.bodyLength = bodyLength;
	}

	public int getMaxSpeedMultiplicator() {
		return maxSpeedMultiplicator;
	}

	public void setMaxSpeedMultiplicator(int maxSpeedMultiplicator) {
		this.maxSpeedMultiplicator = maxSpeedMultiplicator;
	}

	public double getRuntime() {
		return runtime;
	}

	public void setRuntime(double runtime) {
		this.runtime = runtime;
	}

	public double getKappa() {
		return kappa;
	}

	public void setKappa(double kappa) {
		this.kappa = kappa;
	}

	public double getSigma() {
		return sigma;
	}

	public void setSigma(double sigma) {
		this.sigma = sigma;
	}

	public double getPhi() {
		return phi;
	}

	public void setPhi(double phi) {
		this.phi = phi;
	}

	public int getProccesors() {
		return proccesors;
	}

	public void setProccesors(int proccesors) {
		this.proccesors = proccesors;
	}

	public double getSpeedup() {
		return speedup;
	}

	public void setSpeedup(double speedup) {
		this.speedup = speedup;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bodyLength;
		result = prime * result + deathAngle;
		result = prime * result + fieldHeight;
		result = prime * result + fieldLength;
		result = prime * result + fishNumber;
		result = prime * result + iteration;
		long temp;
		temp = Double.doubleToLongBits(kappa);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + maxSpeedMultiplicator;
		result = prime * result + numberOfNeighbours;
		temp = Double.doubleToLongBits(phi);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + proccesors;
		temp = Double.doubleToLongBits(r1);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(r2);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(r3);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(runtime);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(sigma);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(speedup);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + threads;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Configuration other = (Configuration) obj;
		if (bodyLength != other.bodyLength)
			return false;
		if (deathAngle != other.deathAngle)
			return false;
		if (fieldHeight != other.fieldHeight)
			return false;
		if (fieldLength != other.fieldLength)
			return false;
		if (fishNumber != other.fishNumber)
			return false;
		if (iteration != other.iteration)
			return false;
		if (Double.doubleToLongBits(kappa) != Double.doubleToLongBits(other.kappa))
			return false;
		if (maxSpeedMultiplicator != other.maxSpeedMultiplicator)
			return false;
		if (numberOfNeighbours != other.numberOfNeighbours)
			return false;
		if (Double.doubleToLongBits(phi) != Double.doubleToLongBits(other.phi))
			return false;
		if (proccesors != other.proccesors)
			return false;
		if (Double.doubleToLongBits(r1) != Double.doubleToLongBits(other.r1))
			return false;
		if (Double.doubleToLongBits(r2) != Double.doubleToLongBits(other.r2))
			return false;
		if (Double.doubleToLongBits(r3) != Double.doubleToLongBits(other.r3))
			return false;
		if (Double.doubleToLongBits(runtime) != Double.doubleToLongBits(other.runtime))
			return false;
		if (Double.doubleToLongBits(sigma) != Double.doubleToLongBits(other.sigma))
			return false;
		if (Double.doubleToLongBits(speedup) != Double.doubleToLongBits(other.speedup))
			return false;
		if (threads != other.threads)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Configuration [iteration=" + iteration + ", threads=" + threads + ", fishNumber=" + fishNumber
				+ ", fieldLength=" + fieldLength + ", fieldHeight=" + fieldHeight + ", numberOfNeighbours="
				+ numberOfNeighbours + ", deathAngle=" + deathAngle + ", r1=" + r1 + ", r2=" + r2 + ", r3=" + r3
				+ ", bodyLength=" + bodyLength + ", maxSpeedMultiplicator=" + maxSpeedMultiplicator + ", runtime="
				+ runtime + ", kappa=" + kappa + ", sigma=" + sigma + ", phi=" + phi + ", proccesors=" + proccesors
				+ ", speedup=" + speedup + "]";
	}
	
	
	
	
}
