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
import view_admin.WorkerSalesDepartmentAdminMenu;
import view_workerselldevice.WorkerSalesDepartmentMenu;

public class ProviderMenu extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public ProviderMenu() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 603, 531);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel MenuTitleLabel = new JLabel("Робота з постачальниками");
		MenuTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MenuTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		MenuTitleLabel.setBounds(46, 13, 486, 38);
		contentPane.add(MenuTitleLabel);
		
		JButton InfoButton = new JButton("Переглянути інформацію про наявних постачальників");
		InfoButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ProviderMenu.this.setVisible(false);
				try {
					new InfoProvider(ProviderMenu.this).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		InfoButton.setForeground(Color.DARK_GRAY);
		InfoButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		InfoButton.setBounds(46, 97, 486, 43);
		contentPane.add(InfoButton);
		
		JButton AddButton = new JButton("Додати нового постачальника");
		AddButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ProviderMenu.this.setVisible(false);
				try {
					new AddProvider(ProviderMenu.this).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}
		});
		AddButton.setForeground(Color.BLACK);
		AddButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		AddButton.setBounds(46, 177, 486, 43);
		contentPane.add(AddButton);
		
		JButton EditButton = new JButton("Редагувати інформацію про постачальника");
		EditButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ProviderMenu.this.setVisible(false);
				try {
					new EditProvider(ProviderMenu.this).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}
		});
		EditButton.setForeground(Color.BLACK);
		EditButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		EditButton.setBounds(46, 257, 486, 43);
		contentPane.add(EditButton);
		
		
		JButton ChooseButton = new JButton("Обрати постачальника для подальшої роботи з ним");
		ChooseButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ProviderMenu.this.setVisible(false);
				try {
					new ChooseProvider().setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}
		});
		ChooseButton.setForeground(Color.BLACK);
		ChooseButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		ChooseButton.setBounds(46, 340, 486, 43);
		contentPane.add(ChooseButton);
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(AuthorisationMenu.user_role.equals("worker_of_delivery_department"))
				{
					ProviderMenu.this.setVisible(false);
					ProviderMenu.this.dispose();
					try {
						new WorkerSalesDepartmentMenu().setVisible(true);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				if(AuthorisationMenu.user_role.equals("admin"))
				{
					ProviderMenu.this.setVisible(false);
					ProviderMenu.this.dispose();
					try {
						new WorkerSalesDepartmentAdminMenu().setVisible(true);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnBack.setBounds(435, 429, 97, 25);
		contentPane.add(btnBack);
		
	}
}
