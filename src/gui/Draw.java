package gui;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import actions.Collision;
import clocks.GameClock;
import game.PickUp;
import game.Snake;

public class Draw extends JLabel{
	
	Point p;
	private String crntUser;
	private String recUser;
	private String recScore;
	
	protected void paintComponent(Graphics g) {
		
		// Current User
		try {
			BufferedReader crntUserReader = new BufferedReader(new FileReader("currentUser.txt"));
			crntUser = "";
			String username = crntUserReader.readLine();
			while(username != null) { // lesen bis keine Zeile mehr
				crntUser = username;
				username = crntUserReader.readLine();
			} 	
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Record User
		try {
			BufferedReader recUserReader = new BufferedReader(new FileReader("recordUser.txt"));
			recUser = "";
			String recordUsername = recUserReader.readLine();
			while(recordUsername != null) { // lesen bis keine Zeile mehr
				recUser = recordUsername;
				recordUsername = recUserReader.readLine();
			} 
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Record User Score
		try {
			BufferedReader recUserScoreReader = new BufferedReader(new FileReader("recordUserScore.txt"));
			recScore = "";
			String recordUserScore = recUserScoreReader.readLine();
			while(recordUserScore != null) { // lesen bis keine Zeile mehr
				recScore = recordUserScore;
				recordUserScore = recUserScoreReader.readLine();
			} 
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		
		// Draw Background
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, Gui.width, Gui.height);
		
		JLabel lblUser = new JLabel("User: " + crntUser);
		lblUser.setFont(new Font("Arial", Font.PLAIN, 18));
		lblUser.setBounds(760, 35, 200, 20);
		lblUser.setForeground(Color.BLACK);
		this.add(lblUser);

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
		g.drawString("Score: " + Snake.score,  130, 25);
		g.drawString("Your best: " + Snake.bestscore,  760,  25);
		// Record
		g.drawString("All-time record",  760,  165);
		g.drawString("User: " + recUser,  760,  195);
		g.drawString("Score: " + recScore,  760,  225);
		
		repaint();		
	}
	
}
