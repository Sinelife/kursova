package view_workerselldevice;

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

import dao.ClientDao;
import domain.Client;
import view.AuthorisationMenu;


public class EditClient extends JFrame 
{

	private JPanel contentPane;
	public static String name_to_edit;
	public static int id_to_edit;
	

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public EditClient(JFrame parent) throws SQLException 
	{
		ClientDao cd = new ClientDao();
		List<Client> clients = cd.getAll();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		comboBox.setBounds(40, 121, 437, 34);
		contentPane.add(comboBox);
		for(Client client : clients) 
		{
			comboBox.addItem(client.getName());
		}
		
		JLabel lblNewLabel = new JLabel("Вибір клієнта для редагування.");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(46, 25, 559, 59);
		contentPane.add(lblNewLabel);
		
		
		JButton SelectButton = new JButton("Вибрати");
		SelectButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				name_to_edit = String.valueOf(comboBox.getSelectedItem());
				for(Client client : clients) 
				{
					id_to_edit = client.getId();
					if(client.getName().equals(name_to_edit))
					{
						break;
					}
				}
				EditClient.this.setVisible(false);
				try {
					new EditClientFrame(EditClient.this).setVisible(true);
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
				EditClient.this.setVisible(false);
				EditClient.this.dispose();
			}
		});
		btnBack.setBounds(489, 427, 97, 25);
		contentPane.add(btnBack);
	}
}
