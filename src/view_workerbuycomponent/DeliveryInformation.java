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

import dao.DeliveryDao;
import domain.Delivery;
import view.AuthorisationMenu;
import view_director.DeliveryDepartmentDirectorMenu;

import java.awt.Color;
import javax.swing.SwingConstants;

public class DeliveryInformation extends JFrame {

	private JPanel contentPane;
	private JTextField NameField;
	private JTextField StartDateField;
	private JCheckBox PaidCheckBox;
	private JCheckBox ShippedCheckBox;
	

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public DeliveryInformation(JFrame parent) throws SQLException 
	{
		DeliveryDao dd = new DeliveryDao();
		Delivery d = new Delivery();
		
		if(AuthorisationMenu.user_role.equals("director"))
		{
			d = dd.readDelivery(DeliveryDepartmentDirectorMenu.delivery_id_to_look);		
		}
		else if(InfoProvider.information_check == 1)
		{
			d = dd.readDelivery(InfoProvider.delivery_id_to_look);
		}
		else
		{
			d = dd.readDelivery(EditDeliveryComponent.delivery_id_to_edit);
		}
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 408);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel lblNewLabel = new JLabel("Інформація про замовлення постачання");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(0, 13, 628, 59);
		contentPane.add(lblNewLabel);
		
		JLabel NameLabel = new JLabel("Назва замовлення");
		NameLabel.setBounds(69, 123, 129, 22);
		contentPane.add(NameLabel);
		
		JLabel StartDateLabel = new JLabel("Дата початку");
		StartDateLabel.setBounds(69, 165, 129, 22);
		contentPane.add(StartDateLabel);
		
		JLabel PaidLabel = new JLabel("Сплачено");
		PaidLabel.setBounds(69, 211, 129, 22);
		contentPane.add(PaidLabel);
		
		JLabel ShippedLabel = new JLabel("Відвантажено");
		ShippedLabel.setBounds(69, 245, 129, 22);
		contentPane.add(ShippedLabel);

		
		NameField = new JTextField();
		NameField.setBackground(Color.WHITE);
		NameField.setEditable(false);
		NameField.setBounds(201, 123, 350, 22);
		contentPane.add(NameField);
		NameField.setColumns(10);
		NameField.setText(d.getDeliveryName());
		
		StartDateField = new JTextField();
		StartDateField.setBackground(Color.WHITE);
		StartDateField.setEditable(false);
		StartDateField.setBounds(201, 165, 350, 22);
		contentPane.add(StartDateField);
		StartDateField.setColumns(10);
		StartDateField.setText(String.valueOf(d.getStartDate()));
		
		PaidCheckBox = new JCheckBox();
		PaidCheckBox.setEnabled(false);
		PaidCheckBox.setBounds(201, 208, 32, 25);
		contentPane.add(PaidCheckBox);
		if(d.isPaid() == true)
		{
			PaidCheckBox.setSelected(true);
		}
		else
		{
			PaidCheckBox.setSelected(false);
		}
		
		ShippedCheckBox = new JCheckBox();
		ShippedCheckBox.setEnabled(false);
		ShippedCheckBox.setBounds(201, 242, 32, 25);
		contentPane.add(ShippedCheckBox);
		ShippedCheckBox.setSelected(d.isPaid());
		if(d.isShipped() == true)
		{
			ShippedCheckBox.setSelected(true);
		}
		else
		{
			ShippedCheckBox.setSelected(false);
		}
		
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				DeliveryInformation.this.setVisible(false);
				DeliveryInformation.this.dispose();
			}
		});
		btnBack.setBounds(499, 303, 97, 25);
		contentPane.add(btnBack);
	}

}
