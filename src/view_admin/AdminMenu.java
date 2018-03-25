package view_admin;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.UserDao;
import domain.User;
import view.AuthorisationMenu;
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
		setBounds(100, 100, 454, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel MenuTitleLabel = new JLabel("Меню адміністратора");
		MenuTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MenuTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		MenuTitleLabel.setBounds(0, 13, 436, 38);
		contentPane.add(MenuTitleLabel);
		
		JLabel UserPIBLabel = new JLabel(UserSurnameName);
		UserPIBLabel.setHorizontalAlignment(SwingConstants.CENTER);
		UserPIBLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		UserPIBLabel.setBounds(0, 64, 436, 38);
		contentPane.add(UserPIBLabel);
		
		JLabel lblNewLabel = new JLabel("Робота з ...");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 139, 436, 25);
		contentPane.add(lblNewLabel);
		
		
		JButton UserMenuButton = new JButton("користувачами");
		UserMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				AdminMenu.this.setVisible(false);
				new UserMenu().setVisible(true); 
			}
		});
		UserMenuButton.setForeground(Color.BLACK);
		UserMenuButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		UserMenuButton.setBounds(50, 183, 328, 43);
		contentPane.add(UserMenuButton);
		

		JButton ConstructorTablesButton = new JButton("з конструкторським бюро");
		ConstructorTablesButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				AdminMenu.this.setVisible(false);
				try {
					new ConstructorAdminMenu().setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}
		});
		ConstructorTablesButton.setForeground(new Color(0, 0, 0));
		ConstructorTablesButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		ConstructorTablesButton.setBounds(50, 259, 328, 43);
		contentPane.add(ConstructorTablesButton);
		
		
		JButton SalesDepartmentWorkerTablesButton = new JButton("з відділом продажів");
		SalesDepartmentWorkerTablesButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				AdminMenu.this.setVisible(false);
				try {
					new WorkerSalesDepartmentAdminMenu().setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}
		});
		SalesDepartmentWorkerTablesButton.setForeground(Color.BLACK);
		SalesDepartmentWorkerTablesButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		SalesDepartmentWorkerTablesButton.setBounds(50, 334, 328, 43);
		contentPane.add(SalesDepartmentWorkerTablesButton);
		
		
		JButton DeliveryDepartmentWorkerTablesButton = new JButton("з відділом постачання");
		DeliveryDepartmentWorkerTablesButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				AdminMenu.this.setVisible(false);
				try {
					new WorkerDeliveryDepartmentAdminMenu().setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}
		});
		DeliveryDepartmentWorkerTablesButton.setForeground(Color.BLACK);
		DeliveryDepartmentWorkerTablesButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		DeliveryDepartmentWorkerTablesButton.setBounds(50, 410, 328, 43);
		contentPane.add(DeliveryDepartmentWorkerTablesButton);
		
		
		JButton btnBack = new JButton("Розлогінитися");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminMenu.this.setVisible(false);
				AdminMenu.this.dispose();
				new AuthorisationMenu().setVisible(true);
			}
		});
		btnBack.setBounds(297, 492, 127, 25);
		contentPane.add(btnBack);
		
	}
}
