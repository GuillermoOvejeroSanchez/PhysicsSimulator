package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.NewtonUniversalGravitation;
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
		super.setLayout(new BorderLayout());
		createJToolBar();
		//this.add(createJToolBar());
		

	}

	// other private/protected methods
	public JToolBar createJToolBar() {
		JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setRollover(true);
		JPanel leftPanel = new JPanel(new FlowLayout());
		
		JButton load = new JButton();
		load.setToolTipText("Load a file");
		//load.addActionListener(this);
		load.setIcon(new ImageIcon("icons/open.png"));
		toolBar.add(load);
		
		toolBar.addSeparator();
		
		JButton simulator = new JButton();
		simulator.setToolTipText("Open Simulator Config");
		simulator.setIcon(new ImageIcon("icons/physics.png"));
		
		simulator.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				inputLaw();
				
			}
		});
		
		toolBar.add(simulator);
		
		toolBar.addSeparator();
		
		JButton play = new JButton();
		play.setToolTipText("Play's the simulation");
		play.setIcon(new ImageIcon("icons/run.png"));
		toolBar.add(play);
		
		JButton stop = new JButton();
		stop.setToolTipText("Stop's the simulation");
		stop.setIcon(new ImageIcon("icons/stop.png"));
		toolBar.add(stop);
		
		JLabel stepsLabel = new JLabel("Steps");
		toolBar.add(stepsLabel);
		JSpinner steps = new JSpinner();
		
		//steps.setPreferredSize(new Dimension(75,10));
		//steps.setMaximumSize(delta.getPreferredSize());
		steps.setMaximumSize(steps.getPreferredSize());
		steps.setPreferredSize(new Dimension(75,15));
		steps.setModel(new SpinnerNumberModel(1000,0,99999999999999999.0,1));
		toolBar.add(steps);
		
		JLabel deltaLabel = new JLabel("Delta-Time");
		toolBar.add(deltaLabel);
		JTextField delta = new JTextField(5);
		delta.setMaximumSize(delta.getPreferredSize());
		toolBar.add(delta);
		
		
		
		
		toolBar.add(leftPanel);
		
		//add(Box.createHorizontalGlue());
		JButton exit = new JButton();
		exit.setToolTipText("Exit's the simulation");
		exit.setIcon(new ImageIcon("icons/exit.png"));
		
		toolBar.addSeparator();
		
		toolBar.add(exit);
		
		this.add(toolBar, BorderLayout.PAGE_START);
		
		

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
	
	//----------------- DIALOG SELECCIONAR GRAVEDAD -----------------------
	public JFrame inputLaw() {
		
		JFrame inputDialog = new JFrame("Input Dialog");
		
		
		Object[] possibilities = {"Newton Universal Gravitation Law","Falling To Center Gravity","No Gravity Law"};
		
		String n = (String) JOptionPane.showInputDialog(this,
				"Select gravy laws to be used.",
				"Gravity Law Selector", 
				  JOptionPane.INFORMATION_MESSAGE,
				  null,
				  possibilities,
				  "Newton Universal Gravitation Law");
		
		if(n.equalsIgnoreCase("Newton Universal Gravitation Law")) {
			// TODO se aplica ley de newton
		}
		else if(n.equalsIgnoreCase("Falling To Center Gravity")) {
			//TODO se aplica ley 
		}
		else {
			//TODO no gravedad 
		}
		
		
		inputDialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inputDialog.pack();
		inputDialog.setVisible(true);
		
		return null; 
	}
	
	//--------------------------------------------------------------------------

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