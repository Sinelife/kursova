package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import dao.ComponentDao;
import dao.DeviceDao;
import domain.Component;
import domain.Device;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SpecificationInformation extends JFrame {

	private JPanel contentPane;
	JTable table;
	public int id_to_choose;
	public String name_to_choose;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public SpecificationInformation(JFrame parent) throws SQLException 
	{
		DeviceDao dd = new DeviceDao();
		List<Device> devices = dd.getAll();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		comboBox.setBounds(40, 121, 504, 34);
		contentPane.add(comboBox);
		for(Device device : devices) 
		{
			comboBox.addItem(device.getName());
		}
		
		JLabel lblNewLabel = new JLabel("Специфікації приладів");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(46, 25, 559, 59);
		contentPane.add(lblNewLabel);
		
		
		String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};

				Object[][] data = {
				{"Kathy", "Smith",
				"Snowboarding", new Integer(5), new Boolean(false)},
				{"John", "Doe",
				"Rowing", new Integer(3), new Boolean(true)},
				{"Sue", "Black",
				"Knitting", new Integer(2), new Boolean(false)},
				{"Jane", "White",
				"Speed reading", new Integer(20), new Boolean(true)},
				{"Joe", "Brown",
				"Pool", new Integer(10), new Boolean(false)}
				};
				JTable table = new JTable(data, columnNames);

				contentPane.add(table.getTableHeader(), BorderLayout.PAGE_START);
				contentPane.add(table, BorderLayout.CENTER);
				
				
				
						
		JButton button = new JButton("Вибрати");
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				ComponentDao cd = new ComponentDao();
				List<Component> components = null;
				try {
					components = cd.getAll();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				name_to_choose = String.valueOf(comboBox.getSelectedItem());
				for(Device device : devices)
				{
					if(device.getName().equals(name_to_choose))
					{
						id_to_choose = device.getId();
					}
				}

			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button.setBounds(331, 197, 119, 25);
		contentPane.add(button);
		
		

	}
}
