package view_constructor;

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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.DeviceDao;
import domain.Device;
import main.MethodsForFrames;
import view.AuthorisationMenu;

public class SpecialDeviceInfo extends JFrame {

	private JPanel contentPane;
	public static int id_to_look;

	DeviceDao dd = new DeviceDao();
	
	List<Device> devices = dd.getAll();
	List<Device> devicesAllInChoosen;
	List<Device> devicesAllNotInChoosen;
	List<Device> devicesOneInChoosen;
	List<Device> devicesOnlyAllInChoosen;
	
	/**
	 * Create the frame.
	 * @param deviceMenu 
	 * @throws SQLException 
	 */
	public SpecialDeviceInfo(JFrame parent) throws SQLException 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 583, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		JLabel MenuTitleLabel = new JLabel("Спеціальна інформація");
		MenuTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MenuTitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		MenuTitleLabel.setBounds(0, 13, 565, 38);
		contentPane.add(MenuTitleLabel);
		
		
		JComboBox<String> DeviceComboBox = new JComboBox<String>();
		DeviceComboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		DeviceComboBox.setBounds(213, 77, 229, 34);
		contentPane.add(DeviceComboBox);
		for(Device device : devices) 
		{
			DeviceComboBox.addItem(device.getName());
		}
		
		
		
		JLabel DeviceAllInChoosenLabel = new JLabel("що місять усі ті компоненти");
		DeviceAllInChoosenLabel.setHorizontalAlignment(SwingConstants.CENTER);
		DeviceAllInChoosenLabel.setBounds(24, 186, 229, 25);
		contentPane.add(DeviceAllInChoosenLabel);
		
		JComboBox<String> DeviceAllInChoosenComboBox = new JComboBox<String>();
		DeviceAllInChoosenComboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		DeviceAllInChoosenComboBox.setBounds(24, 215, 229, 34);
		contentPane.add(DeviceAllInChoosenComboBox);
	
		
		JLabel DeviceAllNotInChoosenLabel = new JLabel("що не містять жодного");
		DeviceAllNotInChoosenLabel.setHorizontalAlignment(SwingConstants.CENTER);
		DeviceAllNotInChoosenLabel.setBounds(290, 186, 229, 25);
		contentPane.add(DeviceAllNotInChoosenLabel);
		
		JComboBox<String> DeviceAllNotInChoosenComboBox = new JComboBox<String>();
		DeviceAllNotInChoosenComboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		DeviceAllNotInChoosenComboBox.setBounds(290, 215, 229, 34);
		contentPane.add(DeviceAllNotInChoosenComboBox);
	
		
		JLabel DeviceOneInChoosenLabel = new JLabel("що містять хоча б один компонент");
		DeviceOneInChoosenLabel.setHorizontalAlignment(SwingConstants.CENTER);
		DeviceOneInChoosenLabel.setBounds(290, 291, 229, 25);
		contentPane.add(DeviceOneInChoosenLabel);
		
		JComboBox<String> DeviceOneInChoosenComboBox = new JComboBox<String>();
		DeviceOneInChoosenComboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		DeviceOneInChoosenComboBox.setBounds(290, 320, 229, 34);
		contentPane.add(DeviceOneInChoosenComboBox);
		
		
		JLabel DeviceOnlyAllInChoosenLabel = new JLabel("що містять усі і тільки ті компоненти");
		DeviceOnlyAllInChoosenLabel.setHorizontalAlignment(SwingConstants.CENTER);
		DeviceOnlyAllInChoosenLabel.setBounds(24, 291, 229, 25);
		contentPane.add(DeviceOnlyAllInChoosenLabel);
		
		JComboBox<String> DeviceOnlyAllInChoosenComboBox = new JComboBox<String>();
		DeviceOnlyAllInChoosenComboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		DeviceOnlyAllInChoosenComboBox.setBounds(24, 320, 229, 34);
		contentPane.add(DeviceOnlyAllInChoosenComboBox);
		
		
		
		JButton SelectButton = new JButton("ВИБРАТИ");
		SelectButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				id_to_look = MethodsForFrames.getDeviceIdByDeviceName(DeviceComboBox, devices);
				
				MethodsForFrames.getAllDeviceWhichHasAllComponentsWhichHasChosenDevice(id_to_look, devicesAllInChoosen, DeviceAllInChoosenComboBox);
				MethodsForFrames.getAllDeviceWhichHasAllComponentsWhichHasNotChosenDevice(id_to_look, devicesAllNotInChoosen, DeviceAllNotInChoosenComboBox);
				MethodsForFrames.getAllDeviceWhichHasAtLeastOneComponentWhichHasChosenDevice(id_to_look, devicesOneInChoosen, DeviceOneInChoosenComboBox);
				MethodsForFrames.getAllDeviceWhichHasOnlyAllComponentsWhichHasChosenDevice(id_to_look, devicesOnlyAllInChoosen, DeviceOnlyAllInChoosenComboBox);
			}
		});
		SelectButton.setBounds(290, 124, 97, 25);
		contentPane.add(SelectButton);
		
		
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				SpecialDeviceInfo.this.setVisible(false);
				SpecialDeviceInfo.this.dispose();
			}
		});
		btnBack.setBounds(456, 406, 97, 25);
		contentPane.add(btnBack);
	}
}
