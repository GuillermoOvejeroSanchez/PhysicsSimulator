package simulator.factories;

import org.json.JSONObject;

import simulator.misc.Vector;
import simulator.model.Body;
import simulator.model.MassLosingBody;

public class MassLosingBodyBuilder extends Builder<Body>{

	@Override
	public JSONObject createData() {
		JSONObject data = new JSONObject();
		data.put("id", "the identifier");
		data.put("pos", "current position of the body");
		data.put("vel", "curren velocity of the body");
		data.put("mass", "body's mass");
		data.put("freq", "frequency in which the body loses mass");
		data.put("factor", "Mass losing factor");
		return data;
	}

	@Override
	public Body createTheInstance(JSONObject data) {
		Body bodyObject = null;
		Vector acc = defaultVector();
		Vector vel = new Vector(jsonArrayTodoubleArray(data.getJSONArray("pos")));
		Vector pos = new Vector(jsonArrayTodoubleArray(data.getJSONArray("vel")));
		double factor = data.getDouble("factor");
		double freq = data.getDouble("freq");
		bodyObject = new MassLosingBody(data.getString("id"), vel, acc, pos, data.getDouble("mass"), factor, freq);
	return bodyObject;
		
	}

}
