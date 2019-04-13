package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import simulator.control.Controller;
import simulator.model.BodiesTableModel;
import simulator.model.SimulatorObserver;

public class BodiesTable extends JPanel{

	private static final long serialVersionUID = 1L;

	BodiesTableModel bodiesTableM; 
	
	BodiesTable(Controller ctrl) {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "Bodies",
				TitledBorder.LEFT, TitledBorder.TOP));
		this.setPreferredSize(new Dimension(800, 150));
		
		//TODO quitar que se puedan desplazar las columanas con los nobres
		
		createTable(ctrl); 
		
// TODO complete
	}
	
	public JTable createTable(Controller ctrl) {
		
		bodiesTableM = new BodiesTableModel(ctrl); 
		JTable bodiesTable = new JTable(bodiesTableM); 
		
		
		//JScrollPane bodiesScroll = new JScrollPane();
		
		this.add(new JScrollPane(bodiesTable));
		
		
		
		return null; 
	}
	
}