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
		setBounds(100, 100, 590, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel MenuTitleLabel = new JLabel("Робота з замовленнями на купівлю");
		MenuTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MenuTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		MenuTitleLabel.setBounds(0, 13, 572, 38);
		contentPane.add(MenuTitleLabel);
		
		JLabel ClientNameLabel = new JLabel("клієнт " + ChooseClient.name_to_choose);
		ClientNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		ClientNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ClientNameLabel.setBounds(0, 55, 572, 31);
		contentPane.add(ClientNameLabel);
		
		

		JButton InfoOrderButton = new JButton("Перегляд інформації про замовлення");
		InfoOrderButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				OrderMenu.this.setVisible(false);
				try {
					new InfoOrder(OrderMenu.this).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		InfoOrderButton.setForeground(Color.DARK_GRAY);
		InfoOrderButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		InfoOrderButton.setBounds(106, 125, 357, 43);
		contentPane.add(InfoOrderButton);
		
		
		JButton AddOrderButton = new JButton("Додати нове замовлення");
		AddOrderButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				OrderMenu.this.setVisible(false);
				new AddOrder(OrderMenu.this).setVisible(true);		
			}
		});
		AddOrderButton.setForeground(Color.BLACK);
		AddOrderButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		AddOrderButton.setBounds(106, 214, 357, 43);
		contentPane.add(AddOrderButton);
		
		
		
		JButton EditOrderButton = new JButton("Редагувати інформацію про замовлення");
		EditOrderButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				OrderMenu.this.setVisible(false);
				try {
					new EditOrder(OrderMenu.this).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}	
			}
		});
		EditOrderButton.setForeground(Color.BLACK);
		EditOrderButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		EditOrderButton.setBounds(106, 304, 357, 43);
		contentPane.add(EditOrderButton);
		
		
		JButton EditOrderDeviceButton = new JButton("Робота з замовленням");
		EditOrderDeviceButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				OrderMenu.this.setVisible(false);
				try {
					new EditOrderDevice(OrderMenu.this).setVisible(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		EditOrderDeviceButton.setForeground(Color.DARK_GRAY);
		EditOrderDeviceButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		EditOrderDeviceButton.setBounds(106, 394, 357, 43);
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
					e1.printStackTrace();
				}
			}
		});
		btnBack.setBounds(448, 485, 97, 25);
		contentPane.add(btnBack);
		
	}
}
