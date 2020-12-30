/**
 * @author Manuel & Sven
 */

package gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clocks.GameClock;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;

public class LoginScreen extends JDialog { // Dieser JDialog ermöglicht die Auswahl einer Schwierigkeitsstufe, die Aktivierung von Boni und die Eingabe eines Benutzernamens
	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField tfUsername; // textField für die Eingabe des Usernames
	public static boolean loginManual = false; // Wurde das Manual über den Login-Screen gestartet?
		
	@SuppressWarnings({ "resource", "unused" })
	public LoginScreen() throws IOException {
		setTitle("Snakeology - Login");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		tfUsername = new JTextField(); // In dieses Feld kann der Benutzername eingetragen werden
		tfUsername.setBounds(108, 130, 151, 26);
		contentPanel.add(tfUsername);
		tfUsername.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setBounds(108, 115, 72, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Difficulty");
		lblNewLabel_1.setBounds(108, 46, 72, 14);
		contentPanel.add(lblNewLabel_1);
		
		JButton btnEasy = new JButton("easy");

		btnEasy.setBounds(108, 71, 65, 23);
		contentPanel.add(btnEasy);
		
		JButton btnNormal = new JButton("normal");

		btnNormal.setBounds(176, 71, 83, 23);
		contentPanel.add(btnNormal);
		
		JButton btnHard = new JButton("hard");

		btnHard.setBounds(262, 71, 65, 23);
		contentPanel.add(btnHard);
		
		JCheckBox checkBoniOn = new JCheckBox("Boni");
		checkBoniOn.setBounds(268, 132, 59, 23);
		contentPanel.add(checkBoniOn);
		
		JButton btnManual = new JButton("Manual");

		btnManual.setBounds(8, 231, 78, 23);
		contentPanel.add(btnManual);
		
		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		okButton.setBounds(292, 231, 53, 23);
		contentPanel.add(okButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		cancelButton.setBounds(350, 231, 77, 23);
		contentPanel.add(cancelButton);
		
		// Erstellt, falls nicht schon vorhanden, eine neue Textdatei "currentUser"
		FileWriter fwCrntUser = new FileWriter("currentUser.txt", false);
		BufferedWriter userWriter = new BufferedWriter(fwCrntUser);
		// Erstellt, falls nicht schon vorhanden, eine neue Textdatei "record"
		FileWriter fwRecord = new FileWriter("record.txt", true);
		BufferedWriter recordWriter = new BufferedWriter(fwRecord);
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tfUsername.getText().isEmpty()) { // Wenn kein Benutzername eingegeben wird, erscheint eine Fehlermeldung
					JOptionPane.showMessageDialog(null,"Please enter a valid Username.","Invalid username",JOptionPane.WARNING_MESSAGE);
					tfUsername.setText("");
				} else { // Standardschwierigkeitsstufe ist "normal", wenn keine ausgewählt wurde
					if (GameClock.difficulty != "easy" && GameClock.difficulty != "normal" && GameClock.difficulty != "hard") {
						GameClock.difficulty = "normal";
					}
					try {
						// Username in BufferedReader speichern
						userWriter.write(tfUsername.getText());
						userWriter.flush(); // daten übertragen
						tfUsername.setText("");
					} catch (IOException e) {
						e.printStackTrace();
					}
					Gui g = new Gui();
					GameClock gc = new GameClock();
					g.create();
					gc.start(); // Methode aus Thread
					if (checkBoniOn.isSelected()) {
						GameClock.boniOn = true;
					} else {GameClock.boniOn = false;}
					dispose();
				}
			}
		});
		
		okButton.setActionCommand("OK");
		getRootPane().setDefaultButton(okButton);
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // Schliest das Fenster
			}
		});
		
		cancelButton.setActionCommand("Cancel");
		
		btnManual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Öffnet die Bedienungsanleitung
				loginManual = true;
				ManualScreenBasics ManualBS = new ManualScreenBasics();
				ManualBS.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				ManualBS.setVisible(true);
			}
		});
		
		btnEasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Toggle Button easy
				GameClock.difficulty = "easy";
				btnEasy.setEnabled(false);
				btnNormal.setEnabled(true);
				btnHard.setEnabled(true);
			}
		});
		
		btnNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Toggle Button normal
				GameClock.difficulty = "normal";
				btnEasy.setEnabled(true);
				btnNormal.setEnabled(false);
				btnHard.setEnabled(true);
			}
		});
		
		btnHard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // Toggle Button hard
				GameClock.difficulty = "hard";
				btnEasy.setEnabled(true);
				btnNormal.setEnabled(true);
				btnHard.setEnabled(false);
			}
		});
	}
}
