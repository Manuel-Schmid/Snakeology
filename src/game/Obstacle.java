package game;

import java.util.concurrent.ThreadLocalRandom;

public class Obstacle {
	int x;
	int y;
	public static boolean isBlack = false;
	
	public Obstacle() {
		isBlack = false;
		this.setX(ThreadLocalRandom.current().nextInt(0,15)); // Random Koordinaten zwischen 0 und 15 für Hindernisse
		this.setY(ThreadLocalRandom.current().nextInt(0,15));
		// Hier soll verhindert werden, dass ein Obstacle in einem Tail spawnt
		for(int i = 0; i < Snake.tails.size(); i++) { // Iterieren durch Tails
			if(x == Snake.tails.get(i).getX() && y == Snake.tails.get(i).getY()) {
				this.reset();
			}
		}
	}
	
	public void reset() {
		// Black Obstacle
		isBlack = false;
		int randomNum = ThreadLocalRandom.current().nextInt(1, 10 + 1); // Zufallszahl von 1-10
		if (randomNum == 5) {
			isBlack = true;
		}
		// Normal Obstacle
		this.setX(ThreadLocalRandom.current().nextInt(0,15));
		this.setY(ThreadLocalRandom.current().nextInt(0,15));
		// Hier soll verhindert werden, dass ein Obstacle in einem Tail spawnt
		for(int i = 0; i < Snake.tails.size(); i++) { // Iterieren durch Tails
			if(x == Snake.tails.get(i).getX() && y == Snake.tails.get(i).getY()) {
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
