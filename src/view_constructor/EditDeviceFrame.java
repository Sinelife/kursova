package view_constructor;

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
import main.MethodsForFrames;
import view.AuthorisationMenu;
import javax.swing.SwingConstants;


public class EditDeviceFrame extends JFrame {

	private JPanel contentPane;
	private JTextField NameField;
	private JTextField SupplyVoltageField;
	private JTextField BorderRegulationTimeField;
	private JTextField RatingField;
	private JTextField StartDateField;
	private JTextField WorkPriceField;
	
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public EditDeviceFrame(JFrame parent) throws SQLException 
	{
		DeviceDao dd = new DeviceDao();
		Device d = dd.readDevice(EditDevice.id_to_edit);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 622, 538);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel lblNewLabel = new JLabel("Редагування інформаціх про прилад");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel.setBounds(60, 13, 473, 59);
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
		PriceLabel.setBounds(22, 343, 175, 22);
		contentPane.add(PriceLabel);
		
		
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
		
		WorkPriceField = new JTextField();
		WorkPriceField.setText("null");
		WorkPriceField.setColumns(10);
		WorkPriceField.setBounds(209, 343, 350, 22);
		contentPane.add(WorkPriceField);
		WorkPriceField.setText(String.valueOf(d.getWorkPrice()));
		
		
		
		JButton EditButton = new JButton("РЕДАГУВАТИ");
		EditButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		EditButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try {
					MethodsForFrames.updateDevice(d, NameField, SupplyVoltageField, BorderRegulationTimeField, RatingField, StartDateField, WorkPriceField);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				EditDeviceFrame.this.setVisible(false);
				EditDeviceFrame.this.dispose();
				new DeviceMenu().setVisible(true);
			}
		});
		EditButton.setBounds(209, 393, 118, 25);
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
		btnBack.setBounds(462, 450, 97, 25);
		contentPane.add(btnBack);
	}

}
