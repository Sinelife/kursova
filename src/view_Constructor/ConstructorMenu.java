package view_Constructor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.UserDao;
import domain.User;
import view.AuthorisationMenu;
import view.ChangeLoginPassword;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ConstructorMenu extends JFrame {

	/**
	 * 
	 */
	private JPanel contentPane;

	/**
	 * Create the frame.
	 * @throws SQLException
	 */
	public ConstructorMenu() throws SQLException 
	{
		UserDao ud = new UserDao();
		User u = ud.readUser(AuthorisationMenu.user_id_to_choose);
		String UserSurnameName = u.getSurname() + " " + u.getName();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel MenuTitleLabel = new JLabel("Меню члена конструкторського відділу");
		MenuTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		MenuTitleLabel.setBounds(53, 13, 456, 38);
		contentPane.add(MenuTitleLabel);
		
		JLabel UserPIBLabel = new JLabel(UserSurnameName);
		UserPIBLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		UserPIBLabel.setBounds(130, 64, 267, 38);
		contentPane.add(UserPIBLabel);
		
		JButton DeviceMenuButton = new JButton("1)Меню роботи з приладами");
		DeviceMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				ConstructorMenu.this.setVisible(false);
				new DeviceMenu().setVisible(true); 
			}
		});
		DeviceMenuButton.setForeground(Color.BLACK);
		DeviceMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
		DeviceMenuButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		DeviceMenuButton.setBounds(84, 154, 287, 43);
		contentPane.add(DeviceMenuButton);
		
		JButton ComponentMenuButton = new JButton("2)Меню роботи з компонентами");
		ComponentMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ConstructorMenu.this.setVisible(false);
				new ComponentMenu().setVisible(true); 
			}
		});
		ComponentMenuButton.setForeground(Color.BLACK);
		ComponentMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
		ComponentMenuButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		ComponentMenuButton.setBounds(84, 246, 287, 43);
		contentPane.add(ComponentMenuButton);
		
		JButton button_2 = new JButton("3)Змінити пароль та логін");
		button_2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ConstructorMenu.this.setVisible(false);
				try {
					new ChangeLoginPassword(ConstructorMenu.this).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		button_2.setForeground(Color.BLACK);
		button_2.setHorizontalAlignment(SwingConstants.LEFT);
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		button_2.setBounds(84, 338, 287, 43);
		contentPane.add(button_2);
		
		
		
		JButton btnBack = new JButton("Розлогінитися");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConstructorMenu.this.setVisible(false);
				ConstructorMenu.this.dispose();
				new AuthorisationMenu().setVisible(true);
			}
		});
		btnBack.setBounds(369, 410, 140, 25);
		contentPane.add(btnBack);
	}
}
