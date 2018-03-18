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

public class EditUser extends JFrame {


	private JPanel contentPane;
	public static String surname_name_to_edit;
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
		setBounds(100, 100, 646, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		

		JComboBox UserComboBox = new JComboBox();
		UserComboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		UserComboBox.setBounds(40, 121, 504, 34);
		contentPane.add(UserComboBox);
		for(User user : users) 
		{
			UserComboBox.addItem(user.getSurname() + " " + user.getName());
		}
		
		JLabel lblNewLabel = new JLabel("Вибір акаунту працівника для редагування.");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(46, 25, 559, 59);
		contentPane.add(lblNewLabel);
		
		
		
		JButton SelectButton = new JButton("Вибрати");
		SelectButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				id_to_edit = MethodsForFrames.getUsertIdByUserSurnameAndName(surname_name_to_edit, id_to_edit, UserComboBox, users);
				
				EditUser.this.setVisible(false);
				try {
					new EditUserFrame(EditUser.this).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		SelectButton.setBounds(46, 427, 97, 25);
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
		btnBack.setBounds(489, 427, 97, 25);
		contentPane.add(btnBack);
	}

}
