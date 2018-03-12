package view_admin;

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

import dao.UserDao;
import domain.User;

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
		UserDao ud = new UserDao();
		User u = new User();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 627, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Додавання нового працівника");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(103, 13, 421, 59);
		contentPane.add(lblNewLabel);
		
		JLabel SurnameLabel = new JLabel("Прізвище");
		SurnameLabel.setBounds(36, 157, 91, 22);
		contentPane.add(SurnameLabel);
		
		JLabel NameLabel = new JLabel("Ім'я");
		NameLabel.setBounds(36, 209, 91, 22);
		contentPane.add(NameLabel);
		
		JLabel LoginLabel = new JLabel("Логін");
		LoginLabel.setBounds(36, 267, 91, 22);
		contentPane.add(LoginLabel);
		
		JLabel PasswordLabel = new JLabel("Пароль");
		PasswordLabel.setBounds(36, 318, 91, 22);
		contentPane.add(PasswordLabel);
		
		JLabel RoleLabel = new JLabel("Роль");
		RoleLabel.setBounds(36, 370, 91, 22);
		contentPane.add(RoleLabel);
		
		
		SurnameField = new JTextField();
		SurnameField.setBounds(143, 157, 333, 22);
		contentPane.add(SurnameField);
		SurnameField.setColumns(10);
		
		NameField = new JTextField();
		NameField.setBounds(143, 209, 333, 22);
		contentPane.add(NameField);
		NameField.setColumns(10);
		
		LoginField = new JTextField();
		LoginField.setColumns(10);
		LoginField.setBounds(143, 267, 333, 22);
		contentPane.add(LoginField);
		
		PasswordField = new JTextField();
		PasswordField.setColumns(10);
		PasswordField.setBounds(143, 318, 333, 22);
		contentPane.add(PasswordField);
		
		RoleField = new JTextField();
		RoleField.setColumns(10);
		RoleField.setBounds(143, 370, 333, 22);
		contentPane.add(RoleField);
		
		
		JButton AddButton = new JButton("Додати");
		AddButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				u.setSurname(SurnameField.getText());
				u.setName(NameField.getText());
				u.setLogin(LoginField.getText());
				u.setPassword(PasswordField.getText());
				u.setRole(RoleField.getText());
				try {
					ud.addUser(u);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (parent != null)
					parent.setVisible(true);
				AddUser.this.setVisible(false);
				AddUser.this.dispose();
			}
		});
		AddButton.setBounds(36, 473, 97, 25);
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
		btnBack.setBounds(482, 473, 97, 25);
		contentPane.add(btnBack);
	}
}
