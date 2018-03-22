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
import main.MethodsForFrames;
import view.AuthorisationMenu;
import java.awt.Color;


public class EditComponentFrame extends JFrame {

	private JPanel contentPane;
	private JTextField NameField;
	private JTextField TechnicalInfoField;
	private JTextField PriceField;
	private JTextField TypeField;
	
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public EditComponentFrame(JFrame parent) throws SQLException 
	{
 	  	ComponentDao cd = new ComponentDao();
 	  	Component c = cd.readComponent(EditComponent.id_to_edit);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel lblNewLabel = new JLabel("Редагування компонента");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(103, 13, 421, 59);
		contentPane.add(lblNewLabel);
		
		JLabel TypeLabel = new JLabel("Тип");
		TypeLabel.setBounds(35, 131, 136, 22);
		contentPane.add(TypeLabel);
		
		JLabel NameLabel = new JLabel("Назва компоненту");
		NameLabel.setBounds(35, 178, 136, 22);
		contentPane.add(NameLabel);
		
		JLabel TechnicalInfoLabel = new JLabel("Технічна інформація");
		TechnicalInfoLabel.setBounds(35, 228, 136, 22);
		contentPane.add(TechnicalInfoLabel);
		
		JLabel PriceLabel = new JLabel("Ціна");
		PriceLabel.setBounds(35, 274, 136, 22);
		contentPane.add(PriceLabel);
		
		
		TypeField = new JTextField();
		TypeField.setBackground(Color.WHITE);
		TypeField.setEditable(false);
		TypeField.setBounds(208, 131, 350, 22);
		contentPane.add(TypeField);
		TypeField.setColumns(10);
		TypeField.setText(c.getType());
		
		NameField = new JTextField();
		NameField.setBackground(Color.WHITE);
		NameField.setBounds(208, 178, 350, 22);
		contentPane.add(NameField);
		NameField.setColumns(10);
		NameField.setText(c.getName());
		
		TechnicalInfoField = new JTextField();
		TechnicalInfoField.setColumns(10);
		TechnicalInfoField.setBounds(209, 228, 350, 22);
		contentPane.add(TechnicalInfoField);
		TechnicalInfoField.setText(c.getTechnicalInfo());
		
		PriceField = new JTextField();
		PriceField.setText((String) null);
		PriceField.setColumns(10);
		PriceField.setBounds(209, 274, 350, 22);
		contentPane.add(PriceField);
		PriceField.setText(String.valueOf(c.getPrice()));
		
		
		JButton EditButton = new JButton("Редагувати");
		EditButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		EditButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try {
					MethodsForFrames.updateComponent(c, NameField, TechnicalInfoField, PriceField);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				EditComponentFrame.this.setVisible(false);
				EditComponentFrame.this.dispose();
				new ComponentMenu().setVisible(true);
			}
		});
		EditButton.setBounds(35, 359, 125, 25);
		contentPane.add(EditButton);
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				EditComponentFrame.this.setVisible(false);
				EditComponentFrame.this.dispose();
			}
		});
		btnBack.setBounds(487, 360, 97, 25);
		contentPane.add(btnBack);
	}

}
