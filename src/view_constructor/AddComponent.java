package view_constructor;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.ComponentDao;
import domain.Specialization;
import main.MethodsForFrames;
import view.AuthorisationMenu;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.SwingConstants;

public class AddComponent extends JFrame {

	private JPanel contentPane;
	private JComboBox<String> TypeComboBox;
	private JTextField NameField;
	private JTextField TechnicalInfoField;
	private JTextField PriceField;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public AddComponent(JFrame parent) throws SQLException
	{
		ComponentDao cd = new ComponentDao();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 609, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel lblNewLabel = new JLabel("Додавання нового компонента");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(80, 13, 421, 59);
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
		PriceLabel.setBounds(36, 318, 136, 22);
		contentPane.add(PriceLabel);
		
		
		TypeComboBox = new JComboBox<String>();
		TypeComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		TypeComboBox.setForeground(Color.BLACK);
		TypeComboBox.setBackground(Color.WHITE);
		TypeComboBox.setBounds(209, 157, 350, 22);
		contentPane.add(TypeComboBox);
		List<Specialization> list = cd.getAllSpecializations();
		for(Specialization specialization : list)
		{
			TypeComboBox.addItem(specialization.getSpecialization());
		}
		
		
		NameField = new JTextField();
		NameField.setBounds(209, 209, 350, 22);
		contentPane.add(NameField);
		NameField.setColumns(10);
		
		TechnicalInfoField = new JTextField();
		TechnicalInfoField.setColumns(10);
		TechnicalInfoField.setBounds(209, 267, 350, 22);
		contentPane.add(TechnicalInfoField);
				
		PriceField = new JTextField();
		PriceField.setColumns(10);
		PriceField.setBounds(209, 318, 350, 22);
		contentPane.add(PriceField);

		
		
		JButton AddButton = new JButton("ДОДАТИ");
		AddButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try {
					MethodsForFrames.addComponent(TypeComboBox, NameField, TechnicalInfoField, PriceField);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if (parent != null)
					parent.setVisible(true);
				AddComponent.this.setVisible(false);
				AddComponent.this.dispose();
			}
		});
		AddButton.setBounds(209, 378, 97, 25);
		contentPane.add(AddButton);

		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				AddComponent.this.setVisible(false);
				AddComponent.this.dispose();
			}
		});
		btnBack.setBounds(462, 450, 97, 25);
		contentPane.add(btnBack);
	}
}
