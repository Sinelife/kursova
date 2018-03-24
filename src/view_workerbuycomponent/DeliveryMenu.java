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
		setBounds(100, 100, 626, 597);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel MenuTitleLabel = new JLabel("������ � ������������ ����������");
		MenuTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MenuTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		MenuTitleLabel.setBounds(0, 13, 608, 38);
		contentPane.add(MenuTitleLabel);
		
		JLabel ProviderNameLabel = new JLabel("������������ " + ChooseProvider.name_to_choose);
		ProviderNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		ProviderNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ProviderNameLabel.setBounds(0, 55, 608, 31);
		contentPane.add(ProviderNameLabel);
		
		

		JButton InfoDeliveryButton = new JButton("�������� ���������� ��� ����������");
		InfoDeliveryButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				DeliveryMenu.this.setVisible(false);
				try {
					new InfoDelivery(DeliveryMenu.this).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		InfoDeliveryButton.setForeground(Color.DARK_GRAY);
		InfoDeliveryButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		InfoDeliveryButton.setBounds(105, 124, 400, 43);
		contentPane.add(InfoDeliveryButton);
		
		
		JButton AddDeliveryButton = new JButton("������ ���� ����������");
		AddDeliveryButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				DeliveryMenu.this.setVisible(false);
				new AddDelivery(DeliveryMenu.this).setVisible(true);		
			}
		});
		AddDeliveryButton.setForeground(Color.BLACK);
		AddDeliveryButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		AddDeliveryButton.setBounds(105, 213, 400, 43);
		contentPane.add(AddDeliveryButton);
		
		
		
		JButton EditDeliveryButton = new JButton("���������� ���������� ��� ����������");
		EditDeliveryButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				DeliveryMenu.this.setVisible(false);
				try {
					new EditDelivery(DeliveryMenu.this).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}	
			}
		});
		EditDeliveryButton.setForeground(Color.BLACK);
		EditDeliveryButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		EditDeliveryButton.setBounds(105, 303, 400, 43);
		contentPane.add(EditDeliveryButton);
		
		
		JButton EditDeliveryDeviceButton = new JButton("������ � �����������");
		EditDeliveryDeviceButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				DeliveryMenu.this.setVisible(false);
				try {
					new EditDeliveryComponent(DeliveryMenu.this).setVisible(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		EditDeliveryDeviceButton.setForeground(Color.DARK_GRAY);
		EditDeliveryDeviceButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		EditDeliveryDeviceButton.setBounds(105, 393, 400, 43);
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
					e1.printStackTrace();
				}
			}
		});
		btnBack.setBounds(452, 499, 97, 25);
		contentPane.add(btnBack);
		
	}
}
