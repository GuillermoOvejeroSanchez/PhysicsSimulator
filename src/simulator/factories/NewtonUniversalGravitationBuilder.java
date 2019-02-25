package simulator.factories;

import org.json.JSONObject;

import simulator.model.GravityLaws;
import simulator.model.NewtonUniversalGravitation;

public class NewtonUniversalGravitationBuilder extends Builder<GravityLaws> {

	public NewtonUniversalGravitationBuilder() {
		_typeTag = "nlug";
		_desc = "Newton's universal law gravitation builder";
	}
	
	@Override
	public JSONObject createData() {
		return super.createData();
	}

	@Override
	public GravityLaws createTheInstance(JSONObject jo) {
		GravityLaws gl = new NewtonUniversalGravitation();
		return gl;
	}

}
