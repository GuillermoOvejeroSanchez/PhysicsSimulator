package simulator.control;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.Body;
import simulator.model.PhysicsSimulator;

public class Controller {

	PhysicsSimulator _sim;
	Factory<Body> _bodiesFactory;
	public Controller(PhysicsSimulator sim, Factory<Body> _bodyFactory) {
		_sim = sim;
		_bodiesFactory = _bodyFactory;
	}

	public void loadBodies(InputStream in) {
		JSONObject jsonInupt = new JSONObject(new JSONTokener(in));
		JSONArray bodies = jsonInupt.getJSONArray("bodies");
		for (int i = 0; i < bodies.length(); i++)
		_sim.addBody(_bodiesFactory.createInstance(bodies.getJSONObject(i)));
		
	}

	public void run(Integer _steps, OutputStream out) {
		PrintStream p = (out == null) ? null : new PrintStream(out);
		
		for(int i = 0; i < _steps;i++) {
		_sim.advance();
		}
		
	}

}
