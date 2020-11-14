package game;

public class Head {
	Dir dir = Dir.RIGHT;
	int x;
	int y;
	
	public Head(int x, int y) {
		this.setX(x);
		this.setY(y);
	}
	

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
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
