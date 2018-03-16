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
		setBounds(100, 100, 608, 509);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel UserPIBLabel = new JLabel(UserSurnameName);
		UserPIBLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		UserPIBLabel.setBounds(205, 64, 267, 38);
		contentPane.add(UserPIBLabel);
		
		JLabel MenuTitleLabel = new JLabel("���� ����� ����� ����������");
		MenuTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		MenuTitleLabel.setBounds(93, 13, 379, 38);
		contentPane.add(MenuTitleLabel);
		
		JButton ProviderMenuButton = new JButton("1)���� ������ � ���������������");
		ProviderMenuButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				WorkerDeliveryDepartmentMenu.this.setVisible(false);
				new ProviderMenu().setVisible(true); 
			}
		});
		ProviderMenuButton.setForeground(Color.BLACK);
		ProviderMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
		ProviderMenuButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		ProviderMenuButton.setBounds(53, 177, 419, 43);
		contentPane.add(ProviderMenuButton);
		
		JButton DeliveryMenuButton = new JButton("2)������ ���� �� ������");
		DeliveryMenuButton.setForeground(Color.RED);
		DeliveryMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
		DeliveryMenuButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		DeliveryMenuButton.setBounds(53, 269, 419, 43);
		contentPane.add(DeliveryMenuButton);
		
		
		
		JButton LogOutButtton = new JButton("������������");
		LogOutButtton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WorkerDeliveryDepartmentMenu.this.setVisible(false);
				WorkerDeliveryDepartmentMenu.this.dispose();
				new AuthorisationMenu().setVisible(true);
			}
		});
		LogOutButtton.setBounds(454, 430, 130, 25);
		contentPane.add(LogOutButtton);
	}

}
