package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class BuilderBasedFactory<T> implements Factory<T> {

	
	static List<JSONObject> _factoryElements;
	private List<Builder<T>> _builders;	

	public BuilderBasedFactory(List<Builder<T>> builders) {
		_builders = new ArrayList<Builder<T>>(builders);
	}
	@Override
	public T createInstance(JSONObject info) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JSONObject> getInfo() {
		// TODO Auto-generated method stub
		return null;
	}


}
