package view_director;

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

import dao.DeviceDao;
import domain.Component;
import domain.Device;
import view.AuthorisationMenu;
import view_Constructor.ComponentInformation;
import view_Constructor.DeviceInformation;
import javax.swing.JTextField;

public class ConstructDepartmentDirectorMenu extends JFrame 
{

	private JPanel contentPane;
	
	public static int device_id_to_look;
	public static String device_name_to_look;
	public static int component_id_to_look;
	public static String component_name_to_look;

	private static List<Component> componentsInDevice;
	private static List<Device> devices;
	
	static JComboBox<String> DeviceComboBox;
	JComboBox<String> ComponentsInDeviceComboBox;
	
	private JTextField ComponentNumberInDeviceField;
	private JTextField CostOfComponentsInDeviceField;
	private JTextField ComponentNumberField;
	private JTextField CostOfComponentsField;
	
	private static int componentNumberInDevice;
	private static int componentCostInDevice;
	private static int componentNumber;
	private static int componentCost;



	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public ConstructDepartmentDirectorMenu(JFrame parent) throws SQLException 
	{
		DeviceDao dd = new DeviceDao();
		devices = dd.getAll();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 784, 682);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel DeviceLabel = new JLabel("Список приладів");
		DeviceLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		DeviceLabel.setBounds(38, 13, 198, 25);
		contentPane.add(DeviceLabel);
		
		DeviceComboBox = new JComboBox<String>();
		DeviceComboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		DeviceComboBox.setBounds(38, 51, 479, 34);
		contentPane.add(DeviceComboBox);
		for(Device device : devices) 
		{
			DeviceComboBox.addItem(device.getName());
		}
		
		ComponentsInDeviceComboBox = new JComboBox<String>();
		ComponentsInDeviceComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ComponentsInDeviceComboBox.setBounds(38, 387, 479, 34);
		contentPane.add(ComponentsInDeviceComboBox);
		
		
		
		
		
		JButton SelectButton = new JButton("Вибрати");
		SelectButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				device_name_to_look = String.valueOf(DeviceComboBox.getSelectedItem());
				for(Device device : devices) 
				{
					device_id_to_look = device.getId();
					if(device.getName().equals(device_name_to_look))
					{
						break;
					}
				}
				
				ComponentsInDeviceComboBox.removeAllItems();
				try {
					componentsInDevice = dd.getAllComponentsInDevice(device_id_to_look);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				for(Component component : componentsInDevice) 
				{
					ComponentsInDeviceComboBox.addItem(component.getName());
				}
				
				
				
				try {
					componentNumberInDevice = dd.getAllComponentNumberInDevice(device_id_to_look);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ComponentNumberInDeviceField.setText(String.valueOf(componentNumberInDevice));
				
				
				try {
					componentCostInDevice = dd.getAllComponentCostInDevice(device_id_to_look);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				CostOfComponentsInDeviceField.setText(String.valueOf(componentCostInDevice));

			}
		});
		SelectButton.setBounds(38, 109, 97, 25);
		contentPane.add(SelectButton);
		
		
		
		JButton DeviceInfoButton = new JButton("Інформація");
		DeviceInfoButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				device_name_to_look = String.valueOf(DeviceComboBox.getSelectedItem());
				for (Device device : devices) {
					device_id_to_look = device.getId();
					if (device.getName().equals(device_name_to_look)) 
					{
						break;
					}
				}
				ConstructDepartmentDirectorMenu.this.setVisible(false);
				try {
					new DeviceInformation(ConstructDepartmentDirectorMenu.this).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		DeviceInfoButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DeviceInfoButton.setBounds(529, 51, 122, 34);
		contentPane.add(DeviceInfoButton);
		
		JLabel ComponentNumberInDeviceLabel = new JLabel("Кількість компонентів");
		ComponentNumberInDeviceLabel.setBounds(38, 165, 146, 25);
		contentPane.add(ComponentNumberInDeviceLabel);
		
		JLabel CostOfComponentsInDeviceLabel = new JLabel("Вартість компонентів.");
		CostOfComponentsInDeviceLabel.setBounds(38, 246, 146, 25);
		contentPane.add(CostOfComponentsInDeviceLabel);
		
		ComponentNumberInDeviceField = new JTextField();
		ComponentNumberInDeviceField.setColumns(10);
		ComponentNumberInDeviceField.setBounds(38, 194, 146, 22);
		contentPane.add(ComponentNumberInDeviceField);
		
		CostOfComponentsInDeviceField = new JTextField();
		CostOfComponentsInDeviceField.setColumns(10);
		CostOfComponentsInDeviceField.setBounds(38, 275, 146, 22);
		contentPane.add(CostOfComponentsInDeviceField);
	
	
		
		
		
		JLabel ComponentInDeviceLabel = new JLabel("Список компонентів в приладі");
		ComponentInDeviceLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ComponentInDeviceLabel.setBounds(38, 349, 249, 25);
		contentPane.add(ComponentInDeviceLabel);

		
	
		JButton ComponentInfoButton = new JButton("Інформація");
		ComponentInfoButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				component_name_to_look = String.valueOf(ComponentsInDeviceComboBox.getSelectedItem());
				for (Component component : componentsInDevice) 
				{
					component_id_to_look = component.getId();
					if (component.getName().equals(component_name_to_look)) 
					{
						break;
					}
				}
				ConstructDepartmentDirectorMenu.this.setVisible(false);
				try {
					new ComponentInformation(ConstructDepartmentDirectorMenu.this).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		ComponentInfoButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ComponentInfoButton.setBounds(529, 387, 122, 34);
		contentPane.add(ComponentInfoButton);
		
		
		
		
		JButton SelectComponentButton = new JButton("Вибрати");
		SelectComponentButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				component_name_to_look = String.valueOf(ComponentsInDeviceComboBox.getSelectedItem());
				for (Component component : componentsInDevice) 
				{
					component_id_to_look = component.getId();
					if (component.getName().equals(component_name_to_look)) 
					{
						break;
					}
				}
				
				try {
					componentNumber = dd.getComponentNumberInDevice(device_id_to_look,component_id_to_look);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ComponentNumberField.setText(String.valueOf(componentNumber));
				
				
				try {
					componentCost = dd.getComponentCostInDevice(device_id_to_look,component_id_to_look);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				CostOfComponentsField.setText(String.valueOf(componentCost));
			}
		});
		SelectComponentButton.setBounds(38, 438, 97, 25);
		contentPane.add(SelectComponentButton);
		
		
		
		
		JLabel ComponentNumberLabel = new JLabel("Кількість компонентів");
		ComponentNumberLabel.setBounds(38, 476, 146, 25);
		contentPane.add(ComponentNumberLabel);
		
		JLabel CostOfComponentsLabel = new JLabel("Вартість компонентів");
		CostOfComponentsLabel.setBounds(38, 557, 146, 25);
		contentPane.add(CostOfComponentsLabel);
		
		ComponentNumberField = new JTextField();
		ComponentNumberField.setColumns(10);
		ComponentNumberField.setBounds(38, 505, 146, 22);
		contentPane.add(ComponentNumberField);
		
		CostOfComponentsField = new JTextField();
		CostOfComponentsField.setColumns(10);
		CostOfComponentsField.setBounds(38, 586, 146, 22);
		contentPane.add(CostOfComponentsField);
		
				
				
		
				
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				ConstructDepartmentDirectorMenu.this.setVisible(false);
				ConstructDepartmentDirectorMenu.this.dispose();
			}
		});
		btnBack.setBounds(657, 570, 97, 25);
		contentPane.add(btnBack);

	}
	
	
	
}
