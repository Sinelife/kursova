package view_workerselldevice;

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

import dao.ClientDao;
import domain.Client;
import domain.Device;

public class InfoClient extends JFrame {

	private JPanel contentPane;
	public static int id_to_look;
	public static String name_to_look;


	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public InfoClient(JFrame parent) throws SQLException 
	{
		ClientDao cd = new ClientDao();
		List<Client> clients = cd.getAll();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox<String> ClientComboBox = new JComboBox<String>();
		ClientComboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		ClientComboBox.setBounds(40, 121, 430, 34);
		contentPane.add(ClientComboBox);
		for(Client client : clients) 
		{
			ClientComboBox.addItem(client.getName());
		}
		
		JLabel lblNewLabel = new JLabel("Перегляд інформації про клієнта");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(46, 25, 559, 59);
		contentPane.add(lblNewLabel);
		
		
		JComboBox<String> DeviceInClientComboBox = new JComboBox<String>();
		DeviceInClientComboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		DeviceInClientComboBox.setBounds(40, 258, 430, 34);
		contentPane.add(DeviceInClientComboBox);
		
		
		
		JButton InfoButton = new JButton("Інформація");
		InfoButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				name_to_look = String.valueOf(ClientComboBox.getSelectedItem());
				for(Client client : clients) 
				{
					id_to_look = client.getId();
					if(client.getName().equals(name_to_look))
					{
						break;
					}
				}
				InfoClient.this.setVisible(false);
				try {
					new ClientInformation(InfoClient.this).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		InfoButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		InfoButton.setBounds(483, 121, 122, 34);
		contentPane.add(InfoButton);
		
		
		JButton btnNewButton = new JButton("Вибрати");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				DeviceInClientComboBox.removeAllItems();
				List<Device> devicesInClient = null;
				name_to_look = String.valueOf(ClientComboBox.getSelectedItem());
				for(Client client : clients) 
				{
					id_to_look = client.getId();
					if(client.getName().equals(name_to_look))
					{
						break;
					}
				}
				
				try {
					devicesInClient = cd.getAllDevicesInClient(id_to_look);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				for(Device device : devicesInClient)
				{
					DeviceInClientComboBox.addItem(device.getName());
				}
			}
		});
		btnNewButton.setBounds(188, 177, 97, 25);
		contentPane.add(btnNewButton);
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				InfoClient.this.setVisible(false);
				InfoClient.this.dispose();
			}
		});
		btnBack.setBounds(489, 427, 97, 25);
		contentPane.add(btnBack);
		
	}
}
