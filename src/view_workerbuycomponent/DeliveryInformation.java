package view_workerbuycomponent;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.DeliveryDao;
import domain.Delivery;
import java.awt.Color;

public class DeliveryInformation extends JFrame {

	private JPanel contentPane;
	private JTextField NameField;
	private JTextField StartDateField;
	private JCheckBox PaidCheckBox;
	

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public DeliveryInformation(JFrame parent) throws SQLException 
	{
		DeliveryDao dd = new DeliveryDao();
		Delivery d = dd.readDelivery(EditDeliveryComponent.delivery_id_to_edit);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("���������� ��� ���������� ����������");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(29, 13, 556, 59);
		contentPane.add(lblNewLabel);
		
		JLabel NameLabel = new JLabel("����� ����������");
		NameLabel.setBounds(29, 143, 129, 22);
		contentPane.add(NameLabel);
		
		JLabel StartDateLabel = new JLabel("���� �������");
		StartDateLabel.setBounds(29, 185, 129, 22);
		contentPane.add(StartDateLabel);
		
		JLabel PaidLabel = new JLabel("��������");
		PaidLabel.setBounds(29, 231, 129, 22);
		contentPane.add(PaidLabel);
		
		
		NameField = new JTextField();
		NameField.setBackground(Color.WHITE);
		NameField.setEditable(false);
		NameField.setBounds(161, 143, 350, 22);
		contentPane.add(NameField);
		NameField.setColumns(10);
		NameField.setText(d.getDeliveryName());
		
		StartDateField = new JTextField();
		StartDateField.setBackground(Color.WHITE);
		StartDateField.setEditable(false);
		StartDateField.setBounds(161, 185, 350, 22);
		contentPane.add(StartDateField);
		StartDateField.setColumns(10);
		StartDateField.setText(String.valueOf(d.getStartDate()));
		
		PaidCheckBox = new JCheckBox();
		PaidCheckBox.setEnabled(false);
		PaidCheckBox.setBounds(161, 228, 113, 25);
		contentPane.add(PaidCheckBox);
		PaidCheckBox.setSelected(d.getPaid());
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				DeliveryInformation.this.setVisible(false);
				DeliveryInformation.this.dispose();
			}
		});
		btnBack.setBounds(488, 364, 97, 25);
		contentPane.add(btnBack);
	}

}
