package simulator.control;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.Body;
import simulator.model.GravityLaws;
import simulator.model.PhysicsSimulator;
import simulator.model.SimulatorObserver;

public class Controller {

	PhysicsSimulator _sim;
	Factory<Body> _bodiesFactory;
	Factory<GravityLaws> _gravityLaws;

	public Controller(PhysicsSimulator sim, Factory<Body> _bodyFactory, Factory<GravityLaws> gravityLaws) {
		_sim = sim;
		_bodiesFactory = _bodyFactory;
		_gravityLaws = gravityLaws;
	}

	public void loadBodies(InputStream in) throws JSONException, Exception {

		JSONObject jsonInput = new JSONObject(new JSONTokener(in));
		JSONArray bodies = jsonInput.getJSONArray("bodies");
		for (int i = 0; i < bodies.length(); i++)
			_sim.addBody(_bodiesFactory.createInstance(bodies.getJSONObject(i)));

	}

	public void run(Integer n, OutputStream out) {
		JSONObject state = new JSONObject();
		JSONArray s = new JSONArray();
		
		s.put(new JSONObject(_sim.toString()));
		for (int i = 0; i < n; i++) {
			_sim.advance();
			s.put(new JSONObject(_sim.toString()));
		}

		state.put("states", s);

		try {
			out.write(state.toString().getBytes());
		} catch (IOException a) {
			System.err.println("Error en la salida");
		}
	}

	public void reset() {
		_sim.reset();
	}

	public void setDeltaTime(double dt) {
		_sim.setDeltaTime(dt);
	}

	public void addObserver(SimulatorObserver o) {
		_sim.addObserver(o);
	}

	public void run(int n) {
		for (int i = 0; i < n; i++) {
			_sim.advance();
		}
	}

	public Factory<GravityLaws> getGravityLawsFactory() {
		return _gravityLaws;
	}

	public void setGravityLaws(JSONObject info) {
		_sim.setGravityLaws(_gravityLaws.createInstance(info));
	}

}