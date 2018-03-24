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
import main.MethodsForFrames;
import view.AuthorisationMenu;
import javax.swing.SwingConstants;

public class EditClientFrame extends JFrame {

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
	public EditClientFrame(JFrame parent) throws SQLException 
	{
		ClientDao cd = new ClientDao();
		Client c = cd.readClient(EditClient.id_to_edit);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel lblNewLabel = new JLabel("Редагування інформації клієнта");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setBounds(0, 13, 628, 49);
		contentPane.add(lblNewLabel);
		
		JLabel NameLabel = new JLabel("Ім'я");
		NameLabel.setBounds(52, 107, 175, 22);
		contentPane.add(NameLabel);
		
		JLabel PhoneLabel = new JLabel("Телефон");
		PhoneLabel.setBounds(52, 149, 175, 22);
		contentPane.add(PhoneLabel);
		
		JLabel ContactPIBLabel = new JLabel("Контактна особа");
		ContactPIBLabel.setBounds(52, 195, 175, 22);
		contentPane.add(ContactPIBLabel);
		
		JLabel CodeERPOULabel = new JLabel("Код ЕДРПОУ");
		CodeERPOULabel.setBounds(52, 239, 175, 22);
		contentPane.add(CodeERPOULabel);
		
		JLabel CodeTaxpayerLabel = new JLabel("Код платника податків");
		CodeTaxpayerLabel.setBounds(52, 279, 175, 22);
		contentPane.add(CodeTaxpayerLabel);
		
		
		NameField = new JTextField();
		NameField.setBounds(239, 107, 350, 22);
		contentPane.add(NameField);
		NameField.setColumns(10);
		NameField.setText(c.getName());
		
		PhoneField = new JTextField();
		PhoneField.setBounds(239, 149, 350, 22);
		contentPane.add(PhoneField);
		PhoneField.setColumns(10);
		PhoneField.setText(c.getPhone());
		
		ContactPIBField = new JTextField();
		ContactPIBField.setColumns(10);
		ContactPIBField.setBounds(239, 195, 350, 22);
		contentPane.add(ContactPIBField);
		ContactPIBField.setText(c.getContactPIB());
		
		CodeERPOUField = new JTextField();
		CodeERPOUField.setColumns(10);
		CodeERPOUField.setBounds(239, 239, 350, 22);
		contentPane.add(CodeERPOUField);
		CodeERPOUField.setText(c.getCodeERPOU());
		
		CodeTaxpayerField = new JTextField();
		CodeTaxpayerField.setColumns(10);
		CodeTaxpayerField.setBounds(239, 279, 350, 22);
		contentPane.add(CodeTaxpayerField);
		CodeTaxpayerField.setText(c.getCodeTaxpayer());

		
		JButton AddButton = new JButton("РЕДАГУВАТИ");
		AddButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try {
					MethodsForFrames.updateClient(c, NameField, PhoneField, ContactPIBField, CodeERPOUField, CodeTaxpayerField);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				EditClientFrame.this.setVisible(false);
				EditClientFrame.this.dispose();
				new ClientMenu().setVisible(true);
			}
		});
		AddButton.setBounds(239, 330, 125, 25);
		contentPane.add(AddButton);
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				EditClientFrame.this.setVisible(false);
				EditClientFrame.this.dispose();
			}
		});
		btnBack.setBounds(492, 388, 97, 25);
		contentPane.add(btnBack);
	}

}
