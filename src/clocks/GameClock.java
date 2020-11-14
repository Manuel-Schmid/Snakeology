package clocks;

import actions.Collision;
import game.Snake;

public class GameClock extends Thread{
	public static boolean running = true;
	
	public void run() {
		while(running) { // Auch wenn es nicht funktioniert st�rtzt es nicht ab, es wird nur ein StackTrace geprintet
			try {
				sleep(200);
				Snake.move();
				Snake.waitToMove = false;
				Collision.collidePickUp();
				if(Collision.collideSelf()) {
					Snake.tails.clear();
					// Score zur�cksetzen
					Snake.score = 0;
				}
				if(Collision.collideWall()) {
					Snake.tails.clear();
					Snake.head.setX(7);
					Snake.head.setY(7);
					// Score zur�cksetzen
					Snake.score = 0;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
