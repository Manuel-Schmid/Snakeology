package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ManualScreenBasics extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public ManualScreenBasics() {
		
		Gui.jf.setEnabled(false);
		
		setTitle("Snakeology - Manual");
		setBounds(100, 100, 663, 410);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton btnNext = new JButton("Next (1/2)");

		btnNext.setActionCommand("OK");
		btnNext.setBounds(527, 331, 112, 31);
		contentPanel.add(btnNext);
		
		JLabel lblManual = new JLabel("Manual");
		lblManual.setHorizontalAlignment(SwingConstants.LEFT);
		lblManual.setForeground(Color.BLACK);
		lblManual.setFont(new Font("Arial", Font.PLAIN, 25));
		lblManual.setBounds(23, 11, 126, 40);
		contentPanel.add(lblManual);
		
		JLabel lblBasics = new JLabel("Basics");
		lblBasics.setHorizontalAlignment(SwingConstants.LEFT);
		lblBasics.setForeground(Color.BLACK);
		lblBasics.setFont(new Font("Arial", Font.PLAIN, 18));
		lblBasics.setBounds(23, 62, 112, 22);
		contentPanel.add(lblBasics);
		
		JLabel lblNewLabel = new JLabel("PickUps");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setBounds(23, 98, 232, 23);
		contentPanel.add(lblNewLabel);
		
		JLabel lblYouHave = new JLabel("- Red PickUps add one tail to the snake");
		lblYouHave.setFont(new Font("Arial", Font.PLAIN, 13));
		lblYouHave.setBounds(33, 124, 276, 23);
		contentPanel.add(lblYouHave);
		
		JLabel lblYouHave_1 = new JLabel("- Golden PickUps add three tails to the snake");
		lblYouHave_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblYouHave_1.setBounds(33, 146, 276, 23);
		contentPanel.add(lblYouHave_1);
		
		JLabel lblNormal = new JLabel("Score");
		lblNormal.setFont(new Font("Arial", Font.BOLD, 14));
		lblNormal.setBounds(23, 222, 232, 23);
		contentPanel.add(lblNormal);
		
		JLabel lblGameSpeed = new JLabel("- The Score is always the same as the tail length");
		lblGameSpeed.setFont(new Font("Arial", Font.PLAIN, 13));
		lblGameSpeed.setBounds(33, 246, 285, 23);
		contentPanel.add(lblGameSpeed);
		
		JLabel lblThereAre = new JLabel("- In the top left corner is your current Score");
		lblThereAre.setFont(new Font("Arial", Font.PLAIN, 13));
		lblThereAre.setBounds(33, 268, 276, 23);
		contentPanel.add(lblThereAre);
		
		JLabel lblHard = new JLabel("Boni");
		lblHard.setFont(new Font("Arial", Font.BOLD, 14));
		lblHard.setBounds(349, 98, 232, 23);
		contentPanel.add(lblHard);
		
		JLabel lblFastGame = new JLabel("- You can enable or disable Boni");
		lblFastGame.setFont(new Font("Arial", Font.PLAIN, 13));
		lblFastGame.setBounds(359, 124, 250, 23);
		contentPanel.add(lblFastGame);
		
		JLabel lblThereAre_1 = new JLabel("- If a Bonus is active you can see ");
		lblThereAre_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblThereAre_1.setBounds(359, 146, 238, 23);
		contentPanel.add(lblThereAre_1);
		
		JLabel lblKillYouOn = new JLabel("  how long it will last");
		lblKillYouOn.setFont(new Font("Arial", Font.PLAIN, 13));
		lblKillYouOn.setBounds(359, 160, 238, 23);
		contentPanel.add(lblKillYouOn);
		
		JLabel lblPickupsAnd = new JLabel("- PickUps and obstacles get reset after ");
		lblPickupsAnd.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPickupsAnd.setBounds(33, 168, 276, 23);
		contentPanel.add(lblPickupsAnd);
		
		JLabel lblEveryFivePoints = new JLabel("  every five Points");
		lblEveryFivePoints.setFont(new Font("Arial", Font.PLAIN, 13));
		lblEveryFivePoints.setBounds(33, 184, 238, 23);
		contentPanel.add(lblEveryFivePoints);
		
		JLabel lblInThe = new JLabel("- In the top right corner is the highest Score you have reached");
		lblInThe.setFont(new Font("Arial", Font.PLAIN, 13));
		lblInThe.setBounds(33, 290, 361, 23);
		contentPanel.add(lblInThe);
		
		JLabel lblIfYour = new JLabel("- If your Score is higher than the current Record it will be saved ");
		lblIfYour.setFont(new Font("Arial", Font.PLAIN, 13));
		lblIfYour.setBounds(33, 311, 373, 23);
		contentPanel.add(lblIfYour);
		
		JLabel lblOnTheComputer = new JLabel("  on the Computer");
		lblOnTheComputer.setFont(new Font("Arial", Font.PLAIN, 13));
		lblOnTheComputer.setBounds(33, 327, 452, 23);
		contentPanel.add(lblOnTheComputer);
		
		JLabel lblOnDifficulty = new JLabel("- On difficulty easy the extraLife-Bonus");
		lblOnDifficulty.setFont(new Font("Arial", Font.PLAIN, 13));
		lblOnDifficulty.setBounds(359, 180, 238, 23);
		contentPanel.add(lblOnDifficulty);
		
		JLabel lblRestoresYourExtralife = new JLabel("  restores your extraLife if you lost it before");
		lblRestoresYourExtralife.setFont(new Font("Arial", Font.PLAIN, 13));
		lblRestoresYourExtralife.setBounds(359, 196, 260, 23);
		contentPanel.add(lblRestoresYourExtralife);
		setLocationRelativeTo(null);
		
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManualScreenDifficulties manualDS = new ManualScreenDifficulties();
				manualDS.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				manualDS.setVisible(true);
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
