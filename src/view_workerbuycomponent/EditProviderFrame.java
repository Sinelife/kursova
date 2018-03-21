package view_workerbuycomponent;

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

import dao.ProviderDao;
import domain.Provider;
import main.MethodsForFrames;
import view.AuthorisationMenu;


public class EditProviderFrame extends JFrame {

	private JPanel contentPane;
	private JTextField NameField;
	private JTextField PhoneField;
	private JTextField ContactPIBField;
	private JTextField CodeERPOUField;
	private JTextField CodeTaxpayerField;
	private JTextField SpecializationField;


	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public EditProviderFrame(JFrame parent) throws SQLException 
	{
		ProviderDao pd = new ProviderDao();
		Provider p = pd.readProvider(EditProvider.id_to_edit);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel lblNewLabel = new JLabel("Редагування інформації постачальника");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(50, 13, 480, 59);
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
		SpecializationLabel.setBounds(22, 342, 175, 22);
		contentPane.add(SpecializationLabel);
		
		
		NameField = new JTextField();
		NameField.setBounds(209, 129, 350, 22);
		contentPane.add(NameField);
		NameField.setColumns(10);
		NameField.setText(p.getName());
		
		PhoneField = new JTextField();
		PhoneField.setBounds(209, 171, 350, 22);
		contentPane.add(PhoneField);
		PhoneField.setColumns(10);
		PhoneField.setText(p.getPhone());
		
		ContactPIBField = new JTextField();
		ContactPIBField.setColumns(10);
		ContactPIBField.setBounds(209, 217, 350, 22);
		contentPane.add(ContactPIBField);
		ContactPIBField.setText(p.getContactPIB());
		
		CodeERPOUField = new JTextField();
		CodeERPOUField.setColumns(10);
		CodeERPOUField.setBounds(209, 261, 350, 22);
		contentPane.add(CodeERPOUField);
		CodeERPOUField.setText(p.getCodeERPOU());
		
		CodeTaxpayerField = new JTextField();
		CodeTaxpayerField.setColumns(10);
		CodeTaxpayerField.setBounds(209, 301, 350, 22);
		contentPane.add(CodeTaxpayerField);
		CodeTaxpayerField.setText(p.getCodeTaxpayer());
		
		SpecializationField = new JTextField();
		SpecializationField.setColumns(10);
		SpecializationField.setBounds(209, 342, 350, 22);
		contentPane.add(SpecializationField);
		SpecializationField.setText(p.getSpecialization());

		
		JButton AddButton = new JButton("Редагувати");
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
		AddButton.setBounds(52, 427, 123, 25);
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
		btnBack.setBounds(489, 427, 97, 25);
		contentPane.add(btnBack);
	}
}