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
		return null;
	}

	@Override
	public List<JSONObject> getBuilderInfo() {
		List<JSONObject> builderList = new List<JSONObject>();
		for(Builders b: _builders) {
			
		}
		return null;
	}


}
