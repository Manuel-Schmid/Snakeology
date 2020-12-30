/**
 * @author Manuel
 */

package actions;

import java.io.IOException;

import javax.swing.JDialog;

import gui.LoginScreen;

public class Main {

	public static void main(String[] args) throws IOException {
		// Beim Start des Programms wird der LoginScreen angezeigt.
		LoginScreen loginS = new LoginScreen();
		loginS.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		loginS.setVisible(true);
		
	}
	
}
