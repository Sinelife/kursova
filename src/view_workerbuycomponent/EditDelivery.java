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

public class EditDelivery extends JFrame {

	private JPanel contentPane;
	private JTextField StartDateField;
	private JCheckBox PaidCheckBox;
	private JCheckBox ShippedCheckBox;

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
		setBounds(100, 100, 759, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel lblNewLabel = new JLabel("Редагування замовлення постачання");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(145, 13, 525, 59);
		contentPane.add(lblNewLabel);
		
		JComboBox<String> DeliveryComboBox = new JComboBox<String>();
		DeliveryComboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		DeliveryComboBox.setBounds(39, 85, 559, 34);
		contentPane.add(DeliveryComboBox);
		for(Delivery delivery : deliveries) 
		{
			DeliveryComboBox.addItem(delivery.getDeliveryName());
		}
		
		JLabel StartDateLabel = new JLabel("Дата початку");
		StartDateLabel.setBounds(39, 197, 133, 22);
		contentPane.add(StartDateLabel);
		
		JLabel PaidLabel = new JLabel("Сплачено");
		PaidLabel.setBounds(39, 243, 133, 22);
		contentPane.add(PaidLabel);
		
		JLabel ShippedLabel = new JLabel("Відвантажено");
		ShippedLabel.setBounds(39, 283, 129, 22);
		contentPane.add(ShippedLabel);
		
		StartDateField = new JTextField();
		StartDateField.setBounds(184, 197, 337, 22);
		contentPane.add(StartDateField);
		StartDateField.setColumns(10);
		
		PaidCheckBox = new JCheckBox();
		PaidCheckBox.setBounds(180, 243, 113, 25);
		contentPane.add(PaidCheckBox);
		
		ShippedCheckBox = new JCheckBox();
		ShippedCheckBox.setBounds(180, 280, 113, 25);
		contentPane.add(ShippedCheckBox);

		
		
		JButton SelectButton = new JButton("Вибрати");
		SelectButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				delivery_id_to_edit = MethodsForFrames.getDeliveryIdByDeliveryName(delivery_name_to_edit, delivery_id_to_edit, DeliveryComboBox, deliveries);

				try {
					d = dd.readDelivery(delivery_id_to_edit);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				StartDateField.setText(String.valueOf(d.getStartDate()));
				if(d.isPaid() == true)
				{
					PaidCheckBox.setSelected(true);
				}
				else
				{
					PaidCheckBox.setSelected(false);
				}
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
		SelectButton.setBounds(39, 142, 97, 25);
		contentPane.add(SelectButton);
		
		
		
		JButton button = new JButton("Редагувати");
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				MethodsForFrames.updateDelivery(d, StartDateField, PaidCheckBox, ShippedCheckBox);
				EditDelivery.this.setVisible(false);
				EditDelivery.this.dispose();
				new DeliveryMenu().setVisible(true);
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button.setBounds(35, 359, 123, 34);
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
		btnBack.setBounds(632, 365, 97, 25);
		contentPane.add(btnBack);
	}
}
