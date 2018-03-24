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

public class DeleteUser extends JFrame {

	private JPanel contentPane;
	public static String surname_name_to_delete;
	public static int id_to_delete;
	
	UserDao ud;
	List<User> users;
	User u;
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public DeleteUser(JFrame parent) throws SQLException
	{
		ud = new UserDao();
		users = ud.getAll();
		u = new User();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		

		JLabel lblNewLabel = new JLabel("Вибір працівника для видалення");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(0, 25, 628, 59);
		contentPane.add(lblNewLabel);
		
		JComboBox UserComboBox = new JComboBox();
		UserComboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		UserComboBox.setBounds(40, 121, 427, 34);
		contentPane.add(UserComboBox);
		for(User user : users) 
		{
			if(user.getRole().equals("admin"))
			{
				
			}
			else
			{
				UserComboBox.addItem(user.getSurname() + " " + user.getName());
			}
		}
		

		
		
		JButton DeleteButton = new JButton("ВИДАЛИТИ");
		DeleteButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				id_to_delete = MethodsForFrames.getUsertIdByUserSurnameAndName(surname_name_to_delete, id_to_delete, UserComboBox, users);

				try {
					u = ud.readUser(id_to_delete);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					ud.delete(u);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (parent != null)
					parent.setVisible(true);
				DeleteUser.this.setVisible(false);
				DeleteUser.this.dispose();
			}
		});
		DeleteButton.setBounds(202, 201, 97, 25);
		contentPane.add(DeleteButton);
		
		
		
		
		JButton button = new JButton("Інформація");
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				id_to_delete = MethodsForFrames.getUsertIdByUserSurnameAndName(surname_name_to_delete, id_to_delete, UserComboBox, users);

				DeleteUser.this.setVisible(false);
				try {
					new UserInformation(DeleteUser.this).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button.setBounds(479, 121, 122, 34);
		contentPane.add(button);
		
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				DeleteUser.this.setVisible(false);
				DeleteUser.this.dispose();
			}
		});
		btnBack.setBounds(504, 276, 97, 25);
		contentPane.add(btnBack);
	}

}
