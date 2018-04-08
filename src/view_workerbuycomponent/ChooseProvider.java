package view_workerbuycomponent;

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

import dao.ProviderDao;
import domain.Provider;
import main.MethodsForFrames;
import view.AuthorisationMenu;
import javax.swing.SwingConstants;

public class ChooseProvider extends JFrame {

	private JPanel contentPane;
	
	public static int id_to_choose;
	public static String name_to_choose;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public ChooseProvider() throws SQLException 
	{
		ProviderDao pd = new ProviderDao();
		List<Provider> providers = pd.getAll();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 598, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel lblNewLabel = new JLabel("Вибір постачальника для подальшої роботи з ним");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel.setBounds(0, 13, 580, 44);
		contentPane.add(lblNewLabel);


		JComboBox<String> ProviderComboBox = new JComboBox<String>();
		ProviderComboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		ProviderComboBox.setBounds(74, 93, 427, 34);
		contentPane.add(ProviderComboBox);
		for(Provider provider : providers) 
		{
			ProviderComboBox.addItem(provider.getName());
		}
		
		
		
		
		JButton SelectButton = new JButton("ВИБРАТИ");
		SelectButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				id_to_choose = MethodsForFrames.getProviderIdByProviderName(ProviderComboBox, providers);
				Provider p = new Provider();
				try {
					p = pd.readProvider(ChooseProvider.id_to_choose);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				name_to_choose = p.getName();
				ChooseProvider.this.setVisible(false);
				new DeliveryMenu().setVisible(true);
				
			}
		});
		SelectButton.setBounds(210, 161, 118, 25);
		contentPane.add(SelectButton);
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ChooseProvider.this.setVisible(false);
				ChooseProvider.this.dispose();
				new ProviderMenu().setVisible(true);
			}
		});
		btnBack.setBounds(471, 239, 97, 25);
		contentPane.add(btnBack);
	}

}
