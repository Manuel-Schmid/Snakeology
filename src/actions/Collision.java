package actions;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JDialog;

import clocks.GameClock;
import game.Bonus;
import game.Obstacle;
import game.PickUp;
import game.Snake;
import gui.DeathScreen;
import gui.Draw;

public class Collision {
	
	public static String activeBonus = "";
	
	public static void collideSelf() throws IOException {
		for(int i = 0; i < Snake.tails.size(); i++) { // Iterieren durch Tails
			if(Snake.head.getX() == Snake.tails.get(i).getX() && Snake.head.getY() == Snake.tails.get(i).getY() && !Snake.tails.get(i).isWait()) {
				if (GameClock.extraLife == true) {
					GameClock.extraLife = false;
				} else {
					Snake.head.setX(7);
					Snake.head.setY(7);
					Snake.tails.clear();
					// Spiel stoppen & DeathScreen anzeigen
					GameClock.running = false;
					DeathScreen deathS = new DeathScreen();
					deathS.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					deathS.setVisible(true);
				}
			}
		}
	}
	
	public static void collideWall() throws IOException {	
		if (Snake.head.getX()<0 || Snake.head.getX() > 15 || Snake.head.getY()<0 || Snake.head.getY() > 15 ) {// Wenn der Head oben, unten links oder rechts in die Border fährt
			if (GameClock.difficulty == "hard") {
				if (GameClock.extraLife == true) { // Extraleben abziehen und auf anderer Seite spawnen
					GameClock.extraLife = false;
					if (Snake.head.getX()<0) {
						Snake.head.setX(15);
					} else if (Snake.head.getX()>15){
						Snake.head.setX(0);
					} else if (Snake.head.getY()<0){
						Snake.head.setY(15);
					} else if (Snake.head.getY()>15){
						Snake.head.setY(0);
					}
				} else {
					Snake.head.setX(7);
					Snake.head.setY(7);
					Snake.tails.clear();
					// Spiel stoppen & DeathScreen anzeigen
					GameClock.running = false;
					DeathScreen deathS = new DeathScreen();
					deathS.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					deathS.setVisible(true);
				}
			} else if (Snake.head.getX()<0){
				Snake.head.setX(15);
			} else if (Snake.head.getX()>15){
				Snake.head.setX(0);
			} else if (Snake.head.getY()<0){
				Snake.head.setY(15);
			} else if (Snake.head.getY()>15){
				Snake.head.setY(0);
			}
		}
	}
	
	public static void collidePickUp() {
		if(Snake.head.getX() == Snake.pickup.getX() && Snake.head.getY() == Snake.pickup.getY()) {
			// Score erhöhen
			if (PickUp.isGolden) {
				if (Collision.activeBonus == "double") {
					Snake.score += 6;
					for (int i = 0; i < 6; i++) {
						Snake.addTail();
					}
				} else {
					Snake.score += 3;
					for (int i = 0; i < 3; i++) {
						Snake.addTail();
					}
				}
			} else {
				if (Collision.activeBonus == "double") {
					Snake.score += 2;
					Snake.addTail();
					Snake.addTail();
				} else {
					Snake.score += 1;
					Snake.addTail();
				}
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
			Snake.removeTail(1);
		}
	}
	
	public static void collideBlackObstacle() throws IOException {
		if(Obstacle.isBlack == true && Snake.head.getX() == Snake.obstacle.getX() && Snake.head.getY() == Snake.obstacle.getY()) {
			if (GameClock.extraLife == true) {
				GameClock.extraLife = false;
				Snake.obstacle.reset();
			} else {
				Snake.head.setX(7);
				Snake.head.setY(7);
				Snake.tails.clear();
				GameClock.running = false;
				DeathScreen deathS = new DeathScreen();
				deathS.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				deathS.setVisible(true);
			}
		}
	}
	
	public static void collideBonus() {
		if(Snake.head.getX() == Snake.bonus.getX() && Snake.head.getY() == Snake.bonus.getY()) {
			Snake.bonus.setX(-5);
			Snake.bonus.setY(-5);
			GameClock.bonusTimer = 40;
			activeBonus = Bonus.bonus;
			if (activeBonus == "slowdown") {
				GameClock.bonusTimer = 25;
			}
			else if (activeBonus == "extraLife") {
				GameClock.extraLife = true;
			} else if (activeBonus == "speedup") {
				GameClock.bonusTimer = 60;
			}
		}
	}
}
