package main;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JTextField;

import view.AuthorisationMenu;

public class Main 
{
	public static Connection conn;

	public static void main(String[] args) 
	{
		try {
		    //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/department", "ya", "euncZ23-");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/relsis_db?useSSL=false", "root", "euncZ23-");
		    if (conn != null) {
		    	//JOptionPane.showMessageDialog (null, "Succesful conection to database!" ); 
		    }
		} catch (SQLException ex) {
		    ex.printStackTrace();
		}

		AuthorisationMenu frame = new AuthorisationMenu();
		frame.setVisible(true);
	}

	
	public static void DateToString(Date d,JTextField t)
	{
		String res = null;
		if(d == null)
		{
			res = "";
			t.setText(res);
		}
		else
		{
			t.setText(String.valueOf(d));
		}
	}
	
}
