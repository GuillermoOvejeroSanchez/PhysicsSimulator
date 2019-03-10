package simulator.model;

import java.util.List;
import simulator.misc.Vector;

public class NewtonUniversalGravitation implements GravityLaws {
	
static private final double G = 6.67E-11;
	

	
	public void apply(List<Body> bodies) {
		
		for(int i = 0; i < bodies.size(); i++) {
			
			Vector sumFuerza = new Vector(bodies.get(i).getPosition().dim()); 
			
			if(bodies.get(i).getMass() > 0.00) {
				//Vector sumFuerza = new Vector(bodies.get(i).getPosition().dim()); 
				for(int j = 0; j < bodies.size(); j++) {
					
					Vector fuerzaAux; 
					
					if(!bodies.get(i).equals(bodies.get(j))) {
						
						fuerzaAux = force(bodies.get(i),bodies.get(j)); 
						sumFuerza = sumFuerza.plus(fuerzaAux); 
					}
				}
			}
			else if(bodies.get(i).getMass() <= 0) {
				bodies.get(i).setAcceleration( new Vector (bodies.get(i).acceleration.dim()));
				bodies.get(i).setVelocity(new Vector(bodies.get(i).velocity.dim()));
			}
			
			bodies.get(i).setAcceleration(sumFuerza.scale(1/bodies.get(i).getMass()));
			
		}
	}
	
	private Vector force(Body a, Body b) {
		Vector fuerza = null;
		double distancia; 
		double fuerzaDouble; 
		Vector direccion = null;
		
		
		
		distancia =  a.position.distanceTo(b.position); 
		fuerzaDouble = G*((b.getMass() * a.getMass()) /(distancia*distancia));
		direccion = a.position.minus(b.position).direction(); 
		/*
		System.out.println(distancia);
		System.out.println(direccion.toString());
		System.out.println(fuerzaDouble);
		System.out.println("\n");
		*/
		
		//fuerza = b.velocity.direction().scale((G*(b.getMass() * a.getMass()) /(distancia*distancia)));
		return direccion.scale(fuerzaDouble);
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
	
	/*
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
	*/

}

