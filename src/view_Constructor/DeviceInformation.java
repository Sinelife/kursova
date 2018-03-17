package view_Constructor;

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

import dao.DeviceDao;
import domain.Device;
import main.Main;
import view.AuthorisationMenu;
import view_director.ConstructDepartmentDirectorMenu;
import view_director.DeliveryDepartmentDirectorMenu;
import view_workerbuycomponent.InfoProvider;

import java.awt.Color;

public class DeviceInformation extends JFrame {

	private JPanel contentPane;
	private JTextField NameField;
	private JTextField SupplyVoltageField;
	private JTextField BorderRegulationTimeField;
	private JTextField RatingField;
	private JTextField StartDateField;
	private JTextField PriceField;
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public DeviceInformation(JFrame parent) throws SQLException 
	{
		DeviceDao dd = new DeviceDao();
		Device d = new Device();
		
		
		if(AuthorisationMenu.user_role.equals("director"))
		{
			d = dd.readDevice(ConstructDepartmentDirectorMenu.device_id_to_look);		
		}
		else
		{
			d = dd.readDevice(InfoDevice.id_to_look);
		}
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel lblNewLabel = new JLabel("Інформація про прилад");
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
		
		JLabel PriceLabel = new JLabel("Ціна");
		PriceLabel.setBounds(22, 346, 175, 22);
		contentPane.add(PriceLabel);
		
		
		NameField = new JTextField();
		NameField.setBackground(Color.WHITE);
		NameField.setEditable(false);
		NameField.setBounds(209, 129, 350, 22);
		contentPane.add(NameField);
		NameField.setColumns(10);
		NameField.setText(d.getName());
		
		SupplyVoltageField = new JTextField();
		SupplyVoltageField.setBackground(Color.WHITE);
		SupplyVoltageField.setEditable(false);
		SupplyVoltageField.setBounds(209, 171, 350, 22);
		contentPane.add(SupplyVoltageField);
		SupplyVoltageField.setColumns(10);
		SupplyVoltageField.setText(d.getSupplyVoltage());
		
		BorderRegulationTimeField = new JTextField();
		BorderRegulationTimeField.setBackground(Color.WHITE);
		BorderRegulationTimeField.setEditable(false);
		BorderRegulationTimeField.setColumns(10);
		BorderRegulationTimeField.setBounds(209, 217, 350, 22);
		contentPane.add(BorderRegulationTimeField);
		BorderRegulationTimeField.setText(d.getBorderRegulationTime());
		
		RatingField = new JTextField();
		RatingField.setBackground(Color.WHITE);
		RatingField.setEditable(false);
		RatingField.setColumns(10);
		RatingField.setBounds(209, 261, 350, 22);
		contentPane.add(RatingField);
		RatingField.setText(String.valueOf(d.getRating()));
		
		StartDateField = new JTextField();
		StartDateField.setBackground(Color.WHITE);
		StartDateField.setEditable(false);
		StartDateField.setColumns(10);
		StartDateField.setBounds(209, 301, 350, 22);
		contentPane.add(StartDateField);
		Main.DateToString(d.getDate(), StartDateField);
		
		
		PriceField = new JTextField();
		PriceField.setEditable(false);
		PriceField.setColumns(10);
		PriceField.setBackground(Color.WHITE);
		PriceField.setBounds(209, 346, 350, 22);
		contentPane.add(PriceField);
		PriceField.setText(String.valueOf(d.getPrice()));
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				DeviceInformation.this.setVisible(false);
				DeviceInformation.this.dispose();
			}
		});
		btnBack.setBounds(489, 427, 97, 25);
		contentPane.add(btnBack);
	}

}
