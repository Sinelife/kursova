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
import main.MethodsForFrames;
import view.AuthorisationMenu;

import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.Color;

public class EditDelivery extends JFrame {

	private JPanel contentPane;
	private JTextField StartDateField;
	private JCheckBox ShippedCheckBox;

	public static int delivery_id_to_edit;
	
	Delivery d;
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public EditDelivery(JFrame parent) throws SQLException 
	{
		DeliveryDao dd = new DeliveryDao();
		List<Delivery> deliveries = dd.getAllFromProviderNotPaid(ChooseProvider.id_to_choose);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 759, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel lblNewLabel = new JLabel("Редагування замовлення постачання");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(0, 13, 741, 59);
		contentPane.add(lblNewLabel);
		
		JComboBox<String> DeliveryComboBox = new JComboBox<String>();
		DeliveryComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		DeliveryComboBox.setBounds(41, 85, 667, 34);
		contentPane.add(DeliveryComboBox);
		for(Delivery delivery : deliveries) 
		{
			DeliveryComboBox.addItem(delivery.getDeliveryName());
		}
		
		JLabel StartDateLabel = new JLabel("Дата початку");
		StartDateLabel.setBounds(103, 199, 133, 22);
		contentPane.add(StartDateLabel);
		
		JLabel ShippedLabel = new JLabel("Відвантажено");
		ShippedLabel.setBounds(103, 253, 166, 22);
		contentPane.add(ShippedLabel);
		
		StartDateField = new JTextField();
		StartDateField.setBackground(Color.WHITE);
		StartDateField.setEditable(false);
		StartDateField.setBounds(248, 199, 337, 22);
		contentPane.add(StartDateField);
		StartDateField.setColumns(10);
		
		ShippedCheckBox = new JCheckBox();
		ShippedCheckBox.setBounds(244, 250, 25, 25);
		contentPane.add(ShippedCheckBox);

		
		
		JButton SelectButton = new JButton("ВИБРАТИ");
		SelectButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				delivery_id_to_edit = MethodsForFrames.getDeliveryIdByDeliveryName(DeliveryComboBox, deliveries);

				try {
					d = dd.readDelivery(delivery_id_to_edit);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				StartDateField.setText(String.valueOf(d.getStartDate()));
				if(d.isShipped() == true)
				{
					ShippedCheckBox.setSelected(true);
				}
				else
				{
					ShippedCheckBox.setSelected(false);
				}
			}
		});
		SelectButton.setBounds(248, 146, 97, 25);
		contentPane.add(SelectButton);
		
		
		
		JButton button = new JButton("РЕДАГУВАТИ");
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				MethodsForFrames.updateDelivery(d, ShippedCheckBox);
				EditDelivery.this.setVisible(false);
				EditDelivery.this.dispose();
				new DeliveryMenu().setVisible(true);
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button.setBounds(248, 304, 123, 25);
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
		btnBack.setBounds(632, 346, 97, 25);
		contentPane.add(btnBack);
	}
}
