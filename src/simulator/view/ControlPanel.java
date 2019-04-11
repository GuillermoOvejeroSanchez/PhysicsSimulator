package simulator.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.json.JSONObject;

import simulator.control.Controller;
import simulator.factories.Factory;
import simulator.model.Body;
import simulator.model.GravityLaws;
import simulator.model.SimulatorObserver;

public class ControlPanel extends JPanel implements SimulatorObserver {

	private static final long serialVersionUID = -7690206431664849527L;
	// ...
	private final Integer DEFAULT_STEPS = 1000;
	private Controller _ctrl;
	private boolean _stopped;
	JToggleButton toggleButton;
	private JButton load;
	private JButton simulator;
	private JButton play;
	private JButton stop;
	private JButton exit;
	private JSpinner steps;
	private JTextField delta;
	private int _stepsNumber;
	
	ControlPanel(Controller ctrl) {
		_ctrl = ctrl;
		_stopped = true;
		_stepsNumber = DEFAULT_STEPS;
		initGUI();
		_ctrl.addObserver(this);
	}

	private void initGUI() {
		//TODO Show dialog with errors
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
		
		
		load = new JButton();
		load.setToolTipText("Load a file");
		load.setIcon(new ImageIcon("icons/open.png"));
		load.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e){
				JFileChooser fileChooser = new JFileChooser(new File("D:\\dev\\Java\\PhysicsSimulator\\resources\\examples"));
				int seleccion = fileChooser.showOpenDialog(toolBar);
				if (seleccion == JFileChooser.APPROVE_OPTION) {
					String file = (fileChooser.getSelectedFile().toString());
					try (InputStream is = new FileInputStream(new File(file));) {
						_ctrl.loadBodies(is);
					} catch (Exception e1) {
						e1.getStackTrace();
						JFrame error = new JFrame("Input Dialog");
						JOptionPane.showMessageDialog(error, file, "Invalid File", JOptionPane.ERROR_MESSAGE, null);
					}

				}
			}
		});
		toolBar.add(load);
		
		toolBar.addSeparator();
		
		simulator = new JButton();
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
		
		play = new JButton();
		play.setToolTipText("Play's the simulation");
		play.setIcon(new ImageIcon("icons/run.png"));
		play.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Desactivar el resto de botones menos stop
				_stopped = false;
				run_sim(_stepsNumber);
			}
		});
		toolBar.add(play);
		
		stop = new JButton();
		stop.setToolTipText("Stop's the simulation");
		stop.setIcon(new ImageIcon("icons/stop.png"));
		stop.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				_stopped = true;
			}
		});
		toolBar.add(stop);
		
		JLabel stepsLabel = new JLabel("Steps");
		toolBar.add(stepsLabel);
		steps = new JSpinner();
		
		//steps.setPreferredSize(new Dimension(75,10));
		//steps.setMaximumSize(delta.getPreferredSize());
		steps.setMaximumSize(steps.getPreferredSize());
		steps.setPreferredSize(new Dimension(75,15));
		steps.setModel(new SpinnerNumberModel(DEFAULT_STEPS.intValue(),0,Integer.MAX_VALUE,1));
		steps.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				_stepsNumber = Integer.parseInt(steps.getValue().toString());
			}
		});
		toolBar.add(steps);
		
		JLabel deltaLabel = new JLabel("Delta-Time");
		toolBar.add(deltaLabel);
		delta = new JTextField(5);
		delta.setMaximumSize(delta.getPreferredSize());
		delta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					_ctrl.setDeltaTime(Double.parseDouble(delta.getText()));					
				} catch (Exception e2) {
					e2.getStackTrace();
				}
			}
		});
		toolBar.add(delta);
		
		toolBar.add(leftPanel);

		exit = new JButton();
		exit.setToolTipText("Exit's the simulation");
		exit.setIcon(new ImageIcon("icons/exit.png"));
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame exit = new JFrame("Input Dialog");
				int exitSelection = JOptionPane.showConfirmDialog(exit, "Really want to close it?", "Exit Application", JOptionPane.OK_CANCEL_OPTION);
				System.out.println(exitSelection);
				if(exitSelection == 0)
					System.exit(exitSelection);	
			}
		});
		toolBar.addSeparator();
		toolBar.add(exit);
		
		this.add(toolBar, BorderLayout.PAGE_START);

		return toolBar;
	}

	//TODO
	private void run_sim(int n) {
		if (n > 0 && !_stopped) {
			try {
				_ctrl.run(1);
			} catch (Exception e) {
				// TODO show the error in a dialog box
				JDialog error = new JDialog();
				error.setVisible(true);
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
			_ctrl.reset();
		}
	}
	
	
	//----------------- DIALOG SELECCIONAR GRAVEDAD -----------------------
	public JFrame inputLaw() {
		
		JFrame inputDialog = new JFrame("Input Dialog");
		int i = 0;
		Object[] possibilities = new Object[3];
		Factory<GravityLaws> laws = _ctrl.getGravityLawsFactory();
		for (JSONObject jo : laws.getInfo()) {
			
			possibilities[i] = jo.get("desc").toString();
			i++;
			i%=3;
		}
		System.out.println(laws.getInfo().toString());
		try {
			String n = (String) JOptionPane.showInputDialog(this,
					"Select gravy laws to be used.",
					"Gravity Law Selector", 
					  JOptionPane.INFORMATION_MESSAGE,
					  new ImageIcon("icons/physics.png"),
					  possibilities,
					  null);

			if (n != null) {
				for (JSONObject fe : laws.getInfo()) {
					if (n.equals(fe.getString("desc"))) {
						_ctrl.setGravityLaws(fe);
						break;
					}
				}
				
			} 

			
		} catch (Exception e) {
			e.getStackTrace();
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
		delta.setText(String.valueOf(dt));
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		delta.setText(String.valueOf(dt));
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {

	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {

	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		delta.setText(String.valueOf(dt));
	}

	@Override
	public void onGravityLawChanged(String gLawsDesc) {

	}
}