package clocks;

import java.io.IOException;


import actions.Collision;
import game.Obstacle;
import game.Snake;
import gui.Draw;
import gui.Gui;

public class GameClock extends Thread{
	public static boolean running = true;
	public static String difficulty;
	public static boolean extraLife = false;
	public static boolean boniOn = false;
	public static int bonusTimer = 40;
	
	public GameClock() {
		
	}
	
	public void run() {
		if (difficulty == "easy") {	extraLife = true; } else { extraLife = false; }
		while(running) { // Auch wenn es nicht funktioniert stürtzt es nicht ab, es wird nur ein StackTrace geprintet
			try {
				if (bonusTimer == 0) {
					Snake.bonus.reset();
					Collision.activeBonus = "";
					if (difficulty == "normal" || difficulty == "hard") {extraLife = false; }
					bonusTimer = 40;
				}
				if (extraLife == true) { Draw.lblHeart.setVisible(true); } else { Draw.lblHeart.setVisible(false); }
				// Tick Management
				if (Collision.activeBonus == "slowdown" && bonusTimer > 0) {
					sleep(275);
					bonusTimer -= 2;
				} else if (Collision.activeBonus == "speedup" && bonusTimer > 0){
					sleep(140);
					bonusTimer--;
				} else if (difficulty == "easy") {
					sleep(250);
				} else if (difficulty == "normal") {
					if (Snake.score < 10) { // Schlange wird mit höherer Punktzahl schneller
						sleep(185);
					} else if (Snake.score < 15) {
						sleep(175);
					} else if (Snake.score < 20) {
						sleep(165);
					} else {
						sleep(155);
					}
				} else if (difficulty == "hard") {
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
				
				if (Collision.activeBonus == "extraLife" || Collision.activeBonus == "speedup" || Collision.activeBonus == "teleporter") {
					bonusTimer--;
				}
				System.out.println(bonusTimer);
				Snake.move();
				Snake.waitToMove = false;
				Collision.collidePickUp();
				Collision.collideSelf();
				Collision.collideWall();
				if (difficulty == "hard" || GameClock.difficulty == "normal" ) {
					Collision.collideNormalObstacle();
					Collision.collideBlackObstacle();
				}
				if (boniOn == true) {
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
