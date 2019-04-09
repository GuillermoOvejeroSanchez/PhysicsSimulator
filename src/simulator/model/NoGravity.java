package simulator.model;

import java.util.List;

public class NoGravity implements GravityLaws {

	@Override
	public void apply(List<Body> bodies) {
	}

	public String toStringDes() {
		return "The gravity wont change, its like the outer space";
	}
	
	public String toString() {
		
		return "No Gravity Law"; 
	}
}
