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
import view_workerselldevice.ClientMenu;

public class WorkerSalesDepartmentAdminMenu extends JFrame 
{

	private JPanel contentPane;


	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public WorkerSalesDepartmentAdminMenu() throws SQLException 
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
		
		JLabel MenuTitleLabel = new JLabel("Меню адміна(як члена відділу продажу)");
		MenuTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		MenuTitleLabel.setBounds(35, 13, 531, 38);
		contentPane.add(MenuTitleLabel);
		
		JButton ClientMenuButton = new JButton("1)Меню роботи з кліентами");
		ClientMenuButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				WorkerSalesDepartmentAdminMenu.this.setVisible(false);
				new ClientMenu().setVisible(true); 
			}
		});
		ClientMenuButton.setForeground(Color.BLACK);
		ClientMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
		ClientMenuButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		ClientMenuButton.setBounds(53, 177, 390, 43);
		contentPane.add(ClientMenuButton);
		
		JButton OrderMenuButton = new JButton("2)Меню роботи з замовленнями");
		OrderMenuButton.setForeground(Color.BLACK);
		OrderMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
		OrderMenuButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		OrderMenuButton.setBounds(53, 269, 390, 43);
		contentPane.add(OrderMenuButton);
		
		
		
		JButton LogOutButtton = new JButton("BACK");
		LogOutButtton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WorkerSalesDepartmentAdminMenu.this.setVisible(false);
				WorkerSalesDepartmentAdminMenu.this.dispose();
				try {
					new AdminMenu().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		LogOutButtton.setBounds(498, 430, 86, 25);
		contentPane.add(LogOutButtton);
	}

}
