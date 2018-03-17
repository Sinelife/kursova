package view_workerselldevice;

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

import dao.ClientDao;
import domain.Client;
import view.AuthorisationMenu;
import view_director.SalesDepartmentDirectorMenu;
import java.awt.Color;

public class ClientInformation extends JFrame 
{

	private JPanel contentPane;
	private JTextField NameField;
	private JTextField PhoneField;
	private JTextField ContactPIBField;
	private JTextField CodeERPOUField;
	private JTextField CodeTaxpayerField;


	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public ClientInformation(JFrame parent) throws SQLException 
	{
		ClientDao cd = new ClientDao();
		Client c = new Client();
		
		
		if(AuthorisationMenu.user_role.equals("director"))
		{
			c = cd.readClient(SalesDepartmentDirectorMenu.client_id_to_look);		
		}
		else
		{
			c = cd.readClient(InfoClient.client_id_to_look);
		}
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel lblNewLabel = new JLabel("Інформація клієнта");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(52, 13, 472, 59);
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
		
		
		NameField = new JTextField();
		NameField.setBackground(Color.WHITE);
		NameField.setEditable(false);
		NameField.setBounds(209, 129, 350, 22);
		contentPane.add(NameField);
		NameField.setColumns(10);
		NameField.setText(c.getName());
		
		PhoneField = new JTextField();
		PhoneField.setBackground(Color.WHITE);
		PhoneField.setEditable(false);
		PhoneField.setBounds(209, 171, 350, 22);
		contentPane.add(PhoneField);
		PhoneField.setColumns(10);
		PhoneField.setText(c.getPhone());
		
		ContactPIBField = new JTextField();
		ContactPIBField.setBackground(Color.WHITE);
		ContactPIBField.setEditable(false);
		ContactPIBField.setColumns(10);
		ContactPIBField.setBounds(209, 217, 350, 22);
		contentPane.add(ContactPIBField);
		ContactPIBField.setText(c.getContactPIB());
		
		CodeERPOUField = new JTextField();
		CodeERPOUField.setBackground(Color.WHITE);
		CodeERPOUField.setEditable(false);
		CodeERPOUField.setColumns(10);
		CodeERPOUField.setBounds(209, 261, 350, 22);
		contentPane.add(CodeERPOUField);
		CodeERPOUField.setText(c.getCodeERPOU());
		
		CodeTaxpayerField = new JTextField();
		CodeTaxpayerField.setBackground(Color.WHITE);
		CodeTaxpayerField.setEditable(false);
		CodeTaxpayerField.setColumns(10);
		CodeTaxpayerField.setBounds(209, 301, 350, 22);
		contentPane.add(CodeTaxpayerField);
		CodeTaxpayerField.setText(c.getCodeTaxpayer());
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				ClientInformation.this.setVisible(false);
				ClientInformation.this.dispose();
			}
		});
		btnBack.setBounds(489, 427, 97, 25);
		contentPane.add(btnBack);
	}


}
