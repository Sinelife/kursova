package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.ComponentDao;
import dao.DeviceDao;
import domain.Component;
import domain.Device;


public class EditDeviceFrame extends JFrame {

	private JPanel contentPane;
	private JTextField NameField;
	private JTextField SupplyVoltageField;
	private JTextField BorderRegulationTimeField;
	private JTextField RatingField;
	private JTextField StartDateField;
	
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public EditDeviceFrame(JFrame parent) throws SQLException 
	{
		DeviceDao dd = new DeviceDao();
		Device d = dd.readDevice(EditDevice.id_to_edit);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("����������� ���������� ��� ������");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel.setBounds(51, 13, 473, 59);
		contentPane.add(lblNewLabel);
		
		JLabel NameLabel_1 = new JLabel("����� �������");
		NameLabel_1.setBounds(22, 129, 175, 22);
		contentPane.add(NameLabel_1);
		
		JLabel SupplyVoltageLabel = new JLabel("������� ��������");
		SupplyVoltageLabel.setBounds(22, 171, 175, 22);
		contentPane.add(SupplyVoltageLabel);
		
		JLabel BorderRegulationtimeLabel = new JLabel("������� ����������� ����");
		BorderRegulationtimeLabel.setBounds(22, 217, 175, 22);
		contentPane.add(BorderRegulationtimeLabel);
		
		JLabel RatingLabel = new JLabel("������� �������");
		RatingLabel.setBounds(22, 261, 175, 22);
		contentPane.add(RatingLabel);
		
		JLabel StartDateLabel = new JLabel("���� ���������");
		StartDateLabel.setBounds(22, 301, 175, 22);
		contentPane.add(StartDateLabel);
		
		
		NameField = new JTextField();
		NameField.setBounds(209, 129, 350, 22);
		contentPane.add(NameField);
		NameField.setColumns(10);
		NameField.setText(d.getName());
		
		SupplyVoltageField = new JTextField();
		SupplyVoltageField.setBounds(209, 171, 350, 22);
		contentPane.add(SupplyVoltageField);
		SupplyVoltageField.setColumns(10);
		SupplyVoltageField.setText(d.getSupplyVoltage());
		
		BorderRegulationTimeField = new JTextField();
		BorderRegulationTimeField.setColumns(10);
		BorderRegulationTimeField.setBounds(209, 217, 350, 22);
		contentPane.add(BorderRegulationTimeField);
		BorderRegulationTimeField.setText(d.getBorderRegulationTime());
		
		RatingField = new JTextField();
		RatingField.setColumns(10);
		RatingField.setBounds(209, 261, 350, 22);
		contentPane.add(RatingField);
		RatingField.setText(String.valueOf(d.getRating()));
		
		StartDateField = new JTextField();
		StartDateField.setColumns(10);
		StartDateField.setBounds(209, 301, 350, 22);
		contentPane.add(StartDateField);
		StartDateField.setText(String.valueOf(d.getDate()));
		
		
		JButton EditButton = new JButton("����������");
		EditButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		EditButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				d.setName(NameField.getText());
				d.setSupplyVoltage(SupplyVoltageField.getText());
				d.setBorderRegulationTime(BorderRegulationTimeField.getText());
				d.setRating(Integer.valueOf(RatingField.getText()));
				d.setDate(Date.valueOf(StartDateField.getText()));
				try {
					dd.updateDevice(d);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				EditDeviceFrame.this.setVisible(false);
				EditDeviceFrame.this.dispose();
				new DeviceMenu().setVisible(true);
			}
		});
		EditButton.setBounds(36, 427, 125, 25);
		contentPane.add(EditButton);
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				EditDeviceFrame.this.setVisible(false);
				EditDeviceFrame.this.dispose();
			}
		});
		btnBack.setBounds(489, 427, 97, 25);
		contentPane.add(btnBack);
	}

}
