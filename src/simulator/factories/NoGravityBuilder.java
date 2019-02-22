package simulator.factories;

import org.json.JSONObject;

import simulator.model.GravityLaws;

public class NoGravityBuilder extends Builder<GravityLaws> {

	@Override
	public JSONObject createData() {
		return super.createData();
	}

	@Override
	public GravityLaws createTheInstance(JSONObject jo) {
		// TODO Auto-generated method stub
		return null;
	}

}
