package simulator.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PhysicsSimulator {

	private GravityLaws _gravityLaws;
	private List<Body> _bodies;
	private double _dt;
	private double _time;
	private List<SimulatorObserver> _observers;

	public PhysicsSimulator(GravityLaws gravityLaws, Double _dtime) {
		_gravityLaws = gravityLaws;
		_bodies = new LinkedList<Body>();
		_observers = new ArrayList<SimulatorObserver>();
		if (_gravityLaws == null)
			throw new IllegalArgumentException();
		if (_dtime.isNaN())
			throw new IllegalArgumentException();
		else
			_dt = _dtime;
		_time = 0.0;
	}

	public void addBody(Body b) throws IllegalArgumentException {

		if (_bodies.contains(b))
			throw new IllegalArgumentException("Cuerpo con ID duplicado");
		_bodies.add(b);
		
		for (SimulatorObserver simulatorObserver : _observers) {
			simulatorObserver.onBodyAdded(_bodies, b);
		}
	}

	private void notifyObservers(List<SimulatorObserver> _observers) {
		// TODO Auto-generated method stub
		for (SimulatorObserver ob : _observers) {
			ob.onAdvance(_bodies, _time);
			ob.onBodyAdded(_bodies, _bodies.get(_bodies.size()-1));
			ob.onDeltaTimeChanged(_dt);
			ob.onGravityLawChanged(_gravityLaws.toString());
			ob.onRegister(_bodies, _time, _dt, _gravityLaws.toString());
			ob.onReset(_bodies, _time, _dt, _gravityLaws.toString());
		}
		
	}

	public void advance() {
		_gravityLaws.apply(_bodies);
		for (Body body : _bodies) {
			body.move(_dt);
		}
		_time += _dt;
		
		for (SimulatorObserver simulatorObserver : _observers) {
			simulatorObserver.onAdvance(_bodies, _time);;
		}
	}

	public String toString() {

		StringBuilder state = new StringBuilder();

		state.append("{ \"time\":  ");
		state.append(_time);
		state.append(" ,  \"bodies\":  [ ");

		for (Body body : _bodies) {
			state.append(body.toString());
			state.append(" , ");
		}
		state.append(" ] } ,\n");

		return state.toString();
	}

	public void setDeltaTime(double dt) {
		try {
			this._dt = dt;
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
		
		for (SimulatorObserver simulatorObserver : _observers) {
			simulatorObserver.onDeltaTimeChanged(_dt);
		}
	}

	public void reset() {
		_time = 0.0;
		_bodies.clear();
		
		for (SimulatorObserver simulatorObserver : _observers) {
			simulatorObserver.onReset(_bodies, 0.0, _dt, _gravityLaws.toString());
		}
	}

	public void setGravityLaws(GravityLaws gravityLaws) {
		if (gravityLaws == null)
			throw new IllegalArgumentException();
		else {
			this._gravityLaws = gravityLaws;
			for (SimulatorObserver simulatorObserver : _observers) {
				simulatorObserver.onGravityLawChanged(_gravityLaws.toString());;
			}
		}
	}

	public void addObserver(SimulatorObserver o) {
		if (!_observers.contains(o))
			_observers.add(o);

		_observers.get(_observers.size()-1).onRegister(_bodies, _time, _dt, _gravityLaws.toString());
	}

}
