package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector;


public abstract class Builder<T> {
	
	protected String _typeTag;
	protected String _desc;
	
	public T createInstance(JSONObject info) {
		T b = null;
		if (_typeTag != null && _typeTag.equals(info.getString("type")))
		b = createTheInstance(info.getJSONObject("data"));
		return b;
		}
	
	
	public JSONObject getBuilderInfo() {
		JSONObject info = new JSONObject();
		info.put("type", _typeTag);
		info.put("data", createData());
		info.put("desc", _desc);
		return info;
	}
	
	public JSONObject createData() {
		return new JSONObject();
	}
	
	public abstract T createTheInstance(JSONObject jo);
	
	protected double[] jsonArrayTodoubleArray(JSONArray ja) {
		double[] da = new double[ja.length()];
		for (int i = 0; i < da.length; i++)
		da[i] = ja.getDouble(i);
		return da;
		}
	
	
	protected Vector defaultVector(){
		double[] defaultList = { 0.0, 0.0 }; 
		Vector defaultVector  = new Vector(defaultList);
		return defaultVector;
		
	}

}
