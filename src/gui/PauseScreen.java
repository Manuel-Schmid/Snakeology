/**
 * @author Manuel
 */

package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clocks.GameClock;
import game.Snake;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;

public class PauseScreen extends JDialog { // Dieser JDialog mit der Pausierung des Spiels aufgerufen und kann das Spiel beenden, oder weiterfahren lassen
	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	
	public PauseScreen() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Snakeology - paused");

		Gui.jf.setEnabled(false);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		Image imgSnake = new ImageIcon(this.getClass().getResource("/snake.png")).getImage();
		setIconImage(imgSnake);
		
		JLabel lblGamePaused = new JLabel("Game paused");
		lblGamePaused.setHorizontalAlignment(SwingConstants.CENTER);
		lblGamePaused.setBounds(96, 97, 241, 40);
		lblGamePaused.setFont(new Font("Tahoma", Font.PLAIN, 33));
		contentPanel.add(lblGamePaused);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton btnResume = new JButton("Resume");
		
		btnResume.setActionCommand("OK");
		buttonPane.add(btnResume);
		getRootPane().setDefaultButton(btnResume);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setActionCommand("Cancel");
		buttonPane.add(btnQuit);
		
		btnResume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Hebt die Pausierung des Spiels auf und es geht weiter
				Gui.jf.setEnabled(true);
				GameClock gc = new GameClock();
				gc.start();
				GameClock.running = true;
				Snake.move();
				Snake.waitToMove = false;
				dispose(); // Schliesst den Pause-Screen
			}
		});
		
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Beendet das Spiel
				Gui.jf.dispose(); // Schliesst die Spieloberfläche
				dispose(); // Schliesst den Pause-Screen
			}
		});
		
		// Close whole Game on Window Close
		this.addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent e) {
	    		Gui.jf.dispose(); // Schliesst die Spieloberfläche bei Klick auf X
	        }
	    });
	}
}
