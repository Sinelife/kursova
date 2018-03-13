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
import view_admin.WorkerDeliveryDepartmentAdminMenu;
import view_admin.WorkerSalesDepartmentAdminMenu;
import view_workerselldevice.AddClient;
import view_workerselldevice.EditClient;
import view_workerselldevice.InfoClient;
import view_workerselldevice.WorkerSalesDepartmentMenu;

public class ProviderMenu extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public ProviderMenu() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 701, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel MenuTitleLabel = new JLabel("Меню роботи з постачальниками");
		MenuTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		MenuTitleLabel.setBounds(157, 13, 417, 38);
		contentPane.add(MenuTitleLabel);
		
		JButton InfoButton = new JButton("1)Переглянути інформацію про наявних постачальників");
		InfoButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ProviderMenu.this.setVisible(false);
				try {
					new InfoProvider(ProviderMenu.this).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		InfoButton.setHorizontalAlignment(SwingConstants.LEFT);
		InfoButton.setForeground(Color.RED);
		InfoButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		InfoButton.setBounds(42, 111, 456, 43);
		contentPane.add(InfoButton);
		
		JButton AddButton = new JButton("2)Додати інформацію про нового постачальника");
		AddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ProviderMenu.this.setVisible(false);
				new AddProvider(ProviderMenu.this).setVisible(true); 
			}
		});
		AddButton.setHorizontalAlignment(SwingConstants.LEFT);
		AddButton.setForeground(Color.BLACK);
		AddButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		AddButton.setBounds(42, 191, 456, 43);
		contentPane.add(AddButton);
		
		JButton EditButton = new JButton("3)Редагувати інформацію про постачальника");
		EditButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ProviderMenu.this.setVisible(false);
				try {
					new EditProvider(ProviderMenu.this).setVisible(true);
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
		
		JButton DevicesInClientButton = new JButton("4)Компоненти, які постачає постачальник");
		DevicesInClientButton.setHorizontalAlignment(SwingConstants.LEFT);
		DevicesInClientButton.setForeground(Color.RED);
		DevicesInClientButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		DevicesInClientButton.setBounds(42, 350, 456, 43);
		contentPane.add(DevicesInClientButton);
		

		
		
		
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
						new WorkerDeliveryDepartmentMenu().setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(AuthorisationMenu.user_role.equals("admin"))
				{
					ProviderMenu.this.setVisible(false);
					ProviderMenu.this.dispose();
					try {
						new WorkerDeliveryDepartmentAdminMenu().setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnBack.setBounds(574, 455, 97, 25);
		contentPane.add(btnBack);
	}

}
