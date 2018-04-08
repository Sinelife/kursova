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
		setBounds(100, 100, 563, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel lblNewLabel = new JLabel("Вибір клієнта для подальшої роботи з ним");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(0, 29, 545, 59);
		contentPane.add(lblNewLabel);


		JComboBox<String> ClientComboBox = new JComboBox<String>();
		ClientComboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ClientComboBox.setBounds(40, 101, 458, 34);
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
				id_to_choose = MethodsForFrames.getClientIdByClientName(ClientComboBox, clients);
				Client c = new Client();
				try {
					c = cd.readClient(id_to_choose);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				name_to_choose = c.getName();
				ChooseClient.this.setVisible(false);
				new OrderMenu().setVisible(true);
			}
		});
		SelectButton.setBounds(189, 166, 118, 25);
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
		btnBack.setBounds(401, 261, 97, 25);
		contentPane.add(btnBack);
	}

}
