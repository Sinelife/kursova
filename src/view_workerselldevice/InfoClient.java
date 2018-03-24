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
import domain.Client;
import domain.OrderDevice;
import main.MethodsForFrames;
import view.AuthorisationMenu;
import domain.Order;
import javax.swing.SwingConstants;

public class InfoClient extends JFrame {

	private JPanel contentPane;
	public static int client_id_to_look;
	public static String client_name_to_look;
	public static int order_id_to_look;
	public static String order_name_to_look;

	public List<Order> OrdersInClient;
	public List<OrderDevice> DevicesInfoInOrder;
	
	public static int order_information_check;
	
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
		order_information_check = 1;
		
		
		JLabel lblNewLabel = new JLabel("Перегляд інформації про клієнта");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(0, 13, 932, 49);
		contentPane.add(lblNewLabel);
		
		JLabel ClientLabel = new JLabel("Список клієнтів");
		ClientLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ClientLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ClientLabel.setBounds(40, 82, 732, 25);
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
		OrderLabel.setHorizontalAlignment(SwingConstants.CENTER);
		OrderLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		OrderLabel.setBounds(40, 259, 732, 25);
		contentPane.add(OrderLabel);

		
		JComboBox<String> OrderInClientComboBox = new JComboBox<String>();
		OrderInClientComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		OrderInClientComboBox.setBounds(40, 293, 732, 34);
		contentPane.add(OrderInClientComboBox);
		
		JLabel DeviceInfoLabel = new JLabel("Інформація про прилади в замовленні на купівлю");
		DeviceInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		DeviceInfoLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		DeviceInfoLabel.setBounds(40, 448, 732, 25);
		contentPane.add(DeviceInfoLabel);
		
		
		JComboBox<String> DeviceInOrderComboBox = new JComboBox<String>();
		DeviceInOrderComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DeviceInOrderComboBox.setBounds(40, 480, 732, 34);
		contentPane.add(DeviceInOrderComboBox);
		
		
		
		JButton ClientInfoButton = new JButton("Інформація");
		ClientInfoButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				client_id_to_look = MethodsForFrames.getClientIdByClientName(client_name_to_look, client_id_to_look, ClientComboBox, clients);
				InfoClient.this.setVisible(false);
				try {
					new ClientInformation(InfoClient.this).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		ClientInfoButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ClientInfoButton.setBounds(785, 121, 122, 34);
		contentPane.add(ClientInfoButton);
		
		
		JButton SelectClientButton = new JButton("ВИБРАТИ");
		SelectClientButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				client_id_to_look = MethodsForFrames.getClientIdByClientName(client_name_to_look, client_id_to_look, ClientComboBox, clients);
				OrdersInClient = MethodsForFrames.getAllOrdersInClient(client_id_to_look, OrdersInClient, OrderInClientComboBox);
			}
		});
		SelectClientButton.setBounds(326, 171, 106, 25);
		contentPane.add(SelectClientButton);
		
		
		
		
		
		JButton SelectOrderButton = new JButton("ВИБРАТИ");
		SelectOrderButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				order_id_to_look = MethodsForFrames.getOrderIdByOrderName(order_name_to_look, order_id_to_look, OrderInClientComboBox, OrdersInClient);
				MethodsForFrames.getDeviceInfoFromOrder(order_id_to_look, DevicesInfoInOrder, DeviceInOrderComboBox);
			}
		});
		SelectOrderButton.setBounds(326, 346, 106, 25);
		contentPane.add(SelectOrderButton);
		
		
		
		JButton OrderInfoButton = new JButton("Інформація");
		OrderInfoButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				order_id_to_look = MethodsForFrames.getOrderIdByOrderName(order_name_to_look, order_id_to_look, OrderInClientComboBox, OrdersInClient);
				InfoClient.this.setVisible(false);
				try {
					new OrderInformation(InfoClient.this).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		OrderInfoButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		OrderInfoButton.setBounds(784, 293, 122, 34);
		contentPane.add(OrderInfoButton);
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				InfoClient.this.setVisible(false);
				InfoClient.this.dispose();
			}
		});
		btnBack.setBounds(810, 525, 97, 25);
		contentPane.add(btnBack);
		
	}
}
