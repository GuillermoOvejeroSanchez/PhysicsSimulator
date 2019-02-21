package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;
import simulator.misc.Vector;
import simulator.model.Body;


import java.util.ArrayList;

public class BasicBodyBuilder<T> extends Builder<Body> {

	@Override
	public Body createTheInstance(JSONObject jo) {
				Body bodyObject = null;

				ArrayList<String> params = new ArrayList<>();
				JSONArray bodyArray = jo.getJSONArray("bodies");

				for (int i = 0; i < bodyArray.length(); i++) {

					JSONObject body = bodyArray.getJSONObject(i);

					System.out.println(body.get("data"));
					JSONObject data = body.getJSONObject("data");
					String type = body.getString("type");

					for (String key : data.keySet()) {
						params.add(key);
					}

					double[] acc = { 0.0, 0.0 };
					Vector accVec = new Vector(acc);
					JSONArray posJSON = data.getJSONArray("pos");
					JSONArray velJSON = data.getJSONArray("vel");
					double[] posA = new double[2];
					double[] velA = new double[2];
					for (int j = 0; j < posJSON.length(); j++) {
						posA[j] = posJSON.getDouble(j);
						velA[j] = velJSON.getDouble(j);
					}
					Vector vel = new Vector(velA);
					Vector pos = new Vector(posA);
					bodyObject = new Body(data.getString("id"), vel, accVec, pos, data.getDouble("mass"));
				}
				return bodyObject;
			}

	@Override
	JSONObject createData() {
		
		return null;
	}


}
