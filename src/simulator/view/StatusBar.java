package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class StatusBar extends JPanel implements SimulatorObserver {

	private static final long serialVersionUID = 1L;

	private static String[] statusNames = { "Time: ", "Bodies: ", "Laws: ",
	};

	private JLabel _currTime; // for current time
	private JLabel _currLaws; // for gravity laws
	private JLabel _numOfBodies; // for number of bodies

	public StatusBar(Controller ctrl) {

		this._currLaws = new JLabel();
		this._currTime = new JLabel("0.00");
		this._numOfBodies = new JLabel("0");

		initGUI();
		ctrl.addObserver(this);
	}

	private void initGUI() {
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBorder(BorderFactory.createBevelBorder(1));
		// this.setPreferredSize(new Dimension(800, 20));

		this.setForeground(Color.GRAY);
		this.setFont(new Font("verdana", Font.BOLD, 5));

		createStatusBar();
	}

// SimulatorObserver methods

	private void createStatusBar() {

		JLabel timeLabel = new JLabel(statusNames[0]);
		this.add(timeLabel);
		//this.add(_currTime);
		this.add(timeLab());

		this.add(Box.createHorizontalStrut(3));
		this.add(separator());

		JLabel bodiesLabel = new JLabel(statusNames[1]);
		this.add(bodiesLabel);
		this.add(_numOfBodies);

		this.add(Box.createHorizontalStrut(3));
		this.add(separator());

		JLabel lawsLabel = new JLabel(statusNames[2]);
		this.add(lawsLabel);
		this.add(_currLaws);

	}

	private JLabel separator() {
		JLabel sep = new JLabel(" | ");
		sep.setForeground(Color.GRAY);

		return sep;
	}
	
	/*
	  Hacemos esta funcion porque al poner 10.000 pasos el tiempo es demasiado grande que hace que las demas etiquetas se muevan 
	  a lo largo de la estatus bar, creando un panel y poniedole un tamaño lo subficientemente grande evitamos que la status bar se redimensione 
	 */
	
	private JPanel timeLab() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(75, 20));
		
		panel.add(_currTime);

		return panel;
	}

	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
				_numOfBodies.setText(Integer.toString(bodies.size()));
				_currTime.setText(Double.toString(time));
				_currLaws.setText(gLawsDesc.toString());
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
				_numOfBodies.setText(Integer.toString(bodies.size()));
				_currTime.setText(Double.toString(time));
				_currLaws.setText(gLawsDesc.toString());
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
				_numOfBodies.setText(Integer.toString(bodies.size()));
				
	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
				_currTime.setText(Double.toString(time));
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
	}

	@Override
	public void onGravityLawChanged(String gLawsDesc) {
				_currLaws.setText(gLawsDesc);
				
	}
}