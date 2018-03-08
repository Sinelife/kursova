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

public class OrderMenu extends JFrame {

	private JPanel contentPane;



	/**
	 * Create the frame.
	 */
	public OrderMenu() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 714, 636);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel MenuTitleLabel = new JLabel("Меню роботи з замовленнями");
		MenuTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		MenuTitleLabel.setBounds(157, 13, 417, 38);
		contentPane.add(MenuTitleLabel);
		
		JButton OrderInClientButton = new JButton("1)Переглянути клієнтів і їх замовлення");
		OrderInClientButton.setHorizontalAlignment(SwingConstants.LEFT);
		OrderInClientButton.setForeground(Color.RED);
		OrderInClientButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		OrderInClientButton.setBounds(42, 111, 456, 43);
		contentPane.add(OrderInClientButton);
		
		JButton AddButton = new JButton("2)Додати нове замовлення");
		AddButton.setHorizontalAlignment(SwingConstants.LEFT);
		AddButton.setForeground(Color.RED);
		AddButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		AddButton.setBounds(42, 191, 456, 43);
		contentPane.add(AddButton);
		
		JButton EditButton = new JButton("3)Редагувати замовлення");
		EditButton.setHorizontalAlignment(SwingConstants.LEFT);
		EditButton.setForeground(Color.RED);
		EditButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		EditButton.setBounds(42, 271, 456, 43);
		contentPane.add(EditButton);
		
		JButton DevicesInOrderInfoButton = new JButton("4)Перегляд приладів в замовленні");
		DevicesInOrderInfoButton.setHorizontalAlignment(SwingConstants.LEFT);
		DevicesInOrderInfoButton.setForeground(Color.RED);
		DevicesInOrderInfoButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		DevicesInOrderInfoButton.setBounds(42, 350, 456, 43);
		contentPane.add(DevicesInOrderInfoButton);
		
		JButton DevicesInOrderChangeButton = new JButton("5)Робота з приладами в замовленні");
		DevicesInOrderChangeButton.setHorizontalAlignment(SwingConstants.LEFT);
		DevicesInOrderChangeButton.setForeground(Color.RED);
		DevicesInOrderChangeButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		DevicesInOrderChangeButton.setBounds(42, 429, 456, 43);
		contentPane.add(DevicesInOrderChangeButton);
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				OrderMenu.this.setVisible(false);
				OrderMenu.this.dispose();
				try {
					new WorkerSalesDepartmentMenu().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBack.setBounds(587, 551, 97, 25);
		contentPane.add(btnBack);
		
	}
}
