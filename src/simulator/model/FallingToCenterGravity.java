package simulator.model;

import java.util.List;

public class FallingToCenterGravity implements GravityLaws {

	static private final double g = 9.81;

	public void apply(List<Body> bodies) {
		for (Body body : bodies) {
			body.setAcceleration(body.getPosition().direction().scale(-g));
		}

	}
}
