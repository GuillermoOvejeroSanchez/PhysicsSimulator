package simulator.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class PhysicsSimulator {

	
	private GravityLaws _gravityLaws;
	private List<Body> _bodies;
	private double _dt;
	private double _time; 
	private JSONObject stateJSON;

	public PhysicsSimulator(GravityLaws gravityLaws, Double _dtime){
		_gravityLaws = gravityLaws;
		_bodies = new ArrayList<Body>();
		if(_gravityLaws == null)
			throw new IllegalArgumentException();
		if(_dtime.isNaN())
			throw new IllegalArgumentException(); 
		else
			_dt = _dtime;
		_time = 0.0;
		stateJSON = new JSONObject();
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
		stateJSON.put("time", _time);
		JSONArray bodies = new JSONArray();
		for (Body body : _bodies) {
			bodies.put(body);
		}
		stateJSON.put("bodies",bodies);
		
		//System.out.println(stateJSON);
		return stateJSON.toString();
		
	}

}
