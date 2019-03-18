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
		/*
		for (int i = 0; i < bodies.size(); i++) {

			Vector sumFuerza = new Vector(bodies.get(i).getPosition().dim());

			if (bodies.get(i).getMass() > 0.00) {
				for (int j = 0; j < bodies.size(); j++) {

					Vector fuerzaAux;

					if (!bodies.get(i).equals(bodies.get(j))) {

						fuerzaAux = force(bodies.get(i), bodies.get(j));
						sumFuerza = sumFuerza.plus(fuerzaAux);
					}
				}
			} else if (bodies.get(i).getMass() <= 0) {
				bodies.get(i).setAcceleration(new Vector(bodies.get(i).acceleration.dim()));
				bodies.get(i).setVelocity(new Vector(bodies.get(i).velocity.dim()));
			}

			bodies.get(i).setAcceleration(sumFuerza.scale(1 / bodies.get(i).getMass()));

		}
		
		*/
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
}
