package view_constructor;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


import main.MethodsForFrames;
import view.AuthorisationMenu;

import javax.swing.JButton;

public class AddComponent extends JFrame {

	private JPanel contentPane;
	private JTextField TypeField;
	private JTextField NameField;
	private JTextField TechnicalInfoField;
	private JTextField PriceField;


	/**
	 * Create the frame.
	 */
	public AddComponent(JFrame parent)
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel lblNewLabel = new JLabel("Додавання нового компонента");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(103, 13, 421, 59);
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
		
		
		TypeField = new JTextField();
		TypeField.setBounds(209, 157, 350, 22);
		contentPane.add(TypeField);
		TypeField.setColumns(10);
		
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

		
		
		JButton AddButton = new JButton("Додати");
		AddButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				MethodsForFrames.addComponent(TypeField, NameField, TechnicalInfoField, PriceField);
				if (parent != null)
					parent.setVisible(true);
				AddComponent.this.setVisible(false);
				AddComponent.this.dispose();
			}
		});
		AddButton.setBounds(52, 427, 97, 25);
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
		btnBack.setBounds(489, 427, 97, 25);
		contentPane.add(btnBack);
	}
}
