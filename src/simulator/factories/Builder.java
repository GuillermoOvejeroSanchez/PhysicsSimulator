package simulator.factories;

import org.json.JSONObject;

public abstract class Builder<T> {

	Builder(){
		
	}
	
	double[] jsonArrayToDoubleArray(JSONObject jo) {
		return null;
		
	}
	
	public void createInstance(JSONObject jo) {
		
	}
	JSONObject getBuilderInfo() {
		return null;
	}
	
	abstract JSONObject createData();
	
	abstract void createTheInstance(JSONObject jo);
}
