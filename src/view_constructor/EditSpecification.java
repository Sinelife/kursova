package view_constructor;

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

import dao.DeviceDao;
import domain.Component;
import domain.ComponentDevice;
import domain.Device;
import main.MethodsForFrames;
import view.AuthorisationMenu;

import javax.swing.JTextField;

public class EditSpecification extends JFrame {

	private JPanel contentPane;
	public static int id_to_choose;
	public static String name_to_choose;
	
	private JTextField NumberAddField;
	private JTextField NumberEditField;
	
	private static List<Component> components_for_delete = null;
	private static List<Component> components_for_add = null;
	private static List<Component> components_for_edit = null;
	
	private static DeviceDao dd = null;
	private List<Device> devices = null;
	
	static JComboBox<String> DeleteComponentComboBox = new JComboBox<String>();
	static JComboBox<String> AddComponentComboBox = new JComboBox<String>();
	static JComboBox<String> EditComponentComboBox = new JComboBox<String>();
	
	public static String c_name;
	public static int c_id;

	/**
	 * Create the frame.
	 * @throws SQLException
	 */
	public EditSpecification(JFrame parent) throws SQLException 
	{
		dd = new DeviceDao();
		devices = dd.getAll();
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 647);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		InfoDevice.device_information_check = 2;
		
		
		JLabel lblNewLabel = new JLabel("Вибір приладу для редагування специфікації");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(46, 25, 559, 59);
		contentPane.add(lblNewLabel);

		JComboBox<String> DeviceComboBox = new JComboBox<String>();
		DeviceComboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		DeviceComboBox.setBounds(40, 121, 504, 34);
		contentPane.add(DeviceComboBox);
		for(Device device : devices) 
		{
			DeviceComboBox.addItem(device.getName());
		}
		
		

		DeleteComponentComboBox.setBounds(28, 302, 164, 34);
		contentPane.add(DeleteComponentComboBox);
		
		AddComponentComboBox.setBounds(263, 302, 180, 34);
		contentPane.add(AddComponentComboBox);
		
		EditComponentComboBox.setBounds(510, 302, 180, 34);
		contentPane.add(EditComponentComboBox);

		
		
		JButton SelectButton = new JButton("Вибрати");
		SelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				id_to_choose = MethodsForFrames.getDeviceIdByDeviceName(name_to_choose, id_to_choose, DeviceComboBox, devices);
				FillAdd_Edit_DeleteComboBoxesFromLists();
			}
		});
		SelectButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		SelectButton.setBounds(425, 180, 119, 25);
		contentPane.add(SelectButton);
		
		
		
		JButton InfoButton = new JButton("Інформація");
		InfoButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				id_to_choose = MethodsForFrames.getDeviceIdByDeviceName(name_to_choose, id_to_choose, DeviceComboBox, devices);
				
				EditSpecification.this.setVisible(false);
				try {
					new DeviceInformation(EditSpecification.this).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		InfoButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		InfoButton.setBounds(556, 121, 122, 34);
		contentPane.add(InfoButton);
		
		
		
		JLabel DeleteComponentLabel = new JLabel("Видалення компоненту");
		DeleteComponentLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DeleteComponentLabel.setBounds(28, 261, 164, 28);
		contentPane.add(DeleteComponentLabel);
		
		JLabel AddComponentLabel = new JLabel("Додавання компоненту");
		AddComponentLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		AddComponentLabel.setBounds(263, 261, 180, 28);
		contentPane.add(AddComponentLabel);

		JLabel EditComponentLabel = new JLabel("Редагування");
		EditComponentLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		EditComponentLabel.setBounds(510, 261, 180, 28);
		contentPane.add(EditComponentLabel);
		

		
		JLabel NumberAddLabel = new JLabel("Кількість");
		NumberAddLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		NumberAddLabel.setBounds(263, 365, 180, 28);
		contentPane.add(NumberAddLabel);
		
		JLabel NumberEditLabel = new JLabel("Кількість");
		NumberEditLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		NumberEditLabel.setBounds(510, 365, 180, 28);
		contentPane.add(NumberEditLabel);
		
		NumberAddField = new JTextField();
		NumberAddField.setBounds(263, 399, 180, 28);
		contentPane.add(NumberAddField);
		NumberAddField.setColumns(10);
		
		NumberEditField = new JTextField();
		NumberEditField.setColumns(10);
		NumberEditField.setBounds(510, 399, 180, 28);
		contentPane.add(NumberEditField);
		MyItemListener actionListener = new MyItemListener();
		EditComponentComboBox.addItemListener(actionListener);
		
		

		
		JButton DeleteComponentButton = new JButton("Видалити");
		DeleteComponentButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				c_id = MethodsForFrames.getComponentIdByComponentName(c_name, c_id, DeleteComponentComboBox, components_for_delete);
				MethodsForFrames.deleteComponentsFromDevice(id_to_choose, c_id);
			}
		});
		DeleteComponentButton.setBounds(28, 465, 125, 28);
		contentPane.add(DeleteComponentButton);
		
		
		
		
		JButton AddComponentButton = new JButton("Додати");
		AddComponentButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				c_id = MethodsForFrames.getComponentIdByComponentName(c_name, c_id, AddComponentComboBox, components_for_add);
				MethodsForFrames.addComponentsToDevice(id_to_choose, c_id, NumberAddField);
			}
		});
		AddComponentButton.setBounds(263, 467, 125, 28);
		contentPane.add(AddComponentButton);
		
		
		
		
		JButton EditComponentButton = new JButton("Редагувати");
		EditComponentButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				c_id = MethodsForFrames.getComponentIdByComponentName(c_name, c_id, EditComponentComboBox, components_for_edit);
				MethodsForFrames.updateComponentsInDevice(id_to_choose, c_id, NumberEditField);
			}
		});
		EditComponentButton.setBounds(510, 467, 125, 28);
		contentPane.add(EditComponentButton);
		
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (parent != null)
					parent.setVisible(true);
				EditSpecification.this.setVisible(false);
				EditSpecification.this.dispose();
			}
		});
		btnBack.setBounds(643, 550, 97, 25);
		contentPane.add(btnBack);

	}

	

	public static void FillAdd_Edit_DeleteComboBoxesFromLists()
	{
		try {
			components_for_delete = dd.getAllComponentsInDevice(id_to_choose);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		DeleteComponentComboBox.removeAllItems();
		for(Component component : components_for_delete) 
		{
			DeleteComponentComboBox.addItem(component.getName());	
		}
		
		try {
			components_for_add = dd.getAllComponentsNotInDevice(id_to_choose);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		AddComponentComboBox.removeAllItems();
		for(Component component : components_for_add) 
		{
			AddComponentComboBox.addItem(component.getName());	
		}
		
		try {
			components_for_edit = dd.getAllComponentsInDevice(id_to_choose);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		EditComponentComboBox.removeAllItems();
		for(Component component : components_for_edit) 
		{
			EditComponentComboBox.addItem(component.getName());	
		}
	}
	
	
	
	class MyItemListener implements ItemListener 
	{
		// This method is called only if a new item has been selected.
		public void itemStateChanged(ItemEvent evt) {

			if (evt.getStateChange() == ItemEvent.SELECTED) {
				String c_name = String.valueOf(EditComponentComboBox.getSelectedItem());
				int c_id = 0;
				
				c_id = MethodsForFrames.getComponentIdByComponentName(c_name, c_id, EditComponentComboBox, components_for_edit);
				
				ComponentDevice record = null;
				try {
					record = dd.readComponentInDevice(id_to_choose, c_id);
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
