/**
 * @author Sven
 */

package game;

public class Tail { // erstellt ein neues Tail-Objekt
	
	// Koordinaten
	int x;
	int y;
	boolean wait = true; // Warten auf Bewegung
	
	public Tail(int x, int y) {
		this.setX(x);
		this.setY(y);
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

	public boolean isWait() {
		return wait;
	}

	public void setWait(boolean wait) {
		this.wait = wait;
	}	
	
	
}
