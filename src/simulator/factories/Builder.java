package simulator.factories;

import org.json.JSONObject;


public abstract class Builder<T> {
	
	double[] jsonArrayToDoubleArray(JSONObject jo) {
		return null;
		
	}
	
	public void createInstance(JSONObject jo) {
		
	}
	JSONObject getBuilderInfo() {
		return null;
	}
	
	abstract JSONObject createData();
	
	public abstract T createTheInstance(JSONObject jo);
}
