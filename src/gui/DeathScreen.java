package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import actions.Collision;
import clocks.GameClock;
import game.Obstacle;
import game.PickUp;
import game.Snake;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JProgressBar;
import javax.swing.border.MatteBorder;

public class DeathScreen extends JDialog { // JDialog

	private static final long serialVersionUID = -1567277742685379400L;

	private final JPanel contentPanel = new JPanel();
	
	private String crntUser;
	private String recScore;
	private String record;
	public boolean isNewRecord = false;
	String splitter = ";";
		
	/**
	 * Create the dialog.
	 * @throws IOException 
	 */
	public DeathScreen() throws IOException {
		setTitle("Snakeology - You died");
				
		Gui.jf.setEnabled(false);
				
		// get Current User
		try {
			BufferedReader crntUserReader = new BufferedReader(new FileReader("currentUser.txt"));
			crntUser = "";
			String username = crntUserReader.readLine();
			while(username != null) { // lesen bis keine Zeile mehr
				crntUser = username;
				username = crntUserReader.readLine();
			} 
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// New Record Handling
		try {
			BufferedReader recReader = new BufferedReader(new FileReader("record.txt"));
			record = "";
			String rec = recReader.readLine();
			while (rec != null) {
				record = rec;
				rec = recReader.readLine();
			}
			if(record == "") {
				record = " " + splitter + 0 + splitter + " ";
			}
			String[] recordArr = record.split(splitter);
			int recordScore = Integer.parseInt(recordArr[1]);
			
			if (Snake.score > recordScore) {
				isNewRecord = true;
				// Record Writer
				String myScore = Integer.toString(Snake.score);
				FileWriter fwRecord = new FileWriter("record.txt", false);
				BufferedWriter recordWriter = new BufferedWriter(fwRecord);
				try {
					recordWriter.write("");
					recordWriter.flush();
					recordWriter.write(crntUser + splitter + myScore + splitter + GameClock.difficulty);
					recordWriter.flush(); // daten �bertragen
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				isNewRecord = true;
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("YOU DIED");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 33));
		lblNewLabel.setBounds(133, 41, 162, 38);
		contentPanel.add(lblNewLabel);
		
		JLabel lblUser = new JLabel("User: " + crntUser);
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setFont(new Font("Arial", Font.PLAIN, 18));
		lblUser.setBounds(143, 116, 133, 38);
		lblUser.setForeground(Color.BLACK);
		contentPanel.add(lblUser);
		
		int usrScore = Snake.score;
		JLabel label = new JLabel("Score: " + usrScore);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Arial", Font.PLAIN, 15));
		label.setBounds(143, 138, 133, 38);
		contentPanel.add(label);
		
		JButton btnHard = new JButton("Hard");

		btnHard.setBounds(266, 187, 89, 23);
		contentPanel.add(btnHard);
		
		JButton btnNormal = new JButton("Normal");

		btnNormal.setBounds(167, 187, 89, 23);
		contentPanel.add(btnNormal);
		
		JButton btnEasy = new JButton("Easy");

		btnEasy.setBounds(68, 187, 89, 23);
		contentPanel.add(btnEasy);
		
		JCheckBox checkBoniOn = new JCheckBox("Boni");
		checkBoniOn.setBounds(296, 147, 59, 23);
		contentPanel.add(checkBoniOn);
		
		if (GameClock.boniOn) {
			checkBoniOn.setSelected(true);
		} else { 
			checkBoniOn.setSelected(false); 
		}
		
		if (isNewRecord) {
			JLabel lblNewRecord = new JLabel("NEW RECORD !!!");
			lblNewRecord.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblNewRecord.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewRecord.setBounds(112, 83, 195, 38);
			contentPanel.add(lblNewRecord);
		}

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton btnAgain = new JButton("Play Again");

		btnAgain.setActionCommand("OK");
		buttonPane.add(btnAgain);
		getRootPane().setDefaultButton(btnAgain);
		
		JButton btnQuit = new JButton("Quit");
		
		btnQuit.setActionCommand("Cancel");
		buttonPane.add(btnQuit);
		
		btnAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Score zur�cksetzen
				Snake.score = 0;
				Gui g = new Gui();
				GameClock gc = new GameClock();
				g.create();
				gc.start(); // Methode aus Thread
				Snake.pickup.reset();
				Snake.obstacle.reset();
				GameClock.running = true;
				if (checkBoniOn.isSelected()) {
					GameClock.boniOn = true;
					Collision.activeBonus = "";
					Snake.bonus.reset();
				} else {GameClock.boniOn = false;}
				dispose();
			}
		});
		
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gui.jf.dispose();
				dispose();
			}
		});
		
		if (GameClock.difficulty == "easy") {
			btnEasy.setEnabled(false);
			btnNormal.setEnabled(true);
			btnHard.setEnabled(true);
		} else if (GameClock.difficulty == "normal") {
			btnEasy.setEnabled(true);
			btnNormal.setEnabled(false);
			btnHard.setEnabled(true);
		} else if (GameClock.difficulty == "hard") {
			btnEasy.setEnabled(true);
			btnNormal.setEnabled(true);
			btnHard.setEnabled(false);
		}
		
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
		
		// Close whole Game on Window Close
		this.addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent e) {
	    		Gui.jf.dispose();
	        }
	    });
	}
}
