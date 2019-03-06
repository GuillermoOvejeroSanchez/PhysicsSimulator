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
import simulator.model.PhysicsSimulator;

public class Controller {

	PhysicsSimulator _sim;
	Factory<Body> _bodiesFactory;
	public Controller(PhysicsSimulator sim, Factory<Body> _bodyFactory) {
		_sim = sim;
		_bodiesFactory = _bodyFactory;
	}

	public void loadBodies(InputStream in) throws JSONException, Exception {
		
		//El jsonInput con InputStream no funciona bien
		//JSONObject jsonInupt = new JSONObject(new JSONTokener(in()));
		
		StringBuilder input = new StringBuilder();
		int data = in.read();
		input.append((char) data);
		while(data != -1) {
			data = in.read();
			input.append((char) data);
		}
		JSONObject jsonInput = new JSONObject(new JSONTokener(input.toString()));
		JSONArray bodies = jsonInput.getJSONArray("bodies");
		for (int i = 0; i < bodies.length(); i++)
		_sim.addBody(_bodiesFactory.createInstance(bodies.getJSONObject(i)));
		
	}

	public void run(Integer _steps, OutputStream out) {
		
		JSONObject estados = new JSONObject(); 
		JSONArray jsonArray = new JSONArray();
		
		jsonArray.put(new JSONObject(_sim.toString())); 
		
		int i  = 0; 
		
		while(i < _steps) {
			_sim.advance();
			jsonArray.put(new JSONObject(_sim.toString())); 
			i++; 
		}
		
		estados.put("state", jsonArray); 
		
		 try {
			out.write(estados.toString().getBytes());
		}catch (IOException a) {
			System.err.println("Error en la salida");
		}
		 System.out.println("Output de la simulacion realizado");
	}

} 