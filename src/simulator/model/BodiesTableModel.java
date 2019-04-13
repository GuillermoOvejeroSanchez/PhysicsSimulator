package simulator.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;

public class BodiesTableModel extends AbstractTableModel implements SimulatorObserver {

	private static final long serialVersionUID = -4047517202507896183L;

	private List<Body> _bodies;

	private final static String[] titulos = { "Id", "Mass", "Position", "Velocity", "Aceleration",

	};

	public BodiesTableModel(Controller ctrl) {

		ctrl.addObserver(this);

	}

	@Override
	public String getColumnName(int column) {

		if (titulos == null) {
			return "";
		} else {
			return titulos[column].toString();
		}

	}

	@Override
	public int getColumnCount() {
		return titulos.length;
	}

	@Override
	public int getRowCount() {

		if (this._bodies == null) {
			return 0;
		} else {
			return this._bodies.size();
		}

	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Body cuerpo = _bodies.get(rowIndex);

		switch (titulos[columnIndex]) {
		case "Id":
			return cuerpo.getId();
		case "Mass":
			return cuerpo.getMass();
		case "Position":
			return cuerpo.getPosition();
		case "Velocity":
			return cuerpo.getVelocity();
		case "Aceleration":
			return cuerpo.getAcceleration();
		default:
			return "";
		}

	}

	public Object getRowData(int colum, Body cuerpo) {

		return null;
	}

	private void addBodies(List<Body> bodies) {
		_bodies = bodies;
		// initTable();
	}

	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		_bodies = new ArrayList<>(bodies);
		repaint();

	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		// TODO Auto-generated method stub
		_bodies = new ArrayList<>(bodies);
		repaint();
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		// TODO Auto-generated method stub
		// this._bodies.add(b);
		this._bodies = bodies;
		repaint();
	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
		// TODO Auto-generated method stub
		addBodies(bodies);
		fireTableStructureChanged();
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGravityLawChanged(String gLawsDesc) {
		// TODO Auto-generated method stub

	}
// SimulatorObserver methods

	public void setBodies(List<Body> cuerpo) {
	}

	public void setColumsNames() {

	}

	public void refresh() {
		this.fireTableDataChanged();
	}

	public void repaint() {
		this.fireTableStructureChanged();
	}

}