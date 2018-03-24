package view_constructor;

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

import dao.ComponentDao;
import domain.Component;
import view.AuthorisationMenu;
import view_director.ConstructDepartmentDirectorMenu;

import java.awt.Color;
import javax.swing.SwingConstants;

public class ComponentInformation extends JFrame {

	private JPanel contentPane;
	private JTextField TypeField;
	private JTextField NameField;
	private JTextField TechnicalInfoField;
	private JTextField PriceField;
	


	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public ComponentInformation(JFrame parent) throws SQLException 
	{
 	  	ComponentDao cd = new ComponentDao();
 	  	Component c = new Component();
		
 	  	
		if(AuthorisationMenu.user_role.equals("director"))
		{
			c = cd.readComponent(ConstructDepartmentDirectorMenu.component_id_to_look);		
		}
		else
		{
			c = cd.readComponent(InfoComponent.id_to_look);
		}
 	  	
 	  	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 604, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel lblNewLabel = new JLabel("Інформація про компонент");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(85, 13, 421, 59);
		contentPane.add(lblNewLabel);
		
		JLabel TypeLabel = new JLabel("Тип компоненту");
		TypeLabel.setBounds(36, 157, 136, 22);
		contentPane.add(TypeLabel);
		
		JLabel NameLabel = new JLabel("Назва компоненту");
		NameLabel.setBounds(36, 209, 136, 22);
		contentPane.add(NameLabel);
		
		JLabel TechnicalInfoLabel = new JLabel("Технічна інформація");
		TechnicalInfoLabel.setBounds(36, 267, 136, 22);
		contentPane.add(TechnicalInfoLabel);
		
		JLabel PriceLabel = new JLabel("Ціна");
		PriceLabel.setBounds(36, 319, 136, 22);
		contentPane.add(PriceLabel);
		
		
		TypeField = new JTextField();
		TypeField.setBackground(Color.WHITE);
		TypeField.setEditable(false);
		TypeField.setBounds(209, 157, 350, 22);
		contentPane.add(TypeField);
		TypeField.setColumns(10);
		TypeField.setText(c.getType());
		
		NameField = new JTextField();
		NameField.setBackground(Color.WHITE);
		NameField.setEditable(false);
		NameField.setBounds(209, 209, 350, 22);
		contentPane.add(NameField);
		NameField.setColumns(10);
		NameField.setText(c.getName());
		
		TechnicalInfoField = new JTextField();
		TechnicalInfoField.setBackground(Color.WHITE);
		TechnicalInfoField.setEditable(false);
		TechnicalInfoField.setColumns(10);
		TechnicalInfoField.setBounds(209, 267, 350, 22);
		contentPane.add(TechnicalInfoField);
		TechnicalInfoField.setText(c.getTechnicalInfo());
		
		
		PriceField = new JTextField();
		PriceField.setText((String) null);
		PriceField.setEditable(false);
		PriceField.setColumns(10);
		PriceField.setBackground(Color.WHITE);
		PriceField.setBounds(209, 319, 350, 22);
		contentPane.add(PriceField);
		PriceField.setText(String.valueOf(c.getPrice()));
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				ComponentInformation.this.setVisible(false);
				ComponentInformation.this.dispose();
			}
		});
		btnBack.setBounds(462, 404, 97, 25);
		contentPane.add(btnBack);
	}

}
