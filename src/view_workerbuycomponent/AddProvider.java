package view_workerbuycomponent;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.ComponentDao;
import domain.Specialization;
import main.MethodsForFrames;
import view.AuthorisationMenu;
import javax.swing.JComboBox;
import java.awt.Color;

public class AddProvider extends JFrame {

	private JPanel contentPane;
	private JTextField NameField;
	private JTextField PhoneField;
	private JTextField ContactPIBField;
	private JTextField CodeERPOUField;
	private JTextField CodeTaxpayerField;
	private JTextField SpecializationField;

	private String result = "";


	JComboBox<String> NotInComboBox = new JComboBox<String>();
	JComboBox<String> InComboBox = new JComboBox<String>();
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public AddProvider(JFrame parent) throws SQLException 
	{		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel lblNewLabel = new JLabel("Додавання нового постачальника");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(52, 13, 507, 59);
		contentPane.add(lblNewLabel);
		
		JLabel NameLabel = new JLabel("Ім'я");
		NameLabel.setBounds(22, 129, 175, 22);
		contentPane.add(NameLabel);
		
		JLabel PhoneLabel = new JLabel("Телефон");
		PhoneLabel.setBounds(22, 171, 175, 22);
		contentPane.add(PhoneLabel);
		
		JLabel ContactPIBLabel = new JLabel("Контактна особа");
		ContactPIBLabel.setBounds(22, 217, 175, 22);
		contentPane.add(ContactPIBLabel);
		
		JLabel CodeERPOULabel = new JLabel("Код ЕДРПОУ");
		CodeERPOULabel.setBounds(22, 261, 175, 22);
		contentPane.add(CodeERPOULabel);
		
		JLabel CodeTaxpayerLabel = new JLabel("Код платника податків");
		CodeTaxpayerLabel.setBounds(22, 301, 175, 22);
		contentPane.add(CodeTaxpayerLabel);
		
		JLabel SpecializationLabel = new JLabel("Спеціалізація");
		SpecializationLabel.setBounds(22, 343, 175, 22);
		contentPane.add(SpecializationLabel);
		
		
		NameField = new JTextField();
		NameField.setBounds(209, 129, 350, 22);
		contentPane.add(NameField);
		NameField.setColumns(10);
		
		PhoneField = new JTextField();
		PhoneField.setBounds(209, 171, 350, 22);
		contentPane.add(PhoneField);
		PhoneField.setColumns(10);
		
		ContactPIBField = new JTextField();
		ContactPIBField.setColumns(10);
		ContactPIBField.setBounds(209, 217, 350, 22);
		contentPane.add(ContactPIBField);
		
		CodeERPOUField = new JTextField();
		CodeERPOUField.setColumns(10);
		CodeERPOUField.setBounds(209, 261, 350, 22);
		contentPane.add(CodeERPOUField);
		
		CodeTaxpayerField = new JTextField();
		CodeTaxpayerField.setColumns(10);
		CodeTaxpayerField.setBounds(209, 301, 350, 22);
		contentPane.add(CodeTaxpayerField);
		
		SpecializationField = new JTextField();
		SpecializationField.setBackground(Color.WHITE);
		SpecializationField.setEditable(false);
		SpecializationField.setColumns(10);
		SpecializationField.setBounds(209, 343, 350, 22);
		contentPane.add(SpecializationField);

		
		NotInComboBox.setBackground(Color.WHITE);
		NotInComboBox.setBounds(209, 366, 175, 22);
		contentPane.add(NotInComboBox);
		ComponentDao cd = new ComponentDao();
		List<Specialization> specializationList = cd.getAllSpecializations();
		for(Specialization specialization : specializationList)
		{
			NotInComboBox.addItem(specialization.getSpecialization());
		}
	
		InComboBox.setBackground(Color.WHITE);
		InComboBox.setBounds(384, 366, 175, 22);
		contentPane.add(InComboBox);

		
		
		JButton PlusButton = new JButton("+");
		PlusButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String word = NotInComboBox.getSelectedItem().toString();
				result = result.concat(word + ", ");
				SpecializationField.setText(result);
				NotInComboBox.removeItem(word);
				InComboBox.addItem(word);
			}
		});
		PlusButton.setBounds(273, 396, 47, 25);
		contentPane.add(PlusButton);
		
		
		
		
		JButton MinusButton = new JButton("-");
		MinusButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String word = InComboBox.getSelectedItem().toString();
				InComboBox.removeItem(word);
				NotInComboBox.addItem(word);
				result = SpecializationField.getText();
				result = result.replace(word + ", ", "");
				SpecializationField.setText(result);
			}
		});
		MinusButton.setBounds(429, 396, 47, 25);
		contentPane.add(MinusButton);
		
		
		
		
		
		JButton AddButton = new JButton("Додати");
		AddButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				MethodsForFrames.addProvider(NameField, PhoneField, ContactPIBField, CodeERPOUField, CodeTaxpayerField, SpecializationField);
				if (parent != null)
					parent.setVisible(true);
				AddProvider.this.setVisible(false);
				AddProvider.this.dispose();
			}
		});
		AddButton.setBounds(12, 473, 97, 25);
		contentPane.add(AddButton);


		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				AddProvider.this.setVisible(false);
				AddProvider.this.dispose();
			}
		});
		btnBack.setBounds(504, 473, 97, 25);
		contentPane.add(btnBack);
		


	}
	
}
