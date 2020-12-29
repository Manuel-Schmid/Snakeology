package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import actions.Main;
import clocks.GameClock;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;

public class LoginScreen extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JTextField tfUsername;
		
	/**
	 * Create the dialog.
	 * @throws IOException 
	 */
	public LoginScreen() throws IOException {
		setTitle("Snakeology - Login");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		tfUsername = new JTextField();
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
						
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		
		// Create and Fill User File
		FileWriter fwCrntUser = new FileWriter("currentUser.txt", false);
		BufferedWriter userWriter = new BufferedWriter(fwCrntUser);
		// Create Other Files
		FileWriter fwRecord = new FileWriter("record.txt", true);
		BufferedWriter recordWriter = new BufferedWriter(fwRecord);
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tfUsername.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Please enter a valid Username.","invalid username",JOptionPane.WARNING_MESSAGE);
					tfUsername.setText("");
				} else {
					if (GameClock.difficulty != "easy" && GameClock.difficulty != "normal" && GameClock.difficulty != "hard") {
						GameClock.difficulty = "normal";
					}
					try {
						// Username in BufferedReader speichern
						userWriter.write(tfUsername.getText());
						userWriter.flush(); // daten übertragen
						tfUsername.setText("");
					} catch (IOException e) {
						// TODO Auto-generated catch block
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
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		
		btnEasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameClock.difficulty = "easy";
				btnEasy.setEnabled(false);
				btnNormal.setEnabled(true);
				btnHard.setEnabled(true);
			}
		});
		
		btnNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameClock.difficulty = "normal";
				btnEasy.setEnabled(true);
				btnNormal.setEnabled(false);
				btnHard.setEnabled(true);
			}
		});
		
		btnHard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GameClock.difficulty = "hard";
				btnEasy.setEnabled(true);
				btnNormal.setEnabled(true);
				btnHard.setEnabled(false);
			}
		});
	}
}
