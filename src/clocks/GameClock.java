package clocks;

public class GameClock extends Thread{
	public static boolean running = true;
	
	public void run() {
		while(running) { // Auch wenn es nicht funktioniert stürtzt es nicht ab, es wird nur ein StackTrace geprintet
			try {
				sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
