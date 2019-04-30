package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
		mainPanel.add(new ControlPanel(_ctrl), BorderLayout.NORTH);
		mainPanel.add(centerPanel(), BorderLayout.CENTER);

		mainPanel.add(new StatusBar(_ctrl), BorderLayout.SOUTH);

		this.setVisible(true);
		this.setBounds(400, 0, 800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	

	private JPanel centerPanel() {
		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(Color.WHITE);
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));

		panelCentral.add(new BodiesTable(_ctrl));
		panelCentral.add(new Viewer(_ctrl));

		return panelCentral;

	}

}
