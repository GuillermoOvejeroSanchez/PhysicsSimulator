package simulator.factories;

import org.json.JSONObject;

import simulator.model.FallingToCenterGravity;
import simulator.model.GravityLaws;

public class FallingToCenterGravityBuilder<T> extends Builder<GravityLaws> {

	public FallingToCenterGravityBuilder() {
		_typeTag = "ftcg";
		_desc = "Falling to the center gravity";
	}
	
	@Override
	public JSONObject createData() {
		return super.createData();
	}

	@Override
	public
	GravityLaws createTheInstance(JSONObject jo) {
		GravityLaws gl = new FallingToCenterGravity();
		return gl;
		
	}

}
