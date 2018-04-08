package view_constructor;

import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

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

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class SpecificationInformation extends JFrame {

	private JPanel contentPane;
	public static int id_to_choose;

	List<Component> components = null;
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public SpecificationInformation(JFrame parent) throws SQLException 
	{
		DeviceDao dd = new DeviceDao();
		List<Device> devices = dd.getAll();
	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 738, 507);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		InfoDevice.device_information_check = 3;
		
		
		JLabel lblNewLabel = new JLabel("Специфікації приладів");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(147, 27, 412, 59);
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
		ComponentInfoComboBox.setBounds(40, 288, 504, 34);
		contentPane.add(ComponentInfoComboBox);
		
		
						
		JButton SelectButton = new JButton("ВИБРАТИ");
		SelectButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{	
				id_to_choose = MethodsForFrames.getDeviceIdByDeviceName(DeviceComboBox, devices);
				MethodsForFrames.getSpecificationInfo(id_to_choose, components, ComponentInfoComboBox);
			}
		});
		SelectButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		SelectButton.setBounds(248, 203, 105, 25);
		contentPane.add(SelectButton);
		

		
		JButton InfoButton = new JButton("Інформація");
		InfoButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				id_to_choose = MethodsForFrames.getDeviceIdByDeviceName(DeviceComboBox, devices);
				SpecificationInformation.this.setVisible(false);
				try {
					new DeviceInformation(SpecificationInformation.this).setVisible(true);
				} catch (SQLException e1) {
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
		btnBack.setBounds(581, 403, 97, 25);
		contentPane.add(btnBack);
		

	}
}
