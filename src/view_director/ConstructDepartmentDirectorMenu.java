package view_director;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.AuthorisationMenu;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class ConstructDepartmentDirectorMenu extends JFrame {

	private JPanel contentPane;



	/**
	 * Create the frame.
	 */
	public ConstructDepartmentDirectorMenu()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 614, 527);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel MenuTitleLabel = new JLabel("���� ������ � ���������������� ����");
		MenuTitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		MenuTitleLabel.setBounds(67, 27, 417, 38);
		contentPane.add(MenuTitleLabel);
		
		JButton InfoButton = new JButton("1)����������� ���� ��� ����������.");
		InfoButton.setHorizontalAlignment(SwingConstants.LEFT);
		InfoButton.setForeground(Color.BLACK);
		InfoButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		InfoButton.setBounds(42, 145, 496, 43);
		contentPane.add(InfoButton);
		
		JButton AddButton = new JButton("2)����������� ���� ��� ������� � ������������.");
		AddButton.setHorizontalAlignment(SwingConstants.LEFT);
		AddButton.setForeground(SystemColor.desktop);
		AddButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		AddButton.setBounds(42, 243, 496, 43);
		contentPane.add(AddButton);
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConstructDepartmentDirectorMenu.this.setVisible(false);
				ConstructDepartmentDirectorMenu.this.dispose();
				try {
					new DirectorMenu().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBack.setBounds(489, 427, 97, 25);
		contentPane.add(btnBack);
	}

}
