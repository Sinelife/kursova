package view_workerselldevice;

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

import dao.ClientDao;
import dao.OrderDeviceDao;
import domain.Client;
import domain.OrderDevice;
import view.AuthorisationMenu;
import domain.Order;

public class InfoClient extends JFrame {

	private JPanel contentPane;
	public static int client_id_to_look;
	public static String client_name_to_look;
	public static int order_id_to_look;
	public static String order_name_to_look;

	public List<Order> OrdersInClient;
	public List<OrderDevice> DevicesInfoInOrder;
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public InfoClient(JFrame parent) throws SQLException 
	{
		ClientDao cd = new ClientDao();
		List<Client> clients = cd.getAll();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel lblNewLabel = new JLabel("Перегляд інформації про клієнта");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(211, 25, 400, 59);
		contentPane.add(lblNewLabel);
		
		JLabel ClientLabel = new JLabel("Список клієнтів");
		ClientLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ClientLabel.setBounds(40, 82, 615, 25);
		contentPane.add(ClientLabel);

		JComboBox<String> ClientComboBox = new JComboBox<String>();
		ClientComboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		ClientComboBox.setBounds(40, 121, 732, 34);
		contentPane.add(ClientComboBox);
		for(Client client : clients) 
		{
			ClientComboBox.addItem(client.getName());
		}
		
		
		JLabel OrderLabel = new JLabel("Список замовлень на купівлю обраного клієнта");
		OrderLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		OrderLabel.setBounds(40, 259, 615, 25);
		contentPane.add(OrderLabel);

		
		JComboBox<String> OrderInClientComboBox = new JComboBox<String>();
		OrderInClientComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		OrderInClientComboBox.setBounds(40, 293, 732, 34);
		contentPane.add(OrderInClientComboBox);
		
		JLabel DeviceInfoLabel = new JLabel("Інформація про прилади в замовленні на купівлю");
		DeviceInfoLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		DeviceInfoLabel.setBounds(40, 448, 615, 25);
		contentPane.add(DeviceInfoLabel);
		
		
		JComboBox<String> DeviceInOrderComboBox = new JComboBox<String>();
		DeviceInOrderComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DeviceInOrderComboBox.setBounds(40, 480, 732, 34);
		contentPane.add(DeviceInOrderComboBox);
		
		
		
		JButton InfoButton = new JButton("Інформація");
		InfoButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				client_name_to_look = String.valueOf(ClientComboBox.getSelectedItem());
				for(Client client : clients) 
				{
					client_id_to_look = client.getId();
					if(client.getName().equals(client_name_to_look))
					{
						break;
					}
				}
				InfoClient.this.setVisible(false);
				try {
					new ClientInformation(InfoClient.this).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		InfoButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		InfoButton.setBounds(785, 121, 122, 34);
		contentPane.add(InfoButton);
		
		
		JButton SelectClientButton = new JButton("Вибрати");
		SelectClientButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				OrderInClientComboBox.removeAllItems();
				
				client_name_to_look = String.valueOf(ClientComboBox.getSelectedItem());
				for(Client client : clients) 
				{
					client_id_to_look = client.getId();
					if(client.getName().equals(client_name_to_look))
					{
						break;
					}
				}
				
				try {
					OrdersInClient = cd.getAllOrdersInClient(client_id_to_look);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				for(Order order : OrdersInClient)
				{
					OrderInClientComboBox.addItem(order.getOrderName());
				}

			}
		});
		SelectClientButton.setBounds(40, 179, 106, 25);
		contentPane.add(SelectClientButton);
		
		
		
		
		
		JButton SelectOrderButton = new JButton("Вибрати");
		SelectOrderButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				DeviceInOrderComboBox.removeAllItems();
				order_name_to_look = String.valueOf(OrderInClientComboBox.getSelectedItem());
				for(Order order : OrdersInClient) 
				{
					order_id_to_look = order.getId();
					if(order.getOrderName().equals(order_name_to_look))
					{
						break;
					}
				}
				OrderDeviceDao odd = new OrderDeviceDao();
				
				try {
					DevicesInfoInOrder = odd.getAllFromOrder(order_id_to_look);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for(OrderDevice order_device : DevicesInfoInOrder)
				{
					String device_name = null;
					try {
						device_name = odd.getDeviceNameById(order_id_to_look, order_device.getDeviceId());
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
					String device_supply_voltage = null;
					try {
						device_supply_voltage = odd.getSupplyVoltageById(order_id_to_look, order_device.getDeviceId());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					String border_regulation_time = null;
					try {
						border_regulation_time = odd.getBorderRegulationTimeById(order_id_to_look, order_device.getDeviceId());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					String info = "Назва: " + device_name + 
							"  Напруга живлення: " + device_supply_voltage +
							"  Границі регулювання часу: " + border_regulation_time +
							"  Кількість: " + order_device.getNumber();
					DeviceInOrderComboBox.addItem(info);
				}
			}
		});
		SelectOrderButton.setBounds(40, 340, 106, 25);
		contentPane.add(SelectOrderButton);
		
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				InfoClient.this.setVisible(false);
				InfoClient.this.dispose();
			}
		});
		btnBack.setBounds(823, 525, 97, 25);
		contentPane.add(btnBack);
		
	}
}
