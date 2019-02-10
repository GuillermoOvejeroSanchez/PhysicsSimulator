package simulator.model;


import simulator.misc.Vector;

public class Body {
	protected String id;
	protected Vector velocity;
	protected Vector acceleration;
	protected Vector position;
	protected double mass;
	
	public Body(String id, Vector vel, Vector acc, Vector pos, double mass) {
		this.id = id;
		this.velocity = vel;
		this.acceleration = acc;
		this.position = pos;
		this.mass = mass;
	}
	
	public String getId() {
		return this.id;
	}
	
	public Vector getVelocity() {
		return this.velocity;
	}
	
	public Vector getAcceleration() {
		return this.acceleration;
	}
	
	public Vector getPosition() {
		return this.position;
	}
	
	public double getMass() {
		return this.mass;
	}
	
	public void setVelocity(Vector v) {
		this.velocity = v;
	}
	
	public void setAcceleration(Vector a) {
		this.acceleration = a;
	}
	
	public void setPosition(Vector p) {
		this.position = p;
	}
	
	void move(double t) {
		this.position = position.plus(velocity.scale(t)).plus(acceleration.scale(1/2).scale(t).scale(t)) ;
		this.velocity = velocity.plus(acceleration.scale(t));
	}
	
	public String toString() {
		//return id;	
	
		return null; 
	}
	
	
	
}
	

