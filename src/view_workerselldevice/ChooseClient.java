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

public class ChooseClient extends JFrame {

	private JPanel contentPane;
	public static String name_to_choose;
	public static int id_to_choose;


	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public ChooseClient() throws SQLException 
	{
		ClientDao cd = new ClientDao();
		List<Client> clients = cd.getAll();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 563, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel lblNewLabel = new JLabel("Вибір клієнта для подальшої роботи з ним");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setBounds(40, 29, 470, 59);
		contentPane.add(lblNewLabel);


		JComboBox<String> ClientComboBox = new JComboBox<String>();
		ClientComboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		ClientComboBox.setBounds(40, 121, 427, 34);
		contentPane.add(ClientComboBox);
		for(Client client : clients) 
		{
			ClientComboBox.addItem(client.getName());
		}
		
		
		
		
		JButton SelectButton = new JButton("Вибрати");
		SelectButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				id_to_choose = MethodsForFrames.getClientIdByClientName(name_to_choose, id_to_choose, ClientComboBox, clients);
				name_to_choose = ClientComboBox.getSelectedItem().toString();
				
				ChooseClient.this.setVisible(false);
				new OrderMenu().setVisible(true);
			}
		});
		SelectButton.setBounds(40, 311, 118, 25);
		contentPane.add(SelectButton);
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ChooseClient.this.setVisible(false);
				ChooseClient.this.dispose();
				new ClientMenu().setVisible(true);
			}
		});
		btnBack.setBounds(436, 311, 97, 25);
		contentPane.add(btnBack);
	}

}
