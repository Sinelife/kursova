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
import main.MethodsForFrames;
import view.AuthorisationMenu;
import view_constructor.ComponentInformation;
import view_constructor.DeviceInformation;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

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
	private JTextField ComponentsPriceField;
	private JTextField ComponentNumberField;
	private JTextField CostOfComponentsField;
	private JTextField WorkPriceField;
	private JTextField ProfitPriceField;
	private JTextField SumPriceField;
	
	private static int componentNumberInDevice;
	private static int componentPrice;
	private static int workPrice;
	private static int profitPrice;
	private static int sumPrice;
	
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
		DeviceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		DeviceLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		DeviceLabel.setBounds(38, 13, 479, 25);
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
		
		
		
		
		
		JButton SelectButton = new JButton("ВИБРАТИ");
		SelectButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				device_id_to_look = MethodsForFrames.getDeviceIdByDeviceName(device_name_to_look, device_id_to_look, DeviceComboBox, devices);
				
				ComponentsInDeviceComboBox.removeAllItems();
				try {
					componentsInDevice = dd.getAllComponentsInDevice(device_id_to_look);
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				for(Component component : componentsInDevice) 
				{
					ComponentsInDeviceComboBox.addItem(component.getName());
				}
				
				
				
				try {
					componentNumberInDevice = dd.getAllComponentNumberInDevice(device_id_to_look);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				ComponentNumberInDeviceField.setText(String.valueOf(componentNumberInDevice));
				
				
				Device d = null;
				try {
					d = dd.readDevice(device_id_to_look);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				componentPrice = d.getComponentsPrice();
				ComponentsPriceField.setText(String.valueOf(componentPrice));
				
				workPrice = d.getWorkPrice();
				WorkPriceField.setText(String.valueOf(workPrice));
				
				profitPrice = d.getProfitPrice();
				ProfitPriceField.setText(String.valueOf(profitPrice));
				
				sumPrice = d.getSumPrice();
				SumPriceField.setText(String.valueOf(sumPrice));
			}
		});
		SelectButton.setBounds(38, 109, 97, 25);
		contentPane.add(SelectButton);
		
		
		
		JButton DeviceInfoButton = new JButton("Інформація");
		DeviceInfoButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				device_id_to_look = MethodsForFrames.getDeviceIdByDeviceName(device_name_to_look, device_id_to_look, DeviceComboBox, devices);
				
				ConstructDepartmentDirectorMenu.this.setVisible(false);
				try {
					new DeviceInformation(ConstructDepartmentDirectorMenu.this).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		DeviceInfoButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DeviceInfoButton.setBounds(529, 51, 122, 34);
		contentPane.add(DeviceInfoButton);
		
		JLabel ComponentNumberInDeviceLabel = new JLabel("Кількість компонентів");
		ComponentNumberInDeviceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ComponentNumberInDeviceLabel.setBounds(38, 165, 146, 25);
		contentPane.add(ComponentNumberInDeviceLabel);
		
		JLabel ComponentsPriceLabel = new JLabel("Вартість компонентів");
		ComponentsPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ComponentsPriceLabel.setBounds(38, 246, 146, 25);
		contentPane.add(ComponentsPriceLabel);
		
		JLabel ProfitPriceabel = new JLabel("Прибуток");
		ProfitPriceabel.setHorizontalAlignment(SwingConstants.CENTER);
		ProfitPriceabel.setBounds(389, 246, 146, 25);
		contentPane.add(ProfitPriceabel);
		
		JLabel WorkPriceLabel = new JLabel("Вартість зборки");
		WorkPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		WorkPriceLabel.setBounds(216, 246, 146, 25);
		contentPane.add(WorkPriceLabel);
		
		JLabel SumPriceLabel = new JLabel("Загальна вартість");
		SumPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		SumPriceLabel.setBounds(561, 246, 146, 25);
		contentPane.add(SumPriceLabel);
		
		ComponentNumberInDeviceField = new JTextField();
		ComponentNumberInDeviceField.setHorizontalAlignment(SwingConstants.CENTER);
		ComponentNumberInDeviceField.setColumns(10);
		ComponentNumberInDeviceField.setBounds(38, 194, 146, 22);
		contentPane.add(ComponentNumberInDeviceField);
		
		ComponentsPriceField = new JTextField();
		ComponentsPriceField.setHorizontalAlignment(SwingConstants.CENTER);
		ComponentsPriceField.setColumns(10);
		ComponentsPriceField.setBounds(38, 275, 146, 22);
		contentPane.add(ComponentsPriceField);
		
		WorkPriceField = new JTextField();
		WorkPriceField.setHorizontalAlignment(SwingConstants.CENTER);
		WorkPriceField.setColumns(10);
		WorkPriceField.setBounds(216, 275, 146, 22);
		contentPane.add(WorkPriceField);
		
		ProfitPriceField = new JTextField();
		ProfitPriceField.setHorizontalAlignment(SwingConstants.CENTER);
		ProfitPriceField.setColumns(10);
		ProfitPriceField.setBounds(389, 275, 146, 22);
		contentPane.add(ProfitPriceField);
		
		SumPriceField = new JTextField();
		SumPriceField.setHorizontalAlignment(SwingConstants.CENTER);
		SumPriceField.setColumns(10);
		SumPriceField.setBounds(561, 275, 146, 22);
		contentPane.add(SumPriceField);
	
		
		
		JLabel ComponentInDeviceLabel = new JLabel("Список компонентів в приладі");
		ComponentInDeviceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ComponentInDeviceLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ComponentInDeviceLabel.setBounds(38, 349, 479, 25);
		contentPane.add(ComponentInDeviceLabel);

		
	
		JButton ComponentInfoButton = new JButton("Інформація");
		ComponentInfoButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				component_id_to_look = MethodsForFrames.getComponentIdByComponentName(component_name_to_look, component_id_to_look, ComponentsInDeviceComboBox, componentsInDevice);
				
				ConstructDepartmentDirectorMenu.this.setVisible(false);
				try {
					new ComponentInformation(ConstructDepartmentDirectorMenu.this).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		ComponentInfoButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ComponentInfoButton.setBounds(529, 387, 122, 34);
		contentPane.add(ComponentInfoButton);
		
		
		
		
		JButton SelectComponentButton = new JButton("ВИБРАТИ");
		SelectComponentButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				component_id_to_look = MethodsForFrames.getComponentIdByComponentName(component_name_to_look, component_id_to_look, ComponentsInDeviceComboBox, componentsInDevice);
				
				try {
					componentNumber = dd.getComponentNumberInDevice(device_id_to_look,component_id_to_look);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				ComponentNumberField.setText(String.valueOf(componentNumber));
				
				
				try {
					componentCost = dd.getComponentCostInDevice(device_id_to_look,component_id_to_look);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				CostOfComponentsField.setText(String.valueOf(componentCost));
			}
		});
		SelectComponentButton.setBounds(38, 438, 97, 25);
		contentPane.add(SelectComponentButton);
		
		
		
		
		JLabel ComponentNumberLabel = new JLabel("Кількість компонентів");
		ComponentNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ComponentNumberLabel.setBounds(38, 476, 146, 25);
		contentPane.add(ComponentNumberLabel);
		
		JLabel CostOfComponentsLabel = new JLabel("Вартість компонентів");
		CostOfComponentsLabel.setHorizontalAlignment(SwingConstants.CENTER);
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
