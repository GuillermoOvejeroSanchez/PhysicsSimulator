package test;

import org.json.JSONObject;

import simulator.misc.Vector;
import simulator.model.Body;
import simulator.model.PhysicsSimulator;

public class tesBody {

	public static void main(String[] args) {

		double[] posa = { 30.0, 40.0 };
		double[] vela = { 100.0, 1.0 };
		double[] acca = { 0.0, 1.0 };
		double mass = 100;
		Vector pos = new Vector(posa);
		Vector vel = new Vector(vela);
		Vector acc = new Vector(acca);
		Body body = new Body("test", vel, acc, pos, mass);
		Body body2 = new Body("test2", vel, acc, pos, mass);

		StringBuilder state = new StringBuilder();
		state.append("{\r\n" + "\"states\": [\r\n");
		state.append("{ \"time\": ");
		state.append(0.0);
		state.append(", \"bodies\": [ ");
		state.append(body.toString());
		state.append(",");
		state.append(body2.toString());
		state.append(" ] },\n");

		body.move(100.0);
		body2.move(100.0);

		state.append("{ \"time\": ");
		state.append(100.0);
		state.append(", \"bodies\": [ ");
		state.append(body.toString());
		state.append(",");
		state.append(body2.toString());
		state.append(" ] },");

	}

}
