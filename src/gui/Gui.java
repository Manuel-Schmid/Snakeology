package gui;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import actions.KeyHandler;
import clocks.GameClock;

public class Gui {
		
	public Gui() {
		if (jf != null) {
			jf.dispose();
		}
	}
	
	static JFrame jf;
	Draw d;
	
	public static int width = 1000, height = 600;
	public static int xoff = 230, yoff = 20;
	
	public void create() {
		jf = new JFrame("Snakeology"); // Titel
		jf.setSize(width, height);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);
		jf.setLayout(null);
		jf.setResizable(false);
		jf.addKeyListener(new KeyHandler());
		
		d = new Draw();
		d.setBounds(0, 0, width, height);
		d.setVisible(true);
		jf.add(d);
		
		jf.requestFocus();
		jf.setVisible(true);
		
	}
	
}
