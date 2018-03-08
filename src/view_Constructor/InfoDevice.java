package view_Constructor;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class InfoDevice extends JFrame {

	private JPanel contentPane;
	public static int id_to_look;
	public static String name_to_look;
	public static int id_to_find;
	public static String voltage_to_find;
	public static String name_to_find;
	public static int rating_big_to_find;
	public static int rating_small_to_find;
	private JTextField textField;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public InfoDevice(JFrame parent) throws SQLException
	{
		DeviceDao dd = new DeviceDao();
		List<Device> devices = dd.getAll();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox<String> comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		comboBox.setBounds(46, 283, 438, 34);
		contentPane.add(comboBox);
		for(Device device : devices) 
		{
			comboBox.addItem(device.getName());
		}
		
		
		JLabel lblNewLabel = new JLabel("Пошук та перегляд інформації про прилади");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setBounds(46, 25, 559, 59);
		contentPane.add(lblNewLabel);
		
		
		JButton InfoButton = new JButton("Інформація");
		InfoButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				name_to_look = String.valueOf(comboBox.getSelectedItem());
				for(Device device : devices) 
				{
					id_to_look = device.getId();
					if(device.getName().equals(name_to_look))
					{
						break;
					}
				}
				InfoDevice.this.setVisible(false);
				try {
					new DeviceInformation(InfoDevice.this).setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		InfoButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		InfoButton.setBounds(496, 283, 122, 34);
		contentPane.add(InfoButton);
		
		
		
		
		textField = new JTextField();
		textField.setBounds(92, 123, 152, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		JButton FindNameButton = new JButton("Пошук за назвою");
		FindNameButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				name_to_find = textField.getText();
				comboBox.removeAllItems();
				for(Device device : devices) 
				{
					if(device.getName().equals(name_to_find))
					{
						comboBox.addItem(device.getName());
					}
				}
			}
		});
		FindNameButton.setBounds(301, 122, 151, 25);
		contentPane.add(FindNameButton);
		
		JButton FindVoltageButton = new JButton("Пошук за напругою");
		FindVoltageButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				voltage_to_find = textField.getText();
				comboBox.removeAllItems();
				for(Device device : devices) 
				{
					if(device.getSupplyVoltage().equals(voltage_to_find))
					{
						comboBox.addItem(device.getName());
					}
				}
			}
		});
		FindVoltageButton.setBounds(301, 97, 151, 25);
		contentPane.add(FindVoltageButton);
		
		

		JLabel FindRatingLabel = new JLabel("Пошук за рейтингом");
		FindRatingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		FindRatingLabel.setBounds(301, 147, 151, 22);
		contentPane.add(FindRatingLabel);
		
		JButton FindRatingSmallButton = new JButton("Менше");
		FindRatingSmallButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				rating_small_to_find = Integer.valueOf(textField.getText());
				comboBox.removeAllItems();
				for(Device device : devices) 
				{
					if(device.getRating() < rating_small_to_find)
					{
						comboBox.addItem(device.getName());
					}
				}
			}
		});
		FindRatingSmallButton.setBounds(301, 169, 77, 25);
		contentPane.add(FindRatingSmallButton);
		
		JButton FindRatingBigButton = new JButton("Більше");
		FindRatingBigButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				rating_big_to_find = Integer.valueOf(textField.getText());
				comboBox.removeAllItems();
				for(Device device : devices) 
				{
					if(device.getRating() > rating_big_to_find)
					{
						comboBox.addItem(device.getName());
					}
				}
			}
		});
		FindRatingBigButton.setBounds(375, 169, 77, 25);
		contentPane.add(FindRatingBigButton);
		
		
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				InfoDevice.this.setVisible(false);
				InfoDevice.this.dispose();
			}
		});
		btnBack.setBounds(489, 427, 97, 25);
		contentPane.add(btnBack);

	}

}
