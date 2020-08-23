import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;



import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class LeaderBoards {

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main() {
		JFrame frame = new JFrame("Leaderboards");
		frame.setTitle("Leaderboards");
		frame.setBounds(100, 100, 388, 404);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 352, 343);
		frame.getContentPane().add(panel);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		

		//Table to view all the books
		final String columnNames[] = {"Winners"};
		DefaultTableModel tableModel;
		
		tableModel = new DefaultTableModel(0,10);
		
		tableModel.setColumnIdentifiers(columnNames);
		

		JTable table_1_1 = new JTable();
		table_1_1.setBounds(10, 11, 341, 282);
		table_1_1.setModel(tableModel);
		panel.add(table_1_1);
		table_1_1.setModel(tableModel);
		String line;
		
		
		
		
	
		
		BufferedReader reader;
		    try{       
		        reader = new BufferedReader(new FileReader("winners.txt"));
		        while((line = reader.readLine()) != null) {
		           tableModel.addRow(line.split("/")); 
		        }
		        reader.close();
		     }
		    catch(Exception e){
		        JOptionPane.showMessageDialog(null, "Error");
		e.printStackTrace();
		}
		
		
	
}}

