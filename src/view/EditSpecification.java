package view;

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

import dao.ComponentDao;
import dao.DeviceDao;
import domain.Component;
import domain.ComponentDevice;
import domain.Device;
import javax.swing.JTextField;

public class EditSpecification extends JFrame {

	private JPanel contentPane;
	public static int id_to_choose;
	public static String name_to_choose;
	private JTextField NumberAddField;
	private JTextField NumberEditField;
	private List<Component> components_for_delete = null;
	private List<Component> components_for_add = null;
	private List<Component> components_for_edit = null;
	private DeviceDao dd = null;
	private List<Device> devices = null;
	private ComponentDao cd = null;
	JComboBox<String> DeleteComponentComboBox = new JComboBox<String>();
	JComboBox<String> AddComponentComboBox = new JComboBox<String>();
	JComboBox<String> EditComponentComboBox = new JComboBox<String>();

	/**
	 * Create the frame.
	 * @throws SQLException
	 */
	public EditSpecification(JFrame parent) throws SQLException 
	{
		dd = new DeviceDao();
		devices = dd.getAll();
		cd = new ComponentDao();
		


		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 647);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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

				name_to_choose = String.valueOf(DeviceComboBox.getSelectedItem());
				for(Device device: devices)
				{
					if(device.getName().equals(name_to_choose))
					{
						id_to_choose = device.getId();
					}
				}
				
				try {
					components_for_delete = cd.getAllComponentsInDevice(id_to_choose);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				DeleteComponentComboBox.removeAllItems();
				for(Component component : components_for_delete) 
				{
					DeleteComponentComboBox.addItem(component.getName());	
				}
				
				try {
					components_for_add = cd.getAllComponentsNotInDevice(id_to_choose);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				AddComponentComboBox.removeAllItems();
				for(Component component : components_for_add) 
				{
					AddComponentComboBox.addItem(component.getName());	
				}
				
				try {
					components_for_edit = cd.getAllComponentsInDevice(id_to_choose);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				EditComponentComboBox.removeAllItems();
				for(Component component : components_for_edit) 
				{
					EditComponentComboBox.addItem(component.getName());	
				}
			}
		});
		SelectButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		SelectButton.setBounds(425, 180, 119, 25);
		contentPane.add(SelectButton);
		
		
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
				String c_name = String.valueOf(DeleteComponentComboBox.getSelectedItem());
				int c_id = 0;
				for(Component component : components_for_delete) 
				{
					if(component.getName().equals(c_name))
					{
						c_id = component.getId();
					}
				}
				ComponentDevice record = null;
				try {
					record = dd.readComponentInDevice(id_to_choose, c_id);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					dd.deleteComponentFromDevice(record);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		DeleteComponentButton.setBounds(28, 465, 125, 28);
		contentPane.add(DeleteComponentButton);
		
		
		JButton AddComponentButton = new JButton("Додати");
		AddComponentButton.addActionListener(new ActionListener() 
		{
			@SuppressWarnings("null")
			public void actionPerformed(ActionEvent e) 
			{
				String c_name = String.valueOf(AddComponentComboBox.getSelectedItem());
				int c_id = 0;
				for(Component component : components_for_add) 
				{
					if(component.getName().equals(c_name))
					{
						c_id = component.getId();
					}
				}
				
				ComponentDevice record = new ComponentDevice();
				record.setDeviceId(4);
				record.setComponentId(c_id);
				record.setNumber(Integer.valueOf(NumberAddField.getText()));
				try {
					dd.addComponentToDevice(record);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		AddComponentButton.setBounds(263, 467, 125, 28);
		contentPane.add(AddComponentButton);
		
		
		JButton EditComponentButton = new JButton("Редагувати");
		EditComponentButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String c_name = String.valueOf(EditComponentComboBox.getSelectedItem());
				int c_id = 0;
				for(Component component : components_for_edit) 
				{
					if(component.getName().equals(c_name))
					{
						c_id = component.getId();
					}
				}
				ComponentDevice record = null;
				try {
					record = dd.readComponentInDevice(id_to_choose, c_id);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				record.setNumber(Integer.valueOf(NumberEditField.getText()));
				try {
					dd.updateComponentInDevice(record);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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

	
	
	
	class MyItemListener implements ItemListener 
	{
		  // This method is called only if a new item has been selected.
		  public void itemStateChanged(ItemEvent evt) 
		  {
		

		    if (evt.getStateChange() == ItemEvent.SELECTED) 
		    {
				String c_name = String.valueOf(EditComponentComboBox.getSelectedItem());
				int c_id = 0;
				for(Component component : components_for_edit) 
				{
					if(component.getName().equals(c_name))
					{
						c_id = component.getId();
					}
				}
				ComponentDevice record = null;
				try {
					record = dd.readComponentInDevice(id_to_choose, c_id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	NumberEditField.setText(String.valueOf(record.getNumber()));
		    	
		    } 
		    else if 
		    (evt.getStateChange() == ItemEvent.DESELECTED) 
		    {
		    	NumberEditField.setText("");
		    }
		  }
	}
}
