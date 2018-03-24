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
import main.MethodsForFrames;
import view.AuthorisationMenu;
import java.awt.Color;
import javax.swing.SwingConstants;

public class EditUserFrame extends JFrame {

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
	public EditUserFrame(JFrame parent) throws SQLException 
	{
		UserDao ud = new UserDao();
		User u = ud.readUser(EditUser.id_to_edit);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 587, 529);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel lblNewLabel = new JLabel("Редагування інформації про працівника");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setBounds(0, 13, 569, 59);
		contentPane.add(lblNewLabel);
		
		JLabel SurnameLabel = new JLabel("Прізвище");
		SurnameLabel.setBounds(67, 120, 91, 22);
		contentPane.add(SurnameLabel);
		
		JLabel NameLabel = new JLabel("Ім'я");
		NameLabel.setBounds(67, 172, 91, 22);
		contentPane.add(NameLabel);
		
		JLabel LoginLabel = new JLabel("Логін");
		LoginLabel.setBounds(67, 230, 91, 22);
		contentPane.add(LoginLabel);
		
		JLabel PasswordLabel = new JLabel("Пароль");
		PasswordLabel.setBounds(67, 281, 91, 22);
		contentPane.add(PasswordLabel);
		
		JLabel RoleLabel = new JLabel("Роль");
		RoleLabel.setBounds(67, 333, 91, 22);
		contentPane.add(RoleLabel);
		
		
		SurnameField = new JTextField();
		SurnameField.setBackground(Color.WHITE);
		SurnameField.setBounds(174, 120, 333, 22);
		contentPane.add(SurnameField);
		SurnameField.setColumns(10);
		SurnameField.setText(u.getSurname());
		
		NameField = new JTextField();
		NameField.setBackground(Color.WHITE);
		NameField.setBounds(174, 172, 333, 22);
		contentPane.add(NameField);
		NameField.setColumns(10);
		NameField.setText(u.getName());
		
		LoginField = new JTextField();
		LoginField.setBackground(Color.WHITE);
		LoginField.setColumns(10);
		LoginField.setBounds(174, 230, 333, 22);
		contentPane.add(LoginField);
		LoginField.setText(u.getLogin());
		
		PasswordField = new JTextField();
		PasswordField.setBackground(Color.WHITE);
		PasswordField.setColumns(10);
		PasswordField.setBounds(174, 281, 333, 22);
		contentPane.add(PasswordField);
		PasswordField.setText(u.getPassword());
		
		RoleField = new JTextField();
		RoleField.setBackground(Color.WHITE);
		RoleField.setColumns(10);
		RoleField.setBounds(174, 333, 333, 22);
		contentPane.add(RoleField);
		RoleField.setText(u.getRole());
		
		
		
		JButton EditButton = new JButton("РЕДАГУВАТИ");
		EditButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		EditButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				MethodsForFrames.updateUser(u, SurnameField, NameField, LoginField, PasswordField, RoleField);
				EditUserFrame.this.setVisible(false);
				EditUserFrame.this.dispose();
				new UserMenu().setVisible(true);
			}
		});
		EditButton.setBounds(174, 390, 125, 25);
		contentPane.add(EditButton);
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				EditUserFrame.this.setVisible(false);
				EditUserFrame.this.dispose();
			}
		});
		btnBack.setBounds(428, 435, 97, 25);
		contentPane.add(btnBack);
	}

}
