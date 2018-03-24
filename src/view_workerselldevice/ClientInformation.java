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
import javax.swing.SwingConstants;

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
		setBounds(100, 100, 646, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel lblNewLabel = new JLabel("Інформація про клієнта");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(0, 13, 628, 49);
		contentPane.add(lblNewLabel);
		
		JLabel NameLabel = new JLabel("Ім'я");
		NameLabel.setBounds(32, 105, 175, 22);
		contentPane.add(NameLabel);
		
		JLabel PhoneLabel = new JLabel("Телефон");
		PhoneLabel.setBounds(32, 147, 175, 22);
		contentPane.add(PhoneLabel);
		
		JLabel ContactPIBLabel = new JLabel("Контактна особа");
		ContactPIBLabel.setBounds(32, 193, 175, 22);
		contentPane.add(ContactPIBLabel);
		
		JLabel CodeERPOULabel = new JLabel("Код ЕДРПОУ");
		CodeERPOULabel.setBounds(32, 237, 175, 22);
		contentPane.add(CodeERPOULabel);
		
		JLabel CodeTaxpayerLabel = new JLabel("Код платника податків");
		CodeTaxpayerLabel.setBounds(32, 277, 175, 22);
		contentPane.add(CodeTaxpayerLabel);
		
		
		NameField = new JTextField();
		NameField.setBackground(Color.WHITE);
		NameField.setEditable(false);
		NameField.setBounds(219, 105, 350, 22);
		contentPane.add(NameField);
		NameField.setColumns(10);
		NameField.setText(c.getName());
		
		PhoneField = new JTextField();
		PhoneField.setBackground(Color.WHITE);
		PhoneField.setEditable(false);
		PhoneField.setBounds(219, 147, 350, 22);
		contentPane.add(PhoneField);
		PhoneField.setColumns(10);
		PhoneField.setText(c.getPhone());
		
		ContactPIBField = new JTextField();
		ContactPIBField.setBackground(Color.WHITE);
		ContactPIBField.setEditable(false);
		ContactPIBField.setColumns(10);
		ContactPIBField.setBounds(219, 193, 350, 22);
		contentPane.add(ContactPIBField);
		ContactPIBField.setText(c.getContactPIB());
		
		CodeERPOUField = new JTextField();
		CodeERPOUField.setBackground(Color.WHITE);
		CodeERPOUField.setEditable(false);
		CodeERPOUField.setColumns(10);
		CodeERPOUField.setBounds(219, 237, 350, 22);
		contentPane.add(CodeERPOUField);
		CodeERPOUField.setText(c.getCodeERPOU());
		
		CodeTaxpayerField = new JTextField();
		CodeTaxpayerField.setBackground(Color.WHITE);
		CodeTaxpayerField.setEditable(false);
		CodeTaxpayerField.setColumns(10);
		CodeTaxpayerField.setBounds(219, 277, 350, 22);
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
		btnBack.setBounds(496, 376, 97, 25);
		contentPane.add(btnBack);
	}


}
