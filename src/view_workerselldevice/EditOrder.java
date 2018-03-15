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
import domain.Device;
import domain.Order;

public class EditOrder extends JFrame {

	private JPanel contentPane;
	private JTextField NameField;
	private JTextField StartDateField;
	private JTextField PaidField;

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
		
		JLabel lblNewLabel = new JLabel("Редагування замовлення");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(103, 13, 421, 59);
		contentPane.add(lblNewLabel);
		
		JComboBox<String> OrderComboBox = new JComboBox<String>();
		OrderComboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		OrderComboBox.setBounds(39, 85, 559, 34);
		contentPane.add(OrderComboBox);
		for(Order order : orders) 
		{
			OrderComboBox.addItem(order.getOrderName());
		}
		
		JLabel NameLabel = new JLabel("Назва замовлення");
		NameLabel.setBounds(39, 207, 175, 22);
		contentPane.add(NameLabel);
		
		JLabel StartDateLabel = new JLabel("Дата початку");
		StartDateLabel.setBounds(39, 249, 175, 22);
		contentPane.add(StartDateLabel);
		
		JLabel PaidLabel = new JLabel("Сплачено");
		PaidLabel.setBounds(39, 295, 175, 22);
		contentPane.add(PaidLabel);
		
		
		NameField = new JTextField();
		NameField.setBounds(226, 207, 350, 22);
		contentPane.add(NameField);
		NameField.setColumns(10);
		
		StartDateField = new JTextField();
		StartDateField.setBounds(226, 249, 350, 22);
		contentPane.add(StartDateField);
		StartDateField.setColumns(10);
		
		PaidField = new JTextField();
		PaidField.setColumns(10);
		PaidField.setBounds(226, 295, 350, 22);
		contentPane.add(PaidField);
		
		
		
		JButton SelectButton = new JButton("Вибрати");
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
				PaidField.setText(String.valueOf(o.getPaid()));
				
			}
		});
		SelectButton.setBounds(39, 148, 97, 25);
		contentPane.add(SelectButton);
		
		
		
		JButton button = new JButton("Редагувати");
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				o.setOrderName(NameField.getText());
				o.setStartDate(Date.valueOf(StartDateField.getText()));
				o.setPaid(Boolean.valueOf(PaidField.getText()));
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
