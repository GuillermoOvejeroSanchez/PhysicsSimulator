package simulator.factories;

import org.json.JSONObject;

import simulator.model.GravityLaws;
import simulator.model.NoGravity;

public class NoGravityBuilder extends Builder<GravityLaws> {
	
	public static final String TIPO = "ng"; 
	public static final String DES = "no gravity"; 

	public NoGravityBuilder() {
		this._typeTag = TIPO; 
		this._desc = DES; 
	}
	
	public JSONObject createData() {
		
		JSONObject data = new JSONObject(); 
		
		return super.createData();
	}

	@Override
	public GravityLaws createTheInstance(JSONObject jo) {
		return new NoGravity(); 
	}

}
