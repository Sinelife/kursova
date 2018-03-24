package view_workerbuycomponent;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.ProviderDao;
import domain.Provider;
import main.MethodsForFrames;
import view.AuthorisationMenu;
import javax.swing.SwingConstants;


public class EditProviderFrame extends JFrame {

	private JPanel contentPane;
	private JTextField NameField;
	private JTextField PhoneField;
	private JTextField ContactPIBField;
	private JTextField CodeERPOUField;
	private JTextField CodeTaxpayerField;
	private static JTextField SpecializationField;

	ProviderDao pd = new ProviderDao();
	Provider p = pd.readProvider(EditProvider.id_to_edit);
	
	JComboBox<String> NotInComboBox =  MethodsForFrames.specializationNotInProvider(p);
	JComboBox<String> InComboBox =  MethodsForFrames.specializationInProvider(p);
	
	private static String result = "";
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public EditProviderFrame(JFrame parent) throws SQLException 
	{		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 598);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel lblNewLabel = new JLabel("Редагування інформації постачальника");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setBounds(0, 13, 628, 59);
		contentPane.add(lblNewLabel);
		
		JLabel NameLabel = new JLabel("Ім'я");
		NameLabel.setBounds(49, 128, 175, 22);
		contentPane.add(NameLabel);
		
		JLabel PhoneLabel = new JLabel("Телефон");
		PhoneLabel.setBounds(49, 170, 175, 22);
		contentPane.add(PhoneLabel);
		
		JLabel ContactPIBLabel = new JLabel("Контактна особа");
		ContactPIBLabel.setBounds(49, 216, 175, 22);
		contentPane.add(ContactPIBLabel);
		
		JLabel CodeERPOULabel = new JLabel("Код ЕДРПОУ");
		CodeERPOULabel.setBounds(49, 260, 175, 22);
		contentPane.add(CodeERPOULabel);
		
		JLabel CodeTaxpayerLabel = new JLabel("Код платника податків");
		CodeTaxpayerLabel.setBounds(49, 300, 175, 22);
		contentPane.add(CodeTaxpayerLabel);
		
		JLabel SpecializationLabel = new JLabel("Спеціалізація");
		SpecializationLabel.setBounds(49, 341, 175, 22);
		contentPane.add(SpecializationLabel);
		
		
		NameField = new JTextField();
		NameField.setBounds(236, 128, 350, 22);
		contentPane.add(NameField);
		NameField.setColumns(10);
		NameField.setText(p.getName());
		
		PhoneField = new JTextField();
		PhoneField.setBounds(236, 170, 350, 22);
		contentPane.add(PhoneField);
		PhoneField.setColumns(10);
		PhoneField.setText(p.getPhone());
		
		ContactPIBField = new JTextField();
		ContactPIBField.setColumns(10);
		ContactPIBField.setBounds(236, 216, 350, 22);
		contentPane.add(ContactPIBField);
		ContactPIBField.setText(p.getContactPIB());
		
		CodeERPOUField = new JTextField();
		CodeERPOUField.setColumns(10);
		CodeERPOUField.setBounds(236, 260, 350, 22);
		contentPane.add(CodeERPOUField);
		CodeERPOUField.setText(p.getCodeERPOU());
		
		CodeTaxpayerField = new JTextField();
		CodeTaxpayerField.setColumns(10);
		CodeTaxpayerField.setBounds(236, 300, 350, 22);
		contentPane.add(CodeTaxpayerField);
		CodeTaxpayerField.setText(p.getCodeTaxpayer());
		
		SpecializationField = new JTextField();
		SpecializationField.setColumns(10);
		SpecializationField.setBounds(236, 341, 350, 22);
		contentPane.add(SpecializationField);
		SpecializationField.setText(p.getSpecialization());
		result = SpecializationField.getText();
		
		
		NotInComboBox.setBackground(Color.WHITE);
		NotInComboBox.setBounds(236, 366, 170, 22);
		contentPane.add(NotInComboBox);
	
		InComboBox.setBackground(Color.WHITE);
		InComboBox.setBounds(415, 366, 170, 22);
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
		PlusButton.setBounds(363, 402, 47, 25);
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
		MinusButton.setBounds(410, 402, 47, 25);
		contentPane.add(MinusButton);
		
		
		
		JButton AddButton = new JButton("РЕДАГУВАТИ");
		AddButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try {
					MethodsForFrames.updateProvider(p, NameField, PhoneField, ContactPIBField, CodeERPOUField, CodeTaxpayerField, SpecializationField);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				EditProviderFrame.this.setVisible(false);
				EditProviderFrame.this.dispose();
				new ProviderMenu().setVisible(true);
			}
		});
		AddButton.setBounds(236, 457, 123, 25);
		contentPane.add(AddButton);
		


		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				EditProviderFrame.this.setVisible(false);
				EditProviderFrame.this.dispose();
			}
		});
		btnBack.setBounds(489, 513, 97, 25);
		contentPane.add(btnBack);
	}
	
	

}