package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class BuilderBasedFactory<T> implements Factory<T> {

	static List<JSONObject> _factoryElements;
	private List<Builder<T>> _builders;

	public BuilderBasedFactory(List<Builder<T>> builders) {
		_builders = new ArrayList<Builder<T>>(builders);
		_factoryElements = new ArrayList<JSONObject>();
	}

	@Override
	public T createInstance(JSONObject info) {
		T instance = null;
		for (Builder<T> b : _builders) {
			instance = b.createInstance(info);
			if (instance != null)
				return instance;
		}
		return instance;

	}

	@Override
	public List<JSONObject> getInfo() {
		for (Builder<T> b : _builders) {
			_factoryElements.add(b.getBuilderInfo());
		}
		return _factoryElements;
	}

}
