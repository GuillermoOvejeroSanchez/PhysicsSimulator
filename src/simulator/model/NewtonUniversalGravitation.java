package simulator.model;

import java.util.List;
import simulator.misc.Vector;

public class NewtonUniversalGravitation implements GravityLaws {
	
static private final double G = 6.67E-11;
	
	public NewtonUniversalGravitation() {
		
	}

	
	public void apply(List<Body> bodies) {
	
		//Vector aceleracion = null; 
		//Vector direccion = null; 
		//Vector fuerzaTotal = null; 
		//Vector fuerza = null; 
		double fuerzaDouble = 0; 
		double fuer[] = {0.0, 0.0}; 
	
		
		
		for(int i = 0; i < bodies.size(); i++) {
			
			Vector fuerza = new Vector(fuer); 
			Vector aceleracion = new Vector(fuer); 
			Vector direccion = new Vector(fuer); 
			Vector fuerzaTotal = new Vector(fuer); 
			
			for(int j = 0; j < bodies.size(); j++) {
				if(!bodies.get(i).equals(bodies.get(j))) {
					//fuerzaTotal = fuerzaTotal.plus(force(bodies.get(i), bodies.get(j))); 
					fuerzaDouble +=  G*((bodies.get(i).mass*bodies.get(j).mass) /bodies.get(j).position.distanceTo(bodies.get(i).position));
					direccion = bodies.get(j).position.minus(bodies.get(i).position); 
				}
				fuerza = direccion.scale(fuerzaDouble); 
				
				fuerzaTotal = fuerza.plus(fuerzaTotal);
			}
			//fuerzaDouble = fuerzaTotal.magnitude(); 
			aceleracion = fuerzaTotal.scale(1/bodies.get(i).getMass()); 
			bodies.get(i).setAcceleration(new Vector (aceleracion));	
			
		}
		

		
		
	}
	
	private Vector force(Body a, Body b) {
		Vector fuerza = null;
		fuerza = b.velocity.direction().scale((G*(b.getMass() * a.getMass()) / a.velocity.distanceTo(b.velocity) * a.velocity.distanceTo(b.velocity)));
		return fuerza;
	}
	
	/*
	for(int i = 0; i < bodies.size() - 1; i++) {
			
			if(bodies.get(i).getMass() > 0) {
				fuerza = force(bodies.get(i), bodies.get(i + 1));  
				
				// TODO fuerzas que ejercen sobre un cuerpo hacerlo bien
				//for(int j  = 0; j < bodies.size(); j++) {
				//	
				//}
				
				aceleracion = fuerza.scale(1/bodies.get(i).getMass()); 
				bodies.get(i).setAcceleration(new Vector (aceleracion));	
			}
			else if(bodies.get(i).getMass() == 0) {
				bodies.get(i).setAcceleration(new Vector( bodies.get(i).acceleration.dim()));
				bodies.get(i).setVelocity(new Vector(bodies.get(i).velocity.dim()));
			}
		} 
	*/

}

