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

import java.awt.Color;

public class OrderInformation extends JFrame {

	private JPanel contentPane;
	private JTextField NameField;
	private JTextField StartDateField;
	private JCheckBox PaidCheckBox;
	private JCheckBox ShippedCheckBox;
	

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public OrderInformation(JFrame parent) throws SQLException 
	{
		OrderDao od = new OrderDao();
		Order o = od.readOrder(EditOrderDevice.order_id_to_edit);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel lblNewLabel = new JLabel("Інформація про замовлення на купівлю");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(29, 13, 556, 59);
		contentPane.add(lblNewLabel);
		
		JLabel NameLabel = new JLabel("Назва замовлення");
		NameLabel.setBounds(29, 143, 482, 22);
		contentPane.add(NameLabel);
		
		JLabel StartDateLabel = new JLabel("Дата початку");
		StartDateLabel.setBounds(29, 185, 482, 22);
		contentPane.add(StartDateLabel);
		
		JLabel PaidLabel = new JLabel("Сплачено");
		PaidLabel.setBounds(29, 231, 482, 22);
		contentPane.add(PaidLabel);
		
		JLabel ShippedLabel = new JLabel("Відвантажено");
		ShippedLabel.setBounds(29, 265, 482, 22);
		contentPane.add(ShippedLabel);

		
		NameField = new JTextField();
		NameField.setBackground(Color.WHITE);
		NameField.setEditable(false);
		NameField.setBounds(161, 143, 350, 22);
		contentPane.add(NameField);
		NameField.setColumns(10);
		NameField.setText(o.getOrderName());
		
		StartDateField = new JTextField();
		StartDateField.setBackground(Color.WHITE);
		StartDateField.setEditable(false);
		StartDateField.setBounds(161, 185, 350, 22);
		contentPane.add(StartDateField);
		StartDateField.setColumns(10);
		StartDateField.setText(String.valueOf(o.getStartDate()));
		
		PaidCheckBox = new JCheckBox();
		PaidCheckBox.setEnabled(false);
		PaidCheckBox.setBounds(161, 228, 350, 25);
		contentPane.add(PaidCheckBox);
		PaidCheckBox.setSelected(o.isPaid());
		
		ShippedCheckBox = new JCheckBox();
		ShippedCheckBox.setEnabled(false);
		ShippedCheckBox.setBounds(161, 262, 350, 25);
		contentPane.add(ShippedCheckBox);
		ShippedCheckBox.setSelected(o.isShipped());
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				OrderInformation.this.setVisible(false);
				OrderInformation.this.dispose();
			}
		});
		btnBack.setBounds(488, 364, 97, 25);
		contentPane.add(btnBack);
	}

}
