package simulator.view;

import java.awt.BorderLayout;
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
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class ControlPanel extends JPanel implements SimulatorObserver {

	private static final long serialVersionUID = -7690206431664849527L;

	private final Integer DEFAULT_STEPS = 1500;
	private final Double DEFAULT_DELTA = 2500.0;
	private Controller _ctrl;
	JToggleButton toggleButton;
	protected JButton load;
	protected JButton simulator;
	protected JButton play;
	protected JButton stop;
	protected JButton exit;
	private JSpinner steps;
	private JSpinner delay;
	private JTextField delta;
	private Object[] possibilities;
	private int _stepsNumber;
	private volatile Thread _thread;

	ControlPanel(Controller ctrl) {
		_ctrl = ctrl;
		_stepsNumber = DEFAULT_STEPS;
		_thread = null;
		initGUI();
		_ctrl.addObserver(this);

		possibilities = new Object[_ctrl.getGravityLawsFactory().getInfo().size()];
		int i = 0;
		for (JSONObject jo : _ctrl.getGravityLawsFactory().getInfo()) {
			possibilities[i] = jo.get("desc");
			i++;
		}
	}

	private void initGUI() {
		super.setLayout(new BorderLayout());
		addJToolBar();
	}

	// other private/protected methods
	private void addJToolBar() {
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setRollover(true);
		JPanel leftPanel = new JPanel(new FlowLayout());

		load = new JButton();
		load.setToolTipText("Load a file");
		load.setIcon(new ImageIcon("icons/open.png"));
		load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(load)) {

					JFileChooser fileChooser = new JFileChooser(
							new File("C:\\dev\\Java\\PhysicsSimulator\\resources\\examples")); // Mi ruta para los
																								// inputs
					int seleccion = fileChooser.showOpenDialog(toolBar);
					if (seleccion == JFileChooser.APPROVE_OPTION) {
						String file = (fileChooser.getSelectedFile().toString());
						try (InputStream is = new FileInputStream(new File(file));) {
							_ctrl.reset();
							_ctrl.loadBodies(is);
						} catch (Exception e1) {
							JFrame error = new JFrame("Input Dialog");
							JOptionPane.showMessageDialog(error, "File is not valid", "Invalid File",
									JOptionPane.ERROR_MESSAGE, null);
						}
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
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						setEnable(false);						
					}
				});
				_thread = new Thread(new Runnable() {
					@Override
					public void run() {
						run_sim(_stepsNumber, Long.parseUnsignedLong(delay.getValue().toString()));
						_thread = null;
					}
				});
				_thread.start();
			}
		});
		toolBar.add(play);

		stop = new JButton();
		stop.setToolTipText("Stop's the simulation");
		stop.setIcon(new ImageIcon("icons/stop.png"));
		stop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						if(_thread != null) {
							_thread.interrupt();
						}
					}
				});
			}
		});
		toolBar.add(stop);

		JLabel delayLabel = new JLabel("Delay");
		toolBar.add(delayLabel);
		delay = new JSpinner();

		delay.setMaximumSize(delay.getPreferredSize());
		delay.setPreferredSize(new Dimension(75, 15));
		delay.setModel(new SpinnerNumberModel(1, 0, 1000, 1));
		delay.setToolTipText("Set delay between steps in miliseconds");
		delay.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				try {
					_stepsNumber = Integer.parseInt(steps.getValue().toString());
				} catch (Exception e1) {
					_stepsNumber = DEFAULT_STEPS;
				}
			}
		});
		toolBar.add(delay);
		
		
		JLabel stepsLabel = new JLabel("Steps");
		toolBar.add(stepsLabel);
		
		steps = new JSpinner();

		steps.setMaximumSize(steps.getPreferredSize());
		steps.setPreferredSize(new Dimension(75, 15));
		steps.setToolTipText("Set number of steps to run");
		steps.setModel(new SpinnerNumberModel(DEFAULT_STEPS.intValue(), 0, Integer.MAX_VALUE, 1));
		steps.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				try {
					_stepsNumber = Integer.parseInt(steps.getValue().toString());
				} catch (Exception e1) {
					_stepsNumber = DEFAULT_STEPS;
				}
			}
		});
		toolBar.add(steps);

		JLabel deltaLabel = new JLabel("Delta-Time");
		toolBar.add(deltaLabel);
		delta = new JTextField(5);
		delta.setMaximumSize(delta.getPreferredSize());
		delta.setToolTipText("Set delta time for each step");
		delta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					_ctrl.setDeltaTime(Double.parseDouble(delta.getText()));
				} catch (Exception e2) {
					_ctrl.setDeltaTime(DEFAULT_DELTA);
				}
			}
		});
		toolBar.add(delta);

		toolBar.add(leftPanel);

		exit = new JButton();
		exit.setToolTipText("Exit's the simulation");
		exit.setIcon(new ImageIcon("icons/exit.png"));
		exit.setToolTipText("Exit's the application");
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame exit = new JFrame("Input Dialog");
				int exitSelection = JOptionPane.showConfirmDialog(exit, "Really want to close it?", "Exit Application",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, new ImageIcon("icons/error.png"));
				if (exitSelection == 0)
					System.exit(exitSelection);
			}
		});
		toolBar.addSeparator();
		toolBar.add(exit);

		this.add(toolBar, BorderLayout.PAGE_START);
	}

	private void setEnable(boolean enable) {
		load.setEnabled(enable);
		simulator.setEnabled(enable);
		play.setEnabled(enable);
		steps.setEnabled(enable);
		delta.setEnabled(enable);
		exit.setEnabled(enable);
	}

	private void run_sim(int n, long delay) {
		while( n>0 && !Thread.interrupted() ) { //(the current thread has not been intereptred)  TODO
			// 1. execute the simulator one step, i.e., call method
			// _ctrl.run(1) and handle exceptions if any
			try {
				_ctrl.run(1);
			} catch (Exception e) {
				
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);	
					}
				});
				setEnable(true);
				return;
			}
			// 2. sleep the current thread for ’delay’ milliseconds TODO
			try {
				Thread.sleep(delay);
				//run_sim(n-1, delay);
			} catch (InterruptedException e) {
				setEnable(true);
				return;
			}
			n--;
		}
			setEnable(true);
		/*
		if (n > 0 && !_stopped) {
			try {
				_ctrl.run(1);
			} catch (Exception e) {
				JFrame error = new JFrame("Input Dialog");
				JOptionPane.showMessageDialog(error, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, null);
				setEnable(true);
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
			setEnable(true);
			_ctrl.reset();
		}
		
		*/
	}

	// ----------------- DIALOG SELECCIONAR GRAVEDAD -----------------------
	private void inputLaw() {

		try {
			String n = (String) JOptionPane.showInputDialog(this, "Select gravy laws to be used.",
					"Gravity Law Selector", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("icons/physics.png"),
					possibilities, null);

			if (n != null) {
				for (JSONObject fe : _ctrl.getGravityLawsFactory().getInfo()) {
					if (n.equals(fe.getString("desc"))) {
						_ctrl.setGravityLaws(fe);
						break;
					}
				}

			}

		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	// --------------------------------------------------------------------------

	// SimulatorObserver methods

	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				delta.setText(String.valueOf(dt));
				// TODO Auto-generated method stub
			}
		});
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				delta.setText(String.valueOf(dt));
				
			}
		});
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {

	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {

	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				delta.setText(String.valueOf(dt));
				
			}
		});
	}

	@Override
	public void onGravityLawChanged(String gLawsDesc) {

	}
}