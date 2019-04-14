package simulator.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
		this.add(_currTime);

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

	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		this._numOfBodies.setText(Integer.toString(bodies.size()));
		this._currTime.setText(Double.toString(time));
		this._currLaws.setText(gLawsDesc.toString());
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		this._numOfBodies.setText(Integer.toString(bodies.size()));
		this._currTime.setText(Double.toString(time));
		this._currLaws.setText(gLawsDesc.toString());
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		this._numOfBodies.setText(Integer.toString(bodies.size()));
	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
		this._currTime.setText(Double.toString(time));
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
	}

	@Override
	public void onGravityLawChanged(String gLawsDesc) {
		this._currLaws.setText(gLawsDesc);
	}
}