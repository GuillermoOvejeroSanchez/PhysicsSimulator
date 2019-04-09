package simulator.model;

import java.util.List;

import simulator.misc.Vector;

public class FallingToCenterGravity implements GravityLaws {

	static private final double g = 9.81;

	public void apply(List<Body> bodies) {
		for (Body b : bodies) {
			Vector o = new Vector(b.getPosition().dim()); // origin (0,...,0)
			Vector direction = o.minus(b.getPosition()).direction(); // direction(o - pa)
			b.setAcceleration(direction.scale(g));
		}

	}

	public String toStringDes() {
		return "All of the bodies 'fall's' into the center of the grid, simulating a kind of" + "immovable object";
	}
	
	public String toString() {
		return "Falling To Center Gravity";
	}
}
