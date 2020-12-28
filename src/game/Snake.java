package game;

import java.awt.Point;
import java.util.ArrayList;

import clocks.GameClock;
import gui.Gui;

public class Snake {
	
	public static int score = 0;
	
	public static int bestscore = 0;
	
	public static boolean waitToMove = false; // Wartezeit zwischen klick und Bewegung
	
	public static Head head = new Head(7, 7); // Startposition etwa in Mitte
	
	public static ArrayList<Tail> tails = new ArrayList<Tail>();
	
	public static PickUp pickup = new PickUp();	
	
	public static Obstacle obstacle = new Obstacle();
	
	public static Bonus bonus = new Bonus();
	
	public static void addTail() { // F�gt Immer Head und dann Tails ein
		if(tails.size() < 1) {
			tails.add(new Tail(head.getX(), head.getY()));
		} else {
			tails.add(new Tail(tails.get(tails.size()-1).x, tails.get(tails.size()-1).y));
		}
		if (tails.size() % 5 == 0) {
			obstacle.reset();
		}
	}
	
	public static void removeTail(int c) { // Entfernt ein Tail von der Schlange
		for (int i = 0; i < c; i++) {
			if(tails.size() > 0) {
				tails.remove(tails.size() - 1);
			}
		}
	}
	
	public static void move() {
		
		// Move Tails
		if(tails.size() >= 2) {
			for(int i = tails.size()-1; i>=1; i--) {
				if(tails.get(i).isWait()) { // Wenn Sie sich noch nicht bewegen kann
					tails.get(i).setWait(false);
				} else { // Wenn Sie sich bewegen kann
					tails.get(i).setX(tails.get(i-1).getX());
					tails.get(i).setY(tails.get(i-1).getY());
				}
			}
		}
		
		// Move first Tail to Head: Setzt erstes Tail an Position des Heads bei Bewegung				
		if(tails.size() >= 1) {
			if(tails.get(0).isWait()) { // Wenn Sie sich noch nicht bewegen kann
				tails.get(0).setWait(false);
			} else { // Wenn Sie sich bewegen kann
				tails.get(0).setX(head.getX());
				tails.get(0).setY(head.getY());
			}
		}
		
		// Move Head
		switch(head.getDir()) {
			case RIGHT: 			// Wenn die Schlange nach rechts geht geht der Kopf auf der X-Achse 1 nach rechts
				head.setX(head.getX() + 1); 
				break;
			case UP: 			// Wenn die Schlange nach rechts geht geht der Kopf auf der X-Achse 1 nach rechts
				head.setY(head.getY() - 1); 
				break;
			case LEFT: 			// Wenn die Schlange nach rechts geht geht der Kopf auf der X-Achse 1 nach rechts
				head.setX(head.getX() - 1); 
				break;
			case DOWN: 			// Wenn die Schlange nach rechts geht geht der Kopf auf der X-Achse 1 nach rechts
				head.setY(head.getY() + 1); 
				break;
		}
	
	}
	
	// Position zu den Koordinaten
	public static Point ptc(int x, int y) {
		Point p = new Point(0, 0);
		p.x = x * 32 + Gui.xoff;
		p.y = y * 32 + Gui.yoff;
		
		return p;
	}
		
}
