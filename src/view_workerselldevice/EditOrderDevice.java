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

import javax.swing.JTextField;

public class EditOrderDevice extends JFrame {

	private JPanel contentPane;

	public static int order_id_to_edit;
	public static String order_name_to_edit;

	List<Order> orders;
	private List<Device> DevicesInOrderEdit = null;
	private List<Device> DevicesInOrderDelete = null;
	private List<Device> DevicesNotInOrder = null;
	
	private JTextField NumberAddField;
	private JTextField NumberEditField;
	
	JComboBox<String> AddComboBox = new JComboBox<String>();
	JComboBox<String> DeleteComboBox = new JComboBox<String>();
	JComboBox<String> EditComboBox = new JComboBox<String>();
	
	OrderDao od = new OrderDao();
	
	/**
	 * Create the frame.
	 * @throws SQLException
	 */
	public EditOrderDevice(JFrame parent) throws SQLException 
	{
		orders = od.getAllFromClient(ChooseClient.id_to_choose);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 759, 576);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Редагування замовлення");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(103, 13, 421, 59);
		contentPane.add(lblNewLabel);
		
		JComboBox<String> OrderComboBox = new JComboBox<String>();
		OrderComboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		OrderComboBox.setBounds(39, 85, 559, 34);
		contentPane.add(OrderComboBox);
		for(Order order : orders) 
		{
			OrderComboBox.addItem(order.getOrderName());
		}
		
		
		JLabel AddLabel = new JLabel("Додавання");
		AddLabel.setBounds(39, 272, 141, 16);
		contentPane.add(AddLabel);
		
		JLabel DeleteLabel = new JLabel("Видалення");
		DeleteLabel.setBounds(275, 275, 141, 16);
		contentPane.add(DeleteLabel);
		
		JLabel EditLabel = new JLabel("Редагування");
		EditLabel.setBounds(508, 275, 141, 16);
		contentPane.add(EditLabel);
		
		
		AddComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		AddComboBox.setBounds(39, 304, 141, 22);
		contentPane.add(AddComboBox);
		
		DeleteComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		DeleteComboBox.setBounds(275, 304, 141, 22);
		contentPane.add(DeleteComboBox);
		
		EditComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		EditComboBox.setBounds(508, 304, 141, 22);
		contentPane.add(EditComboBox);	
		MyItemListener actionListener = new MyItemListener();
		EditComboBox.addItemListener(actionListener);		
		
		
		JButton SelectButton = new JButton("Вибрати");
		SelectButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				order_name_to_edit = String.valueOf(OrderComboBox.getSelectedItem());
				for(Order order : orders) 
				{
					if(order.getOrderName().equals(order_name_to_edit))
					{
						order_id_to_edit = order.getId();
					}
				}
				
				try {
					DevicesInOrderEdit = od.getAllDevicesInOrder(order_id_to_edit);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					DevicesInOrderDelete = od.getAllDevicesInOrder(order_id_to_edit);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					DevicesNotInOrder = od.getAllDevicesNotInOrder(order_id_to_edit);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
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
		});
		SelectButton.setBounds(39, 162, 97, 25);
		contentPane.add(SelectButton);
		
		
	
		
		NumberAddField = new JTextField();
		NumberAddField.setBounds(39, 372, 141, 25);
		contentPane.add(NumberAddField);
		NumberAddField.setColumns(10);
		
		JLabel NumberAddLabel = new JLabel("Кількість");
		NumberAddLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		NumberAddLabel.setBounds(39, 342, 141, 28);
		contentPane.add(NumberAddLabel);
		
		NumberEditField = new JTextField();
		NumberEditField.setColumns(10);
		NumberEditField.setBounds(508, 372, 141, 25);
		contentPane.add(NumberEditField);
		
		JLabel NumberEditLabel = new JLabel("Кількість");
		NumberEditLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		NumberEditLabel.setBounds(508, 342, 141, 28);
		contentPane.add(NumberEditLabel);
		
		
		
		JButton AddButton = new JButton("Додати");
		AddButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String device_name = String.valueOf(AddComboBox.getSelectedItem());
				int device_id = 0;
				for(Device device : DevicesNotInOrder) 
				{
					if(device.getName().equals(device_name))
					{
						device_id = device.getId();
					}
				}
				
				OrderDevice record = new OrderDevice();
				record.setOrderId(order_id_to_edit);;
				record.setDeviceId(device_id);
				record.setNumber(Integer.valueOf(NumberAddField.getText()));
				try {
					od.addDeviceInOrder(record);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		AddButton.setBounds(39, 433, 125, 28);
		contentPane.add(AddButton);
		
		
		
		JButton DeleteButton = new JButton("Видалити");
		DeleteButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String device_name = String.valueOf(DeleteComboBox.getSelectedItem());
				int device_id = 0;
				for(Device device : DevicesInOrderDelete) 
				{
					if(device.getName().equals(device_name))
					{
						device_id = device.getId();
					}
				}
				OrderDevice record = null;
				try {
					record = od.readDeviceInOrder(order_id_to_edit, device_id);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					od.deleteDeviceFromOrder(record);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		DeleteButton.setBounds(275, 435, 125, 28);
		contentPane.add(DeleteButton);
		
		
		
		JButton EditButton = new JButton("Редагувати");
		EditButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String device_name = String.valueOf(EditComboBox.getSelectedItem());
				int device_id = 0;
				for(Device device : DevicesInOrderEdit) 
				{
					if(device.getName().equals(device_name))
					{
						device_id = device.getId();
					}
				}
				OrderDevice record = null;
				try {
					record = od.readDeviceInOrder(order_id_to_edit, device_id);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				record.setNumber(Integer.valueOf(NumberEditField.getText()));
				try {
					od.updateDeviceInOrder(record);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		EditButton.setBounds(508, 435, 125, 28);
		contentPane.add(EditButton);
		
		
		
		
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				EditOrderDevice.this.setVisible(false);
				EditOrderDevice.this.dispose();
			}
		});
		btnBack.setBounds(632, 491, 97, 25);
		contentPane.add(btnBack);
	}
	
	
	class MyItemListener implements ItemListener 
	{
		// This method is called only if a new item has been selected.
		public void itemStateChanged(ItemEvent evt) 
		{
			if (evt.getStateChange() == ItemEvent.SELECTED) {
				String device_name = String.valueOf(EditComboBox.getSelectedItem());
				int device_id = 0;
				for (Device device : DevicesInOrderEdit) {
					if (device.getName().equals(device_name)) 
					{
						device_id = device.getId();
					}
				}
				OrderDevice record = null;
				try {
					record = od.readDeviceInOrder(order_id_to_edit, device_id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				NumberEditField.setText(String.valueOf(record.getNumber()));

			} else if (evt.getStateChange() == ItemEvent.DESELECTED) {
				NumberEditField.setText("");
			}
		}
	}
}
