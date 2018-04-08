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
import main.MethodsForFrames;
import view.AuthorisationMenu;
import javax.swing.SwingConstants;


public class EditClient extends JFrame 
{

	private JPanel contentPane;

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
		setBounds(100, 100, 575, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		JLabel lblNewLabel = new JLabel("Вибір клієнта для редагування");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setBounds(0, 25, 557, 59);
		contentPane.add(lblNewLabel);
		

		JComboBox<String> ClientComboBox = new JComboBox<String>();
		ClientComboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		ClientComboBox.setBounds(57, 121, 437, 34);
		contentPane.add(ClientComboBox);
		for(Client client : clients) 
		{
			ClientComboBox.addItem(client.getName());
		}
		
		
		JButton SelectButton = new JButton("ВИБРАТИ");
		SelectButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				id_to_edit = MethodsForFrames.getClientIdByClientName(ClientComboBox, clients);
				
				EditClient.this.setVisible(false);
				try {
					new EditClientFrame(EditClient.this).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		SelectButton.setBounds(215, 193, 97, 25);
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
		btnBack.setBounds(420, 260, 97, 25);
		contentPane.add(btnBack);
	}
}
