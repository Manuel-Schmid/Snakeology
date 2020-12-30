/**
 * @author Manuel
 */

package game;

import java.util.concurrent.ThreadLocalRandom;

import clocks.GameClock;

public class Obstacle { // Diese Klasse erstellt ein Hindernis-Objekt
	int x;
	int y;
	public static boolean isBlack = false; // Hindernisse für den Hard-Mode
	
	public Obstacle() { // Hindernisse im Hardmode sind schwarz
		if (GameClock.difficulty == "hard") {
			isBlack = true;
		} else {
			isBlack = false;
		}
		this.setX(ThreadLocalRandom.current().nextInt(0,15)); // Random Koordinaten zwischen 0 und 15 für Hindernisse für X und Y Achse
		this.setY(ThreadLocalRandom.current().nextInt(0,15));
		// Hier soll verhindert werden, dass ein Obstacle in einem Tail oder PickUp spawnt
		for(int i = 0; i < Snake.tails.size(); i++) { // Iterieren durch Tails
			if(x == Snake.tails.get(i).getX() && y == Snake.tails.get(i).getY()) {
				this.reset();
			}
			if(x == Snake.pickup.getX() && y == Snake.pickup.getY()) {
				this.reset();
			}
		}
	}
	
	public void reset() {
		if (GameClock.difficulty == "hard") {  // Hindernisse im Hardmode sind schwarz
			isBlack = true;
		} else {
			isBlack = false;
		}
		this.setX(ThreadLocalRandom.current().nextInt(0,15));
		this.setY(ThreadLocalRandom.current().nextInt(0,15));
		// Hier soll verhindert werden, dass ein Obstacle in einem Tail, PickUp oder Bonus spawnt
		for(int i = 0; i < Snake.tails.size(); i++) { // Iterieren durch Tails
			if(x == Snake.tails.get(i).getX() && y == Snake.tails.get(i).getY()) {
				this.reset();
			}
			if(x == Snake.pickup.getX() && y == Snake.pickup.getY()) {
				this.reset();
			}
			if(x == Snake.bonus.getX() && y == Snake.bonus.getY()) {
				this.reset();
			}
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
