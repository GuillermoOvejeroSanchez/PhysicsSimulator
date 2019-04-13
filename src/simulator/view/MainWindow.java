package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
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
		
		mainPanel.add(centerPanel(), BorderLayout.CENTER);
		
		mainPanel.add(new StatusBar(_ctrl), BorderLayout.SOUTH); 
		
		this.setVisible(true);
		this.setBounds(400,0,800,800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	// other private/protected methods
	
	private JPanel centerPanel() {
		JPanel panelCentral = new JPanel(); 
		panelCentral.setBackground(Color.WHITE);
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
		
		panelCentral.add(new BodiesTable(_ctrl));
		panelCentral.add(Box.createRigidArea(new Dimension(0, 10)));
		panelCentral.add(new Viewer(_ctrl)); 
		
		
		return panelCentral; 
		
		
	}
	
}

















