simport java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DB_Test {

	private JFrame frame;
	private JTextField textName;
	private JPasswordField passwordField;
	
	Connection con = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DB_Test window = new DB_Test();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DB_Test() {
		initialize();
		
		SQLConnection sqlc = new SQLConnection();
		con = sqlc.dbconnect();
		
		if (con != null) JOptionPane.showMessageDialog(null, "Connected to the DB");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Your Name:");
		lblNewLabel.setBounds(43, 37, 70, 14);
		frame.getContentPane().add(lblNewLabel);
		
		textName = new JTextField();
		textName.setBounds(116, 34, 86, 20);
		frame.getContentPane().add(textName);
		textName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setBounds(43, 72, 70, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(116, 69, 86, 20);
		frame.getContentPane().add(passwordField);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					//setup and exectue the SQL query
					String query = "SELECT * from person where name = ? and password = ?;";
					PreparedStatement pst = con.prepareStatement(query);
					pst.setString(1, textName.getText());
					pst.setString(2, passwordField.getText());
					ResultSet rs = pst.executeQuery();
					int count = 0;
					while (rs.next()) {
						count++;
					}//end of while
					
					if(count == 1) {
						JOptionPane.showMessageDialog(null, "User Name and Password is correct!");
					}//end of if
					else {
						JOptionPane.showMessageDialog(null, "User Name and Password is invalid!");
					}//end of else
					
				}//end of try action
				catch (Exception ex) {
					
					JOptionPane.showMessageDialog(null, "Error in action");
					System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
					
				}//end of catch action
			}
		});
		btnSubmit.setBounds(113, 114, 89, 23);
		frame.getContentPane().add(btnSubmit);
	}
}
