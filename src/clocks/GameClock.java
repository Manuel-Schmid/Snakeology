package clocks;

import java.io.IOException;


import actions.Collision;
import game.Obstacle;
import game.Snake;

public class GameClock extends Thread{
	public static boolean running = true;
	public static String difficulty;
	
	public GameClock() {
		
	}
	
	public void run() {
		while(running) { // Auch wenn es nicht funktioniert stürtzt es nicht ab, es wird nur ein StackTrace geprintet
			try {
				if (GameClock.difficulty == "easy") {
					sleep(210);
				}
				else if (GameClock.difficulty == "normal") {
					if (Snake.score < 10) { // Schlange wird mit höherer Punktzahl schneller
						sleep(185);
					} else if (Snake.score < 15) {
						sleep(175);
					} else if (Snake.score < 20) {
						sleep(165);
					} else {
						sleep(155);
					}
				}
				else if (GameClock.difficulty == "hard") {
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

				Snake.move();
				Snake.waitToMove = false;
				Collision.collidePickUp();
				Collision.collideSelf();
				Collision.collideWall();
				if (GameClock.difficulty == "hard" ) {
					Collision.collideNormalObstacle();
					Collision.collideBlackObstacle();
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
