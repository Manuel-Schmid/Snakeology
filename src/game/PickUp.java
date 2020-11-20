package game;

import java.util.concurrent.ThreadLocalRandom;

public class PickUp {
	int x;
	int y;
	
	public PickUp() {
		this.setX(ThreadLocalRandom.current().nextInt(0,15)); // Random Koordinaten zwischen 0 und 15 für Äpfel
		this.setY(ThreadLocalRandom.current().nextInt(0,15));
	}
	
	/*// Hier soll verhindert werden, dass ein Apfel in einem Tail spawnt
 * 		for(int i = 0; i < Snake.tails.size(); i++) { // Iterieren durch Tails
			if(Snake.head.getX() == Snake.tails.get(i).getX() && Snake.head.getY() == Snake.tails.get(i).getY() && !Snake.tails.get(i).isWait()) {
				return true;
			}
		}
		
		if(Snake.head.getX() == Snake.pickup.getX() && Snake.head.getY() == Snake.pickup.getY())
	 * */
	
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
