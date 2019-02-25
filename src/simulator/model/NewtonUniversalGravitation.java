package simulator.model;

import java.util.List;
import simulator.misc.Vector;

public class NewtonUniversalGravitation implements GravityLaws {
	
static private final double G = 6.67E-11;
	
	public NewtonUniversalGravitation() {
		
	}

	
	public void apply(List<Body> bodies) {
	
		Vector aceleracion; 
		Vector fuerza; 
		
		for(int i = 0; i < bodies.size(); i++) {
			
			if(bodies.get(i).getMass() > 0) {
				fuerza = force(bodies.get(i), bodies.get(i + 1));  // TODO fuerzas que ejercen sobre un cuerpo hacerlo bien
				aceleracion = fuerza.scale(1/bodies.get(i).getMass()); 
				bodies.get(i).setAcceleration(new Vector (aceleracion));
				
				
			}
			else if(bodies.get(i).getMass() == 0) {
				bodies.get(i).setAcceleration(new Vector( bodies.get(i).acceleration.dim()));
				bodies.get(i).setVelocity(new Vector(bodies.get(i).velocity.dim()));
			}
		}
		
		
	}
	
	private Vector force(Body a, Body b) {
		Vector fuerza = null;
		
		fuerza = b.velocity.direction().scale((G*(b.getMass() * a.getMass()) / a.velocity.distanceTo(b.velocity) * a.velocity.distanceTo(b.velocity)));
		
		
		
		return fuerza ;
	}

}
