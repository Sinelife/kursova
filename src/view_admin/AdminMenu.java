package view_admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.UserDao;
import domain.User;
import view.AuthorisationMenu;
import view_Constructor.DeviceMenu;
import view_Constructor.InfoDevice;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AdminMenu extends JFrame {

	/**
	 * 
	 */
	private JPanel contentPane;


	/**
	 * Create the frame.
	 * @throws SQLException
	 */
	public AdminMenu() throws SQLException 
	{
		UserDao ud = new UserDao();
		User u = ud.readUser(AuthorisationMenu.user_id_to_choose);
		String UserSurnameName = u.getSurname() + " " + u.getName();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 527);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel MenuTitleLabel = new JLabel("Меню адміністратора");
		MenuTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		MenuTitleLabel.setBounds(128, 13, 456, 38);
		contentPane.add(MenuTitleLabel);
		
		JLabel UserPIBLabel = new JLabel(UserSurnameName);
		UserPIBLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		UserPIBLabel.setBounds(205, 64, 267, 38);
		contentPane.add(UserPIBLabel);
		
		JButton DeviceMenuButton = new JButton("1)Меню роботи з користувачами");
		DeviceMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				AdminMenu.this.setVisible(false);
				new UserMenu().setVisible(true); 
			}
		});
		DeviceMenuButton.setForeground(Color.BLACK);
		DeviceMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
		DeviceMenuButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		DeviceMenuButton.setBounds(53, 177, 390, 43);
		contentPane.add(DeviceMenuButton);
		

		
		
		
		JButton btnBack = new JButton("Розлогінитися");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminMenu.this.setVisible(false);
				AdminMenu.this.dispose();
				new AuthorisationMenu().setVisible(true);
			}
		});
		btnBack.setBounds(550, 429, 140, 25);
		contentPane.add(btnBack);
	}
}
