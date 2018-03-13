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
import domain.Component;

public class InfoProvider extends JFrame {

	private JPanel contentPane;
	public static int id_to_look;
	public static String name_to_look;


	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public InfoProvider(JFrame parent) throws SQLException 
	{
		ProviderDao pd = new ProviderDao();
		List<Provider> providers = pd.getAll();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox<String> ProviderComboBox = new JComboBox<String>();
		ProviderComboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		ProviderComboBox.setBounds(40, 121, 430, 34);
		contentPane.add(ProviderComboBox);
		for(Provider provider : providers) 
		{
			ProviderComboBox.addItem(provider.getName());
		}
		
		JLabel lblNewLabel = new JLabel("Перегляд інформації про постачальника");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(46, 25, 559, 59);
		contentPane.add(lblNewLabel);
		
		
		JComboBox<String> ComponentInProviderComboBox = new JComboBox<String>();
		ComponentInProviderComboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		ComponentInProviderComboBox.setBounds(40, 258, 430, 34);
		contentPane.add(ComponentInProviderComboBox);
		
		
		
		JButton InfoButton = new JButton("Інформація");
		InfoButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				name_to_look = String.valueOf(ProviderComboBox.getSelectedItem());
				for(Provider provider : providers) 
				{
					id_to_look = provider.getId();
					if(provider.getName().equals(name_to_look))
					{
						break;
					}
				}
				InfoProvider.this.setVisible(false);
				try {
					new ProviderInformation(InfoProvider.this).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		InfoButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		InfoButton.setBounds(483, 121, 122, 34);
		contentPane.add(InfoButton);
		
		
		JButton btnNewButton = new JButton("Вибрати");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ComponentInProviderComboBox.removeAllItems();
				List<Component> ComponentsInProvider = null;
				name_to_look = String.valueOf(ProviderComboBox.getSelectedItem());
				for(Provider provider : providers) 
				{
					id_to_look = provider.getId();
					if(provider.getName().equals(name_to_look))
					{
						break;
					}
				}
				
				try {
					ComponentsInProvider = pd.getAllComponentsInProvider(id_to_look);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				for(Component component : ComponentsInProvider)
				{
					ComponentInProviderComboBox.addItem(component.getName());
				}
			}
		});
		btnNewButton.setBounds(188, 177, 97, 25);
		contentPane.add(btnNewButton);
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				InfoProvider.this.setVisible(false);
				InfoProvider.this.dispose();
			}
		});
		btnBack.setBounds(489, 427, 97, 25);
		contentPane.add(btnBack);
		
	}
}
