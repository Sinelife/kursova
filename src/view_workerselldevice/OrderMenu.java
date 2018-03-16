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

import view.AuthorisationMenu;

public class OrderMenu extends JFrame {

	private JPanel contentPane;



	/**
	 * Create the frame.
	 */
	public OrderMenu() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 698, 597);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel MenuTitleLabel = new JLabel("Меню роботи з замовленнями на купівлю");
		MenuTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		MenuTitleLabel.setBounds(89, 13, 509, 38);
		contentPane.add(MenuTitleLabel);
		
		JLabel ClientNameLabel = new JLabel("клієнт " + ChooseClient.name_to_choose);
		ClientNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		ClientNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ClientNameLabel.setBounds(69, 55, 443, 31);
		contentPane.add(ClientNameLabel);
		
		

		JButton InfoOrderButton = new JButton("1)Перегляд інформації про замовлення");
		InfoOrderButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				OrderMenu.this.setVisible(false);
				try {
					new InfoOrder(OrderMenu.this).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		InfoOrderButton.setHorizontalAlignment(SwingConstants.LEFT);
		InfoOrderButton.setForeground(Color.DARK_GRAY);
		InfoOrderButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		InfoOrderButton.setBounds(42, 145, 456, 43);
		contentPane.add(InfoOrderButton);
		
		
		JButton AddOrderButton = new JButton("2)Додати нове замовлення");
		AddOrderButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				OrderMenu.this.setVisible(false);
				new AddOrder(OrderMenu.this).setVisible(true);		
			}
		});
		AddOrderButton.setHorizontalAlignment(SwingConstants.LEFT);
		AddOrderButton.setForeground(Color.BLACK);
		AddOrderButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		AddOrderButton.setBounds(42, 234, 456, 43);
		contentPane.add(AddOrderButton);
		
		
		
		JButton EditOrderButton = new JButton("3)Редагувати інформацію про замовлення");
		EditOrderButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				OrderMenu.this.setVisible(false);
				try {
					new EditOrder(OrderMenu.this).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		EditOrderButton.setHorizontalAlignment(SwingConstants.LEFT);
		EditOrderButton.setForeground(Color.BLACK);
		EditOrderButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		EditOrderButton.setBounds(42, 324, 456, 43);
		contentPane.add(EditOrderButton);
		
		
		JButton EditOrderDeviceButton = new JButton("4)Робота з замовленням");
		EditOrderDeviceButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				OrderMenu.this.setVisible(false);
				try {
					new EditOrderDevice(OrderMenu.this).setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		EditOrderDeviceButton.setHorizontalAlignment(SwingConstants.LEFT);
		EditOrderDeviceButton.setForeground(Color.DARK_GRAY);
		EditOrderDeviceButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		EditOrderDeviceButton.setBounds(42, 414, 456, 43);
		contentPane.add(EditOrderDeviceButton);
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				OrderMenu.this.setVisible(false);
				OrderMenu.this.dispose();
				try {
					new ChooseClient().setVisible(true);
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
