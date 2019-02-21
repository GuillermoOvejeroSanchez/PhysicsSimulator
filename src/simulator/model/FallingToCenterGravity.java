package simulator.model;

import java.util.List;
import java.util.Vector;

public class FallingToCenterGravity implements GravityLaws{
	
	final static double g = 9.81; 
	
	private Vector vectorGravedad; 
	private double[] datos = {9.81, 0.00, 0.00};
	
	public FallingToCenterGravity() {
		double[] aux = datos;
		//vectorGravedad = new Vector(aux);
		
	}

	
	
	public void apply(List<Body> bodies) {
		
		Body b = null; 
		
		for(int i = 0; i < bodies.size(); i++ ) {
			
			
		}
		
	}

}
