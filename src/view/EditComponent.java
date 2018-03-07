package view;

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



public class EditComponent extends JFrame {

	private JPanel contentPane;
	public static String name_to_edit;
	public static int id_to_edit;


	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public EditComponent(JFrame parent) throws SQLException 
	{
		ComponentDao cd = new ComponentDao();
		List<Component> components = cd.getAll();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		comboBox.setBounds(40, 121, 504, 34);
		contentPane.add(comboBox);
		for(Component component : components) 
		{
			comboBox.addItem(component.getName());
		}
		
		JLabel lblNewLabel = new JLabel("���� ������ ���������� ��� �����������.");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(46, 25, 559, 59);
		contentPane.add(lblNewLabel);
		
		
		JButton SelectButton = new JButton("�������");
		SelectButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				name_to_edit = String.valueOf(comboBox.getSelectedItem());
				for(Component component : components) 
				{
					id_to_edit = component.getId();
					if(component.getName().equals(name_to_edit))
					{
						break;
					}
				}
				EditComponent.this.setVisible(false);
				try {
					new EditComponentFrame(EditComponent.this).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		SelectButton.setBounds(46, 427, 97, 25);
		contentPane.add(SelectButton);
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				EditComponent.this.setVisible(false);
				EditComponent.this.dispose();
			}
		});
		btnBack.setBounds(489, 427, 97, 25);
		contentPane.add(btnBack);
	}

}
