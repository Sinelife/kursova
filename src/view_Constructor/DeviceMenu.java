package view_Constructor;

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
		setBounds(100, 100, 742, 619);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel MenuTitleLabel = new JLabel("Меню роботи з приладами");
		MenuTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		MenuTitleLabel.setBounds(157, 13, 417, 38);
		contentPane.add(MenuTitleLabel);
		
		JButton InfoButton = new JButton("1)Переглянути інформацію про наявні прилади.");
		InfoButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				DeviceMenu.this.setVisible(false);
				try {
					new InfoDevice(DeviceMenu.this).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		InfoButton.setHorizontalAlignment(SwingConstants.LEFT);
		InfoButton.setForeground(Color.BLACK);
		InfoButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		InfoButton.setBounds(42, 111, 456, 43);
		contentPane.add(InfoButton);
		
		JButton AddButton = new JButton("2)Додати інформацію про новий прилад.");
		AddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				DeviceMenu.this.setVisible(false);
				new AddDevice(DeviceMenu.this).setVisible(true); 
			}
		});
		AddButton.setHorizontalAlignment(SwingConstants.LEFT);
		AddButton.setForeground(Color.BLACK);
		AddButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		AddButton.setBounds(42, 191, 456, 43);
		contentPane.add(AddButton);
		
		JButton EditButton = new JButton("3)Редагувати інформацію про прилад.");
		EditButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				DeviceMenu.this.setVisible(false);
				try {
					new EditDevice(DeviceMenu.this).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		EditButton.setHorizontalAlignment(SwingConstants.LEFT);
		EditButton.setForeground(Color.BLACK);
		EditButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		EditButton.setBounds(42, 271, 456, 43);
		contentPane.add(EditButton);
		
		JButton SpecificationButton = new JButton("4)Переглянути специфікацію прилада.");
		SpecificationButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				DeviceMenu.this.setVisible(false);
				try {
					new SpecificationInformation(DeviceMenu.this).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		SpecificationButton.setHorizontalAlignment(SwingConstants.LEFT);
		SpecificationButton.setForeground(Color.GRAY);
		SpecificationButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		SpecificationButton.setBounds(42, 350, 456, 43);
		contentPane.add(SpecificationButton);
		
		JButton EditSpecificationButton = new JButton("5)Змінити специфікацію приладу.");
		EditSpecificationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				DeviceMenu.this.setVisible(false);
				try {
					new EditSpecification(DeviceMenu.this).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		EditSpecificationButton.setHorizontalAlignment(SwingConstants.LEFT);
		EditSpecificationButton.setForeground(Color.BLACK);
		EditSpecificationButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		EditSpecificationButton.setBounds(42, 429, 456, 43);
		contentPane.add(EditSpecificationButton);
		
		
		
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
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(AuthorisationMenu.user_role.equals("admin"))
				{
					try {
						new ConstructorAdminMenu().setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnBack.setBounds(602, 534, 97, 25);
		contentPane.add(btnBack);
	}

}
