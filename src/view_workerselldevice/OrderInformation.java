package view_workerselldevice;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.OrderDao;
import domain.Order;
import view.AuthorisationMenu;
import view_director.SalesDepartmentDirectorMenu;
import java.awt.Color;
import javax.swing.SwingConstants;

public class OrderInformation extends JFrame {

	private JPanel contentPane;
	private JTextField NameField;
	private JTextField StartDateField;
	private JCheckBox PaidCheckBox;
	private JCheckBox ShippedCheckBox;
	private JTextField EndDateField;
	private JTextField SumCostField;
	

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public OrderInformation(JFrame parent) throws SQLException 
	{
		OrderDao od = new OrderDao();
		Order o = new Order();

		
		if(AuthorisationMenu.user_role.equals("director"))
		{
			o = od.readOrder(SalesDepartmentDirectorMenu.order_id_to_look);		
		}
		if(ClientMenu.order_information_check == 1)
		{
			o = od.readOrder(InfoClient.order_id_to_look);
		}
		if(ClientMenu.order_information_check == 2)
		{
			o = od.readOrder(EditOrderDevice.order_id_to_edit);
		}
		if(ClientMenu.order_information_check == 3)
		{
			o = od.readOrder(InfoOrder.order_id_to_look);
		}
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel lblNewLabel = new JLabel("Інформація про замовлення на купівлю");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(0, 13, 628, 59);
		contentPane.add(lblNewLabel);
		
		JLabel NameLabel = new JLabel("Назва замовлення");
		NameLabel.setBounds(68, 109, 120, 22);
		contentPane.add(NameLabel);
		
		JLabel StartDateLabel = new JLabel("Дата початку");
		StartDateLabel.setBounds(68, 151, 120, 22);
		contentPane.add(StartDateLabel);
		
		JLabel PaidLabel = new JLabel("Сплачено");
		PaidLabel.setBounds(68, 197, 120, 22);
		contentPane.add(PaidLabel);
		
		JLabel ShippedLabel = new JLabel("Відвантажено");
		ShippedLabel.setBounds(68, 231, 120, 22);
		contentPane.add(ShippedLabel);	
		
		JLabel EndLabel = new JLabel("Дата кінця");
		EndLabel.setBounds(68, 266, 120, 22);
		contentPane.add(EndLabel);
		
		JLabel SumCostLabel = new JLabel("Сумарна вартість");
		SumCostLabel.setBounds(68, 301, 120, 22);
		contentPane.add(SumCostLabel);

		
		
		NameField = new JTextField();
		NameField.setBackground(Color.WHITE);
		NameField.setEditable(false);
		NameField.setBounds(200, 109, 350, 22);
		contentPane.add(NameField);
		NameField.setColumns(10);
		NameField.setText(o.getOrderName());
		
		StartDateField = new JTextField();
		StartDateField.setBackground(Color.WHITE);
		StartDateField.setEditable(false);
		StartDateField.setBounds(200, 151, 350, 22);
		contentPane.add(StartDateField);
		StartDateField.setColumns(10);
		StartDateField.setText(String.valueOf(o.getStartDate()));
		
		PaidCheckBox = new JCheckBox();
		PaidCheckBox.setEnabled(false);
		PaidCheckBox.setBounds(200, 194, 25, 25);
		contentPane.add(PaidCheckBox);
		if(o.isPaid() == true)
		{
			PaidCheckBox.setSelected(true);
		}
		else
		{
			PaidCheckBox.setSelected(false);
		}
		
		ShippedCheckBox = new JCheckBox();
		ShippedCheckBox.setEnabled(false);
		ShippedCheckBox.setBounds(200, 228, 25, 25);
		contentPane.add(ShippedCheckBox);
		if(o.isShipped() == true)
		{
			ShippedCheckBox.setSelected(true);
		}
		else
		{
			ShippedCheckBox.setSelected(false);
		}
		
		
		EndDateField = new JTextField();
		EndDateField.setText("null");
		EndDateField.setEditable(false);
		EndDateField.setColumns(10);
		EndDateField.setBackground(Color.WHITE);
		EndDateField.setBounds(200, 266, 350, 22);
		contentPane.add(EndDateField);
		EndDateField.setText(String.valueOf(o.getEndDate()));
		
		
		SumCostField = new JTextField();
		SumCostField.setText("null");
		SumCostField.setEditable(false);
		SumCostField.setColumns(10);
		SumCostField.setBackground(Color.WHITE);
		SumCostField.setBounds(200, 301, 350, 22);
		contentPane.add(SumCostField);
		SumCostField.setText(String.valueOf(o.getSumCost()));
		
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				OrderInformation.this.setVisible(false);
				OrderInformation.this.dispose();
			}
		});
		btnBack.setBounds(503, 358, 97, 25);
		contentPane.add(btnBack);
	}

}
