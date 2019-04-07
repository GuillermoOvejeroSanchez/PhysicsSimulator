package simulator.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;

public class BodiesTableModel extends AbstractTableModel implements SimulatorObserver {

	private static final long serialVersionUID = -4047517202507896183L;

	private List<Body> _bodies;
	
	private int numOfRows; 
	
	private static String[] titulos = {
			"Id",
			"Mass",
			"Position",
			"Velocity",
			"Acceleration",
				
		}; 
	
	Object[][] datosCasilla; 

	public BodiesTableModel(Controller ctrl) {
		_bodies = new ArrayList<>();
		ctrl.addObserver(this);
		
		initTable();
		
	}
	
	
	
	public void initTable() {
		numOfRows = _bodies.size(); 
		
		datosCasilla = new Object[numOfRows][titulos.length]; 
		
		for(int i = 0; i < titulos.length; i++) {
			for(int j = 0; j < numOfRows; j++ ) {
				datosCasilla[i][j] = null; 
			}
		}
		
		
		
		
	}

	
	@Override
	public String getColumnName(int column) {
		
		if(titulos == null) {
			return ""; 
		}
		else {
			return titulos[column].toString();
		}
		
		
		
	}
	
	@Override
	public int getColumnCount() {
		
		return titulos.length; 
	}
	@Override
	public int getRowCount() {
	
		return _bodies.size();
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		//Body cuerpo = this._bodies.get(rowIndex); 
		//return getRowData(rowIndex, cuerpo);
		
		return datosCasilla[rowIndex][columnIndex]; 
	}
	
public Object getRowData(int colum, Body cuerpo) {
		
		Object casilla = null; 
		
		switch(colum) {
		case 0: casilla = cuerpo.getId(); break; 
		case 1: casilla = cuerpo.getPosition(); break; 
		case 2: casilla = cuerpo.getVelocity(); break;
		case 3: casilla = cuerpo.getAcceleration(); break;
		default: assert (false);
		
		}
		
		
		
		return casilla; 
	}
	
	
	
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
		// TODO Auto-generated method stub

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
		this._bodies = cuerpo; 
		this.fireTableDataChanged();
	}
	
	public void setColumsNames() {
		
	}
	

}