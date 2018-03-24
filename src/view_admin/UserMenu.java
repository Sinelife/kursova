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
		setBounds(100, 100, 488, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel MenuTitleLabel = new JLabel("Робота з користувачами");
		MenuTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MenuTitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		MenuTitleLabel.setBounds(0, 13, 470, 38);
		contentPane.add(MenuTitleLabel);
		
		JButton AddUserButton = new JButton("Додати працівника");
		AddUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				UserMenu.this.setVisible(false);
				new AddUser(UserMenu.this).setVisible(true); 
			}
		});
		AddUserButton.setForeground(Color.BLACK);
		AddUserButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		AddUserButton.setBounds(94, 113, 281, 43);
		contentPane.add(AddUserButton);
		
		JButton DeleteUserButton = new JButton("Видалити працівника");
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
		DeleteUserButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		DeleteUserButton.setBounds(94, 207, 281, 43);
		contentPane.add(DeleteUserButton);
		
		JButton EditUserButton = new JButton("Редагувати працівника");
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
		EditUserButton.setForeground(Color.BLACK);
		EditUserButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		EditUserButton.setBounds(94, 305, 281, 43);
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
		btnBack.setBounds(361, 388, 97, 25);
		contentPane.add(btnBack);
	}
}
