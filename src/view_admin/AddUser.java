package view_admin;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import main.MethodsForFrames;
import view.AuthorisationMenu;
import javax.swing.SwingConstants;

public class AddUser extends JFrame 
{
	private JPanel contentPane;
	private JTextField SurnameField;
	private JTextField NameField;
	private JTextField LoginField;
	private JTextField PasswordField;
	private JTextField RoleField;

	/**
	 * Create the frame.
	 */
	public AddUser(JFrame parent) 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 627, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		JLabel lblNewLabel = new JLabel("Додавання нового працівника");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(0, 13, 609, 59);
		contentPane.add(lblNewLabel);
		
		JLabel SurnameLabel = new JLabel("Прізвище");
		SurnameLabel.setBounds(88, 101, 91, 22);
		contentPane.add(SurnameLabel);
		
		JLabel NameLabel = new JLabel("Ім'я");
		NameLabel.setBounds(88, 153, 91, 22);
		contentPane.add(NameLabel);
		
		JLabel LoginLabel = new JLabel("Логін");
		LoginLabel.setBounds(88, 211, 91, 22);
		contentPane.add(LoginLabel);
		
		JLabel PasswordLabel = new JLabel("Пароль");
		PasswordLabel.setBounds(88, 262, 91, 22);
		contentPane.add(PasswordLabel);
		
		JLabel RoleLabel = new JLabel("Роль");
		RoleLabel.setBounds(88, 314, 91, 22);
		contentPane.add(RoleLabel);
		
		
		SurnameField = new JTextField();
		SurnameField.setBounds(195, 101, 333, 22);
		contentPane.add(SurnameField);
		SurnameField.setColumns(10);
		
		NameField = new JTextField();
		NameField.setBounds(195, 153, 333, 22);
		contentPane.add(NameField);
		NameField.setColumns(10);
		
		LoginField = new JTextField();
		LoginField.setColumns(10);
		LoginField.setBounds(195, 211, 333, 22);
		contentPane.add(LoginField);
		
		PasswordField = new JTextField();
		PasswordField.setColumns(10);
		PasswordField.setBounds(195, 262, 333, 22);
		contentPane.add(PasswordField);
		
		RoleField = new JTextField();
		RoleField.setColumns(10);
		RoleField.setBounds(195, 314, 333, 22);
		contentPane.add(RoleField);
		
		
		JButton AddButton = new JButton("ДОДАТИ");
		AddButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				MethodsForFrames.addUser(SurnameField, NameField, LoginField, PasswordField, RoleField);
				if (parent != null)
					parent.setVisible(true);
				AddUser.this.setVisible(false);
				AddUser.this.dispose();
			}
		});
		AddButton.setBounds(195, 370, 97, 25);
		contentPane.add(AddButton);
		
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				AddUser.this.setVisible(false);
				AddUser.this.dispose();
			}
		});
		btnBack.setBounds(476, 417, 97, 25);
		contentPane.add(btnBack);
	}
}
