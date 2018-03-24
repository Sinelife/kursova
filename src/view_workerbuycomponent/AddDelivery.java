package view_workerbuycomponent;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import main.MethodsForFrames;
import view.AuthorisationMenu;
import javax.swing.SwingConstants;

public class AddDelivery extends JFrame 
{

	private JPanel contentPane;
	private JTextField StartDateField;
	private JCheckBox PaidCheckBox;
	private JCheckBox ShippedCheckBox;

	public AddDelivery(JFrame parent) 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel lblNewLabel = new JLabel("Додавання нового замовлення постачання");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setBounds(0, 13, 628, 59);
		contentPane.add(lblNewLabel);
		
		JLabel StartDateLabel = new JLabel("Дата початку");
		StartDateLabel.setBounds(29, 115, 129, 22);
		contentPane.add(StartDateLabel);
		
		JLabel PaidLabel = new JLabel("Сплачено");
		PaidLabel.setBounds(29, 161, 129, 22);
		contentPane.add(PaidLabel);
		
		JLabel ShippedLabel = new JLabel("Відвантажено");
		ShippedLabel.setBounds(29, 195, 129, 22);
		contentPane.add(ShippedLabel);
		
		StartDateField = new JTextField();
		StartDateField.setBounds(161, 115, 350, 22);
		contentPane.add(StartDateField);
		StartDateField.setColumns(10);
		
		PaidCheckBox = new JCheckBox();
		PaidCheckBox.setBounds(161, 158, 113, 25);
		contentPane.add(PaidCheckBox);

		ShippedCheckBox = new JCheckBox();
		ShippedCheckBox.setBounds(161, 192, 113, 25);
		contentPane.add(ShippedCheckBox);
		
		
		JButton AddButton = new JButton("ДОДАТИ");
		AddButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				MethodsForFrames.addDelivery(ChooseProvider.name_to_choose, ChooseProvider.id_to_choose, StartDateField, PaidCheckBox, ShippedCheckBox);
				if (parent != null)
					parent.setVisible(true);
				AddDelivery.this.setVisible(false);
				AddDelivery.this.dispose();
			}
		});
		AddButton.setBounds(161, 244, 97, 25);
		contentPane.add(AddButton);
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				AddDelivery.this.setVisible(false);
				AddDelivery.this.dispose();
			}
		});
		btnBack.setBounds(505, 314, 97, 25);
		contentPane.add(btnBack);
	}

}
