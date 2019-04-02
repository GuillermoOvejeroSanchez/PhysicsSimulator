package simulator.model;

import java.util.List;
import simulator.misc.Vector;

public class NewtonUniversalGravitation implements GravityLaws {

	static private final double G = 6.67E-11;

	public void apply(List<Body> bodies) {

		for (Body body : bodies) {

			Vector sumFuerza = new Vector(body.getPosition().dim());

			if (body.getMass() > 0.00) {
				for (Body body2 : bodies) {

					Vector fuerzaAux;

					if (!body.equals(body2)) {

						fuerzaAux = force(body, body2);
						sumFuerza = sumFuerza.plus(fuerzaAux);
					}
				}
			} else if (body.getMass() <= 0) {
				body.setAcceleration(new Vector(body.acceleration.dim()));
				body.setVelocity(new Vector(body.velocity.dim()));
			}

			body.setAcceleration(sumFuerza.scale(1 / body.getMass()));

		}
	}

	private Vector force(Body a, Body b) {
		double distancia;
		double fuerzaDouble;
		Vector direccion = null;

		distancia = a.position.distanceTo(b.position);
		fuerzaDouble = G * ((b.getMass() * a.getMass()) / (distancia * distancia));
		direccion = a.position.minus(b.position).direction();
		return direccion.scale(-fuerzaDouble);
	}

	public String toString() {
		return "Newton's law of universal gravitation states that every particle attracts every"
				+ " other particle in the universe with a force which is directly proportional"
				+ " to the product of their masses and inversely proportional to the square of"
				+ " the distance between their centers.";
	}
}
