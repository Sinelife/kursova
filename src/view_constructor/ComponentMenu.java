package view_constructor;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.AuthorisationMenu;
import view_admin.ConstructorAdminMenu;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class ComponentMenu extends JFrame {

	private JPanel contentPane;



	/**
	 * Create the frame.
	 */
	public ComponentMenu()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel MenuTitleLabel = new JLabel("Робота з компонентами");
		MenuTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MenuTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		MenuTitleLabel.setBounds(118, 23, 313, 38);
		contentPane.add(MenuTitleLabel);
		
		JButton InfoButton = new JButton("Переглянути інформацію про наявні компоненти");
		InfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ComponentMenu.this.setVisible(false);
				try {
					new InfoComponent(ComponentMenu.this).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}
		});
		InfoButton.setForeground(Color.BLACK);
		InfoButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		InfoButton.setBounds(42, 111, 453, 43);
		contentPane.add(InfoButton);
		
		JButton AddButton = new JButton("Додати новий компонент");
		AddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ComponentMenu.this.setVisible(false);
				try {
					new AddComponent(ComponentMenu.this).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}
		});
		AddButton.setForeground(SystemColor.desktop);
		AddButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		AddButton.setBounds(42, 191, 453, 43);
		contentPane.add(AddButton);
		
		JButton EditButton = new JButton("Редагувати інформацію про компонент");
		EditButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ComponentMenu.this.setVisible(false);
				try {
					new EditComponent(ComponentMenu.this).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}
		});
		EditButton.setForeground(SystemColor.desktop);
		EditButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		EditButton.setBounds(42, 271, 453, 43);
		contentPane.add(EditButton);
		
		JButton InDeviceButton = new JButton("Список приладів,які містять компонент");
		InDeviceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ComponentMenu.this.setVisible(false);
				try {
					new DeviceWhichHasComponent(ComponentMenu.this).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}
		});
		InDeviceButton.setForeground(Color.BLACK);
		InDeviceButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		InDeviceButton.setBounds(42, 350, 453, 43);
		contentPane.add(InDeviceButton);
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ComponentMenu.this.setVisible(false);
				ComponentMenu.this.dispose();
				if(AuthorisationMenu.user_role.equals("constructor"))
				{
					try {
						new ConstructorMenu().setVisible(true);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				if(AuthorisationMenu.user_role.equals("admin"))
				{
					try {
						new ConstructorAdminMenu().setVisible(true);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnBack.setBounds(398, 448, 97, 25);
		contentPane.add(btnBack);
	}

}
