package actions;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JDialog;

import clocks.GameClock;
import game.Dir;
import game.Snake;
import gui.PauseScreen;

public class KeyHandler implements KeyListener{

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) { // Bewegungen der Schlange
		switch (e.getKeyCode()) {
		// WASD-Tasten
		case KeyEvent.VK_W: 
			if(!(Snake.head.getDir() == Dir.DOWN) && !Snake.waitToMove) { // verhindert Probleme durch schnelles Tastenhämmern (hoch-runter)
				Snake.head.setDir(Dir.UP);
				Snake.waitToMove = true;
			}
			break;
		case KeyEvent.VK_A: 
			if(!(Snake.head.getDir() == Dir.RIGHT) && !Snake.waitToMove) {
				Snake.head.setDir(Dir.LEFT);
				Snake.waitToMove = true;
			}
			break;
		case KeyEvent.VK_S: 
			if(!(Snake.head.getDir() == Dir.UP) && !Snake.waitToMove) { 
				Snake.head.setDir(Dir.DOWN);
				Snake.waitToMove = true;
			}
			break;
		case KeyEvent.VK_D: 
			if(!(Snake.head.getDir() == Dir.LEFT) && !Snake.waitToMove) {
				Snake.head.setDir(Dir.RIGHT);
				Snake.waitToMove = true;
			}
			break;
		// Pfeiltasten
		case KeyEvent.VK_UP: 
			if(!(Snake.head.getDir() == Dir.DOWN) && !Snake.waitToMove) { 
				Snake.head.setDir(Dir.UP);
				Snake.waitToMove = true;
			}
			break;
		case KeyEvent.VK_LEFT: 
			if(!(Snake.head.getDir() == Dir.RIGHT) && !Snake.waitToMove) {
				Snake.head.setDir(Dir.LEFT);
				Snake.waitToMove = true;
			}
			break;
		case KeyEvent.VK_DOWN: 
			if(!(Snake.head.getDir() == Dir.UP) && !Snake.waitToMove) { 
				Snake.head.setDir(Dir.DOWN);
				Snake.waitToMove = true;
			}
			break;
		case KeyEvent.VK_RIGHT: 
			if(!(Snake.head.getDir() == Dir.LEFT) && !Snake.waitToMove) {
				Snake.head.setDir(Dir.RIGHT);
				Snake.waitToMove = true;
			}
			break;
		// PauseScreen
		case KeyEvent.VK_ESCAPE: 
			if(GameClock.running == true) {
				GameClock.running = false;
				PauseScreen pauseS = new PauseScreen();
				pauseS.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				pauseS.setVisible(true);

			}
			break;
		case KeyEvent.VK_SPACE: 
			if(GameClock.running == true) {
				GameClock.running = false;
				PauseScreen pauseS = new PauseScreen();
				pauseS.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				pauseS.setVisible(true);

			}
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
}
