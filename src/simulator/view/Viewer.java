package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import simulator.control.Controller;
import simulator.misc.Vector;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class Viewer extends JComponent implements SimulatorObserver {

	private static final long serialVersionUID = 1L;

	private int _centerX;
	private int _centerY;
	private double _scale;
	private List<Body> _bodies;
	private boolean _showHelp;
	private boolean _autoFit;
	Viewer(Controller ctrl) {
		initGUI();
		ctrl.addObserver(this);

		setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "Viewer",
				TitledBorder.LEFT, TitledBorder.TOP));

		this.setPreferredSize(new Dimension(800, 300));

	}

	private void initGUI() {
		_bodies = new ArrayList<>();
		_scale = 1.0;
		_showHelp = true;
		_autoFit = true;
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyChar()) {
				case '-':
					_scale = _scale * 1.1;
					break;
				case '+':
					_scale = Math.max(1000.0, _scale / 1.1);
					break;
				case '=':
					_autoFit = !_autoFit;
					autoScale();
					break;
				case 'h':
					_showHelp = !_showHelp;
					break;
				default:
				}
				repaint();
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}
		});
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				requestFocus();
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gr = (Graphics2D) g;
		gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gr.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
// calculate the center
		_centerX = getWidth() / 2;
		_centerY = getHeight() / 2;
		centerCross(gr);
		drawBodies(gr);
		helpText(gr);
		autoScale();
	}

	private void autoScale() {
		if(_autoFit) {
			
		double max = 1.0;
		for (Body b : _bodies) {
			Vector p = b.getPosition();
			for (int i = 0; i < p.dim(); i++)
				max = Math.max(max, Math.abs(b.getPosition().coordinate(i)));
		}
		double size = Math.max(1.0, Math.min((double) getWidth(), (double) getHeight()));
		_scale = max > size ? 4.0 * max / size : 1.0;
		}
	}

	private void centerCross(Graphics2D gr) {
		gr.setColor(Color.red);
		gr.drawLine(_centerX - 5, _centerY, _centerX + 5, _centerY);
		gr.drawLine(_centerX, _centerY - 5, _centerX, _centerY + 5);
	}

	private void helpText(Graphics2D gr) {
		if (this._showHelp) {
			long bordeX = Math.round(this.getBounds().getMinX());
			long bordey = Math.round(this.getBounds().getMinY());
			gr.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
			gr.setColor(Color.red);
			String toggle = _autoFit?"activated" : "desactivated";
			gr.drawString("h: toggle help, +: zoom-in, -:zoom-out, =: toggle fit: " + toggle, bordeX + 8, bordey/7);
			gr.drawString("Scalating ratio " + this._scale, bordeX + 8, bordey/5);
		}
	}

	private void drawBodies(Graphics2D gr) {
		try {
			for (Body b : this._bodies) {
				int x = _centerX + (int) (b.getPosition().coordinate(0) / _scale) - 5;
				int y = _centerY + (int) (b.getPosition().coordinate(1) / _scale) - 5;
				
				gr.setColor(Color.BLACK);
				gr.drawString(b.getId(), x - 2, y - 13);
				gr.setColor(Color.BLUE);
				gr.fillOval(x, y, 10, 10);
			}
		} catch (Exception e) {
			e.getMessage();
		}

	}

	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
				_bodies = new ArrayList<>(bodies);
				autoScale();
				repaint();
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
				_bodies.clear();
				autoScale();
				repaint();

	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
				_bodies.add(b);
				repaint();
	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
				repaint();
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
	}

	@Override
	public void onGravityLawChanged(String gLawsDesc) {

	}

}