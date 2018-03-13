package view_Constructor;

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
		setBounds(100, 100, 740, 527);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel MenuTitleLabel = new JLabel("Меню роботи з компонентами");
		MenuTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		MenuTitleLabel.setBounds(157, 13, 417, 38);
		contentPane.add(MenuTitleLabel);
		
		JButton InfoButton = new JButton("1)Переглянути інформацію про наявні компоненти.");
		InfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ComponentMenu.this.setVisible(false);
				try {
					new InfoComponent(ComponentMenu.this).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		InfoButton.setHorizontalAlignment(SwingConstants.LEFT);
		InfoButton.setForeground(Color.GRAY);
		InfoButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		InfoButton.setBounds(42, 111, 496, 43);
		contentPane.add(InfoButton);
		
		JButton AddButton = new JButton("2)Додати інфориацію про новий компонент.");
		AddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ComponentMenu.this.setVisible(false);
				new AddComponent(ComponentMenu.this).setVisible(true); 
			}
		});
		AddButton.setHorizontalAlignment(SwingConstants.LEFT);
		AddButton.setForeground(SystemColor.desktop);
		AddButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		AddButton.setBounds(42, 191, 496, 43);
		contentPane.add(AddButton);
		
		JButton EditButton = new JButton("3)Редагувати інформацію про компонент.");
		EditButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ComponentMenu.this.setVisible(false);
				try {
					new EditComponent(ComponentMenu.this).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		EditButton.setHorizontalAlignment(SwingConstants.LEFT);
		EditButton.setForeground(SystemColor.desktop);
		EditButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		EditButton.setBounds(42, 271, 496, 43);
		contentPane.add(EditButton);
		
		JButton InDeviceButton = new JButton("4)Переглянути список приладів,які містять компонент");
		InDeviceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ComponentMenu.this.setVisible(false);
				try {
					new DeviceWhichHasComponent(ComponentMenu.this).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		InDeviceButton.setHorizontalAlignment(SwingConstants.LEFT);
		InDeviceButton.setForeground(Color.GRAY);
		InDeviceButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		InDeviceButton.setBounds(42, 350, 496, 43);
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
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(AuthorisationMenu.user_role.equals("admin"))
				{
					try {
						new ConstructorAdminMenu().setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnBack.setBounds(596, 427, 97, 25);
		contentPane.add(btnBack);
	}

}
