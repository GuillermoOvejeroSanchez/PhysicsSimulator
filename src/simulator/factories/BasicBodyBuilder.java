package simulator.factories;

import org.json.JSONObject;
import simulator.misc.Vector;
import simulator.model.Body;

public class BasicBodyBuilder extends Builder<Body> {

	public BasicBodyBuilder() {
		_typeTag = "basic";
		_desc = "Default Body";
	}

	@Override
	public Body createTheInstance(JSONObject data) {
		Body bodyObject = null;
		try {
			Vector acc = defaultVector();
			Vector vel = new Vector(jsonArrayTodoubleArray(data.getJSONArray("vel")));
			Vector pos = new Vector(jsonArrayTodoubleArray(data.getJSONArray("pos")));
			bodyObject = new Body(data.getString("id"), vel, acc, pos, data.getDouble("mass"));
		} catch (Exception e) {
			throw new IllegalArgumentException("Datos no validos");
		}
		return bodyObject;
	}

	@Override
	public JSONObject createData() {
		JSONObject data = new JSONObject();
		data.put("id", "the identifier");
		data.put("pos", "current position of the body");
		data.put("vel", "curren velocity of the body");
		data.put("mass", "body's mass");
		return data;
	}

}
