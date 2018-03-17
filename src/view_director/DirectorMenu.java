package view_director;

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

public class DirectorMenu extends JFrame {

	/**
	 * 
	 */
	private JPanel contentPane;

	/**
	 * Create the frame.
	 * @throws SQLException
	 */
	public DirectorMenu() throws SQLException 
	{
		UserDao ud = new UserDao();
		User u = ud.readUser(AuthorisationMenu.user_id_to_choose);
		String UserSurnameName = u.getSurname() + " " + u.getName();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 731, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel MenuTitleLabel = new JLabel("Меню директора");
		MenuTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		MenuTitleLabel.setBounds(192, 13, 267, 38);
		contentPane.add(MenuTitleLabel);
		
		JLabel UserPIBLabel = new JLabel(UserSurnameName);
		UserPIBLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		UserPIBLabel.setBounds(213, 64, 267, 38);
		contentPane.add(UserPIBLabel);
		
		JButton ConstructDepartmentButton = new JButton("1)Меню роботи з данними конструкторського бюро");
		ConstructDepartmentButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				DirectorMenu.this.setVisible(false);
				new ConstructDepartmentDirectorMenu(DirectorMenu.this).setVisible(true); 
			}
		});
		ConstructDepartmentButton.setForeground(Color.BLACK);
		ConstructDepartmentButton.setHorizontalAlignment(SwingConstants.LEFT);
		ConstructDepartmentButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		ConstructDepartmentButton.setBounds(65, 115, 537, 43);
		contentPane.add(ConstructDepartmentButton);
		
		JButton SalesDepartmentButton = new JButton("2)Меню роботи з данними відділу продажу");
		SalesDepartmentButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				DirectorMenu.this.setVisible(false);
				try {
					new SalesDepartmentDirectorMenu(DirectorMenu.this).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		SalesDepartmentButton.setForeground(Color.BLACK);
		SalesDepartmentButton.setHorizontalAlignment(SwingConstants.LEFT);
		SalesDepartmentButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		SalesDepartmentButton.setBounds(65, 207, 537, 43);
		contentPane.add(SalesDepartmentButton);
		
		JButton DeliveryDepartmentButton = new JButton("3)Меню роботи з данними відділу постачання");
		DeliveryDepartmentButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				DirectorMenu.this.setVisible(false);
				try {
					new DeliveryDepartmentDirectorMenu(DirectorMenu.this).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		DeliveryDepartmentButton.setForeground(Color.BLACK);
		DeliveryDepartmentButton.setHorizontalAlignment(SwingConstants.LEFT);
		DeliveryDepartmentButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		DeliveryDepartmentButton.setBounds(65, 299, 537, 43);
		contentPane.add(DeliveryDepartmentButton);
		
		
		
		JButton btnBack = new JButton("Розлогінитися");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DirectorMenu.this.setVisible(false);
				DirectorMenu.this.dispose();
				new AuthorisationMenu().setVisible(true);
			}
		});
		btnBack.setBounds(550, 452, 140, 25);
		contentPane.add(btnBack);
	}
}
