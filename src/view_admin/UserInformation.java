package view_admin;

import java.awt.Color;
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
import view.AuthorisationMenu;
import javax.swing.SwingConstants;

public class UserInformation extends JFrame {

	private JPanel contentPane;
	private JTextField SurnameField;
	private JTextField NameField;
	private JTextField LoginField;
	private JTextField PasswordField;
	private JTextField RoleField;



	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public UserInformation(JFrame parent) throws SQLException 
	{
		UserDao ud = new UserDao();
		User u = ud.readUser(DeleteUser.id_to_delete);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 627, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel lblNewLabel = new JLabel("Інформація про працівника");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(0, 13, 609, 59);
		contentPane.add(lblNewLabel);
		
		
		JLabel SurnameLabel = new JLabel("Прізвище");
		SurnameLabel.setBounds(82, 98, 91, 22);
		contentPane.add(SurnameLabel);
		
		JLabel NameLabel = new JLabel("Ім'я");
		NameLabel.setBounds(82, 150, 91, 22);
		contentPane.add(NameLabel);
		
		JLabel LoginLabel = new JLabel("Логін");
		LoginLabel.setBounds(82, 208, 91, 22);
		contentPane.add(LoginLabel);
		
		JLabel PasswordLabel = new JLabel("Пароль");
		PasswordLabel.setBounds(82, 259, 91, 22);
		contentPane.add(PasswordLabel);
		
		JLabel RoleLabel = new JLabel("Роль");
		RoleLabel.setBounds(82, 311, 91, 22);
		contentPane.add(RoleLabel);
		
		
		SurnameField = new JTextField();
		SurnameField.setEditable(false);
		SurnameField.setBackground(Color.WHITE);
		SurnameField.setBounds(189, 98, 333, 22);
		contentPane.add(SurnameField);
		SurnameField.setColumns(10);
		SurnameField.setText(u.getSurname());
		
		NameField = new JTextField();
		NameField.setBackground(Color.WHITE);
		NameField.setEditable(false);
		NameField.setBounds(189, 150, 333, 22);
		contentPane.add(NameField);
		NameField.setColumns(10);
		NameField.setText(u.getName());
		
		LoginField = new JTextField();
		LoginField.setBackground(Color.WHITE);
		LoginField.setEditable(false);
		LoginField.setColumns(10);
		LoginField.setBounds(189, 208, 333, 22);
		contentPane.add(LoginField);
		LoginField.setText(u.getLogin());
		
		PasswordField = new JTextField();
		PasswordField.setBackground(Color.WHITE);
		PasswordField.setEditable(false);
		PasswordField.setColumns(10);
		PasswordField.setBounds(189, 259, 333, 22);
		contentPane.add(PasswordField);
		PasswordField.setText(u.getPassword());
		
		RoleField = new JTextField();
		RoleField.setBackground(Color.WHITE);
		RoleField.setEditable(false);
		RoleField.setColumns(10);
		RoleField.setBounds(189, 311, 333, 22);
		contentPane.add(RoleField);
		RoleField.setText(u.getRole());
		
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				UserInformation.this.setVisible(false);
				UserInformation.this.dispose();
			}
		});
		btnBack.setBounds(479, 382, 97, 25);
		contentPane.add(btnBack);
	}

}
