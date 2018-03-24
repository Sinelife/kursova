package view_workerselldevice;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.UserDao;
import domain.User;
import view.AuthorisationMenu;
import view.ChangeLoginPassword;

public class WorkerSalesDepartmentMenu extends JFrame 
{

	private JPanel contentPane;


	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public WorkerSalesDepartmentMenu() throws SQLException 
	{
		UserDao ud = new UserDao();
		User u = ud.readUser(AuthorisationMenu.user_id_to_choose);
		String UserSurnameName = u.getSurname() + " " + u.getName();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel UserPIBLabel = new JLabel(UserSurnameName);
		UserPIBLabel.setHorizontalAlignment(SwingConstants.CENTER);
		UserPIBLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		UserPIBLabel.setBounds(0, 64, 545, 38);
		contentPane.add(UserPIBLabel);
		
		JLabel MenuTitleLabel = new JLabel("Член відділу продажу");
		MenuTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MenuTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		MenuTitleLabel.setBounds(0, 13, 545, 38);
		contentPane.add(MenuTitleLabel);
		
		JButton ClientMenuButton = new JButton("кліентами");
		ClientMenuButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				WorkerSalesDepartmentMenu.this.setVisible(false);
				new ClientMenu().setVisible(true); 
			}
		});
		ClientMenuButton.setForeground(Color.BLACK);
		ClientMenuButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		ClientMenuButton.setBounds(171, 177, 200, 43);
		contentPane.add(ClientMenuButton);
		
		JButton OrderMenuButton = new JButton("Змінити логін та пароль");
		OrderMenuButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				WorkerSalesDepartmentMenu.this.setVisible(false);
				try {
					new ChangeLoginPassword(WorkerSalesDepartmentMenu.this).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}
		});
		OrderMenuButton.setForeground(Color.BLACK);
		OrderMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
		OrderMenuButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		OrderMenuButton.setBounds(12, 313, 186, 25);
		contentPane.add(OrderMenuButton);
		
		
		
		JButton LogOutButtton = new JButton("Розлогінитися");
		LogOutButtton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WorkerSalesDepartmentMenu.this.setVisible(false);
				WorkerSalesDepartmentMenu.this.dispose();
				new AuthorisationMenu().setVisible(true);
			}
		});
		LogOutButtton.setBounds(411, 313, 129, 25);
		contentPane.add(LogOutButtton);
		
		JLabel label = new JLabel("\u0420\u043E\u0431\u043E\u0442\u0430 \u0437 ...");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label.setBounds(0, 135, 545, 29);
		contentPane.add(label);
	}

}
