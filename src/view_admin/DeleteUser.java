package view_admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.UserDao;
import domain.Component;
import domain.User;
import view_Constructor.ComponentInformation;
import view_Constructor.InfoComponent;

public class DeleteUser extends JFrame {

	private JPanel contentPane;
	public static String surname_name_to_delete;
	public static int id_to_delete;
	

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public DeleteUser(JFrame parent) throws SQLException
	{
		UserDao ud = new UserDao();
		List<User> users = ud.getAll();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		comboBox.setBounds(40, 121, 427, 34);
		contentPane.add(comboBox);
		for(User user : users) 
		{
			comboBox.addItem(user.getSurname() + " " + user.getName());
		}
		
		JLabel lblNewLabel = new JLabel("Вибір акаунту працівника для видалення.");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(46, 25, 559, 59);
		contentPane.add(lblNewLabel);
		
		
		JButton DeleteButton = new JButton("Видалити");
		DeleteButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				surname_name_to_delete = String.valueOf(comboBox.getSelectedItem());
				String admin_role = "";
				for(User user : users) 
				{
					id_to_delete = user.getId();
					if(user.getRole().equals("admin"))
					{
						admin_role = user.getRole();
						break;
					}
					String surname_name = user.getSurname() + " " + user.getName();
					if(surname_name.equals(surname_name_to_delete))
					{
						break;
					}
				}
				User u = null;
				try {
					u = ud.readUser(id_to_delete);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(admin_role.equals("admin"))
				{
					JOptionPane.showMessageDialog (null, "Адміна видалити не можна!!!" );
				}
				else
				{
					try {
						ud.delete(u);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (parent != null)
					parent.setVisible(true);
				DeleteUser.this.setVisible(false);
				DeleteUser.this.dispose();
			}
		});
		DeleteButton.setBounds(46, 427, 97, 25);
		contentPane.add(DeleteButton);
		
		
		
		
		JButton button = new JButton("Інформація");
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				surname_name_to_delete = String.valueOf(comboBox.getSelectedItem());
				for(User user : users) 
				{
					id_to_delete = user.getId();
					String surname_name = user.getSurname() + " " + user.getName();
					if(surname_name.equals(surname_name_to_delete))
					{
						break;
					}
				}
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
		btnBack.setBounds(489, 427, 97, 25);
		contentPane.add(btnBack);
	}

}
