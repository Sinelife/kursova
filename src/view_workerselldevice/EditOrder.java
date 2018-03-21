package view_workerselldevice;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import main.MethodsForFrames;
import view.AuthorisationMenu;

import javax.swing.JCheckBox;

public class EditOrder extends JFrame {

	private JPanel contentPane;
	private JTextField StartDateField;
	private JCheckBox PaidCheckBox;
	private JCheckBox ShippedCheckBox;

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
		setBounds(100, 100, 759, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
	
		
		JLabel lblNewLabel = new JLabel("Редагування замовлення на купівлю");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(103, 13, 479, 59);
		contentPane.add(lblNewLabel);
		
		JComboBox<String> OrderComboBox = new JComboBox<String>();
		OrderComboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		OrderComboBox.setBounds(39, 85, 559, 34);
		contentPane.add(OrderComboBox);
		for(Order order : orders) 
		{
			OrderComboBox.addItem(order.getOrderName());
		}
		
		JLabel StartDateLabel = new JLabel("Дата початку");
		StartDateLabel.setBounds(39, 199, 133, 22);
		contentPane.add(StartDateLabel);
		
		JLabel PaidLabel = new JLabel("Сплачено");
		PaidLabel.setBounds(39, 245, 133, 22);
		contentPane.add(PaidLabel);
		
		JLabel ShippedLabel = new JLabel("Відвантажено");
		ShippedLabel.setBounds(39, 284, 129, 22);
		contentPane.add(ShippedLabel);
		
		StartDateField = new JTextField();
		StartDateField.setBounds(184, 199, 337, 22);
		contentPane.add(StartDateField);
		StartDateField.setColumns(10);
		
		PaidCheckBox = new JCheckBox();
		PaidCheckBox.setBounds(180, 245, 113, 25);
		contentPane.add(PaidCheckBox);
		
		ShippedCheckBox = new JCheckBox();
		ShippedCheckBox.setBounds(180, 281, 113, 25);
		contentPane.add(ShippedCheckBox);
		
		
		
		JButton SelectButton = new JButton("Вибрати");
		SelectButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				order_id_to_edit = MethodsForFrames.getOrderIdByOrderName(order_name_to_edit, order_id_to_edit, OrderComboBox, orders);
				
				try {
					o = od.readOrder(order_id_to_edit);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				StartDateField.setText(String.valueOf(o.getStartDate()));
				if(o.isPaid() == true)
				{
					PaidCheckBox.setSelected(true);
				}
				else
				{
					PaidCheckBox.setSelected(false);
				}
				if(o.isShipped() == true)
				{
					ShippedCheckBox.setSelected(true);
				}
				else
				{
					ShippedCheckBox.setSelected(false);
				}
			}
		});
		SelectButton.setBounds(39, 140, 97, 25);
		contentPane.add(SelectButton);
		
		
		
		JButton button = new JButton("Редагувати");
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				MethodsForFrames.updateOrder(o, StartDateField, PaidCheckBox, ShippedCheckBox);
				EditOrder.this.setVisible(false);
				EditOrder.this.dispose();
				new OrderMenu().setVisible(true);
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button.setBounds(39, 356, 123, 34);
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
		btnBack.setBounds(632, 365, 97, 25);
		contentPane.add(btnBack);
	}
}
