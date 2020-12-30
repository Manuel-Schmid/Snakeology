/**
 * @author Manuel
 */

package gui;

import javax.swing.JFrame;
import actions.KeyHandler;

public class Gui { // Gui Klasse, die einen neuen JFrame erstellt und auf die Draw Klasse zugreift
		 
	public Gui() { // Schliesst allfällig übrig gebliebene JFrames bevor ein neuer erstellt wird
		if (jf != null) {
			jf.dispose();
		}
	}
	
	static JFrame jf; // Neuer statischer JFrame
	Draw d; // Neues Draw Objekt
	
	public static int width = 1000, height = 600; // Grösse des Rahmens
	public static int xoff = 230, yoff = 20; // Abstand zum Oberen und seitlichen Rand des Fensters
	
	public void create() { // Erstellt das Hauptfenster
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
