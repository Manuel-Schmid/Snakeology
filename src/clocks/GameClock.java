package clocks;

import java.io.IOException;

import javax.swing.JDialog;

import actions.Collision;
import game.Snake;
import gui.DeathScreen;

public class GameClock extends Thread{
	public static boolean running = true;
	
	public GameClock() {
		
	}
	
	public void run() {
		while(running) { // Auch wenn es nicht funktioniert stürtzt es nicht ab, es wird nur ein StackTrace geprintet
			try {
				if (Snake.score < 10) { // Schlange wird mit höherer Punktzahl schneller
					sleep(180);
				} else if (Snake.score < 25) {
					sleep(160);
				} else {
					sleep(140);
				}
				Snake.move();
				Snake.waitToMove = false;
				Collision.collidePickUp();
				Collision.collideSelf();
				Collision.collideWall();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
