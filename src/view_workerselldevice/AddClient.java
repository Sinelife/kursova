package view_workerselldevice;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.ClientDao;
import domain.Client;
import view.AuthorisationMenu;

import javax.swing.JButton;

public class AddClient extends JFrame
{
	
	private JPanel contentPane;
	private JTextField NameField;
	private JTextField PhoneField;
	private JTextField ContactPIBField;
	private JTextField CodeERPOUField;
	private JTextField CodeTaxpayerField;


	/**
	 * Create the frame.
	 */
	public AddClient(JFrame parent)
	{
		ClientDao cd = new ClientDao();
		Client c = new Client();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel lblNewLabel = new JLabel("Додавання нового клієнта");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(103, 13, 421, 59);
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

		
		JButton AddButton = new JButton("Додати");
		AddButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				c.setName(NameField.getText());
				c.setPhone(PhoneField.getText());
				c.setContactPIB(ContactPIBField.getText());
				c.setCodeERPOU(CodeERPOUField.getText());
				c.setCodeTaxpayer(CodeTaxpayerField.getText());
				try {
					cd.addClient(c);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (parent != null)
					parent.setVisible(true);
				AddClient.this.setVisible(false);
				AddClient.this.dispose();
			}
		});
		AddButton.setBounds(52, 427, 97, 25);
		contentPane.add(AddButton);
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				AddClient.this.setVisible(false);
				AddClient.this.dispose();
			}
		});
		btnBack.setBounds(489, 427, 97, 25);
		contentPane.add(btnBack);
	}
}

