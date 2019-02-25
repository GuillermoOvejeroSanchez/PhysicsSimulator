package simulator.factories;

import org.json.JSONObject;

import simulator.model.GravityLaws;
import simulator.model.NoGravity;

public class NoGravityBuilder extends Builder<GravityLaws> {

	
	public NoGravityBuilder() {
		_typeTag = "ng";
		_desc = "No gravity";
	}
	@Override
	public JSONObject createData() {
		return super.createData();
	}

	@Override
	public GravityLaws createTheInstance(JSONObject jo) {
		GravityLaws gl = new NoGravity();
		return gl;
	}

}
