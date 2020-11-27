package clocks;

import java.io.IOException;

import javax.swing.JDialog;

import actions.Collision;
import game.Snake;
import gui.DeathScreen;

public class GameClock extends Thread{
	public static boolean running = true;
	
	public void run() {
		while(running) { // Auch wenn es nicht funktioniert stürtzt es nicht ab, es wird nur ein StackTrace geprintet
			try {
				if (Snake.score < 10) { // Schlange wird mit höherer Punktzahl schneller
					sleep(180);
				} else if (Snake.score < 25) {
					sleep(150);
				} else {
					sleep(120);
				}
				Snake.move();
				Snake.waitToMove = false;
				Collision.collidePickUp();
				if(Collision.collideSelf()) {
					Snake.tails.clear();
					Snake.head.setX(7);
					Snake.head.setY(7);
					// Score zurücksetzen
					Snake.score = 0;
				}
				if(Collision.collideWall()) {
					Snake.tails.clear();
					Snake.head.setX(7);
					Snake.head.setY(7);
					// Score zurücksetzen
					Snake.score = 0;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
