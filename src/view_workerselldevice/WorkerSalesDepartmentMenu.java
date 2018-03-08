package view_workerselldevice;

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

public class WorkerSalesDepartmentMenu extends JFrame 
{

	private JPanel contentPane;


	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public WorkerSalesDepartmentMenu() throws SQLException 
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
		
		JLabel UserPIBLabel = new JLabel(UserSurnameName);
		UserPIBLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		UserPIBLabel.setBounds(205, 64, 267, 38);
		contentPane.add(UserPIBLabel);
		
		JLabel MenuTitleLabel = new JLabel("���� ����� ����� �������");
		MenuTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		MenuTitleLabel.setBounds(128, 13, 456, 38);
		contentPane.add(MenuTitleLabel);
		
		JButton ClientMenuButton = new JButton("1)���� ������ � ��������");
		ClientMenuButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				 WorkerSalesDepartmentMenu.this.setVisible(false);
				new ClientMenu().setVisible(true); 
			}
		});
		ClientMenuButton.setForeground(Color.BLACK);
		ClientMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
		ClientMenuButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		ClientMenuButton.setBounds(53, 177, 390, 43);
		contentPane.add(ClientMenuButton);
		
		JButton OrderMenuButton = new JButton("2)���� ������ � ������������");
		OrderMenuButton.setForeground(Color.BLACK);
		OrderMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
		OrderMenuButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		OrderMenuButton.setBounds(53, 269, 390, 43);
		contentPane.add(OrderMenuButton);
		
		
		
		JButton LogOutButtton = new JButton("������������");
		LogOutButtton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WorkerSalesDepartmentMenu.this.setVisible(false);
				WorkerSalesDepartmentMenu.this.dispose();
				new AuthorisationMenu().setVisible(true);
			}
		});
		LogOutButtton.setBounds(550, 429, 140, 25);
		contentPane.add(LogOutButtton);
	}

}
