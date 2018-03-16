package view_admin;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import view.AuthorisationMenu;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UserMenu extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public UserMenu() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 635, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel MenuTitleLabel = new JLabel("Меню роботи з користувачами");
		MenuTitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		MenuTitleLabel.setBounds(128, 13, 456, 38);
		contentPane.add(MenuTitleLabel);
		
		JButton AddUserButton = new JButton("1)Додати працівника");
		AddUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				UserMenu.this.setVisible(false);
				new AddUser(UserMenu.this).setVisible(true); 
			}
		});
		AddUserButton.setForeground(Color.BLACK);
		AddUserButton.setHorizontalAlignment(SwingConstants.LEFT);
		AddUserButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		AddUserButton.setBounds(53, 103, 390, 43);
		contentPane.add(AddUserButton);
		
		JButton DeleteUserButton = new JButton("2)Видалити працівника");
		DeleteUserButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				UserMenu.this.setVisible(false);
				try {
					new DeleteUser(UserMenu.this).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		DeleteUserButton.setForeground(Color.BLACK);
		DeleteUserButton.setHorizontalAlignment(SwingConstants.LEFT);
		DeleteUserButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		DeleteUserButton.setBounds(53, 197, 390, 43);
		contentPane.add(DeleteUserButton);
		
		JButton EditUserButton = new JButton("3)Редагувати працівника");
		EditUserButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				UserMenu.this.setVisible(false);
				try {
					new EditUser(UserMenu.this).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		EditUserButton.setHorizontalAlignment(SwingConstants.LEFT);
		EditUserButton.setForeground(Color.BLACK);
		EditUserButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		EditUserButton.setBounds(53, 295, 390, 43);
		contentPane.add(EditUserButton);
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserMenu.this.setVisible(false);
				UserMenu.this.dispose();
				try {
					new AdminMenu().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBack.setBounds(496, 429, 97, 25);
		contentPane.add(btnBack);
	}
}
