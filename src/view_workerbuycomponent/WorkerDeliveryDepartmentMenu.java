package view_workerbuycomponent;

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
import view.ChangeLoginPassword;

public class WorkerDeliveryDepartmentMenu extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public WorkerDeliveryDepartmentMenu() throws SQLException 
	{
		UserDao ud = new UserDao();
		User u = ud.readUser(AuthorisationMenu.user_id_to_choose);
		String UserSurnameName = u.getSurname() + " " + u.getName();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 524, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel UserPIBLabel = new JLabel(UserSurnameName);
		UserPIBLabel.setHorizontalAlignment(SwingConstants.CENTER);
		UserPIBLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		UserPIBLabel.setBounds(0, 64, 506, 38);
		contentPane.add(UserPIBLabel);
		
		JLabel MenuTitleLabel = new JLabel("Член відділу постачання");
		MenuTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MenuTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		MenuTitleLabel.setBounds(0, 13, 506, 38);
		contentPane.add(MenuTitleLabel);
		
		JButton ProviderMenuButton = new JButton("постачальниками");
		ProviderMenuButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				WorkerDeliveryDepartmentMenu.this.setVisible(false);
				new ProviderMenu().setVisible(true); 
			}
		});
		ProviderMenuButton.setForeground(Color.BLACK);
		ProviderMenuButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		ProviderMenuButton.setBounds(137, 201, 235, 43);
		contentPane.add(ProviderMenuButton);
		
		JButton DeliveryMenuButton = new JButton("Змінити логін та пароль");
		DeliveryMenuButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				WorkerDeliveryDepartmentMenu.this.setVisible(false);
				try {
					new ChangeLoginPassword(WorkerDeliveryDepartmentMenu.this).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}
		});
		DeliveryMenuButton.setForeground(Color.BLACK);
		DeliveryMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
		DeliveryMenuButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		DeliveryMenuButton.setBounds(25, 351, 173, 25);
		contentPane.add(DeliveryMenuButton);
		
		
		
		JButton LogOutButtton = new JButton("Розлогінитись");
		LogOutButtton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WorkerDeliveryDepartmentMenu.this.setVisible(false);
				WorkerDeliveryDepartmentMenu.this.dispose();
				new AuthorisationMenu().setVisible(true);
			}
		});
		LogOutButtton.setBounds(345, 351, 130, 25);
		contentPane.add(LogOutButtton);
		
		JLabel label = new JLabel("\u0420\u043E\u0431\u043E\u0442\u0430 \u0437 ...");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label.setBounds(0, 153, 506, 29);
		contentPane.add(label);
	}

}
