package view_workerbuycomponent;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.DeliveryComponentDao;
import dao.DeliveryDao;
import domain.Delivery;
import domain.DeliveryComponent;

public class InfoDelivery extends JFrame 
{

	private JPanel contentPane;
	public static int delivery_id_to_look;
	public static String delivery_name_to_look;

	public List<DeliveryComponent> ComponentsInfoInDelivery;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public InfoDelivery(JFrame parent) throws SQLException 
	{
		DeliveryDao od = new DeliveryDao();
		List<Delivery> deliveries = od.getAllFromProvider(ChooseProvider.id_to_choose);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 784, 507);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Перегляд інформації про замовлення постачання");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(88, 25, 615, 59);
		contentPane.add(lblNewLabel);
		
		JLabel DeliveryLabel = new JLabel("Список замовлень постачання");
		DeliveryLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		DeliveryLabel.setBounds(12, 113, 724, 25);
		contentPane.add(DeliveryLabel);

		JComboBox<String> DeliveryComboBox = new JComboBox<String>();
		DeliveryComboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		DeliveryComboBox.setBounds(12, 149, 724, 34);
		contentPane.add(DeliveryComboBox);
		for(Delivery delivery : deliveries) 
		{
			DeliveryComboBox.addItem(delivery.getDeliveryName());
		}
		
		
		JComboBox<String> ComponentInDeliveryComboBox = new JComboBox<String>();
		ComponentInDeliveryComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ComponentInDeliveryComboBox.setBounds(12, 285, 724, 34);
		contentPane.add(ComponentInDeliveryComboBox);
		
		
		
		JButton SelectDeliveryButton = new JButton("Вибрати");
		SelectDeliveryButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ComponentInDeliveryComboBox.removeAllItems();
				
				delivery_name_to_look = String.valueOf(DeliveryComboBox.getSelectedItem());
				for(Delivery delivery : deliveries) 
				{
					delivery_id_to_look = delivery.getId();
					if(delivery.getDeliveryName().equals(delivery_name_to_look))
					{
						break;
					}
				}
				DeliveryComponentDao dcd = new DeliveryComponentDao();
				try {
					ComponentsInfoInDelivery = dcd.getAllFromDelivery(delivery_id_to_look);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				for(DeliveryComponent delivery_component : ComponentsInfoInDelivery)
				{
					String component_name = null;
					try {
						component_name = dcd.getComponentNameById(delivery_id_to_look, delivery_component.getComponentId());
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
					String component_type = null;
					try {
						component_type = dcd.getComponentTypeById(delivery_id_to_look, delivery_component.getComponentId());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					String info = "Назва: " + component_name + 
							"  Тип: " + component_type +
							"  Кількість: " + delivery_component.getNumber() + 
							"  Ціна: " + delivery_component.getPrice();
					ComponentInDeliveryComboBox.addItem(info);
				}

			}
		});
		SelectDeliveryButton.setBounds(12, 201, 97, 25);
		contentPane.add(SelectDeliveryButton);
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				InfoDelivery.this.setVisible(false);
				InfoDelivery.this.dispose();
			}
		});
		btnBack.setBounds(657, 422, 97, 25);
		contentPane.add(btnBack);
	}

}
