package view_constructor;

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

import view.AuthorisationMenu;
import view_admin.ConstructorAdminMenu;

public class DeviceMenu extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public DeviceMenu() 
	{	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 503, 664);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel MenuTitleLabel = new JLabel("Робота з приладами");
		MenuTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MenuTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		MenuTitleLabel.setBounds(121, 25, 255, 38);
		contentPane.add(MenuTitleLabel);
		
		JButton InfoButton = new JButton("Переглянути інформацію про наявні прилади");
		InfoButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				DeviceMenu.this.setVisible(false);
				try {
					new InfoDevice(DeviceMenu.this).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}
		});
		InfoButton.setForeground(Color.BLACK);
		InfoButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		InfoButton.setBounds(42, 111, 410, 43);
		contentPane.add(InfoButton);
		
		JButton AddButton = new JButton("Додати новий прилад");
		AddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				DeviceMenu.this.setVisible(false);
				new AddDevice(DeviceMenu.this).setVisible(true); 
			}
		});
		AddButton.setForeground(Color.BLACK);
		AddButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		AddButton.setBounds(42, 191, 410, 43);
		contentPane.add(AddButton);
		
		JButton EditButton = new JButton("Редагувати інформацію про прилад");
		EditButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				DeviceMenu.this.setVisible(false);
				try {
					new EditDevice(DeviceMenu.this).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}
		});
		EditButton.setForeground(Color.BLACK);
		EditButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		EditButton.setBounds(42, 271, 410, 43);
		contentPane.add(EditButton);
		
		JButton SpecificationButton = new JButton("Переглянути специфікацію прилада");
		SpecificationButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				DeviceMenu.this.setVisible(false);
				try {
					new SpecificationInformation(DeviceMenu.this).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}
		});
		SpecificationButton.setForeground(Color.BLACK);
		SpecificationButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		SpecificationButton.setBounds(42, 350, 410, 43);
		contentPane.add(SpecificationButton);
		
		JButton EditSpecificationButton = new JButton("Змінити специфікацію приладу");
		EditSpecificationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				DeviceMenu.this.setVisible(false);
				try {
					new EditSpecification(DeviceMenu.this).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}
		});
		EditSpecificationButton.setForeground(Color.BLACK);
		EditSpecificationButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		EditSpecificationButton.setBounds(42, 429, 410, 43);
		contentPane.add(EditSpecificationButton);
		
		JButton SpecialInfoButton = new JButton("Особлива інформація");
		SpecialInfoButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				DeviceMenu.this.setVisible(false);
				try {
					new SpecialDeviceInfo(DeviceMenu.this).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}
		});
		SpecialInfoButton.setForeground(Color.BLACK);
		SpecialInfoButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		SpecialInfoButton.setBounds(42, 505, 410, 43);
		contentPane.add(SpecialInfoButton);
		
		
		
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				DeviceMenu.this.setVisible(false);
				DeviceMenu.this.dispose();
				if(AuthorisationMenu.user_role.equals("constructor"))
				{
					try {
						new ConstructorMenu().setVisible(true);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				if(AuthorisationMenu.user_role.equals("admin"))
				{
					try {
						new ConstructorAdminMenu().setVisible(true);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnBack.setBounds(355, 579, 97, 25);
		contentPane.add(btnBack);
	}
}
