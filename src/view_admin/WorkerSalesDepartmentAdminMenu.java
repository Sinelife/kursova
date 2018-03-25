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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.UserDao;
import domain.User;
import view.AuthorisationMenu;
import view_workerselldevice.ClientMenu;

public class WorkerSalesDepartmentAdminMenu extends JFrame 
{

	private JPanel contentPane;


	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public WorkerSalesDepartmentAdminMenu() throws SQLException 
	{
		UserDao ud = new UserDao();
		User u = ud.readUser(AuthorisationMenu.user_id_to_choose);
		String UserSurnameName = u.getSurname() + " " + u.getName();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		
		JLabel UserPIBLabel = new JLabel(UserSurnameName);
		UserPIBLabel.setHorizontalAlignment(SwingConstants.CENTER);
		UserPIBLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		UserPIBLabel.setBounds(0, 64, 492, 38);
		contentPane.add(UserPIBLabel);
		
		JLabel MenuTitleLabel = new JLabel("Меню адміна(як члена відділу продажу)");
		MenuTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MenuTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		MenuTitleLabel.setBounds(0, 13, 492, 38);
		contentPane.add(MenuTitleLabel);
		
		JButton ClientMenuButton = new JButton("кліентами");
		ClientMenuButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				WorkerSalesDepartmentAdminMenu.this.setVisible(false);
				new ClientMenu().setVisible(true); 
			}
		});
		ClientMenuButton.setForeground(Color.BLACK);
		ClientMenuButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		ClientMenuButton.setBounds(139, 162, 188, 43);
		contentPane.add(ClientMenuButton);
		
		
		
		JButton LogOutButtton = new JButton("BACK");
		LogOutButtton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WorkerSalesDepartmentAdminMenu.this.setVisible(false);
				WorkerSalesDepartmentAdminMenu.this.dispose();
				try {
					new AdminMenu().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		LogOutButtton.setBounds(394, 238, 86, 25);
		contentPane.add(LogOutButtton);
		
		JLabel label = new JLabel("\u0420\u043E\u0431\u043E\u0442\u0430 \u0437 ...");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label.setBounds(0, 115, 492, 25);
		contentPane.add(label);
	}

}
