package view_Constructor;

import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import dao.ComponentDao;
import dao.DeviceDao;
import domain.Component;
import domain.ComponentDevice;
import domain.Device;
import main.MethodsForFrames;
import view.AuthorisationMenu;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SpecificationInformation extends JFrame {

	private JPanel contentPane;
	JTable table;
	public static int id_to_choose;
	public String name_to_choose;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public SpecificationInformation(JFrame parent) throws SQLException 
	{
		DeviceDao dd = new DeviceDao();
		List<Device> devices = dd.getAll();
		ComponentDao cd = new ComponentDao();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 738, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		InfoDevice.device_information_check = 3;
		
		
		JLabel lblNewLabel = new JLabel("Специфікації приладів");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(46, 25, 411, 59);
		contentPane.add(lblNewLabel);
		

		JComboBox<String> DeviceComboBox = new JComboBox<String>();
		DeviceComboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		DeviceComboBox.setBounds(40, 121, 504, 34);
		contentPane.add(DeviceComboBox);
		for(Device device : devices) 
		{
			DeviceComboBox.addItem(device.getName());
		}
		
		
		
		JComboBox<String> ComponentInfoComboBox = new JComboBox<String>();
		ComponentInfoComboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ComponentInfoComboBox.setBounds(40, 324, 504, 34);
		contentPane.add(ComponentInfoComboBox);
		
		
						
		JButton SelectButton = new JButton("Вибрати");
		SelectButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				ComponentInfoComboBox.removeAllItems();	
				List<Component> components = null;

				id_to_choose = MethodsForFrames.getDeviceIdByDeviceName(name_to_choose, id_to_choose, DeviceComboBox, devices);
				
				try {
					components = dd.getAllComponentsInDevice(id_to_choose);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String info[] = new String[100];
				int i = 0 ;
				ComponentDevice c_d = null;
				Component comp = null;
				for(Component component : components)
				{
					try {
						c_d = dd.readComponentInDevice(id_to_choose, component.getId());
						comp = cd.readComponent(component.getId());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					info[i] = "Назва: " + comp.getName() + "    Тип: " + comp.getType() + "    Кількість:" + c_d.getNumber();
					ComponentInfoComboBox.addItem(info[i]);
					i++;
				}
				
			}
		});
		SelectButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		SelectButton.setBounds(331, 197, 119, 25);
		contentPane.add(SelectButton);
		

		
		JButton InfoButton = new JButton("Інформація");
		InfoButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				id_to_choose = MethodsForFrames.getDeviceIdByDeviceName(name_to_choose, id_to_choose, DeviceComboBox, devices);
				
				SpecificationInformation.this.setVisible(false);
				try {
					new DeviceInformation(SpecificationInformation.this).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		InfoButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		InfoButton.setBounds(556, 121, 122, 34);
		contentPane.add(InfoButton);
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (parent != null)
					parent.setVisible(true);
				SpecificationInformation.this.setVisible(false);
				SpecificationInformation.this.dispose();
			}
		});
		btnBack.setBounds(611, 473, 97, 25);
		contentPane.add(btnBack);
		

	}
}
