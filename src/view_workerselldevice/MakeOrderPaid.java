package view_workerselldevice;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.OrderDao;
import domain.Order;
import main.MethodsForFrames;
import view.AuthorisationMenu;

public class MakeOrderPaid extends JFrame {

	private JPanel contentPane;
	private JTextField StartDateField;
	private JCheckBox ShippedCheckBox;

	public static int order_id_to_edit;
	
	Order o;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public MakeOrderPaid(JFrame parent) throws SQLException 
	{
		OrderDao od = new OrderDao();
		List<Order> orders = od.getAllFromClientNotPaid(ChooseClient.id_to_choose);
		
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 759, 431);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
	
		
		JLabel lblNewLabel = new JLabel("Відмічання замовлення продажу як оплаченого");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setBounds(0, 13, 741, 59);
		contentPane.add(lblNewLabel);
		
		JComboBox<String> OrderComboBox = new JComboBox<String>();
		OrderComboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		OrderComboBox.setBounds(62, 85, 623, 34);
		contentPane.add(OrderComboBox);
		for(Order order : orders) 
		{
			OrderComboBox.addItem(order.getOrderName());
		}
		
		JLabel StartDateLabel = new JLabel("Дата початку");
		StartDateLabel.setBounds(106, 204, 133, 22);
		contentPane.add(StartDateLabel);
		
		JLabel ShippedLabel = new JLabel("Відвантажено");
		ShippedLabel.setBounds(106, 257, 129, 22);
		contentPane.add(ShippedLabel);
		
		StartDateField = new JTextField();
		StartDateField.setBackground(Color.WHITE);
		StartDateField.setEditable(false);
		StartDateField.setBounds(251, 204, 337, 22);
		contentPane.add(StartDateField);
		StartDateField.setColumns(10);
		
		ShippedCheckBox = new JCheckBox();
		ShippedCheckBox.setEnabled(false);
		ShippedCheckBox.setBounds(247, 254, 32, 25);
		contentPane.add(ShippedCheckBox);
		
		
		
		JButton SelectButton = new JButton("ВИБРАТИ");
		SelectButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				order_id_to_edit = MethodsForFrames.getOrderIdByOrderName(OrderComboBox, orders);
				
				try {
					o = od.readOrder(order_id_to_edit);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				StartDateField.setText(String.valueOf(o.getStartDate()));
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
		SelectButton.setBounds(251, 142, 97, 25);
		contentPane.add(SelectButton);
		
		
		JButton button = new JButton("ОПЛАЧЕНЕ");
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				MethodsForFrames.makeOrderPaid(o);
				MakeOrderPaid.this.setVisible(false);
				MakeOrderPaid.this.dispose();
				new OrderMenu().setVisible(true);
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button.setBounds(251, 302, 123, 25);
		contentPane.add(button);
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				MakeOrderPaid.this.setVisible(false);
				MakeOrderPaid.this.dispose();
			}
		});
		btnBack.setBounds(588, 333, 97, 25);
		contentPane.add(btnBack);
	}

}
