package simulator.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.ObjectInputFilter.Status;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class StatusBar extends JPanel implements SimulatorObserver {

	private static final long serialVersionUID = 1L;
	
	private static String[] statusNames = {
		"Time: ", 
		"Bodies: ",
		"Laws: ",
		
	};
	
	private final static String DEFAULT_LAW  = "Newton's Universal Gravitation";  

	private JLabel _currTime; // for current time
	private JLabel _currLaws; // for gravity laws
	private JLabel _numOfBodies; // for number of bodies

	// TODO quitar la descripcion de ley y poner solo el nombre 
	
	public StatusBar(Controller ctrl) {
		
		this._currLaws = new JLabel(DEFAULT_LAW); 
		this._currTime = new JLabel("0.00"); 
		this._numOfBodies = new JLabel("0"); 
		
		initGUI();
		ctrl.addObserver(this);
		
		this._currLaws = new JLabel("default Law"); 
		this._currTime = new JLabel("0.00"); 
		this._numOfBodies = new JLabel("0"); 
	}

	private void initGUI() {
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBorder(BorderFactory.createBevelBorder(1));
		//this.setPreferredSize(new Dimension(800, 20));
		
		  this.setForeground(Color.GRAY);
		
		
		this.setFont(new Font("verdana",Font.BOLD,5));
		
		createStatudBar();
// TODO complete the code to build the tool bar
	}

// other private/protected methods
// ...
// SimulatorObserver methods
// ...
	
	public void createStatudBar() {
		
		
		
		JLabel timeLabel = new JLabel(statusNames[0]); 
		this.add(timeLabel); 
		this.add(_currTime); 
		
		this.add(Box.createHorizontalStrut(3));
		//this.add(new JSeparator(SwingConstants.VERTICAL));
		this.add(separator()); 


		JLabel bodiesLabel = new JLabel(statusNames[1]); 
		this.add(bodiesLabel); 
		this.add(_numOfBodies); 
		
		
		this.add(Box.createHorizontalStrut(3));
		//this.add(new JSeparator(SwingConstants.VERTICAL));
		this.add(separator()); 
		
		JLabel lawsLabel = new JLabel(statusNames[2]); 
		this.add(lawsLabel); 
		this.add(_currLaws); 
		
		

	}
	
	public JLabel separator() {
		JLabel sep  = new JLabel(" | "); 
		sep.setForeground(Color.GRAY);
		
		return sep; 
	}
	
	
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		// TODO Auto-generated method stub
		
		this._numOfBodies.setText(Integer.toString(bodies.size()));
		this._currTime.setText(Double.toString(time));
		this._currLaws.setText(gLawsDesc.toString());
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		// TODO Auto-generated method stub
		this._numOfBodies.setText(Integer.toString(bodies.size()));
		this._currTime.setText(Double.toString(time));
		this._currLaws.setText(gLawsDesc.toString());
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		// TODO Auto-generated method stub
		this._numOfBodies.setText(Integer.toString(bodies.size()));
	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
		// TODO Auto-generated method stub
		this._currTime.setText(Double.toString(time));
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		// TODO Auto-generated method stub
		//Esto no hace nada?
	}

	@Override
	public void onGravityLawChanged(String gLawsDesc) {
		// TODO Auto-generated method stub
		this._currLaws.setText(gLawsDesc.toString());
	}
}