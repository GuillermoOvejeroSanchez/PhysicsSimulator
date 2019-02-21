package simulator.model;

import org.json.*;
import simulator.misc.Vector;

public class Body {
	protected String id;
	protected Vector velocity;
	protected Vector acceleration;
	protected Vector position;
	protected double mass;
	protected double elapsedTime;
	public Body(String id, Vector vel, Vector acc, Vector pos, double mass) {
		this.id = id;
		this.velocity = vel;
		this.acceleration = acc;
		this.position = pos;
		this.mass = mass;
		this.elapsedTime = 0.0;
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
		
		elapsedTime = t;
	}
	
	/*
	@Override
	public String toString() {
	return JSONObject.valueToString(this);
	}
	*/
	
	public String toSring() {
		JSONObject cuerpo = new JSONObject(); 
		
		cuerpo.put("id", id); 
		cuerpo.put("mass", mass); 
		cuerpo.put("acceleration", acceleration); 
		cuerpo.put("position", position); 
		cuerpo.put("velocity", velocity); 
		
		
		return cuerpo.toString();
	}
public boolean equals(Body body) {
		
		boolean same = false; 
		
		if(this.id == body.id && this.mass == body.mass) {
			if(this.position.equals(body.position) && this.velocity.equals(body.velocity) && this.acceleration.equals(body.acceleration)) {
				same = true ;
			}
		}
		
		return same; 
	}
	
	
}
	

