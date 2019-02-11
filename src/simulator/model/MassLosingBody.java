package simulator.model;

import simulator.misc.Vector;

public class MassLosingBody extends Body {

	private double lossFactor;
	private double lossFrequency;
	public MassLosingBody(String id, Vector vel, Vector acc, Vector pos, double mass, double factor, double freq) {
		super(id, vel, acc, pos, mass);
		this.setLossFactor(factor);
		this.setLossFrequency(freq);
		
	}
	public double getLossFrequency() {
		return lossFrequency;
	}
	public void setLossFrequency(double lossFrequency) {
		this.lossFrequency = lossFrequency;
	}
	public double getLossFactor() {
		return lossFactor;
	}
	public void setLossFactor(double lossFactor) {
		this.lossFactor = lossFactor;
	}
	
	private void setMass(double mass) {
		this.mass = mass;
	}

	public void setLossMass(){
		if(elapsedTime >= lossFrequency) {
			setMass(this.mass*(1-lossFactor));
			elapsedTime = 0.0;
		}
	}
	
	
}
