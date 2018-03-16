package view_admin;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.UserDao;
import domain.User;
import view.AuthorisationMenu;
import view_Constructor.ComponentMenu;
import view_Constructor.DeviceMenu;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ConstructorAdminMenu extends JFrame {

	/**
	 * 
	 */
	private JPanel contentPane;
	

	/**
	 * Create the frame.
	 * @throws SQLException
	 */
	public ConstructorAdminMenu() throws SQLException 
	{
		UserDao ud = new UserDao();
		User u = ud.readUser(AuthorisationMenu.user_id_to_choose);
		String UserSurnameName = u.getSurname() + " " + u.getName();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 643, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel MenuTitleLabel = new JLabel("Меню адміна(як члена конструкторського бюро)");
		MenuTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		MenuTitleLabel.setBounds(60, 13, 630, 38);
		contentPane.add(MenuTitleLabel);
		
		JLabel UserPIBLabel = new JLabel(UserSurnameName);
		UserPIBLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		UserPIBLabel.setBounds(205, 64, 267, 38);
		contentPane.add(UserPIBLabel);
		
		JButton DeviceMenuButton = new JButton("1)Меню роботи з приладами");
		DeviceMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				ConstructorAdminMenu.this.setVisible(false);
				new DeviceMenu().setVisible(true); 
			}
		});
		DeviceMenuButton.setForeground(Color.BLACK);
		DeviceMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
		DeviceMenuButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		DeviceMenuButton.setBounds(52, 147, 390, 43);
		contentPane.add(DeviceMenuButton);
		
		JButton ComponentMenuButton = new JButton("2)Меню роботи з компонентами");
		ComponentMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ConstructorAdminMenu.this.setVisible(false);
				new ComponentMenu().setVisible(true); 
			}
		});
		ComponentMenuButton.setForeground(Color.BLACK);
		ComponentMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
		ComponentMenuButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		ComponentMenuButton.setBounds(52, 239, 390, 43);
		contentPane.add(ComponentMenuButton);
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConstructorAdminMenu.this.setVisible(false);
				ConstructorAdminMenu.this.dispose();
				try {
					new AdminMenu().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBack.setBounds(477, 313, 103, 25);
		contentPane.add(btnBack);
	}
}
