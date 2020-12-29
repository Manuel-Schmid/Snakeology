package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import actions.Collision;
import clocks.GameClock;
import game.Bonus;
import game.Snake;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JProgressBar;

public class PauseScreen extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	/**
	 * Create the dialog.
	 */
	public PauseScreen() {
		setTitle("Snakeology - paused");

		Gui.jf.setEnabled(false);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
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
			public void actionPerformed(ActionEvent e) {
				Gui.jf.setEnabled(true);
				GameClock gc = new GameClock();
				gc.start();
				GameClock.running = true;
				Snake.move();
				Snake.waitToMove = false;
				dispose();
			}
		});
		
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gui.jf.dispose();
				dispose();
			}
		});
		
		// Close whole Game on Window Close
		this.addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent e) {
	    		Gui.jf.dispose();
	        }
	    });
	}
}
