package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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

public class LoginScreen extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JTextField tfUsername;
		
	/**
	 * Create the dialog.
	 * @throws IOException 
	 */
	public LoginScreen() throws IOException {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(139, 99, 146, 26);
		contentPanel.add(tfUsername);
		tfUsername.setColumns(10);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		
		// Create and Fill User File
		FileWriter fw = new FileWriter("currentUser.txt", false);
		BufferedWriter userWriter = new BufferedWriter(fw);
		// Create Other Files
		FileWriter fw1 = new FileWriter("recordUser.txt", true);
		BufferedWriter fileWriter1 = new BufferedWriter(fw1);
		FileWriter fw2 = new FileWriter("recordUserScore.txt", true);
		BufferedWriter fileWriter2 = new BufferedWriter(fw2);
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
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
				dispose();
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
	
	}
}
