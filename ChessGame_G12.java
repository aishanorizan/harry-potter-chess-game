import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChessGame_G12 extends JFrame
{
	 ImageIcon Background;
	 ImageIcon welcomePage;
	public ChessGame_G12() {
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("From G2_12");
		lblNewLabel_1.setForeground(Color.PINK);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(243, 97, 173, 29);
		getContentPane().add(lblNewLabel_1);
		

		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.setBounds(108, 347, 136, 23);
		getContentPane().add(btnStartGame);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(417, 347, 89, 23);
		getContentPane().add(btnExit);
		
		
		JLabel lblHarryPotterChess = new JLabel("Harry Potter Chess");
		lblHarryPotterChess.setFont(new Font("Viner Hand ITC", Font.PLAIN, 36));
		lblHarryPotterChess.setForeground(Color.RED);
		lblHarryPotterChess.setBounds(151, 25, 384, 61);
		getContentPane().add(lblHarryPotterChess);
		
		

		
		

	} 
	
	
	
	public static void main(String[] args)
	{
		
		JFrame frame = new JFrame("ChessGame_G12");
		frame.setSize(636, 436);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
	
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		
		MainPage(panel);
		
		frame.setVisible(true);
	}
	
	private static void MainPage(JPanel panel)
	{
		
		panel.setLayout(null);


		JLabel lblNewLabel_1 = new JLabel("From G2_12");
		lblNewLabel_1.setForeground(Color.PINK);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(232, 97, 173, 29);
		panel.add(lblNewLabel_1);
		
		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.setBounds(108, 347, 100, 23);
		panel.add(btnStartGame);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(417, 347, 89, 23);
		panel.add(btnExit);
		
		

		JLabel lblHarryPotterChess = new JLabel("Harry Potter Chess");
		lblHarryPotterChess.setFont(new Font("Viner Hand ITC", Font.PLAIN, 36));
		lblHarryPotterChess.setForeground(Color.RED);
		lblHarryPotterChess.setBounds(151, 25, 384, 61);
		panel.add(lblHarryPotterChess);
		

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ChessGame_G12.class.getResource("/explosiveAnimations/Dark/harrypotter.jpg")));
		lblNewLabel.setBounds(0, 0, 636, 436);
		panel.add(lblNewLabel);
		
		
		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
			
				System.exit(0);
					
				} 

 
			
		});
		
		
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
			
					MainPage.main(null);
					
				} 

 
			
		});
	}
}
