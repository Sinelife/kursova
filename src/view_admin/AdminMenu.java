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
		setBounds(100, 100, 740, 527);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel MenuTitleLabel = new JLabel("Меню адміністратора");
		MenuTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		MenuTitleLabel.setBounds(128, 13, 456, 38);
		contentPane.add(MenuTitleLabel);
		
		JLabel UserPIBLabel = new JLabel(UserSurnameName);
		UserPIBLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		UserPIBLabel.setBounds(205, 64, 267, 38);
		contentPane.add(UserPIBLabel);
		
		JButton UserMenuButton = new JButton("1)Меню роботи з користувачами");
		UserMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				AdminMenu.this.setVisible(false);
				new UserMenu().setVisible(true); 
			}
		});
		UserMenuButton.setForeground(Color.BLACK);
		UserMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
		UserMenuButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		UserMenuButton.setBounds(51, 125, 466, 43);
		contentPane.add(UserMenuButton);
		

		JButton ConstructorTablesButton = new JButton("2)Меню роботи з таблицями конструктора");
		ConstructorTablesButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				AdminMenu.this.setVisible(false);
				try {
					new ConstructorAdminMenu().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		ConstructorTablesButton.setHorizontalAlignment(SwingConstants.LEFT);
		ConstructorTablesButton.setForeground(new Color(0, 0, 0));
		ConstructorTablesButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		ConstructorTablesButton.setBounds(51, 201, 466, 43);
		contentPane.add(ConstructorTablesButton);
		
		
		JButton SalesDepartmentWorkerTablesButton = new JButton("3)Меню роботи з таблицями відділу продажу");
		SalesDepartmentWorkerTablesButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				AdminMenu.this.setVisible(false);
				try {
					new WorkerSalesDepartmentAdminMenu().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		SalesDepartmentWorkerTablesButton.setHorizontalAlignment(SwingConstants.LEFT);
		SalesDepartmentWorkerTablesButton.setForeground(new Color(255, 140, 0));
		SalesDepartmentWorkerTablesButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		SalesDepartmentWorkerTablesButton.setBounds(51, 276, 466, 43);
		contentPane.add(SalesDepartmentWorkerTablesButton);
		
		
		JButton DeliveryDepartmentWorkerTablesButton = new JButton("4)Меню роботи з таблицями відділу постачання");
		DeliveryDepartmentWorkerTablesButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				AdminMenu.this.setVisible(false);
				try {
					new WorkerDeliveryDepartmentAdminMenu().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		DeliveryDepartmentWorkerTablesButton.setHorizontalAlignment(SwingConstants.LEFT);
		DeliveryDepartmentWorkerTablesButton.setForeground(Color.RED);
		DeliveryDepartmentWorkerTablesButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		DeliveryDepartmentWorkerTablesButton.setBounds(51, 352, 466, 43);
		contentPane.add(DeliveryDepartmentWorkerTablesButton);
		
		
		JButton btnBack = new JButton("Розлогінитися");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminMenu.this.setVisible(false);
				AdminMenu.this.dispose();
				new AuthorisationMenu().setVisible(true);
			}
		});
		btnBack.setBounds(567, 429, 127, 25);
		contentPane.add(btnBack);
		

		

		

	}
}
