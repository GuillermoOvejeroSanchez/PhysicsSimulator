package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
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
	private Object[] possibilities;
	private int _stepsNumber;

	ControlPanel(Controller ctrl) {
		_ctrl = ctrl;
		_stopped = true;
		_stepsNumber = DEFAULT_STEPS;
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
		createJToolBar();
		// this.add(createJToolBar());

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
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(load)) {

					JFileChooser fileChooser = new JFileChooser(
							new File("C:\\dev\\Java\\PhysicsSimulator\\resources\\examples"));
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
				setEnable(false);
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

		// steps.setPreferredSize(new Dimension(75,10));
		// steps.setMaximumSize(delta.getPreferredSize());
		steps.setMaximumSize(steps.getPreferredSize());
		steps.setPreferredSize(new Dimension(75, 15));
		steps.setModel(new SpinnerNumberModel(DEFAULT_STEPS.intValue(), 0, Integer.MAX_VALUE, 1));
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
			
				//int exitSelection = JOptionPane.showConfirmDialog(exit, "Really want to close it?", "Exit Application", JOptionPane.OK_CANCEL_OPTION);
				int exitSelection = JOptionPane.showConfirmDialog(exit, 
						"Really want to close it?", 
						"Exit Application", 
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE , 
						new ImageIcon("icons/error.png"));
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

	private void setEnable(boolean enable) {
		load.setEnabled(enable);
		simulator.setEnabled(enable);
		play.setEnabled(enable);
		steps.setEnabled(enable);
		delta.setEnabled(enable);
		//exit.setEnabled(enable);
	}
	
	private void run_sim(int n) {
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
	}

	// ----------------- DIALOG SELECCIONAR GRAVEDAD -----------------------
	public void inputLaw() {

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