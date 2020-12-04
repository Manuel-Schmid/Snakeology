package gui;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import game.Snake;

public class Draw extends JLabel{
	
	Point p;
	private String pfad = "C:" + File.separator + "Users" + File.separator + "Many" + File.separator + "eclipse-workspace" + File.separator + "Snakeology" + File.separator + "data" + File.separator;
	private String userFile = "currentUser.txt";
	private String topUsersFile = "topUsers.txt";
	private String topUsersScoresFile = "topUsersScores.txt";
	private String crntUser;
	
	protected void paintComponent(Graphics g) {
		
		try {
			BufferedReader crntUserReader = new BufferedReader(new FileReader(pfad+userFile));
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
		
		// Top List
		DefaultListModel topUsers = new DefaultListModel();
		JList listTopUsers = new JList(topUsers);
		listTopUsers.setEnabled(false);
		listTopUsers.setBounds(760, 135, 150, 93);
		this.add(listTopUsers);
		
		// Top Scores List
		ArrayList<String> topUsersScores = new ArrayList<String>();
		
		try {
			BufferedReader topUsersScoresReader = new BufferedReader(new FileReader(pfad+topUsersScoresFile));
			topUsersScores.clear();
			String userScore = topUsersScoresReader.readLine();
			while(userScore != null) { // lesen bis keine Zeile mehr
				topUsersScores.add(userScore);
				userScore = topUsersScoresReader.readLine();
			}
			
			BufferedReader topUsersReader = new BufferedReader(new FileReader(pfad+topUsersFile));
			topUsers.clear();
			String user = topUsersReader.readLine();
			int i = 0;
			while(user != null) { // lesen bis keine Zeile mehr
				String score = topUsersScores.get(i);
				topUsers.addElement(user + "    " + score);
				user = topUsersReader.readLine();
				i++;
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
		 g.setColor(new Color(204,51,0));
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
		g.drawString("Score: "+ Snake.score,  130, 25);
		g.drawString("Best: "+Snake.bestscore,  760,  25);
		
		repaint();
		
	}
	
}
