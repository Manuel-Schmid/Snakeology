package game;

import java.util.concurrent.ThreadLocalRandom;

public class PickUp {
	int x;
	int y;
	
	public PickUp() {
		this.setX(ThreadLocalRandom.current().nextInt(0,15)); // Random Koordinaten zwischen 0 und 15 für Äpfel
		this.setY(ThreadLocalRandom.current().nextInt(0,15));
	}
	
	public void reset() {
		this.setX(ThreadLocalRandom.current().nextInt(0,15));
		this.setY(ThreadLocalRandom.current().nextInt(0,15));
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
