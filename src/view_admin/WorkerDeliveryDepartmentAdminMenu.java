package view_admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.UserDao;
import domain.User;
import view.AuthorisationMenu;
import view_workerbuycomponent.ProviderMenu;

public class WorkerDeliveryDepartmentAdminMenu extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public WorkerDeliveryDepartmentAdminMenu() throws SQLException 
	{
		UserDao ud = new UserDao();
		User u = ud.readUser(AuthorisationMenu.user_id_to_choose);
		String UserSurnameName = u.getSurname() + " " + u.getName();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 608, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel UserPIBLabel = new JLabel(UserSurnameName);
		UserPIBLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		UserPIBLabel.setBounds(205, 64, 267, 38);
		contentPane.add(UserPIBLabel);
		
		JLabel MenuTitleLabel = new JLabel("Меню адміна(як члена відділу постачання)");
		MenuTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		MenuTitleLabel.setBounds(35, 13, 531, 38);
		contentPane.add(MenuTitleLabel);
		
		JButton ProviderMenuButton = new JButton("1)Меню роботи з постачальниками");
		ProviderMenuButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				WorkerDeliveryDepartmentAdminMenu.this.setVisible(false);
				new ProviderMenu().setVisible(true); 
			}
		});
		ProviderMenuButton.setForeground(Color.BLACK);
		ProviderMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
		ProviderMenuButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		ProviderMenuButton.setBounds(53, 177, 368, 43);
		contentPane.add(ProviderMenuButton);
		
		
		
		JButton LogOutButtton = new JButton("BACK");
		LogOutButtton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WorkerDeliveryDepartmentAdminMenu.this.setVisible(false);
				WorkerDeliveryDepartmentAdminMenu.this.dispose();
				try {
					new AdminMenu().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		LogOutButtton.setBounds(471, 247, 86, 25);
		contentPane.add(LogOutButtton);
	}

}
