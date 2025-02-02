package simulator.factories;

import org.json.JSONObject;

import simulator.model.GravityLaws;
import simulator.model.NewtonUniversalGravitation;

public class NewtonUniversalGravitationBuilder extends Builder<GravityLaws> {

	public static final String TIPO = "nlug";
	public static final String DES = "Newton Universal Gravitation Law";

	public NewtonUniversalGravitationBuilder() {
		this._typeTag = TIPO;
		this._desc = DES;
	}

	@Override
	public JSONObject createData() {
		return super.createData();
	}

	@Override
	public GravityLaws createTheInstance(JSONObject jo) {
		return new NewtonUniversalGravitation();
	}

}
