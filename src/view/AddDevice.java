package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


import dao.DeviceDao;
import domain.Device;

import javax.swing.JButton;

public class AddDevice extends JFrame
{
	
	private JPanel contentPane;
	private JTextField NameField;
	private JTextField SupplyVoltageField;
	private JTextField BorderRegulationTimeField;
	private JTextField RatingField;
	private JTextField StartDateField;


	/**
	 * Create the frame.
	 */
	public AddDevice(JFrame parent)
	{
		DeviceDao dd = new DeviceDao();
		Device d = new Device();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Додавання нового приладу");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(103, 13, 421, 59);
		contentPane.add(lblNewLabel);
		
		JLabel NameLabel_1 = new JLabel("Назва приладу");
		NameLabel_1.setBounds(22, 129, 175, 22);
		contentPane.add(NameLabel_1);
		
		JLabel SupplyVoltageLabel = new JLabel("Напруга живлення");
		SupplyVoltageLabel.setBounds(22, 171, 175, 22);
		contentPane.add(SupplyVoltageLabel);
		
		JLabel BorderRegulationtimeLabel = new JLabel("Границі регулювання часу");
		BorderRegulationtimeLabel.setBounds(22, 217, 175, 22);
		contentPane.add(BorderRegulationtimeLabel);
		
		JLabel RatingLabel = new JLabel("Рейтинг приладу");
		RatingLabel.setBounds(22, 261, 175, 22);
		contentPane.add(RatingLabel);
		
		JLabel StartDateLabel = new JLabel("Дата створення");
		StartDateLabel.setBounds(22, 301, 175, 22);
		contentPane.add(StartDateLabel);
		
		
		NameField = new JTextField();
		NameField.setBounds(209, 129, 350, 22);
		contentPane.add(NameField);
		NameField.setColumns(10);
		
		SupplyVoltageField = new JTextField();
		SupplyVoltageField.setBounds(209, 171, 350, 22);
		contentPane.add(SupplyVoltageField);
		SupplyVoltageField.setColumns(10);
		
		BorderRegulationTimeField = new JTextField();
		BorderRegulationTimeField.setColumns(10);
		BorderRegulationTimeField.setBounds(209, 217, 350, 22);
		contentPane.add(BorderRegulationTimeField);
		
		RatingField = new JTextField();
		RatingField.setColumns(10);
		RatingField.setBounds(209, 261, 350, 22);
		contentPane.add(RatingField);
		
		StartDateField = new JTextField();
		StartDateField.setColumns(10);
		StartDateField.setBounds(209, 301, 350, 22);
		contentPane.add(StartDateField);

		
		JButton AddButton = new JButton("Додати");
		AddButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				d.setName(NameField.getText());
				d.setSupplyVoltage(SupplyVoltageField.getText());
				d.setBorderRegulationTime(BorderRegulationTimeField.getText());
				d.setRating(Integer.valueOf(RatingField.getText()));
				d.setDate(Date.valueOf(StartDateField.getText()));
				try {
					dd.addDevice(d);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (parent != null)
					parent.setVisible(true);
				AddDevice.this.setVisible(false);
				AddDevice.this.dispose();
			}
		});
		AddButton.setBounds(52, 427, 97, 25);
		contentPane.add(AddButton);
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				AddDevice.this.setVisible(false);
				AddDevice.this.dispose();
			}
		});
		btnBack.setBounds(489, 427, 97, 25);
		contentPane.add(btnBack);
	}
}
