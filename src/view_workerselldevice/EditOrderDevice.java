package view_workerselldevice;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.OrderDao;
import domain.Device;
import domain.Order;
import domain.OrderDevice;
import main.MethodsForFrames;
import view.AuthorisationMenu;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class EditOrderDevice extends JFrame {

	private JPanel contentPane;

	public static int order_id_to_edit;
	public static String order_name_to_edit;

	List<Order> orders;
	private static List<Device> DevicesInOrderEdit = null;
	private static List<Device> DevicesInOrderDelete = null;
	private static List<Device> DevicesNotInOrder = null;
	
	private JTextField NumberAddField;
	private JTextField NumberEditField;
	
	static JComboBox<String> AddComboBox = new JComboBox<String>();
	static JComboBox<String> DeleteComboBox = new JComboBox<String>();
	static JComboBox<String> EditComboBox = new JComboBox<String>();
	
	public static String device_name;
	public static int device_id;
	
	static OrderDao od = new OrderDao();
	
	/**
	 * Create the frame.
	 * @throws SQLException
	 */
	public EditOrderDevice(JFrame parent) throws SQLException 
	{
		orders = od.getAllFromClientNotPaid(ChooseClient.id_to_choose);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 759, 561);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel lblNewLabel = new JLabel("Редагування замовлення на купівлю");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setBounds(0, 13, 741, 49);
		contentPane.add(lblNewLabel);
		
		JComboBox<String> OrderComboBox = new JComboBox<String>();
		OrderComboBox.setFont(new Font("Tahoma", Font.PLAIN, 19));
		OrderComboBox.setBounds(39, 85, 559, 34);
		contentPane.add(OrderComboBox);
		for(Order order : orders) 
		{
			OrderComboBox.addItem(order.getOrderName());
		}
		
		
		JLabel AddLabel = new JLabel("Додавання");
		AddLabel.setBounds(59, 235, 173, 16);
		contentPane.add(AddLabel);
		
		JLabel DeleteLabel = new JLabel("Видалення");
		DeleteLabel.setBounds(295, 238, 178, 16);
		contentPane.add(DeleteLabel);
		
		JLabel EditLabel = new JLabel("Редагування");
		EditLabel.setBounds(528, 238, 173, 16);
		contentPane.add(EditLabel);
		
		
		AddComboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		AddComboBox.setBounds(59, 267, 173, 22);
		contentPane.add(AddComboBox);
		
		DeleteComboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		DeleteComboBox.setBounds(295, 267, 178, 22);
		contentPane.add(DeleteComboBox);
		
		EditComboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		EditComboBox.setBounds(528, 267, 173, 22);
		contentPane.add(EditComboBox);	
		MyItemListener actionListener = new MyItemListener();
		EditComboBox.addItemListener(actionListener);		
		
		
		JButton SelectButton = new JButton("ВИБРАТИ");
		SelectButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				order_id_to_edit = MethodsForFrames.getOrderIdByOrderName(order_name_to_edit, order_id_to_edit, OrderComboBox, orders);
				outputAllDeviceComboBoxes();
			}
		});
		SelectButton.setBounds(270, 146, 97, 25);
		contentPane.add(SelectButton);
		
		
	
		
		NumberAddField = new JTextField();
		NumberAddField.setBounds(59, 335, 173, 25);
		contentPane.add(NumberAddField);
		NumberAddField.setColumns(10);
		
		JLabel NumberAddLabel = new JLabel("Кількість");
		NumberAddLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		NumberAddLabel.setBounds(59, 305, 173, 28);
		contentPane.add(NumberAddLabel);
		
		NumberEditField = new JTextField();
		NumberEditField.setColumns(10);
		NumberEditField.setBounds(528, 335, 173, 25);
		contentPane.add(NumberEditField);
		
		JLabel NumberEditLabel = new JLabel("Кількість");
		NumberEditLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		NumberEditLabel.setBounds(528, 305, 173, 28);
		contentPane.add(NumberEditLabel);
		
		
		
		JButton AddButton = new JButton("Додати");
		AddButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				device_id = MethodsForFrames.getDeviceIdByDeviceName(device_name, device_id, AddComboBox, DevicesNotInOrder);
				try {
					MethodsForFrames.addDevicesInOrder(order_id_to_edit, device_id, NumberAddField);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		AddButton.setBounds(59, 396, 125, 28);
		contentPane.add(AddButton);
		
		
		
		JButton DeleteButton = new JButton("Видалити");
		DeleteButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				device_id = MethodsForFrames.getDeviceIdByDeviceName(device_name, device_id, DeleteComboBox, DevicesInOrderDelete);
				try {
					MethodsForFrames.deleteDevicesFromOrder(order_id_to_edit, device_id);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		DeleteButton.setBounds(295, 398, 125, 28);
		contentPane.add(DeleteButton);
		
		
		
		
		
		JButton EditButton = new JButton("Редагувати");
		EditButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				device_id = MethodsForFrames.getDeviceIdByDeviceName(device_name, device_id, EditComboBox, DevicesInOrderEdit);
				try {
					MethodsForFrames.updateDevicesInOrder(order_id_to_edit, device_id, NumberEditField);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		EditButton.setBounds(528, 398, 125, 28);
		contentPane.add(EditButton);
		
		
		
		JButton btnNewButton = new JButton("Інформація");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				order_id_to_edit = MethodsForFrames.getOrderIdByOrderName(order_name_to_edit, order_id_to_edit, OrderComboBox, orders);			
				ClientMenu.order_information_check = 2;
				EditOrderDevice.this.setVisible(false);
				try {
					new OrderInformation(EditOrderDevice.this).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(610, 85, 119, 34);
		contentPane.add(btnNewButton);
		
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				EditOrderDevice.this.setVisible(false);
				EditOrderDevice.this.dispose();
			}
		});
		btnBack.setBounds(632, 472, 97, 25);
		contentPane.add(btnBack);
	}
	
	
	public static void outputAllDeviceComboBoxes()
	{
		
		try {
			DevicesInOrderEdit = od.getAllDevicesInOrder(order_id_to_edit);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			DevicesInOrderDelete = od.getAllDevicesInOrder(order_id_to_edit);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			DevicesNotInOrder = od.getAllDevicesNotInOrder(order_id_to_edit);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		AddComboBox.removeAllItems();
		DeleteComboBox.removeAllItems();
		EditComboBox.removeAllItems();
		
		for(Device device : DevicesNotInOrder)
		{
			AddComboBox.addItem(device.getName());
		}
		for(Device device : DevicesInOrderDelete)
		{
			DeleteComboBox.addItem(device.getName());
		}
		for(Device device : DevicesInOrderEdit)
		{
			EditComboBox.addItem(device.getName());
		}
	}
	
	
	
	
	
	class MyItemListener implements ItemListener 
	{
		// This method is called only if a new item has been selected.
		public void itemStateChanged(ItemEvent evt) 
		{
			if (evt.getStateChange() == ItemEvent.SELECTED) 
			{
				String device_name = String.valueOf(EditComboBox.getSelectedItem());
				int device_id = 0;
				device_id = MethodsForFrames.getDeviceIdByDeviceName(device_name, device_id, EditComboBox, DevicesInOrderEdit);
				
				OrderDevice record = null;
				try {
					record = od.readDeviceInOrder(order_id_to_edit, device_id);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				NumberEditField.setText(String.valueOf(record.getNumber()));

			} else if (evt.getStateChange() == ItemEvent.DESELECTED) {
				NumberEditField.setText("");
			}
		}
	}
}
