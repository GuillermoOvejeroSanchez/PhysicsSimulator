package simulator.model;

import java.util.List;
import simulator.misc.Vector;

public class FallingToCenterGravity implements GravityLaws{
	
	static private final double g = -9.81; 
	
	
	
	public void apply(List<Body> bodies) {
		double[] acc = {g, 0.0}; 
		Body body; 
		Vector gravedad= new Vector(acc); 
		
		for(int i = 0; i < bodies.size(); i++) {
			
			body = bodies.get(i);
			body.setAcceleration(gravedad); 
		}
		
	}

}
