package view_workerselldevice;

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

public class AddOrder extends JFrame 
{

	private JPanel contentPane;
	private JTextField StartDateField;

	public AddOrder(JFrame parent) 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel lblNewLabel = new JLabel("Додавання нового замовлення на купівлю");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(0, 13, 628, 59);
		contentPane.add(lblNewLabel);

		
		JLabel StartDateLabel = new JLabel("Дата початку");
		StartDateLabel.setBounds(60, 116, 129, 22);
		contentPane.add(StartDateLabel);
	
		
		StartDateField = new JTextField();
		StartDateField.setBounds(192, 116, 350, 22);
		contentPane.add(StartDateField);
		StartDateField.setColumns(10);

		
		JButton AddButton = new JButton("ДОДАТИ");
		AddButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				MethodsForFrames.addOrder(ChooseClient.name_to_choose, ChooseClient.id_to_choose, StartDateField);
				if (parent != null)
					parent.setVisible(true);
				AddOrder.this.setVisible(false);
				AddOrder.this.dispose();
			}
		});
		AddButton.setBounds(60, 192, 97, 25);
		contentPane.add(AddButton);
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				AddOrder.this.setVisible(false);
				AddOrder.this.dispose();
			}
		});
		btnBack.setBounds(497, 192, 97, 25);
		contentPane.add(btnBack);
	}

}
