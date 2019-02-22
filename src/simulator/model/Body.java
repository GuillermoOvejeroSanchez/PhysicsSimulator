package simulator.model;

import org.json.*;
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
		return new Vector(velocity);
	}
	
	public Vector getAcceleration() {
		return new Vector(acceleration);
	}
	
	public Vector getPosition() {
		return new Vector(position);
	}
	
	public double getMass() {
		return this.mass;
	}
	
	public void setVelocity(Vector v) {
		velocity = new Vector(v);
	}
	
	public void setAcceleration(Vector a) {
		acceleration = new Vector(a);
	}
	
	public void setPosition(Vector p) {
		position = new Vector(p);
	}
	
	public void move(double t) {
		this.position = position.plus(velocity.scale(t)).plus(acceleration.scale(1/2).scale(t).scale(t)) ;
		this.velocity = velocity.plus(acceleration.scale(t));
	}
	
	@Override
	public String toString() {
	return JSONObject.valueToString(this);
	}
	
	
	
}
	

