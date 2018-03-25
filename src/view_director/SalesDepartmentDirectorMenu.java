package view_director;

import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ClientDao;
import dao.OrderDao;
import domain.Client;
import domain.Order;
import main.MethodsForFrames;
import view.AuthorisationMenu;
import view_workerselldevice.ClientInformation;
import view_workerselldevice.OrderInformation;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class SalesDepartmentDirectorMenu extends JFrame {

	private JPanel contentPane;
	private JTextField OrderNumberField;
	private JTextField PaidOrderNumberField;
	private JTextField ShippedOrderNumberField;
	private JTextField DeviceNumberField;
	private JTextField PaidDeviceNumberField;
	private JTextField ShippedDeviceNumberField;
	private JTextField AllMoneyField;
	private JTextField PaidMoneyField;
	private JTextField ShippedMoneyField;

	public static int client_id_to_look;
	public static String client_name_to_look;
	public static int order_id_to_look;
	public static String order_name_to_look;
	
	private static int orderNumber;
	private static int paidOrderNumber;
	private static int shippedOrderNumber;
	private static int allMoney;
	private static int paidMoney;
	private static int shippedMoney;
	private static int DeviceNumber;
	private static int paidDeviceNumber;
	private static int shippedDeviceNumber;
	
	private static List<Order> ordersInClient;
	private static List<Client> clients;

	static JComboBox<String> ClientComboBox;
	JComboBox<String> OrdersInClientComboBox;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public SalesDepartmentDirectorMenu(JFrame parent) throws SQLException 
	{
		ClientDao cd = new ClientDao();
		clients = cd.getAll();
		OrderDao od = new OrderDao();	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 833, 625);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel ClientLabel = new JLabel("Список клієнтів");
		ClientLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ClientLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ClientLabel.setBounds(38, 13, 564, 25);
		contentPane.add(ClientLabel);
		
		ClientComboBox = new JComboBox<String>();
		ClientComboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		ClientComboBox.setBounds(38, 51, 564, 34);
		contentPane.add(ClientComboBox);
		for(Client Client : clients) 
		{
			ClientComboBox.addItem(Client.getName());
		}
		
		JLabel OrderInClientLabel = new JLabel("Список замовлень на купівлю");
		OrderInClientLabel.setHorizontalAlignment(SwingConstants.CENTER);
		OrderInClientLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		OrderInClientLabel.setBounds(38, 425, 564, 25);
		contentPane.add(OrderInClientLabel);
		
		OrdersInClientComboBox = new JComboBox<String>();
		OrdersInClientComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		OrdersInClientComboBox.setBounds(38, 463, 564, 34);
		contentPane.add(OrdersInClientComboBox);
		
		
		
		
		
		JButton SelectButton = new JButton("ВИБРАТИ");
		SelectButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				client_id_to_look = MethodsForFrames.getClientIdByClientName(client_name_to_look, client_id_to_look, ClientComboBox, clients);
						
				OrdersInClientComboBox.removeAllItems();
				try {
					ordersInClient = od.getAllFromClient(client_id_to_look);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				for(Order order : ordersInClient) 
				{
					OrdersInClientComboBox.addItem(order.getOrderName());
				}
				
				
				
				
				try {
					orderNumber = cd.getOrderNumberOfClient(client_id_to_look);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				OrderNumberField.setText(String.valueOf(orderNumber));
				
				try {
					paidOrderNumber = cd.getPaidOrderNumberOfClient(client_id_to_look);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PaidOrderNumberField.setText(String.valueOf(paidOrderNumber));
				
				try {
					shippedOrderNumber = cd.getShippedOrderNumberOfClient(client_id_to_look);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ShippedOrderNumberField.setText(String.valueOf(shippedOrderNumber));
				
				
				
				try {
					allMoney = cd.getAllMoneyOfClient(client_id_to_look);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				AllMoneyField.setText(String.valueOf(allMoney));
				
				try {
					paidMoney = cd.getPaidMoneyOfClient(client_id_to_look);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PaidMoneyField.setText(String.valueOf(paidMoney));
				
				try {
					shippedMoney = cd.getShippedMoneyOfClient(client_id_to_look);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ShippedMoneyField.setText(String.valueOf(shippedMoney));
				
				
				
				try {
					DeviceNumber = cd.getDeviceNumberOfClient(client_id_to_look);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				DeviceNumberField.setText(String.valueOf(DeviceNumber));
				
				try {
					paidDeviceNumber = cd.getPaidDeviceNumberOfClient(client_id_to_look);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PaidDeviceNumberField.setText(String.valueOf(paidDeviceNumber));
				
				try {
					shippedDeviceNumber = cd.getShippedDeviceNumberOfClient(client_id_to_look);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ShippedDeviceNumberField.setText(String.valueOf(shippedDeviceNumber));
			}
		});
		SelectButton.setBounds(38, 109, 97, 25);
		contentPane.add(SelectButton);
				
				
				
				
		JButton InfoButton = new JButton("Інформація");
		InfoButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				client_id_to_look = MethodsForFrames.getClientIdByClientName(client_name_to_look, client_id_to_look, ClientComboBox, clients);
				
				SalesDepartmentDirectorMenu.this.setVisible(false);
				try {
					new ClientInformation(SalesDepartmentDirectorMenu.this).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		InfoButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		InfoButton.setBounds(614, 54, 122, 34);
		contentPane.add(InfoButton);

		
		
		
		JButton SelectAllButton = new JButton("ВИБРАТИ ВСІХ");
		SelectAllButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try {
					orderNumber = cd.getOrderNumber();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				OrderNumberField.setText(String.valueOf(orderNumber));
				
				try {
					paidOrderNumber = cd.getPaidOrderNumber();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PaidOrderNumberField.setText(String.valueOf(paidOrderNumber));
				
				try {
					shippedOrderNumber = cd.getShippedOrderNumber();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ShippedOrderNumberField.setText(String.valueOf(shippedOrderNumber));
				
				
				
				try {
					allMoney = cd.getAllMoney();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				AllMoneyField.setText(String.valueOf(allMoney));
				
				try {
					paidMoney = cd.getPaidMoney();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PaidMoneyField.setText(String.valueOf(paidMoney));
				
				try {
					shippedMoney = cd.getShippedMoney();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ShippedMoneyField.setText(String.valueOf(shippedMoney));
				
				
				
				try {
					DeviceNumber = cd.getDeviceNumber();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				DeviceNumberField.setText(String.valueOf(DeviceNumber));
				
				try {
					paidDeviceNumber = cd.getPaidDeviceNumber();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PaidDeviceNumberField.setText(String.valueOf(paidDeviceNumber));
				
				try {
					shippedDeviceNumber = cd.getShippedDeviceNumber();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ShippedDeviceNumberField.setText(String.valueOf(shippedDeviceNumber));
			}
		});
		SelectAllButton.setBounds(171, 109, 135, 25);
		contentPane.add(SelectAllButton);

		
		
		
		JLabel OrderNumberLabel = new JLabel("Кількість замовлень");
		OrderNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		OrderNumberLabel.setBounds(38, 156, 198, 25);
		contentPane.add(OrderNumberLabel);
		
		JLabel PaidOrderNumberLabel = new JLabel("Кількість оплачених замовлень");
		PaidOrderNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PaidOrderNumberLabel.setBounds(265, 156, 198, 25);
		contentPane.add(PaidOrderNumberLabel);
		
		JLabel ShippedOrderNumberLabel = new JLabel("Кількість відвантажених замовлень");
		ShippedOrderNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ShippedOrderNumberLabel.setBounds(506, 160, 230, 25);
		contentPane.add(ShippedOrderNumberLabel);
		
		JLabel DeviceNumberLabel = new JLabel("Кількість приладів");
		DeviceNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		DeviceNumberLabel.setBounds(38, 253, 198, 25);
		contentPane.add(DeviceNumberLabel);
		
		JLabel PaidDeviceNumberLabel = new JLabel("Кількість куплених приладів");
		PaidDeviceNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PaidDeviceNumberLabel.setBounds(265, 253, 198, 25);
		contentPane.add(PaidDeviceNumberLabel);
		
		JLabel ShippedDeviceNumberLabel = new JLabel("Кількість відвантажених приладів");
		ShippedDeviceNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ShippedDeviceNumberLabel.setBounds(506, 257, 230, 25);
		contentPane.add(ShippedDeviceNumberLabel);
		
		JLabel SummaryMoneyLabel = new JLabel("Загальна вартість замовлень");
		SummaryMoneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		SummaryMoneyLabel.setBounds(38, 348, 198, 25);
		contentPane.add(SummaryMoneyLabel);
		
		JLabel PaidMoneyLabel = new JLabel("Вартість оплачених замовлень");
		PaidMoneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PaidMoneyLabel.setBounds(265, 348, 198, 25);
		contentPane.add(PaidMoneyLabel);
		
		JLabel ShippedMoneyLabel = new JLabel("Вартість відвантажених замовлень");
		ShippedMoneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ShippedMoneyLabel.setBounds(506, 352, 230, 25);
		contentPane.add(ShippedMoneyLabel);
		
		OrderNumberField = new JTextField();
		OrderNumberField.setHorizontalAlignment(SwingConstants.CENTER);
		OrderNumberField.setBounds(38, 185, 198, 22);
		contentPane.add(OrderNumberField);
		OrderNumberField.setColumns(10);
		
		PaidOrderNumberField = new JTextField();
		PaidOrderNumberField.setHorizontalAlignment(SwingConstants.CENTER);
		PaidOrderNumberField.setColumns(10);
		PaidOrderNumberField.setBounds(265, 185, 198, 22);
		contentPane.add(PaidOrderNumberField);
		
		ShippedOrderNumberField = new JTextField();
		ShippedOrderNumberField.setHorizontalAlignment(SwingConstants.CENTER);
		ShippedOrderNumberField.setColumns(10);
		ShippedOrderNumberField.setBounds(506, 185, 230, 22);
		contentPane.add(ShippedOrderNumberField);
		
		DeviceNumberField = new JTextField();
		DeviceNumberField.setHorizontalAlignment(SwingConstants.CENTER);
		DeviceNumberField.setColumns(10);
		DeviceNumberField.setBounds(38, 282, 198, 22);
		contentPane.add(DeviceNumberField);
		
		PaidDeviceNumberField = new JTextField();
		PaidDeviceNumberField.setHorizontalAlignment(SwingConstants.CENTER);
		PaidDeviceNumberField.setColumns(10);
		PaidDeviceNumberField.setBounds(265, 282, 198, 22);
		contentPane.add(PaidDeviceNumberField);
		
		ShippedDeviceNumberField = new JTextField();
		ShippedDeviceNumberField.setHorizontalAlignment(SwingConstants.CENTER);
		ShippedDeviceNumberField.setColumns(10);
		ShippedDeviceNumberField.setBounds(506, 282, 230, 22);
		contentPane.add(ShippedDeviceNumberField);
		
		AllMoneyField = new JTextField();
		AllMoneyField.setHorizontalAlignment(SwingConstants.CENTER);
		AllMoneyField.setColumns(10);
		AllMoneyField.setBounds(38, 377, 198, 22);
		contentPane.add(AllMoneyField);
		
		PaidMoneyField = new JTextField();
		PaidMoneyField.setHorizontalAlignment(SwingConstants.CENTER);
		PaidMoneyField.setColumns(10);
		PaidMoneyField.setBounds(265, 377, 198, 22);
		contentPane.add(PaidMoneyField);
		
		ShippedMoneyField = new JTextField();
		ShippedMoneyField.setHorizontalAlignment(SwingConstants.CENTER);
		ShippedMoneyField.setColumns(10);
		ShippedMoneyField.setBounds(506, 377, 230, 22);
		contentPane.add(ShippedMoneyField);
		
		
		
		JButton OrderInfoButton = new JButton("Інформація");
		OrderInfoButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				order_id_to_look = MethodsForFrames.getOrderIdByOrderName(order_name_to_look, order_id_to_look, OrdersInClientComboBox, ordersInClient);
				
				SalesDepartmentDirectorMenu.this.setVisible(false);
				try {
					new OrderInformation(SalesDepartmentDirectorMenu.this).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		OrderInfoButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		OrderInfoButton.setBounds(614, 463, 122, 34);
		contentPane.add(OrderInfoButton);
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				SalesDepartmentDirectorMenu.this.setVisible(false);
				SalesDepartmentDirectorMenu.this.dispose();
			}
		});
		btnBack.setBounds(706, 540, 97, 25);
		contentPane.add(btnBack);
	}

}
