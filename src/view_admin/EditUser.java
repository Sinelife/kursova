package view_admin;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.UserDao;
import domain.User;
import main.MethodsForFrames;
import view.AuthorisationMenu;
import javax.swing.SwingConstants;

public class EditUser extends JFrame {


	private JPanel contentPane;

	public static int id_to_edit;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public EditUser(JFrame parent) throws SQLException 
	{
		UserDao ud = new UserDao();
		List<User> users = ud.getAll();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		

		JComboBox<String> UserComboBox = new JComboBox<String>();
		UserComboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		UserComboBox.setBounds(62, 120, 504, 34);
		contentPane.add(UserComboBox);
		for(User user : users) 
		{
			UserComboBox.addItem(user.getSurname() + " " + user.getName());
		}
		
		JLabel lblNewLabel = new JLabel("���� ���������� ��� �����������");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setBounds(0, 25, 628, 59);
		contentPane.add(lblNewLabel);
		
		
		
		JButton SelectButton = new JButton("�������");
		SelectButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				id_to_edit = MethodsForFrames.getUsertIdByUserSurnameAndName(UserComboBox, users);
				EditUser.this.setVisible(false);
				try {
					new EditUserFrame(EditUser.this).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		SelectButton.setBounds(219, 205, 97, 25);
		contentPane.add(SelectButton);
		
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				EditUser.this.setVisible(false);
				EditUser.this.dispose();
			}
		});
		btnBack.setBounds(490, 259, 97, 25);
		contentPane.add(btnBack);
	}

}
