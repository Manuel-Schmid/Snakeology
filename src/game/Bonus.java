package game;

import java.util.concurrent.ThreadLocalRandom;

public class Bonus {
	int x;
	int y;
	public static String bonus = ""; // (slowdown, extraLife, speedup, double)
	
	public Bonus() {
		this.setX(ThreadLocalRandom.current().nextInt(0,15)); // Random Koordinaten zwischen 0 und 15 für Boni
		this.setY(ThreadLocalRandom.current().nextInt(0,15));
		// Hier soll verhindert werden, dass ein Bonus in einem Tail spawnt
		for(int i = 0; i < Snake.tails.size(); i++) { // Iterieren durch Tails
			if(x == Snake.tails.get(i).getX() && y == Snake.tails.get(i).getY()) {
				this.reset();
			}
			if(x == Snake.pickup.getX() && y == Snake.pickup.getY()) {
				this.reset();
			}
			if(x == Snake.obstacle.getX() && y == Snake.obstacle.getY()) {
				this.reset();
			}
		}
		int randomNum = ThreadLocalRandom.current().nextInt(1, 12 + 1); // Zufallszahl von 1-10
		if (randomNum <= 3) {
			bonus = "slowdown";
		} else if (randomNum <= 6) {
			bonus = "extraLife";
		} else if (randomNum <= 9) {
			bonus = "speedup";
		} else if (randomNum <= 12) {
			bonus = "double";
		}
	}
	
	public void reset() {
		this.setX(ThreadLocalRandom.current().nextInt(0,15)); // Random Koordinaten zwischen 0 und 15 für Boni
		this.setY(ThreadLocalRandom.current().nextInt(0,15));
		// Hier soll verhindert werden, dass ein Bonus in einem Tail spawnt
		for(int i = 0; i < Snake.tails.size(); i++) { // Iterieren durch Tails
			if(x == Snake.tails.get(i).getX() && y == Snake.tails.get(i).getY()) {
				this.reset();
			}
			if(x == Snake.pickup.getX() && y == Snake.pickup.getY()) {
				this.reset();
			}
			if(x == Snake.obstacle.getX() && y == Snake.obstacle.getY()) {
				this.reset();
			}
		}
		int randomNum = ThreadLocalRandom.current().nextInt(1, 12 + 1); // Zufallszahl von 1-10
		if (randomNum <= 3) {
			bonus = "slowdown";
		} else if (randomNum <= 6) {
			bonus = "extraLife";
		} else if (randomNum <= 9) {
			bonus = "speedup";
		} else if (randomNum <= 12) {
			bonus = "double";
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
