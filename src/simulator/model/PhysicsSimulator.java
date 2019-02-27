package simulator.model;

import java.util.List;

public class PhysicsSimulator {

	
	private GravityLaws _gravityLaws;
	private List<Body> _bodies;
	private double _dt;
	private double _time; 
	private StringBuilder state;

	public PhysicsSimulator(GravityLaws gravityLaws, Double _dtime){
		_gravityLaws = gravityLaws;
		if(_gravityLaws == null)
			throw new IllegalArgumentException();
		if(_dtime.isNaN())
			throw new IllegalArgumentException(); 
		else
			_dt = _dtime;
		_time = 0.0;
		state = new StringBuilder();
		state.append("{\r\n" + 
				"\"states\": [\r\n");
	}

	public void addBody(Body b) {
		_bodies.add(b);
	}
	
	public void advance() {
		_gravityLaws.apply(_bodies);
		for (Body body : _bodies) {
			body.move(_dt);
			body.toString();
		}
		_time+=_dt;
	}
	
	public String toString() { 
		state.append("{ \"time\": ");
		state.append(_time);
		state.append(", \"bodies\": [ ");
		for (Body body : _bodies) {
			state.append(body.toString());
			state.append(",");
		}
		state.append("] },");
		return state.toString();
		
	}

}
