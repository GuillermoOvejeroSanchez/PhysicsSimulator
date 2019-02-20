package test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.misc.Vector;
import simulator.model.Body;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class test1 {

	public static void main(String[] args) throws JSONException, FileNotFoundException {

		String[] bodyParams = { "pos", "id", "vel", "mass" };
		Body bodyObject;
		JSONObject joFromFile1 = new JSONObject(
				new JSONTokener(new FileInputStream(new File("resources/examples/ex2.3body.txt"))));

		ArrayList<String> params = new ArrayList<>();
		JSONArray bodyArray = joFromFile1.getJSONArray("bodies");

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
			System.out.println(bodyObject.getId());
			System.out.println(bodyObject.getVelocity());
			System.out.println(bodyObject.getAcceleration());
			System.out.println(bodyObject.getPosition());
			System.out.println(bodyObject.getMass());

		}
	}

}
