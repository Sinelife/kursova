package view_constructor;

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

import dao.ComponentDao;
import domain.Component;
import main.MethodsForFrames;
import view.AuthorisationMenu;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class InfoComponent extends JFrame {

	private JPanel contentPane;
	public static int id_to_look;
	public static String name_to_look;
	public static int id_to_find;
	public static String type_to_find;
	public static String name_to_find;
	private JTextField FindField;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public InfoComponent(JFrame parent) throws SQLException
	{
		ComponentDao cd = new ComponentDao();
		List<Component> components = cd.getAll();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 652, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		

		JComboBox<String> ComponentComboBox = new JComboBox<String>();
		ComponentComboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		ComponentComboBox.setBounds(37, 107, 438, 34);
		contentPane.add(ComponentComboBox);
		for(Component component : components) 
		{
			ComponentComboBox.addItem(component.getName());
		}
		
		
		JLabel lblNewLabel = new JLabel("Пошук та перегляд інформації про компоненти");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setBounds(27, 25, 559, 59);
		contentPane.add(lblNewLabel);
		
		
		JButton InfoButton = new JButton("Інформація");
		InfoButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				id_to_look = MethodsForFrames.getComponentIdByComponentName(ComponentComboBox, components);
				
				InfoComponent.this.setVisible(false);
				try {
					new ComponentInformation(InfoComponent.this).setVisible(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		InfoButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		InfoButton.setBounds(489, 110, 122, 34);
		contentPane.add(InfoButton);
		
		FindField = new JTextField();
		FindField.setBounds(200, 217, 152, 22);
		contentPane.add(FindField);
		FindField.setColumns(10);
		
		
		JButton FindNameButton = new JButton("Пошук за назвою");
		FindNameButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ComponentComboBox.removeAllItems();
				name_to_find = FindField.getText();
				System.out.println(name_to_find);
				for(Component component : components) 
				{
					if(component.getName().equals(name_to_find))
					{
						ComponentComboBox.addItem(component.getName());
					}
				}
			}
		});
		FindNameButton.setBounds(200, 287, 151, 25);
		contentPane.add(FindNameButton);
		
		JButton FindTypeButton = new JButton("Пошук за типом");
		FindTypeButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ComponentComboBox.removeAllItems();
				type_to_find = FindField.getText();
				System.out.println(type_to_find);
				for(Component component : components) 
				{
					if(component.getType().equals(type_to_find))
					{
						ComponentComboBox.addItem(component.getName());
					}
				}
			}
		});
		FindTypeButton.setBounds(200, 264, 151, 25);
		contentPane.add(FindTypeButton);
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				InfoComponent.this.setVisible(false);
				InfoComponent.this.dispose();
			}
		});
		btnBack.setBounds(514, 390, 97, 25);
		contentPane.add(btnBack);

	}

}
