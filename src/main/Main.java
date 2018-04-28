package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import view.AuthorisationMenu;

public class Main 
{
	public static Connection conn;

	public static void main(String[] args) 
	{
		try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ais_db?useSSL=false", "root", "euncZ23-");
		} catch (SQLException ex) {
		    ex.printStackTrace();
		}

		AuthorisationMenu frame = new AuthorisationMenu();
		frame.setVisible(true);
	}	
}
