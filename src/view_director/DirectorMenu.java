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
		setBounds(100, 100, 424, 554);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel MenuTitleLabel = new JLabel("Меню директора");
		MenuTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MenuTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		MenuTitleLabel.setBounds(0, 13, 406, 38);
		contentPane.add(MenuTitleLabel);
		
		JLabel UserPIBLabel = new JLabel(UserSurnameName);
		UserPIBLabel.setHorizontalAlignment(SwingConstants.CENTER);
		UserPIBLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		UserPIBLabel.setBounds(0, 64, 406, 38);
		contentPane.add(UserPIBLabel);
		
		JLabel lblNewLabel = new JLabel("Перегляд звітних данних по ...");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(0, 148, 406, 25);
		contentPane.add(lblNewLabel);
		
		
		JButton ConstructDepartmentButton = new JButton("конструкторському бюро");
		ConstructDepartmentButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				DirectorMenu.this.setVisible(false);
				try {
					new ConstructDepartmentDirectorMenu(DirectorMenu.this).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}
		});
		ConstructDepartmentButton.setForeground(Color.BLACK);
		ConstructDepartmentButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		ConstructDepartmentButton.setBounds(60, 192, 295, 43);
		contentPane.add(ConstructDepartmentButton);
		
		JButton SalesDepartmentButton = new JButton("відділу продажів");
		SalesDepartmentButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				DirectorMenu.this.setVisible(false);
				try {
					new SalesDepartmentDirectorMenu(DirectorMenu.this).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}
		});
		SalesDepartmentButton.setForeground(Color.BLACK);
		SalesDepartmentButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		SalesDepartmentButton.setBounds(60, 284, 295, 43);
		contentPane.add(SalesDepartmentButton);
		
		JButton DeliveryDepartmentButton = new JButton("відділу постачання");
		DeliveryDepartmentButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				DirectorMenu.this.setVisible(false);
				try {
					new DeliveryDepartmentDirectorMenu(DirectorMenu.this).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}
		});
		DeliveryDepartmentButton.setForeground(Color.BLACK);
		DeliveryDepartmentButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		DeliveryDepartmentButton.setBounds(60, 376, 295, 43);
		contentPane.add(DeliveryDepartmentButton);
		
		
		
		JButton btnBack = new JButton("Розлогінитися");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DirectorMenu.this.setVisible(false);
				DirectorMenu.this.dispose();
				new AuthorisationMenu().setVisible(true);
			}
		});
		btnBack.setBounds(254, 469, 140, 25);
		contentPane.add(btnBack);
		
	}
}
