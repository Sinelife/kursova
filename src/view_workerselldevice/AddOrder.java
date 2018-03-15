package view_workerselldevice;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.sql.Date;

import dao.OrderDao;
import domain.Order;

public class AddOrder extends JFrame 
{

	private JPanel contentPane;
	private JTextField NameField;
	private JTextField StartDateField;
	private JTextField PaidField;

	public AddOrder(JFrame parent) 
	{
		OrderDao od = new OrderDao();
		Order o = new Order();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Додавання нового замовлення");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(103, 13, 421, 59);
		contentPane.add(lblNewLabel);
		
		JLabel NameLabel = new JLabel("Назва замовлення");
		NameLabel.setBounds(29, 143, 175, 22);
		contentPane.add(NameLabel);
		
		JLabel StartDateLabel = new JLabel("Дата початку");
		StartDateLabel.setBounds(29, 185, 175, 22);
		contentPane.add(StartDateLabel);
		
		JLabel PaidLabel = new JLabel("Сплачено");
		PaidLabel.setBounds(29, 231, 175, 22);
		contentPane.add(PaidLabel);
		
		
		NameField = new JTextField();
		NameField.setBounds(216, 143, 350, 22);
		contentPane.add(NameField);
		NameField.setColumns(10);
		
		StartDateField = new JTextField();
		StartDateField.setBounds(216, 185, 350, 22);
		contentPane.add(StartDateField);
		StartDateField.setColumns(10);
		
		PaidField = new JTextField();
		PaidField.setColumns(10);
		PaidField.setBounds(216, 231, 350, 22);
		contentPane.add(PaidField);

		
		JButton AddButton = new JButton("Додати");
		AddButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				o.setClientId(ChooseClient.id_to_choose);
				o.setOrderName(NameField.getText());
				o.setStartDate(Date.valueOf(StartDateField.getText()));
				o.setPaid(Boolean.valueOf(PaidField.getText()));
				try {
					od.addOrder(o);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (parent != null)
					parent.setVisible(true);
				AddOrder.this.setVisible(false);
				AddOrder.this.dispose();
			}
		});
		AddButton.setBounds(51, 364, 97, 25);
		contentPane.add(AddButton);
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				AddOrder.this.setVisible(false);
				AddOrder.this.dispose();
			}
		});
		btnBack.setBounds(488, 364, 97, 25);
		contentPane.add(btnBack);
	}

}
