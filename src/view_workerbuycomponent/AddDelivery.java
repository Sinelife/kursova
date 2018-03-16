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
		
		
		JLabel lblNewLabel = new JLabel("Додавання нового замовлення постачання");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(29, 13, 533, 59);
		contentPane.add(lblNewLabel);
		
		JLabel NameLabel = new JLabel("Назва замовлення");
		NameLabel.setBounds(29, 143, 129, 22);
		contentPane.add(NameLabel);
		
		JLabel StartDateLabel = new JLabel("Дата початку");
		StartDateLabel.setBounds(29, 185, 129, 22);
		contentPane.add(StartDateLabel);
		
		JLabel PaidLabel = new JLabel("Сплачено");
		PaidLabel.setBounds(29, 231, 129, 22);
		contentPane.add(PaidLabel);
		
		
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

		
		JButton AddButton = new JButton("Додати");
		AddButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				d.setProviderId(ChooseProvider.id_to_choose);
				d.setDeliveryName(NameField.getText());
				d.setStartDate(Date.valueOf(StartDateField.getText()));
				d.setPaid(PaidCheckBox.isSelected());
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
