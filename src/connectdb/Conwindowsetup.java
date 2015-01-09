package connectdb;

import java.awt.EventQueue;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Conwindowsetup {

	private JFrame frame;
	private JTextField hostname;
	private JTextField dbuser;
	private JTextField dbname;
	private JTextField dbpass;
	private JTextField textField_4;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Conwindowsetup window = new Conwindowsetup();
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
	public Conwindowsetup() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 407);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		hostname = new JTextField();
		hostname.setText("host");
		hostname.setBounds(216, 28, 179, 40);
		frame.getContentPane().add(hostname);
		hostname.setColumns(10);
		
		dbuser = new JTextField();
		dbuser.setText("dbuser");
		dbuser.setBounds(216, 95, 179, 35);
		frame.getContentPane().add(dbuser);
		dbuser.setColumns(10);
		
		dbname = new JTextField();
		dbname.setText("dbname");
		dbname.setBounds(216, 155, 179, 40);
		frame.getContentPane().add(dbname);
		dbname.setColumns(10);
		
		dbpass = new JTextField();
		dbpass.setText("pass");
		dbpass.setBounds(216, 220, 179, 35);
		frame.getContentPane().add(dbpass);
		dbpass.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(216, 282, 179, 35);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnconnect = new JButton("Connect");
		btnconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connectnow();
					writeToFile();
					CreateTable();
					//JOptionPane.showMessageDialog (null, "Connection to database success and your credentials saved too!", "Database connection", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					
					JOptionPane.showMessageDialog (null, "Connect now fail", "Database connection", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		btnconnect.setBounds(226, 338, 162, 35);
		frame.getContentPane().add(btnconnect);
	}
	
	
	
	public void connectnow () throws SQLException 
	{ 
		String host= hostname.getText();
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = dbname.getText();
		String driver = "com.mysql.jdbc.Driver";
		String userName = dbuser.getText(); 
		String password = dbpass.getText(); 
		
		try
		{ Class.forName(driver).newInstance(); 
		Connection conn = DriverManager.getConnection(url+dbName,userName,password);
	
		conn.close();
		}
		catch (InstantiationException e) 
		{
			
			e.printStackTrace();
		}
		catch (IllegalAccessException e) 
		{
			
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	    }
		
  //WRITE CONNECTION VARIABLES TO FILE
	public void writeToFile()
	{
		try {
			 
			
			String host= hostname.getText();
			String url = "jdbc:mysql://localhost:3306/";
			String dbName = dbname.getText();
			String driver = "com.mysql.jdbc.Driver";
			String userName = dbuser.getText(); 
			String password = dbpass.getText();   
 
			File file = new File("/home/kay/Desktop/projects/DatabaseConn/src/connectdb/dbconfig.txt");
			try {
				Connection conn = DriverManager.getConnection(url+dbName,userName,password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			 
		     bw.write(host);
		     bw.write("\n");
		     
		     
		     bw.write(url);
		     bw.write("\n");
		     
		     bw.write(dbName);
		     bw.write("\n");
		    
		     bw.write(driver);
		     bw.write("\n");
		     
		    
		     bw.write(userName);
		     bw.write("\n");
		    
		     
		     bw.write(password);
			
		     JOptionPane.showMessageDialog (null, "Connecting to database success and saving  database  credentials success", "Database connection", JOptionPane.INFORMATION_MESSAGE);
			
			 bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	 
	}
	
	//CREATE TABLE 
	
	public void CreateTable() throws SQLException 
	{
		   Connection conn = null;
		   Statement stmt = null;
		 
			String host= hostname.getText();
			String url = "jdbc:mysql://localhost:3306/";
			String dbName = dbname.getText();
			String driver = "com.mysql.jdbc.Driver";
			String userName = dbuser.getText(); 
			String password = dbpass.getText(); 
			conn = DriverManager.getConnection(url+dbName,userName,password);
		
	      stmt = (Statement) conn.createStatement();
	      
	      String sql = "CREATE TABLE IF NOT EXISTS REGISTRATION" +
	                   "(id INTEGER not NULL, " +
	                   " first VARCHAR(255), " + 
	                   " last VARCHAR(255), " + 
	                   " age INTEGER, " + 
	                   " PRIMARY KEY ( id ))"; 

	      stmt.executeUpdate(sql);
	      System.out.println("Created table in given database...");
	       conn.close();
	}
	
}
