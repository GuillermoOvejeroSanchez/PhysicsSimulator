package simulator.factories;

import org.json.JSONObject;

import simulator.model.FallingToCenterGravity;
import simulator.model.GravityLaws;

public class FallingToCenterGravityBuilder extends Builder<GravityLaws> {
	
	public final static String TIPO = "fc"; 
	public final static String DES = "falling to center premoooo"; 
	
	public FallingToCenterGravityBuilder() {
		this._typeTag = TIPO;
		this._desc = DES; 
		
	}
	
	@Override
	public JSONObject createData() {
		return super.createData();
	}

	@Override
	public
	GravityLaws createTheInstance(JSONObject jo) {
		return new FallingToCenterGravity();
		
		
	}

}
