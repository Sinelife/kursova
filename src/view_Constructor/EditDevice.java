package view_Constructor;

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
import domain.Device;
import view.AuthorisationMenu;



public class EditDevice extends JFrame {

	private JPanel contentPane;
	public static String name_to_edit;
	public static int id_to_edit;


	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public EditDevice(JFrame parent) throws SQLException 
	{
		DeviceDao dd = new DeviceDao();
		List<Device> devices = dd.getAll();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		comboBox.setBounds(40, 121, 504, 34);
		contentPane.add(comboBox);
		for(Device device : devices) 
		{
			comboBox.addItem(device.getName());
		}
		
		JLabel lblNewLabel = new JLabel("Меню вибору приладу для редагування.");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(46, 25, 559, 59);
		contentPane.add(lblNewLabel);
		
		
		JButton SelectButton = new JButton("Вибрати");
		SelectButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				name_to_edit = String.valueOf(comboBox.getSelectedItem());
				for(Device device : devices) 
				{
					id_to_edit = device.getId();
					if(device.getName().equals(name_to_edit))
					{
						break;
					}
				}
				EditDevice.this.setVisible(false);
				try {
					new EditDeviceFrame(EditDevice.this).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		SelectButton.setBounds(46, 427, 97, 25);
		contentPane.add(SelectButton);
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				EditDevice.this.setVisible(false);
				EditDevice.this.dispose();
			}
		});
		btnBack.setBounds(489, 427, 97, 25);
		contentPane.add(btnBack);
	}

}
