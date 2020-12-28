package game;

import java.util.concurrent.ThreadLocalRandom;

public class PickUp {
	int x;
	int y;
	public static boolean isGolden = false;
	
	public PickUp() {
		isGolden = false;
		this.setX(ThreadLocalRandom.current().nextInt(0,15)); // Random Koordinaten zwischen 0 und 15 für Äpfel
		this.setY(ThreadLocalRandom.current().nextInt(0,15));
		// Hier soll verhindert werden, dass ein Apfel in einem Tail spawnt
		for(int i = 0; i < Snake.tails.size(); i++) { // Iterieren durch Tails
			if(x == Snake.tails.get(i).getX() && y == Snake.tails.get(i).getY()) {
				this.reset();
			}
		}
		/*if(x == Snake.obstacle.getX() && y == Snake.obstacle.getY()) {
			this.reset();
		}
		if(x == Snake.bonus.getX() && y == Snake.bonus.getY()) {
			this.reset();
		}*/
	}
	
	public void reset() {
		// Golden PickUp
		isGolden = false;
		int randomNum = ThreadLocalRandom.current().nextInt(1, 10 + 1); // Zufallszahl von 1-10
		if (randomNum == 5) {
			isGolden = true;
		}
		// Normal Pickup
		this.setX(ThreadLocalRandom.current().nextInt(0,15));
		this.setY(ThreadLocalRandom.current().nextInt(0,15));
		// Hier soll verhindert werden, dass ein Apfel in einem Tail spawnt
		for(int i = 0; i < Snake.tails.size(); i++) { // Iterieren durch Tails
			if(x == Snake.tails.get(i).getX() && y == Snake.tails.get(i).getY()) {
				this.reset();
			}
			if(x == Snake.obstacle.getX() && y == Snake.obstacle.getY()) {
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
