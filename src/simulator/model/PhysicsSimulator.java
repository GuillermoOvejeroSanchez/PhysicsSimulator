package simulator.model;

import java.util.Iterator;
import java.util.List;

public class PhysicsSimulator {

	
	private GravityLaws _gravityLaws;
	private List<Body> _bodies;
	private double _dt;
	private double _time; 

	public PhysicsSimulator(GravityLaws gravityLaws, Double _dtime){
		_gravityLaws = gravityLaws;
		if(_gravityLaws == null)
			throw new IllegalArgumentException();
		_dt = _dtime;
		_time = 0.0;
	}

	public void addBody(Body b) {
		_bodies.add(b);
	}
	
	public void advance() {
		_gravityLaws.apply(_bodies);
		for (Body body : _bodies) {
			body.move(_dt);
			body.toSring();
		}
		_time+=_dt;
		
		//TODO Print output
	}
	
	public String toString() {
		//TODO
		return null;
		
	}

}
