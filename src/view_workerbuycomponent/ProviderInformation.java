package view_workerbuycomponent;

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
import view_director.DeliveryDepartmentDirectorMenu;

import java.awt.Color;
import javax.swing.SwingConstants;

public class ProviderInformation extends JFrame 
{

	private JPanel contentPane;
	private JTextField NameField;
	private JTextField PhoneField;
	private JTextField ContactPIBField;
	private JTextField CodeERPOUField;
	private JTextField CodeTaxpayerField;
	private JComboBox<?> SpecializationComboBox;


	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public ProviderInformation(JFrame parent) throws SQLException 
	{
		ProviderDao pd = new ProviderDao();
		Provider p = new Provider();
		
		
		if(AuthorisationMenu.user_role.equals("director"))
		{
			p = pd.readProvider(DeliveryDepartmentDirectorMenu.provider_id_to_look);	
			SpecializationComboBox = MethodsForFrames.specializationInProvider(p);
		}
		else
		{
			p = pd.readProvider(InfoProvider.provider_id_to_look);
			SpecializationComboBox = MethodsForFrames.specializationInProvider(p);
		}

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 486);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel lblNewLabel = new JLabel("���������� �������������");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(0, 13, 628, 59);
		contentPane.add(lblNewLabel);
		
		JLabel NameLabel = new JLabel("��'�");
		NameLabel.setBounds(22, 129, 175, 22);
		contentPane.add(NameLabel);
		
		JLabel PhoneLabel = new JLabel("�������");
		PhoneLabel.setBounds(22, 171, 175, 22);
		contentPane.add(PhoneLabel);
		
		JLabel ContactPIBLabel = new JLabel("��������� �����");
		ContactPIBLabel.setBounds(22, 217, 175, 22);
		contentPane.add(ContactPIBLabel);
		
		JLabel CodeERPOULabel = new JLabel("��� ������");
		CodeERPOULabel.setBounds(22, 261, 175, 22);
		contentPane.add(CodeERPOULabel);
		
		JLabel CodeTaxpayerLabel = new JLabel("��� �������� �������");
		CodeTaxpayerLabel.setBounds(22, 301, 175, 22);
		contentPane.add(CodeTaxpayerLabel);
		
		JLabel SpecializationLabel = new JLabel("������������");
		SpecializationLabel.setBounds(22, 342, 175, 22);
		contentPane.add(SpecializationLabel);
		
		
		NameField = new JTextField();
		NameField.setBackground(Color.WHITE);
		NameField.setEditable(false);
		NameField.setBounds(209, 129, 350, 22);
		contentPane.add(NameField);
		NameField.setColumns(10);
		NameField.setText(p.getName());
		
		PhoneField = new JTextField();
		PhoneField.setBackground(Color.WHITE);
		PhoneField.setEditable(false);
		PhoneField.setBounds(209, 171, 350, 22);
		contentPane.add(PhoneField);
		PhoneField.setColumns(10);
		PhoneField.setText(p.getPhone());
		
		ContactPIBField = new JTextField();
		ContactPIBField.setBackground(Color.WHITE);
		ContactPIBField.setEditable(false);
		ContactPIBField.setColumns(10);
		ContactPIBField.setBounds(209, 217, 350, 22);
		contentPane.add(ContactPIBField);
		ContactPIBField.setText(p.getContactPIB());
		
		CodeERPOUField = new JTextField();
		CodeERPOUField.setBackground(Color.WHITE);
		CodeERPOUField.setEditable(false);
		CodeERPOUField.setColumns(10);
		CodeERPOUField.setBounds(209, 261, 350, 22);
		contentPane.add(CodeERPOUField);
		CodeERPOUField.setText(p.getCodeERPOU());
		
		CodeTaxpayerField = new JTextField();
		CodeTaxpayerField.setBackground(Color.WHITE);
		CodeTaxpayerField.setEditable(false);
		CodeTaxpayerField.setColumns(10);
		CodeTaxpayerField.setBounds(209, 301, 350, 22);
		contentPane.add(CodeTaxpayerField);
		CodeTaxpayerField.setText(p.getCodeTaxpayer());
		
		SpecializationComboBox.setBackground(Color.WHITE);
		SpecializationComboBox.setEditable(false);
		SpecializationComboBox.setBounds(209, 342, 350, 22);
		contentPane.add(SpecializationComboBox);
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				ProviderInformation.this.setVisible(false);
				ProviderInformation.this.dispose();
			}
		});
		btnBack.setBounds(491, 401, 97, 25);
		contentPane.add(btnBack);
	}


}
