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
import javax.swing.border.EmptyBorder;
import dao.ComponentDao;
import dao.DeviceDao;
import domain.Component;
import domain.Device;
import main.MethodsForFrames;
import view.AuthorisationMenu;

public class DeviceWhichHasComponent extends JFrame {

	private JPanel contentPane;
	public static int id_to_look;
	public static String name_to_look;
	public List<Device> devices = null;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public DeviceWhichHasComponent(JFrame parent) throws SQLException 
	{
		ComponentDao cd = new ComponentDao();
		List<Component> components = cd.getAll();
		DeviceDao dd = new DeviceDao();


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		
		JLabel lblNewLabel = new JLabel("Прилади, що містять данний компонент");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(34, 13, 557, 59);
		contentPane.add(lblNewLabel);
		
		JComboBox<String> ComponentComboBox = new JComboBox<String>();
		ComponentComboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		ComponentComboBox.setBounds(34, 105, 504, 34);
		contentPane.add(ComponentComboBox);
		for(Component component : components) 
		{
			ComponentComboBox.addItem(component.getName());
		}

		
		JComboBox<String> DeviceComboBox = new JComboBox<String>();
		DeviceComboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		DeviceComboBox.setBounds(34, 264, 504, 34);
		contentPane.add(DeviceComboBox);
		

		
		JButton btnNewButton = new JButton("Вибрати");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				id_to_look = MethodsForFrames.getComponentIdByComponentName(name_to_look, id_to_look, ComponentComboBox, components);

				try {
					devices = dd.getAllDeviceWhichHasComponent(id_to_look);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				DeviceComboBox.removeAllItems();
				for(Device device: devices)
				{
					DeviceComboBox.addItem(device.getName());
				}
			}
		});
		btnNewButton.setBounds(441, 187, 97, 25);
		contentPane.add(btnNewButton);
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				DeviceWhichHasComponent.this.setVisible(false);
				DeviceWhichHasComponent.this.dispose();
			}
		});
		btnBack.setBounds(489, 427, 97, 25);
		contentPane.add(btnBack);

	}
}
