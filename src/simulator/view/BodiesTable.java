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

public class BodiesTable extends JPanel {

	private static final long serialVersionUID = 1L;

	BodiesTableModel bodiesTableM;

	BodiesTable(Controller ctrl) {
		// bodiesTableM = new BodiesTableModel(ctrl);
		JTable bodiesTable = new JTable(new BodiesTableModel(ctrl));

		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "Bodies",
				TitledBorder.LEFT, TitledBorder.TOP));
		this.setPreferredSize(new Dimension(800, 150));

		this.add(new JScrollPane(bodiesTable));

	}

}