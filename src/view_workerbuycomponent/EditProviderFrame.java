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
		
		
		JLabel lblNewLabel = new JLabel("����������� ���������� �������������");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(52, 13, 472, 59);
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

		
		JButton AddButton = new JButton("������");
		AddButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				p.setName(NameField.getText());
				p.setPhone(PhoneField.getText());
				p.setContactPIB(ContactPIBField.getText());
				p.setCodeERPOU(CodeERPOUField.getText());
				p.setCodeTaxpayer(CodeTaxpayerField.getText());
				p.setSpecialization(SpecializationField.getText());
				try {
					pd.updateProvider(p);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				EditProviderFrame.this.setVisible(false);
				EditProviderFrame.this.dispose();
				new ProviderMenu().setVisible(true);
			}
		});
		AddButton.setBounds(52, 427, 97, 25);
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