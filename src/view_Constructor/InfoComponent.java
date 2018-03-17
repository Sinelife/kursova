package view_Constructor;

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
		setBounds(100, 100, 646, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		

		JComboBox<String> ComponentComboBox = new JComboBox<String>();
		ComponentComboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		ComponentComboBox.setBounds(46, 244, 438, 34);
		contentPane.add(ComponentComboBox);
		for(Component component : components) 
		{
			ComponentComboBox.addItem(component.getName());
		}
		
		
		JLabel lblNewLabel = new JLabel("����� �� �������� ���������� ��� ����������");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setBounds(46, 25, 559, 59);
		contentPane.add(lblNewLabel);
		
		
		JButton InfoButton = new JButton("����������");
		InfoButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				id_to_look = MethodsForFrames.getComponentIdByComponentName(name_to_look, id_to_look, ComponentComboBox, components);
				
				InfoComponent.this.setVisible(false);
				try {
					new ComponentInformation(InfoComponent.this).setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		InfoButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		InfoButton.setBounds(496, 244, 122, 34);
		contentPane.add(InfoButton);
		
		FindField = new JTextField();
		FindField.setBounds(92, 123, 152, 22);
		contentPane.add(FindField);
		FindField.setColumns(10);
		
		
		JButton FindNameButton = new JButton("����� �� ������");
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
		FindNameButton.setBounds(301, 122, 151, 25);
		contentPane.add(FindNameButton);
		
		JButton FindTypeButton = new JButton("����� �� �����");
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
		FindTypeButton.setBounds(301, 97, 151, 25);
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
		btnBack.setBounds(489, 427, 97, 25);
		contentPane.add(btnBack);

	}

}
