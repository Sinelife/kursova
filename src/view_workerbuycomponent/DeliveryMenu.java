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

import view.AuthorisationMenu;


public class DeliveryMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public DeliveryMenu() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 698, 597);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel MenuTitleLabel = new JLabel("Меню роботи з замовленнями постачання");
		MenuTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		MenuTitleLabel.setBounds(89, 13, 509, 38);
		contentPane.add(MenuTitleLabel);
		
		JLabel ProviderNameLabel = new JLabel("постачальник " + ChooseProvider.name_to_choose);
		ProviderNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		ProviderNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ProviderNameLabel.setBounds(69, 55, 443, 31);
		contentPane.add(ProviderNameLabel);
		
		

		JButton InfoDeliveryButton = new JButton("1)Перегляд інформації про замовлення");
		InfoDeliveryButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				DeliveryMenu.this.setVisible(false);
				try {
					new InfoDelivery(DeliveryMenu.this).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		InfoDeliveryButton.setHorizontalAlignment(SwingConstants.LEFT);
		InfoDeliveryButton.setForeground(Color.DARK_GRAY);
		InfoDeliveryButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		InfoDeliveryButton.setBounds(42, 145, 456, 43);
		contentPane.add(InfoDeliveryButton);
		
		
		JButton AddDeliveryButton = new JButton("2)Додати нове замовлення");
		AddDeliveryButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				DeliveryMenu.this.setVisible(false);
				new AddDelivery(DeliveryMenu.this).setVisible(true);		
			}
		});
		AddDeliveryButton.setHorizontalAlignment(SwingConstants.LEFT);
		AddDeliveryButton.setForeground(Color.BLACK);
		AddDeliveryButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		AddDeliveryButton.setBounds(42, 234, 456, 43);
		contentPane.add(AddDeliveryButton);
		
		
		
		JButton EditDeliveryButton = new JButton("3)Редагувати інформацію про замовлення");
		EditDeliveryButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				DeliveryMenu.this.setVisible(false);
				try {
					new EditDelivery(DeliveryMenu.this).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		EditDeliveryButton.setHorizontalAlignment(SwingConstants.LEFT);
		EditDeliveryButton.setForeground(Color.BLACK);
		EditDeliveryButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		EditDeliveryButton.setBounds(42, 324, 456, 43);
		contentPane.add(EditDeliveryButton);
		
		
		JButton EditDeliveryDeviceButton = new JButton("4)Робота з замовленням");
		EditDeliveryDeviceButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				DeliveryMenu.this.setVisible(false);
				try {
					new EditDeliveryComponent(DeliveryMenu.this).setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		EditDeliveryDeviceButton.setHorizontalAlignment(SwingConstants.LEFT);
		EditDeliveryDeviceButton.setForeground(Color.DARK_GRAY);
		EditDeliveryDeviceButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		EditDeliveryDeviceButton.setBounds(42, 414, 456, 43);
		contentPane.add(EditDeliveryDeviceButton);
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				DeliveryMenu.this.setVisible(false);
				DeliveryMenu.this.dispose();
				try {
					new ChooseProvider().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBack.setBounds(546, 500, 97, 25);
		contentPane.add(btnBack);
		
	}
}
