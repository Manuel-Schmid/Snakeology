/**
 * @author Manuel
 */

package clocks;

import java.io.IOException;


import actions.Collision;
import game.Snake;
import gui.Draw;

public class GameClock extends Thread{ // Diese Klasse verwaltet die Funktionen des Spiels und arbeitet in Ticks
	
	public static boolean running = true; // Läuft das Spiel?
	public static String difficulty; // Schwierigkeitsstufe
	public static boolean extraLife = false; // Ist ein extraLife aktiv?
	public static boolean boniOn = false; // Sind Boni aktiviert?
	public static int bonusTimer = 40; // Timer wie lange die Boni andauern
	
	public GameClock() {}
	
	public void run() {
		if (difficulty == "easy") {	extraLife = true; } else { extraLife = false; } // Easy-Mode hat ein standardmässiges extra Leben
		while(running) { // Auch wenn es nicht funktioniert stürtzt es nicht ab, es wird nur ein StackTrace geprintet
			try {
				if (bonusTimer == 0) { // Bonus wird neu gesetzt nachdem er abgelaufen ist.
					Snake.bonus.reset();
					Collision.activeBonus = "";
					if (difficulty == "normal" || difficulty == "hard") {extraLife = false; }
					bonusTimer = 40;
				}
				if (extraLife == true) { Draw.lblHeart.setVisible(true); } else { Draw.lblHeart.setVisible(false); } // Sichtbarkeit des Herzbildes
				if (Collision.activeBonus != "") { Draw.b.setVisible(true); } else { Draw.b.setVisible(false); } // Sichtbarkeit der ProgressBar
				// Tick Management, BonusTimer wird verringert
				if (Collision.activeBonus == "slowdown" && bonusTimer > 0) { // Geschwindigkeit mit Slowdown-Bonus
					sleep(230);
					bonusTimer--;
					Draw.b.setValue((int) (4 * bonusTimer)); // ProgressBar-Wert wird angepasst, wieviel Prozent der Zeit in der der Bonus anhält verbleibt noch?
				} else if (Collision.activeBonus == "speedup" && bonusTimer > 0){
					sleep(140);
					bonusTimer--;
					Draw.b.setValue((int) (1.666666 * bonusTimer));  // ProgressBar-Wert wird angepasst, wieviel Prozent der Zeit in der der Bonus anhält verbleibt noch?
				} else if (difficulty == "easy") { // Geschwingkeit im Easy-Mode
					sleep(215); // Schlange bleibt immer gleichschnell
				} else if (difficulty == "normal") { // Geschwingkeit im Normal-Mode
					if (Snake.score < 10) { // Schlange wird mit höherer Punktzahl schneller
						sleep(185);
					} else if (Snake.score < 15) {
						sleep(175);
					} else if (Snake.score < 20) {
						sleep(165);
					} else {
						sleep(155);
					}
				} else if (difficulty == "hard") { // Geschwingkeit im Hard-Mode
					if (Snake.score < 10) { // Schlange wird mit höherer Punktzahl schneller
						sleep(170);
					} else if (Snake.score < 15) {
						sleep(155);
					} else if (Snake.score < 20) {
						sleep(140);
					} else if (Snake.score < 25) {
						sleep(125);
					} else {
						sleep(110);
					}
				}
				
				if (Collision.activeBonus == "extraLife" || Collision.activeBonus == "double") {
					bonusTimer--;
					Draw.b.setValue((int) (2.5 * bonusTimer));  // ProgressBar-Wert wird angepasst, wieviel Prozent der Zeit in der der Bonus anhält verbleibt noch?
				}
	
				Snake.move(); // Schlange bewegt sich (letzte gedrückte Richtung)
				Snake.waitToMove = false;
				// Alle Kollisionen werden aufgerufen
				Collision.collidePickUp();
				Collision.collideSelf();
				Collision.collideWall();
				if (difficulty == "hard" || GameClock.difficulty == "normal" ) { // Im Easy-Mode gibt es keine Hindernisse (Obstacles)
					Collision.collideNormalObstacle();
					Collision.collideBlackObstacle();
				}
				if (boniOn == true) { // Muss nur aufgerufen werden, wenn Boni aktiviert sind
					Collision.collideBonus();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
