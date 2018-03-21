package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.UserDao;
import domain.User;
import view_admin.AdminMenu;
import view_constructor.ConstructorMenu;
import view_director.DirectorMenu;
import view_workerbuycomponent.WorkerDeliveryDepartmentMenu;
import view_workerselldevice.WorkerSalesDepartmentMenu;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AuthorisationMenu extends JFrame 
{

	private JPanel contentPane;
	private JTextField LoginField;
	private JTextField PasswordField;
	private JLabel LoginMenuTitle;
	private JButton button;
	public static int user_id_to_choose;
	public static String user_role;

	/**
	 * Create the frame.
	 */
	public AuthorisationMenu() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 387);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		LoginMenuTitle = new JLabel("Меню залогінювання");
		LoginMenuTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
		LoginMenuTitle.setBounds(148, 24, 338, 33);
		contentPane.add(LoginMenuTitle);
		
		JLabel LoginLabel = new JLabel("Логін");
		LoginLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LoginLabel.setBounds(53, 140, 94, 27);
		contentPane.add(LoginLabel);
		
		JLabel PasswordLabel = new JLabel("Пароль");
		PasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		PasswordLabel.setBounds(53, 193, 94, 27);
		contentPane.add(PasswordLabel);
		
		LoginField = new JTextField();
		LoginField.setBounds(191, 140, 275, 27);
		contentPane.add(LoginField);
		LoginField.setColumns(10);
		
		PasswordField = new JTextField();
		PasswordField.setColumns(10);
		PasswordField.setBounds(191, 193, 275, 27);
		contentPane.add(PasswordField);
		
		button = new JButton("Залогінитися");
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				UserDao ud = new UserDao();
				List<User> users = null;
				try {
					users = ud.getAll();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				String login = LoginField.getText();
				String password = PasswordField.getText();
				boolean loginIn = false;
				
				for(User user : users)
				{
					if(user.getLogin().equals(login)&&(user.getPassword().equals(password)))
					{
						user_role = user.getRole();
						if(user.getRole().equals("constructor"))
						{
							loginIn = true;
							user_id_to_choose = user.getId();
							AuthorisationMenu.this.setVisible(false);
							try {
								new ConstructorMenu().setVisible(true);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
						}
						if(user.getRole().equals("worker_of_sales_department"))
						{
							loginIn = true;
							user_id_to_choose = user.getId();
							AuthorisationMenu.this.setVisible(false);
							try {
								new WorkerSalesDepartmentMenu().setVisible(true);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
						}
						if(user.getRole().equals("worker_of_delivery_department"))
						{
							loginIn = true;
							user_id_to_choose = user.getId();
							AuthorisationMenu.this.setVisible(false);
							try {
								new WorkerDeliveryDepartmentMenu().setVisible(true);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
						}
						if(user.getRole().equals("admin"))
						{
							loginIn = true;
							user_id_to_choose = user.getId();
							AuthorisationMenu.this.setVisible(false);
							try {
								new AdminMenu().setVisible(true);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
						}
						if(user.getRole().equals("director"))
						{
							loginIn = true;
							user_id_to_choose = user.getId();
							AuthorisationMenu.this.setVisible(false);
							try {
								new DirectorMenu().setVisible(true);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
						}
					}
				}
				if(loginIn == false)
				{
					JOptionPane.showMessageDialog(null, "Помилковий логін або пароль!" );
				}
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button.setBounds(284, 270, 144, 33);
		contentPane.add(button);
	}
	
	
	
	public static JPanel setColorOfFrame(JPanel frame, String role)
	{
		if(role.equals("admin"))
		{
			frame.setBackground(new Color(230, 243, 255));
		}
		if(role.equals("constructor"))
		{
			frame.setBackground(new Color(255, 255, 204));
		}
		if(role.equals("worker_of_delivery_department"))
		{
			frame.setBackground(new Color(245, 230, 255));
		}
		if(role.equals("worker_of_sales_department"))
		{
			frame.setBackground(new Color(230, 255, 204));
		}
		if(role.equals("director"))
		{
			frame.setBackground(new Color(255, 230, 230));
		}
		return frame;
	}
}
