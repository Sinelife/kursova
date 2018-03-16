package view_workerbuycomponent;

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

import dao.DeliveryDao;
import domain.Delivery;
import javax.swing.JCheckBox;

public class EditDelivery extends JFrame {

	private JPanel contentPane;
	private JTextField NameField;
	private JTextField StartDateField;

	public static int delivery_id_to_edit;
	public static String delivery_name_to_edit;
	
	Delivery d;
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public EditDelivery(JFrame parent) throws SQLException 
	{
		DeliveryDao dd = new DeliveryDao();
		List<Delivery> deliveries = dd.getAllFromProvider(ChooseProvider.id_to_choose);
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 759, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Редагування замовлення постачання");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(145, 13, 525, 59);
		contentPane.add(lblNewLabel);
		
		JComboBox<String> DeliveryComboBox = new JComboBox<String>();
		DeliveryComboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		DeliveryComboBox.setBounds(39, 85, 559, 34);
		contentPane.add(DeliveryComboBox);
		for(Delivery delivery : deliveries) 
		{
			DeliveryComboBox.addItem(delivery.getDeliveryName());
		}
		
		JLabel NameLabel = new JLabel("Назва замовлення");
		NameLabel.setBounds(39, 207, 133, 22);
		contentPane.add(NameLabel);
		
		JLabel StartDateLabel = new JLabel("Дата початку");
		StartDateLabel.setBounds(39, 249, 133, 22);
		contentPane.add(StartDateLabel);
		
		JLabel PaidLabel = new JLabel("Сплачено");
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
		
		
		
		JButton SelectButton = new JButton("Вибрати");
		SelectButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				delivery_name_to_edit = String.valueOf(DeliveryComboBox.getSelectedItem());
				for(Delivery delivery : deliveries) 
				{
					if(delivery.getDeliveryName().equals(delivery_name_to_edit))
					{
						delivery_id_to_edit = delivery.getId();
						break;
					}
				}
				try {
					d = dd.readDelivery(delivery_id_to_edit);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				NameField.setText(d.getDeliveryName());
				StartDateField.setText(String.valueOf(d.getStartDate()));
				if(d.getPaid() == true)
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
		
		
		
		JButton button = new JButton("Редагувати");
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				d.setDeliveryName(NameField.getText());
				d.setStartDate(Date.valueOf(StartDateField.getText()));
				d.setPaid(PaidCheckBox.isSelected());
				try {
					dd.updateDelivery(d);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				EditDelivery.this.setVisible(false);
				EditDelivery.this.dispose();
				new DeliveryMenu().setVisible(true);
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
				EditDelivery.this.setVisible(false);
				EditDelivery.this.dispose();
			}
		});
		btnBack.setBounds(632, 378, 97, 25);
		contentPane.add(btnBack);
	}
}
