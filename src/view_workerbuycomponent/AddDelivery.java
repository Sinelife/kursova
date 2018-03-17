package view_workerbuycomponent;

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

import java.sql.Date;

import dao.DeliveryDao;
import domain.Delivery;
import view.AuthorisationMenu;

public class AddDelivery extends JFrame 
{

	private JPanel contentPane;
	private JTextField NameField;
	private JTextField StartDateField;
	private JCheckBox PaidCheckBox;
	private JCheckBox ShippedCheckBox;

	public AddDelivery(JFrame parent) 
	{
		DeliveryDao dd = new DeliveryDao();
		Delivery d = new Delivery();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel lblNewLabel = new JLabel("��������� ������ ���������� ����������");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(29, 13, 533, 59);
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
		
		JLabel ShippedLabel = new JLabel("³����������");
		ShippedLabel.setBounds(29, 265, 129, 22);
		contentPane.add(ShippedLabel);
		
		
		NameField = new JTextField();
		NameField.setBounds(161, 143, 350, 22);
		contentPane.add(NameField);
		NameField.setColumns(10);
		
		StartDateField = new JTextField();
		StartDateField.setBounds(161, 185, 350, 22);
		contentPane.add(StartDateField);
		StartDateField.setColumns(10);
		
		PaidCheckBox = new JCheckBox();
		PaidCheckBox.setBounds(161, 228, 113, 25);
		contentPane.add(PaidCheckBox);

		ShippedCheckBox = new JCheckBox();
		ShippedCheckBox.setBounds(161, 262, 113, 25);
		contentPane.add(ShippedCheckBox);
		
		
		JButton AddButton = new JButton("������");
		AddButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				d.setProviderId(ChooseProvider.id_to_choose);
				d.setDeliveryName(NameField.getText());
				d.setStartDate(Date.valueOf(StartDateField.getText()));
				d.setPaid(PaidCheckBox.isSelected());
				d.setShipped(ShippedCheckBox.isSelected());
				try {
					dd.addDelivery(d);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (parent != null)
					parent.setVisible(true);
				AddDelivery.this.setVisible(false);
				AddDelivery.this.dispose();
			}
		});
		AddButton.setBounds(51, 364, 97, 25);
		contentPane.add(AddButton);
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				AddDelivery.this.setVisible(false);
				AddDelivery.this.dispose();
			}
		});
		btnBack.setBounds(488, 364, 97, 25);
		contentPane.add(btnBack);
	}

}
