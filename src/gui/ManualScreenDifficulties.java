/**
 * @author Sven
 */

package gui;

import java.awt.BorderLayout;

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
import java.awt.Color;
import javax.swing.SwingConstants;

public class ManualScreenDifficulties extends JDialog { // Dieser JDialog ist die zweite von zwei Seiten der Bedienungsanleitung und dient nur zur Darstellung von Information, hat also keine mathematische Funktion
	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();

	public ManualScreenDifficulties() {
		
		if (LoginScreen.loginManual == false) {
			Gui.jf.setEnabled(false);
		} 
				
		setTitle("Snakeology - Manual");
		setBounds(100, 100, 663, 410);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
				
		JLabel lblManual = new JLabel("Manual");
		lblManual.setHorizontalAlignment(SwingConstants.LEFT);
		lblManual.setForeground(Color.BLACK);
		lblManual.setFont(new Font("Arial", Font.PLAIN, 25));
		lblManual.setBounds(23, 11, 126, 40);
		contentPanel.add(lblManual);
		
		JLabel lblDifficulties = new JLabel("Difficulties");
		lblDifficulties.setHorizontalAlignment(SwingConstants.LEFT);
		lblDifficulties.setForeground(Color.BLACK);
		lblDifficulties.setFont(new Font("Arial", Font.PLAIN, 18));
		lblDifficulties.setBounds(23, 62, 112, 22);
		contentPanel.add(lblDifficulties);
		
		JLabel lblNewLabel = new JLabel("Easy");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setBounds(23, 98, 232, 23);
		contentPanel.add(lblNewLabel);
		
		JLabel lblYouHave = new JLabel("- Slow game speed");
		lblYouHave.setFont(new Font("Arial", Font.PLAIN, 13));
		lblYouHave.setBounds(33, 124, 250, 23);
		contentPanel.add(lblYouHave);
		
		JLabel lblYouHave_1 = new JLabel("- You have an extra life from the start");
		lblYouHave_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblYouHave_1.setBounds(33, 146, 238, 23);
		contentPanel.add(lblYouHave_1);
		
		JLabel lblYouCan = new JLabel("- You can go through walls");
		lblYouCan.setFont(new Font("Arial", Font.PLAIN, 13));
		lblYouCan.setBounds(33, 166, 238, 23);
		contentPanel.add(lblYouCan);
		
		JLabel lblNormal = new JLabel("Normal");
		lblNormal.setFont(new Font("Arial", Font.BOLD, 14));
		lblNormal.setBounds(23, 223, 232, 23);
		contentPanel.add(lblNormal);
		
		JLabel lblGameSpeed = new JLabel("-  Medium game speed (increasing)");
		lblGameSpeed.setFont(new Font("Arial", Font.PLAIN, 13));
		lblGameSpeed.setBounds(33, 250, 250, 23);
		contentPanel.add(lblGameSpeed);
		
		JLabel lblThereAre = new JLabel("- There are grey obstacles which");
		lblThereAre.setFont(new Font("Arial", Font.PLAIN, 13));
		lblThereAre.setBounds(33, 272, 238, 23);
		contentPanel.add(lblThereAre);
		
		JLabel label_3 = new JLabel("- You can go through Walls");
		label_3.setFont(new Font("Arial", Font.PLAIN, 13));
		label_3.setBounds(33, 306, 238, 23);
		contentPanel.add(label_3);
		
		JLabel lblRemoveOneTail = new JLabel("  remove one tail on collision");
		lblRemoveOneTail.setFont(new Font("Arial", Font.PLAIN, 13));
		lblRemoveOneTail.setBounds(33, 286, 238, 23);
		contentPanel.add(lblRemoveOneTail);
		
		JLabel lblHard = new JLabel("Hard");
		lblHard.setFont(new Font("Arial", Font.BOLD, 14));
		lblHard.setBounds(349, 98, 232, 23);
		contentPanel.add(lblHard);
		
		JLabel lblFastGame = new JLabel("- Fast game speed (increasing)");
		lblFastGame.setFont(new Font("Arial", Font.PLAIN, 13));
		lblFastGame.setBounds(359, 124, 250, 23);
		contentPanel.add(lblFastGame);
		
		JLabel lblThereAre_1 = new JLabel("- There are black obstacles that");
		lblThereAre_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblThereAre_1.setBounds(359, 146, 238, 23);
		contentPanel.add(lblThereAre_1);
		
		JLabel lblYouCant = new JLabel("- You can't go through Walls");
		lblYouCant.setFont(new Font("Arial", Font.PLAIN, 13));
		lblYouCant.setBounds(359, 180, 238, 23);
		contentPanel.add(lblYouCant);
		
		JLabel lblKillYouOn = new JLabel("  kill you on collision");
		lblKillYouOn.setFont(new Font("Arial", Font.PLAIN, 13));
		lblKillYouOn.setBounds(359, 162, 238, 23);
		contentPanel.add(lblKillYouOn);
		
		JButton btnBack = new JButton("Back (2/2)");

		btnBack.setActionCommand("OK");
		btnBack.setBounds(383, 331, 112, 31);
		contentPanel.add(btnBack);
		
		JButton btnClose = new JButton("Close Manual");
		btnClose.setActionCommand("OK");
		btnClose.setBounds(505, 331, 134, 31);
		contentPanel.add(btnClose);
		
		JButton btnContinue = new JButton("Continue Game");
		btnContinue.setActionCommand("OK");
		btnContinue.setBounds(505, 331, 134, 31);
		contentPanel.add(btnContinue);
		
		if (LoginScreen.loginManual) { // Wenn die Anleitung vom Login-Screen aus gestartet wurde wird ein anderer Button angezeigt, als wenn es mitten im Spiel geöffnet wurde
			btnClose.setVisible(true);
			btnContinue.setVisible(false);
		} else {
			btnClose.setVisible(false);
			btnContinue.setVisible(true);
		}
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Leitet wieder zurück auf Seite eins der Bedienungsanleitung
				ManualScreenBasics manualBS = new ManualScreenBasics();
				manualBS.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				manualBS.setVisible(true);
				dispose();
			}
		});
		
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Hebt die Pausierung des Spiels auf und es geht weiter
				Gui.jf.setEnabled(true);
				GameClock gc = new GameClock();
				gc.start();
				GameClock.running = true;
				Snake.move();
				Snake.waitToMove = false;
				dispose(); // Schliesst die Anleitung
			}
		});
		
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginScreen.loginManual = false;
				dispose(); // Schliesst die Anleitung
			}
		});
		
		// Close whole Game on Window Close
		this.addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent e) {
	    		if (LoginScreen.loginManual == false) {
		    		Gui.jf.dispose();  // Schliesst das ganze Spiel falls es bereits offen ist
	    		}
				LoginScreen.loginManual = false;
	        }
	    });
	}
}
