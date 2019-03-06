package simulator.factories;

import org.json.JSONObject;

import simulator.model.FallingToCenterGravity;
import simulator.model.GravityLaws;

public class FallingToCenterGravityBuilder extends Builder<GravityLaws> {
	
	public final static String TIPO = "ftcg"; 
	public final static String DES = "Falling To Center Gravity"; 
	
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
