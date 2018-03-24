package view_constructor;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.DeviceDao;
import domain.Device;
import main.MethodsForFrames;
import view.AuthorisationMenu;
import view_director.ConstructDepartmentDirectorMenu;
import java.awt.Color;
import javax.swing.SwingConstants;

public class DeviceInformation extends JFrame {

	private JPanel contentPane;
	private JTextField NameField;
	private JTextField SupplyVoltageField;
	private JTextField BorderRegulationTimeField;
	private JTextField RatingField;
	private JTextField StartDateField;
	private JTextField WorkPriceField;
	private JTextField ComponentsPriceField;
	private JTextField ProfitPriceField;
	private JTextField SumPriceField;
	
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
		else if(InfoDevice.device_information_check == 1)
		{
			d = dd.readDevice(InfoDevice.id_to_look);
		}
		else if(InfoDevice.device_information_check == 2)
		{
			d = dd.readDevice(EditSpecification.id_to_choose);
		}
		else 
		{
			d = dd.readDevice(SpecificationInformation.id_to_choose);
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 598);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel lblNewLabel = new JLabel("Інформація про прилад");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(83, 0, 421, 59);
		contentPane.add(lblNewLabel);
		
		JLabel NameLabel_1 = new JLabel("Назва приладу");
		NameLabel_1.setBounds(12, 85, 175, 22);
		contentPane.add(NameLabel_1);
		
		JLabel SupplyVoltageLabel = new JLabel("Напруга живлення");
		SupplyVoltageLabel.setBounds(12, 127, 175, 22);
		contentPane.add(SupplyVoltageLabel);
		
		JLabel BorderRegulationtimeLabel = new JLabel("Границі регулювання часу");
		BorderRegulationtimeLabel.setBounds(12, 173, 175, 22);
		contentPane.add(BorderRegulationtimeLabel);
		
		JLabel RatingLabel = new JLabel("Рейтинг приладу");
		RatingLabel.setBounds(12, 217, 175, 22);
		contentPane.add(RatingLabel);
		
		JLabel StartDateLabel = new JLabel("Дата створення");
		StartDateLabel.setBounds(12, 257, 175, 22);
		contentPane.add(StartDateLabel);
		
		JLabel WorkPriceLabel = new JLabel("Вартість зборки");
		WorkPriceLabel.setBounds(12, 302, 175, 22);
		contentPane.add(WorkPriceLabel);
		
		JLabel ComponentsPriceLabel = new JLabel("Вартість компонентів");
		ComponentsPriceLabel.setBounds(12, 346, 175, 22);
		contentPane.add(ComponentsPriceLabel);
		
		JLabel ProfitPriceLabel = new JLabel("Профіт");
		ProfitPriceLabel.setBounds(12, 394, 175, 22);
		contentPane.add(ProfitPriceLabel);
		
		JLabel SumPriceLabel = new JLabel("Загальна ціна");
		SumPriceLabel.setBounds(12, 440, 175, 22);
		contentPane.add(SumPriceLabel);
		
		
		NameField = new JTextField();
		NameField.setBackground(Color.WHITE);
		NameField.setEditable(false);
		NameField.setBounds(199, 85, 350, 22);
		contentPane.add(NameField);
		NameField.setColumns(10);
		NameField.setText(d.getName());
		
		SupplyVoltageField = new JTextField();
		SupplyVoltageField.setBackground(Color.WHITE);
		SupplyVoltageField.setEditable(false);
		SupplyVoltageField.setBounds(199, 127, 350, 22);
		contentPane.add(SupplyVoltageField);
		SupplyVoltageField.setColumns(10);
		SupplyVoltageField.setText(d.getSupplyVoltage());
		
		BorderRegulationTimeField = new JTextField();
		BorderRegulationTimeField.setBackground(Color.WHITE);
		BorderRegulationTimeField.setEditable(false);
		BorderRegulationTimeField.setColumns(10);
		BorderRegulationTimeField.setBounds(199, 173, 350, 22);
		contentPane.add(BorderRegulationTimeField);
		BorderRegulationTimeField.setText(d.getBorderRegulationTime());
		
		RatingField = new JTextField();
		RatingField.setBackground(Color.WHITE);
		RatingField.setEditable(false);
		RatingField.setColumns(10);
		RatingField.setBounds(199, 217, 350, 22);
		contentPane.add(RatingField);
		RatingField.setText(String.valueOf(d.getRating()));
		
		StartDateField = new JTextField();
		StartDateField.setBackground(Color.WHITE);
		StartDateField.setEditable(false);
		StartDateField.setColumns(10);
		StartDateField.setBounds(199, 257, 350, 22);
		contentPane.add(StartDateField);
		MethodsForFrames.DateToString(d.getDate(), StartDateField);
		
		
		WorkPriceField = new JTextField();
		WorkPriceField.setEditable(false);
		WorkPriceField.setColumns(10);
		WorkPriceField.setBackground(Color.WHITE);
		WorkPriceField.setBounds(199, 302, 350, 22);
		contentPane.add(WorkPriceField);
		WorkPriceField.setText(String.valueOf(d.getWorkPrice()));
		
		ComponentsPriceField = new JTextField();
		ComponentsPriceField.setText((String) null);
		ComponentsPriceField.setEditable(false);
		ComponentsPriceField.setColumns(10);
		ComponentsPriceField.setBackground(Color.WHITE);
		ComponentsPriceField.setBounds(199, 346, 350, 22);
		contentPane.add(ComponentsPriceField);
		ComponentsPriceField.setText(String.valueOf(d.getComponentsPrice()));
		
		ProfitPriceField = new JTextField();
		ProfitPriceField.setText((String) null);
		ProfitPriceField.setEditable(false);
		ProfitPriceField.setColumns(10);
		ProfitPriceField.setBackground(Color.WHITE);
		ProfitPriceField.setBounds(199, 394, 350, 22);
		contentPane.add(ProfitPriceField);
		ProfitPriceField.setText(String.valueOf(d.getProfitPrice()));
		
		SumPriceField = new JTextField();
		SumPriceField.setText((String) null);
		SumPriceField.setEditable(false);
		SumPriceField.setColumns(10);
		SumPriceField.setBackground(Color.WHITE);
		SumPriceField.setBounds(199, 440, 350, 22);
		contentPane.add(SumPriceField);
		SumPriceField.setText(String.valueOf(d.getSumPrice()));
		
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				DeviceInformation.this.setVisible(false);
				DeviceInformation.this.dispose();
			}
		});
		btnBack.setBounds(452, 513, 97, 25);
		contentPane.add(btnBack);
	}

}
