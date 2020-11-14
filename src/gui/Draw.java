package gui;

import java.awt.*;
import javax.swing.*;

public class Draw extends JLabel{

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		
		// Draw Background
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, Gui.width, Gui.height);
		
		// Draw Grid
		g.setColor(Color.GRAY);
		for(int x = 0; x < 16; x++) {
			for (int y = 0; y < 16; y++) {
				g.drawRect(x * 32 + Gui.xoff, y * 32 + Gui.yoff, 32, 32);
			}
		}
		
		// Draw Border
		g.setColor(Color.BLACK);
		g.drawRect(Gui.xoff,  Gui.yoff,  512,  512);
		
		repaint();
		
	}
	
}
