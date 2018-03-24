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
import view_admin.WorkerSalesDepartmentAdminMenu;

public class ClientMenu extends JFrame {

	private JPanel contentPane;



	/**
	 * Create the frame.
	 */
	public ClientMenu() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 548, 531);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel MenuTitleLabel = new JLabel("Робота з клієнтами");
		MenuTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MenuTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		MenuTitleLabel.setBounds(0, 13, 530, 38);
		contentPane.add(MenuTitleLabel);
		
		JButton InfoButton = new JButton("Переглянути інформацію про наявних клієнтів");
		InfoButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ClientMenu.this.setVisible(false);
				try {
					new InfoClient(ClientMenu.this).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		InfoButton.setForeground(Color.DARK_GRAY);
		InfoButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		InfoButton.setBounds(42, 111, 438, 43);
		contentPane.add(InfoButton);
		
		JButton AddButton = new JButton("Додати нового клієнта");
		AddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ClientMenu.this.setVisible(false);
				new AddClient(ClientMenu.this).setVisible(true); 
			}
		});
		AddButton.setForeground(Color.BLACK);
		AddButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		AddButton.setBounds(42, 191, 438, 43);
		contentPane.add(AddButton);
		
		JButton EditButton = new JButton("Редагувати інформацію про клієнта");
		EditButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ClientMenu.this.setVisible(false);
				try {
					new EditClient(ClientMenu.this).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}
		});
		EditButton.setForeground(Color.BLACK);
		EditButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		EditButton.setBounds(42, 271, 438, 43);
		contentPane.add(EditButton);
		
		
		JButton ChooseButton = new JButton("Обрати клієнта для подільшої роботи з ним");
		ChooseButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ClientMenu.this.setVisible(false);
				try {
					new ChooseClient().setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}
		});
		ChooseButton.setForeground(Color.DARK_GRAY);
		ChooseButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		ChooseButton.setBounds(42, 354, 438, 43);
		contentPane.add(ChooseButton);
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(AuthorisationMenu.user_role.equals("worker_of_sales_department"))
				{
					ClientMenu.this.setVisible(false);
					ClientMenu.this.dispose();
					try {
						new WorkerSalesDepartmentMenu().setVisible(true);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				if(AuthorisationMenu.user_role.equals("admin"))
				{
					ClientMenu.this.setVisible(false);
					ClientMenu.this.dispose();
					try {
						new WorkerSalesDepartmentAdminMenu().setVisible(true);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnBack.setBounds(383, 446, 97, 25);
		contentPane.add(btnBack);
		
	}

}
