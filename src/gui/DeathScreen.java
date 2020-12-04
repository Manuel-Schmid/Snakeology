package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DeathScreen extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private String pfad = "C:" + File.separator + "Users" + File.separator + "Many" + File.separator + "eclipse-workspace" + File.separator + "Snakeology" + File.separator + "data" + File.separator;
	private String dateiname = "topUsers.txt";
	private JTextField tfUsername;
	
	/**
	 * Create the dialog.
	 * @throws IOException 
	 */
	public DeathScreen() throws IOException {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("YOU DIED");
		lblNewLabel.setBounds(89, 65, 109, 38);
		contentPanel.add(lblNewLabel);
		
		tfUsername = new JTextField();
		tfUsername.setEnabled(false);
		tfUsername.setBounds(88, 119, 146, 26);
		contentPanel.add(tfUsername);
		tfUsername.setColumns(10);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		FileWriter fw = new FileWriter(pfad+dateiname, false);
		BufferedWriter dateiAusgabe = new BufferedWriter(fw);
		
		try {
			// Vor & Nachname in BufferedReader speichern
			dateiAusgabe.write(tfUsername.getText());
			dateiAusgabe.write(";");
			// dateiAusgabe.write(tfNachname.getText()); HIER MUSS DER SCORE GESPEICHERT WERDEN
			dateiAusgabe.newLine();
			dateiAusgabe.flush(); // daten übertragen
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JButton btnAgain = new JButton("Play Again");
		btnAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAgain.setActionCommand("OK");
		buttonPane.add(btnAgain);
		getRootPane().setDefaultButton(btnAgain);
	
	
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnQuit.setActionCommand("Cancel");
		buttonPane.add(btnQuit);
			
		
	}
}
