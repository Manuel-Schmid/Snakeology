package actions;

import game.Snake;

public class Collision {
	public static boolean collideSelf() {
		for(int i = 0; i < Snake.tails.size(); i++) { // Iterieren durch Tails
			if(Snake.head.getX() == Snake.tails.get(i).getX() && Snake.head.getY() == Snake.tails.get(i).getY() && !Snake.tails.get(i).isWait()) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean collideWall() {	
		return (Snake.head.getX()<0 || Snake.head.getX() > 15 || Snake.head.getY()<0 || Snake.head.getY() > 15 ); // Wenn der Head oben, unten links oder rechts in die Border f�hrt
	}
	
	public static void collidePickUp() {
		if(Snake.head.getX() == Snake.pickup.getX() && Snake.head.getY() == Snake.pickup.getY()) {
			Snake.pickup.reset();
			Snake.addTail();
			// Score erh�hen
			Snake.score += 1; 
			 if (Snake.score > Snake.bestscore) {
				 Snake.bestscore = Snake.score;
			 }
			
			
		}
	}
}
