package simulator.factories;

import java.util.List;

import org.json.JSONObject;

public class BuilderBasedFactory<T> implements Factory<T> {

	private List<Builder<T>> builderList;
	public BuilderBasedFactory(List<Builder<T>> builder) {
		setBuilderList(builder);
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
	public List<Builder<T>> getBuilderList() {
		return builderList;
	}
	public void setBuilderList(List<Builder<T>> builderList) {
		this.builderList = builderList;
	}

}
