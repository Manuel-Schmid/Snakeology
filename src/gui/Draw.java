/**
 * @author Manuel
 */

package gui;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import clocks.GameClock;
import game.Bonus;
import game.Obstacle;
import game.PickUp;
import game.Snake;

public class Draw extends JLabel { // Hier wird die Spieloberfläche mit Grid und allen Extras erstellt und gezeichnet
	private static final long serialVersionUID = 1L;
	
	Point p;
	private String crntUser; // Aktueller Benutzer
	private String recUser; // Benutzername des Rekordhalters
	private String recScore; // Punkestand des Rekordhalters
	private String recDifficulty; // Schwierigkeitsgrad des Rekordhalters
	private String record; // // String der in speziellem, mit Semikolon getrenntem Format die Rekorddaten abspeichert
	public static JLabel lblHeart = new JLabel(""); // Label für das Herzbild (extraLife)
	public static JProgressBar b = new JProgressBar(); // ProgressBar für die Anzeige wie lange der aktive Bonus noch anhält
	String splitter = ";";  // Splitzeichen für den "record" String
	
	@SuppressWarnings("resource")
	protected void paintComponent(Graphics g) { // übergabe des "Graphics" Objekts
		
		// Current User
		try {  // liest den aktuellen Benutzer aus dem entsprechenden File aus
			BufferedReader crntUserReader = new BufferedReader(new FileReader("currentUser.txt"));
			crntUser = "";
			String username = crntUserReader.readLine();
			while(username != null) { // lesen bis keine Zeile mehr
				crntUser = username;
				username = crntUserReader.readLine();
			} 	
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		// get Record
		
		try { // liesst den aktuellen Rekord aus dem entsprechenden File aus
			BufferedReader recReader = new BufferedReader(new FileReader("record.txt"));
			record = "";
			String rec = recReader.readLine();
			while (rec != null) {
				record = rec;
				rec = recReader.readLine();
			}
			if(record == "") {
				record = " " + splitter + 0 + splitter + " ";
			}
			String[] recordArr = record.split(splitter);
			recUser = recordArr[0];
			recScore = recordArr[1];
			recDifficulty = recordArr[2];
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
				
		super.paintComponent(g); // Zugriff auf die paintComponent Funktion um das Interface zu zeichnen
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		
		// Draw Background
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, Gui.width, Gui.height);
		
		// Draw Username
		JLabel lblUser = new JLabel("User: " + crntUser);
		lblUser.setFont(new Font("Arial", Font.PLAIN, 18));
		lblUser.setBounds(760, 35, 200, 35);
		lblUser.setForeground(Color.BLACK);
		this.add(lblUser);
		
		// Draw Heart
		if (GameClock.running == true) {
			Image img = new ImageIcon(this.getClass().getResource("/heart48.png")).getImage();
			lblHeart.setIcon(new ImageIcon(img));
			lblHeart.setBounds(145, 50, 48, 48);
			this.add(lblHeart);
		}
		
		// Draw Snake Tails
		g.setColor(new Color(51, 204, 51)); // Schlangenfarbe für Tails
		for(int i = 0; i < Snake.tails.size(); i++) {
			p = Snake.ptc(Snake.tails.get(i).getX(), Snake.tails.get(i).getY());
			g.fillRect(p.x, p.y, 32, 32);
		}
		
		// Draw Snake Head
		 g.setColor(new Color(0,153,0));
		 p = Snake.ptc(Snake.head.getX(), Snake.head.getY());
		 g.fillRect(p.x, p.y, 32, 32);
		
		// Draw PickUp
		 if (PickUp.isGolden) {
			 g.setColor(new Color(255, 200, 0));
		 } else {
			 g.setColor(new Color(204,51,0));
		 }
		 p = Snake.ptc(Snake.pickup.getX(), Snake.pickup.getY());
		 g.fillRect(p.x,  p.y, 32,  32);
		 
		 // Draw Obstacle
		 if (GameClock.difficulty == "hard" || GameClock.difficulty == "normal" ) {
			 if (Obstacle.isBlack) {
				 g.setColor(new Color(0,0,0));
			 } else {
				 g.setColor(new Color(128,128,128));
			 }
			 p = Snake.ptc(Snake.obstacle.getX(), Snake.obstacle.getY());
			 g.fillRect(p.x,  p.y, 32,  32);
		 }
		 
		// Draw Bonus
		if (GameClock.boniOn) {
			 if (Bonus.bonus == "slowdown") {
				 g.setColor(new Color(57, 196, 182));
			 } else if (Bonus.bonus == "extraLife") {
				 g.setColor(new Color(255, 153, 0));
			 } else if (Bonus.bonus == "speedup") {
				 g.setColor(new Color(0, 0, 204));
			 } else if (Bonus.bonus == "double") {
				 g.setColor(new Color(204, 0, 255));
			 }
			 p = Snake.ptc(Snake.bonus.getX(), Snake.bonus.getY());
			 g.fillRect(p.x,  p.y, 32,  32);
		}
				
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
		
		// Draw Score
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("Score: " + Snake.score,  130, 37);
		g.drawString("Your best: " + Snake.bestscore,  760,  37);
				
		// Draw Record
		g.drawString("All-time record",  760,  165);
		g.setFont(new Font("Arial", Font.BOLD, 17));
		g.drawString("User: " + recUser,  760,  190);
		g.drawString("Difficulty: " + recDifficulty,  760,  210);
		g.drawString("Score: " + recScore,  760,  230);
		
		// Draw Bonus ProgressBar
		b.setOrientation(SwingConstants.VERTICAL);
		b.setMinimum(0);
		b.setMaximum(100);
		b.setBackground( new Color(0, 0, 0, 0));
		b.setBounds(760, 370, 80, 163);
		b.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.GRAY));
		this.add(b);
		
		// Draw Hotkeys
		g.drawString("(m) Manual",  70,  495);
		g.drawString("(space) Pause Game",  38,  520);
		
		// Draw Boni Legend
		if (GameClock.boniOn) { // Muss nur angezeigt werden, wenn die Boni aktiviert sind
			g.drawString("Slowdown",  103,  359);
			g.drawString("Speedup",  103,  389);
			g.drawString("Double Points",  103,  419);
			g.drawString("Extra Life",  103,  449);
		
			JLabel lblSlowdown = new JLabel("");
			lblSlowdown.setBounds(70, 340, 25, 25);
			lblSlowdown.setBackground(new Color(57, 196, 182));
			lblSlowdown.setOpaque(true);
			this.add(lblSlowdown);
			
			JLabel lblSpeedup = new JLabel("");
			lblSpeedup.setBounds(70, 370, 25, 25);
			lblSpeedup.setBackground(new Color(0, 0, 204));
			lblSpeedup.setOpaque(true);
			this.add(lblSpeedup);
			
			JLabel lblDouble = new JLabel("");
			lblDouble.setBounds(70, 400, 25, 25);
			lblDouble.setBackground(new Color(204, 0, 255));
			lblDouble.setOpaque(true);
			this.add(lblDouble);
			
			JLabel lblExtraLife = new JLabel("");
			lblExtraLife.setBounds(70, 430, 25, 25);
			lblExtraLife.setBackground(new Color(255, 153, 0));
			lblExtraLife.setOpaque(true);
			this.add(lblExtraLife);
		}
		repaint();
	}
	
}
