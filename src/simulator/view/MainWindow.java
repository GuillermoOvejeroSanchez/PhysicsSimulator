package simulator.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import simulator.view.ControlPanel;

import simulator.control.Controller;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	Controller _ctrl;

	public MainWindow(Controller ctrl) {
		super("Physics Simulator");
		_ctrl = ctrl;
		initGUI();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		setContentPane(mainPanel);
		// TODO complete this method to build the GUI
		
		mainPanel.add(new ControlPanel(_ctrl),BorderLayout.NORTH);
		//mainPanel.add(new BodiesTable(_ctrl));
		//mainPanel.add(new Viewer(_ctrl));
		//mainPanel.add(new StatusBar(_ctrl));
		
		this.setVisible(true);
		this.setBounds(400,100,800,800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	// other private/protected methods
	
}