package view;

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
import view_constructor.ConstructorMenu;
import view_workerbuycomponent.WorkerDeliveryDepartmentMenu;
import view_workerselldevice.WorkerSalesDepartmentMenu;

import java.awt.Color;

public class ChangeLoginPassword extends JFrame {

	private JPanel contentPane;
	private JTextField LoginField;
	private JTextField PasswordField;



	/**
	 * Create the frame.
	 * @throws SQLException
	 */
	public ChangeLoginPassword(JFrame parent) throws SQLException 
	{
		UserDao ud = new UserDao();
		User u = ud.readUser(AuthorisationMenu.user_id_to_choose);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 627, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		JLabel lblNewLabel = new JLabel("«м≥на лог≥ну ≥ паролю");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(103, 13, 421, 59);
		contentPane.add(lblNewLabel);
		
		
		JLabel LoginLabel = new JLabel("Ћог≥н");
		LoginLabel.setBounds(36, 144, 91, 22);
		contentPane.add(LoginLabel);
		
		JLabel PasswordLabel = new JLabel("ѕароль");
		PasswordLabel.setBounds(36, 195, 91, 22);
		contentPane.add(PasswordLabel);
		
		LoginField = new JTextField();
		LoginField.setBackground(Color.WHITE);
		LoginField.setColumns(10);
		LoginField.setBounds(143, 144, 333, 22);
		contentPane.add(LoginField);
		LoginField.setText(u.getLogin());
		
		PasswordField = new JTextField();
		PasswordField.setBackground(Color.WHITE);
		PasswordField.setColumns(10);
		PasswordField.setBounds(143, 195, 333, 22);
		contentPane.add(PasswordField);
		PasswordField.setText(u.getPassword());
		
		
		
		JButton EditButton = new JButton("–едагувати");
		EditButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		EditButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				u.setLogin(LoginField.getText());
				u.setPassword(PasswordField.getText());
				try {
					ud.updateUser(u);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ChangeLoginPassword.this.setVisible(false);
				ChangeLoginPassword.this.dispose();
				if(u.getRole().equals("constructor"))
				{
					try {
						new ConstructorMenu().setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(u.getRole().equals("worker_of_sales_department"))
				{
					try {
						new WorkerSalesDepartmentMenu().setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(u.getRole().equals("worker_of_delivery_department"))
				{
					try {
						new WorkerDeliveryDepartmentMenu().setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		EditButton.setBounds(27, 275, 125, 25);
		contentPane.add(EditButton);
		
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				ChangeLoginPassword.this.setVisible(false);
				ChangeLoginPassword.this.dispose();
			}
		});
		btnBack.setBounds(477, 276, 97, 25);
		contentPane.add(btnBack);
	}

}
