package simulator.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class ControlPanel extends JPanel implements SimulatorObserver {

	private static final long serialVersionUID = -7690206431664849527L;
	// ...
	private Controller _ctrl;
	private boolean _stopped;
	JToggleButton toggleButton;
	ControlPanel(Controller ctrl) {

		_ctrl = ctrl;
		_stopped = true;
		initGUI();
		_ctrl.addObserver(this);
	}

	private void initGUI() {
		// TODO build the tool bar by adding buttons, etc.
		this.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		this.add(createJToolBar());
		

	}

	// other private/protected methods
	public JToolBar createJToolBar() {
		JToolBar toolBar = new JToolBar();
		this.add(toolBar, BorderLayout.PAGE_START);
		
		JButton load = new JButton("Load");
		load.setToolTipText("Load a file");
		//load.addActionListener(this);
		//load.setIcon(new ImageIcon(IconsDir.class.getResource("open.png")));
		JButton simulator = new JButton("Sim");
		simulator.setToolTipText("Open Simulator Config");
		
		JButton play = new JButton("Play");
		play.setToolTipText("Play's the simulation");
		
		JButton stop = new JButton("Stop");
		stop.setToolTipText("Stop's the simulation");
		
		toolBar.add(load);
		toolBar.add(simulator);
		toolBar.add(play);
		toolBar.add(stop);
		return toolBar;
	}
	
	private void run_sim(int n) {
		if (n > 0 && !_stopped) {
			try {
				_ctrl.run(1);
			} catch (Exception e) {
				// TODO show the error in a dialog box
				// TODO enable all buttons
				_stopped = true;
				return;
			}
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					run_sim(n - 1);
				}
			});
		} else {
			_stopped = true;
			// TODO enable all buttons
		}
	}

	// SimulatorObserver methods

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
}