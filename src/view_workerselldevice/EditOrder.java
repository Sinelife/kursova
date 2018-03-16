package view_workerselldevice;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.OrderDao;
import domain.Order;
import view.AuthorisationMenu;

import javax.swing.JCheckBox;

public class EditOrder extends JFrame {

	private JPanel contentPane;
	private JTextField NameField;
	private JTextField StartDateField;

	public static int order_id_to_edit;
	public static String order_name_to_edit;
	
	Order o;
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public EditOrder(JFrame parent) throws SQLException 
	{
		OrderDao od = new OrderDao();
		List<Order> orders = od.getAllFromClient(ChooseClient.id_to_choose);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 759, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
	
		
		JLabel lblNewLabel = new JLabel("����������� ���������� �� ������");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(103, 13, 479, 59);
		contentPane.add(lblNewLabel);
		
		JComboBox<String> OrderComboBox = new JComboBox<String>();
		OrderComboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		OrderComboBox.setBounds(39, 85, 559, 34);
		contentPane.add(OrderComboBox);
		for(Order order : orders) 
		{
			OrderComboBox.addItem(order.getOrderName());
		}
		
		JLabel NameLabel = new JLabel("����� ����������");
		NameLabel.setBounds(39, 207, 133, 22);
		contentPane.add(NameLabel);
		
		JLabel StartDateLabel = new JLabel("���� �������");
		StartDateLabel.setBounds(39, 249, 133, 22);
		contentPane.add(StartDateLabel);
		
		JLabel PaidLabel = new JLabel("��������");
		PaidLabel.setBounds(39, 295, 133, 22);
		contentPane.add(PaidLabel);
		
		
		NameField = new JTextField();
		NameField.setBounds(171, 207, 350, 22);
		contentPane.add(NameField);
		NameField.setColumns(10);
		
		StartDateField = new JTextField();
		StartDateField.setBounds(171, 249, 350, 22);
		contentPane.add(StartDateField);
		StartDateField.setColumns(10);
		
		JCheckBox PaidCheckBox = new JCheckBox();
		PaidCheckBox.setBounds(167, 294, 113, 25);
		contentPane.add(PaidCheckBox);
		
		
		
		JButton SelectButton = new JButton("�������");
		SelectButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				order_name_to_edit = String.valueOf(OrderComboBox.getSelectedItem());
				for(Order order : orders) 
				{
					if(order.getOrderName().equals(order_name_to_edit))
					{
						order_id_to_edit = order.getId();
						break;
					}
				}
				try {
					o = od.readOrder(order_id_to_edit);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				NameField.setText(o.getOrderName());
				StartDateField.setText(String.valueOf(o.getStartDate()));
				if(o.getPaid() == true)
				{
					PaidCheckBox.setSelected(true);
				}
				else
				{
					PaidCheckBox.setSelected(false);
				}
			}
		});
		SelectButton.setBounds(39, 148, 97, 25);
		contentPane.add(SelectButton);
		
		
		
		JButton button = new JButton("����������");
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				o.setOrderName(NameField.getText());
				o.setStartDate(Date.valueOf(StartDateField.getText()));
				o.setPaid(PaidCheckBox.isSelected());
				try {
					od.updateOrder(o);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				EditOrder.this.setVisible(false);
				EditOrder.this.dispose();
				new OrderMenu().setVisible(true);
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button.setBounds(39, 369, 123, 34);
		contentPane.add(button);
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				EditOrder.this.setVisible(false);
				EditOrder.this.dispose();
			}
		});
		btnBack.setBounds(632, 378, 97, 25);
		contentPane.add(btnBack);
	}
}
