package actions;

import java.io.IOException;

import javax.swing.JDialog;

import clocks.GameClock;
import game.Obstacle;
import game.PickUp;
import game.Snake;
import gui.DeathScreen;

public class Collision {
	
	public static void collideSelf() throws IOException {
		for(int i = 0; i < Snake.tails.size(); i++) { // Iterieren durch Tails
			if(Snake.head.getX() == Snake.tails.get(i).getX() && Snake.head.getY() == Snake.tails.get(i).getY() && !Snake.tails.get(i).isWait()) {
				Snake.tails.clear();
				Snake.head.setX(7);
				Snake.head.setY(7);
				// Spiel stoppen & DeathScreen anzeigen
				GameClock.running = false;
				DeathScreen deathS = new DeathScreen();
				deathS.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				deathS.setVisible(true);
			}
		}
	}
	
	public static void collideWall() throws IOException {	
		if (Snake.head.getX()<0 || Snake.head.getX() > 15 || Snake.head.getY()<0 || Snake.head.getY() > 15 ) {// Wenn der Head oben, unten links oder rechts in die Border fährt
			Snake.tails.clear();
			Snake.head.setX(7);
			Snake.head.setY(7);
			// Spiel stoppen & DeathScreen anzeigen
			GameClock.running = false;
			DeathScreen deathS = new DeathScreen();
			deathS.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			deathS.setVisible(true);
		}
	}
	
	public static void collidePickUp() {
		if(Snake.head.getX() == Snake.pickup.getX() && Snake.head.getY() == Snake.pickup.getY()) {
			// Score erhöhen
			if (PickUp.isGolden) {
				Snake.score += 3;
				Snake.addTail();
				Snake.addTail();
				Snake.addTail();
			} else {
				Snake.score += 1;
				Snake.addTail();
			}
			if (Snake.score > Snake.bestscore) {
				Snake.bestscore = Snake.score;
			}
			Snake.pickup.reset();
		}
	}
	
	public static void collideNormalObstacle() {
		if(Obstacle.isBlack == false && Snake.head.getX() == Snake.obstacle.getX() && Snake.head.getY() == Snake.obstacle.getY()) {
			// Score verringern
			if (Snake.score > 1) {
				Snake.score -= 1;
			} else {
				Snake.score = 0;
			}
			Snake.obstacle.reset();
			Snake.removeTail();
		}
	}
	
	public static void collideBlackObstacle() throws IOException {
		if(Obstacle.isBlack == true && Snake.head.getX() == Snake.obstacle.getX() && Snake.head.getY() == Snake.obstacle.getY()) {
			// Kill Snake
			Snake.tails.clear();
			Snake.head.setX(7);
			Snake.head.setY(7);
			// Spiel stoppen & DeathScreen anzeigen
			GameClock.running = false;
			DeathScreen deathS = new DeathScreen();
			deathS.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			deathS.setVisible(true);
		}
	}
}
