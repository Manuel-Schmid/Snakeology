/**
 * @author Manuel
 */

package actions;

import java.awt.Color;
import java.io.IOException;
import javax.swing.JDialog;

import clocks.GameClock;
import game.Bonus;
import game.Obstacle;
import game.PickUp;
import game.Snake;
import gui.DeathScreen;
import gui.Draw;

public class Collision { // Diese Klasse ist für das Handling aller Arten von Kollisionen zuständig
	
	public static String activeBonus = ""; //Variable für den derzeit aktiven Bonus
	
	public static void collideSelf() throws IOException { // Kollision mit einem Tail der Schlange
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
	
	public static void collideWall() throws IOException { // Kollision mit einer der Wände (Rahmen des Spielfelds)
		if (Snake.head.getX()<0 || Snake.head.getX() > 15 || Snake.head.getY()<0 || Snake.head.getY() > 15 ) {// Wenn der Head oben, unten, links oder rechts in die Border fährt
			if (GameClock.difficulty == "hard") { // Beim Hardmode stirbt man direkt
				if (GameClock.extraLife == true) { // Extraleben abziehen und auf anderer Seite spawnen
					GameClock.extraLife = false;
					// Schlange kommt auf anderer Seite wieder raus
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
			// Schlange kommt auf anderer Seite wieder raus
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
	
	public static void collidePickUp() { // Kollision mit einem Apfel
		if(Snake.head.getX() == Snake.pickup.getX() && Snake.head.getY() == Snake.pickup.getY()) {
			// Score erhöhen
			if (PickUp.isGolden) { // Golder Apfel && Double-Bonus Handling
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
				if (Collision.activeBonus == "double") { // Double-Bonus Handling
					Snake.score += 2;
					Snake.addTail();
					Snake.addTail();
				} else {
					Snake.score += 1;
					Snake.addTail();
				}
			}
			if (Snake.score > Snake.bestscore) { // Bestscore aktualisieren
				Snake.bestscore = Snake.score;
			}
			Snake.pickup.reset(); // neuer Apfel erscheint
		}
	}
	
	public static void collideNormalObstacle() { // Kollision mit Hindernis im Normal-Mode
		if(Obstacle.isBlack == false && Snake.head.getX() == Snake.obstacle.getX() && Snake.head.getY() == Snake.obstacle.getY()) {
			// Score verringern
			if (Snake.score > 1) {
				Snake.score -= 1;
			} else {
				Snake.score = 0;
			}
			Snake.obstacle.reset(); // Neues Hindernis erscheint
			Snake.removeTail(1); // Schwanz abziehen
		}
	}
	
	public static void collideBlackObstacle() throws IOException { // Kollision mit Hindernis im Hard-Mode
		if(Obstacle.isBlack == true && Snake.head.getX() == Snake.obstacle.getX() && Snake.head.getY() == Snake.obstacle.getY()) {
			if (GameClock.extraLife == true) { // ExtraLife Handling
				GameClock.extraLife = false;
				Snake.obstacle.reset(); // Neues Hindernis erscheint
			} else {
				// Schlange stirbt
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
	
	public static void collideBonus() { // Kollision mit einem Bonus (wird nur wenn Boni aktiviert sind von der GameClock aufgerufen)
		if(Snake.head.getX() == Snake.bonus.getX() && Snake.head.getY() == Snake.bonus.getY()) {
			Snake.bonus.setX(-5);
			Snake.bonus.setY(-5);
			GameClock.bonusTimer = 40; // Timer zurücksetzen
			activeBonus = Bonus.bonus; // bonus wird aktiv
			Draw.b.setValue(100); // ProgressBar vom Bonus wird aufgefüllt
			if (activeBonus == "slowdown") { // Handling der einzelnen Boni und ProgressBar wird entsprechend dem Bonus eingefärbt
				GameClock.bonusTimer = 25;
				Draw.b.setForeground(new Color(57, 196, 182));
			}
			else if (activeBonus == "extraLife") {
				GameClock.extraLife = true;
				Draw.b.setForeground(new Color(255, 153, 0));
			} else if (activeBonus == "speedup") {
				GameClock.bonusTimer = 60;
				Draw.b.setForeground(new Color(0, 0, 204));
			} else if (activeBonus == "double") {
				Draw.b.setForeground(new Color(204, 0, 255));
			} 
		}
	}
}
