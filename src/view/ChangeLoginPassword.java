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
import javax.swing.SwingConstants;

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
		setBounds(100, 100, 597, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		JLabel lblNewLabel = new JLabel("«Ï≥Ì‡ ÎÓ„≥ÌÛ ≥ Ô‡ÓÎ˛");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(0, 13, 579, 59);
		contentPane.add(lblNewLabel);
		
		
		JLabel LoginLabel = new JLabel("ÀÓ„≥Ì");
		LoginLabel.setBounds(84, 121, 91, 22);
		contentPane.add(LoginLabel);
		
		JLabel PasswordLabel = new JLabel("œ‡ÓÎ¸");
		PasswordLabel.setBounds(84, 172, 91, 22);
		contentPane.add(PasswordLabel);
		
		LoginField = new JTextField();
		LoginField.setBackground(Color.WHITE);
		LoginField.setColumns(10);
		LoginField.setBounds(191, 121, 333, 22);
		contentPane.add(LoginField);
		LoginField.setText(u.getLogin());
		
		PasswordField = new JTextField();
		PasswordField.setBackground(Color.WHITE);
		PasswordField.setColumns(10);
		PasswordField.setBounds(191, 172, 333, 22);
		contentPane.add(PasswordField);
		PasswordField.setText(u.getPassword());
		
		
		
		JButton EditButton = new JButton("–≈ƒ¿√”¬¿“»");
		EditButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		EditButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				u.setLogin(LoginField.getText());
				u.setPassword(PasswordField.getText());
				try {
					ud.updateUser(u);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				ChangeLoginPassword.this.setVisible(false);
				ChangeLoginPassword.this.dispose();
				if(u.getRole().equals("constructor"))
				{
					try {
						new ConstructorMenu().setVisible(true);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				if(u.getRole().equals("worker_of_sales_department"))
				{
					try {
						new WorkerSalesDepartmentMenu().setVisible(true);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				if(u.getRole().equals("worker_of_delivery_department"))
				{
					try {
						new WorkerDeliveryDepartmentMenu().setVisible(true);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		EditButton.setBounds(191, 229, 117, 25);
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
		btnBack.setBounds(427, 289, 97, 25);
		contentPane.add(btnBack);
	}

}
