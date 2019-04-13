package simulator.model;

import simulator.misc.Vector;

public class Body {

	protected String id;
	protected Vector velocity;
	protected Vector acceleration;
	protected Vector position;
	protected double mass;
	protected double id2;

	public Body(String id, Vector vel, Vector acc, Vector pos, double mass) {
		this.id = id;
		this.velocity = vel;
		this.acceleration = acc;
		this.position = pos;
		this.mass = mass;
	}

	public String getId() {
		return this.id;
	}

	public Vector getVelocity() {
		return new Vector(velocity);
	}

	public Vector getAcceleration() {
		return new Vector(acceleration);
	}

	public Vector getPosition() {
		return new Vector(position);
	}

	public double getMass() {
		return this.mass;
	}

	public void setVelocity(Vector v) {
		velocity = new Vector(v);
	}

	public void setAcceleration(Vector a) {
		acceleration = new Vector(a);
	}

	public void setPosition(Vector p) {
		position = new Vector(p);
	}

	public void move(double t) {
		this.position = this.position.plus(this.velocity.scale(t).plus(this.acceleration.scale(t * t * 0.5)));
		this.velocity = this.velocity.plus(this.acceleration.scale(t));
	}

	public String toString() {
		StringBuilder cuerpo = new StringBuilder();

		cuerpo.append("{ \"id\": ");
		cuerpo.append(id);
		cuerpo.append(" , ");
		cuerpo.append("\"mass\": ");
		cuerpo.append(mass);
		cuerpo.append(" , ");
		cuerpo.append("\"pos\": ");
		cuerpo.append(position.toString());
		cuerpo.append(" ,  ");
		cuerpo.append("\"vel\": ");
		cuerpo.append(velocity.toString());
		cuerpo.append(",  ");
		cuerpo.append("\"acc\": ");
		cuerpo.append(acceleration.toString());
		cuerpo.append(" } ");

		return cuerpo.toString();
	}

	public boolean equals(Body body) {

		boolean same = false;

		if (this.id == body.id && this.mass == body.mass) {
			if (this.position.equals(body.position) && this.velocity.equals(body.velocity)
					&& this.acceleration.equals(body.acceleration)) {
				same = true;
			}
		}

		return same;
	}

	public Object dataInformatio(String data) {

		Object casilla = null;

		if (data.equalsIgnoreCase("Id")) {
			casilla = getId();
		} else if (data.equalsIgnoreCase("Position")) {
			casilla = getPosition();
		} else if (data.equalsIgnoreCase("Velocity")) {
			casilla = getVelocity();
		} else if (data.equalsIgnoreCase("Acceleration")) {
			casilla = getAcceleration();
		}

		return casilla;
	}

}
