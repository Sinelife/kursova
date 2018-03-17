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

import dao.DeliveryComponentDao;
import dao.ProviderDao;
import domain.Provider;
import main.MethodsForFrames;
import view.AuthorisationMenu;
import domain.Delivery;
import domain.DeliveryComponent;

public class InfoProvider extends JFrame {

	private JPanel contentPane;
	public static int provider_id_to_look;
	public static String provider_name_to_look;
	public static int delivery_id_to_look;
	public static String delivery_name_to_look;

	public List<Delivery> DeliveriesInProvider;
	public List<DeliveryComponent> ComponentsInfoInDelivery;
	
	public static int information_check;
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public InfoProvider(JFrame parent) throws SQLException 
	{
		ProviderDao pd = new ProviderDao();
		List<Provider> providers = pd.getAll();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 724, 612);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		information_check = 1;
		
		
		JLabel lblNewLabel = new JLabel("Перегляд інформації про постачальника");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(46, 25, 559, 59);
		contentPane.add(lblNewLabel);
		
		JLabel ProviderLabel = new JLabel("Список постачальників");
		ProviderLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ProviderLabel.setBounds(40, 83, 198, 25);
		contentPane.add(ProviderLabel);
		
		
		JComboBox<String> ProviderComboBox = new JComboBox<String>();
		ProviderComboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		ProviderComboBox.setBounds(40, 121, 430, 34);
		contentPane.add(ProviderComboBox);
		for(Provider provider : providers) 
		{
			ProviderComboBox.addItem(provider.getName());
		}
		
		JLabel DeliveryLabel = new JLabel("Список замовлень на постачання обраного постачальника");
		DeliveryLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		DeliveryLabel.setBounds(40, 259, 447, 25);
		contentPane.add(DeliveryLabel);

		
		JComboBox<String> DeliveryInProviderComboBox = new JComboBox<String>();
		DeliveryInProviderComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DeliveryInProviderComboBox.setBounds(40, 293, 430, 34);
		contentPane.add(DeliveryInProviderComboBox);
		
		JLabel ComponentInfoLabel = new JLabel("Інформація про компоненти в замовленні постачання");
		ComponentInfoLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ComponentInfoLabel.setBounds(40, 448, 447, 25);
		contentPane.add(ComponentInfoLabel);
		
		
		JComboBox<String> ComponentInDeliveryComboBox = new JComboBox<String>();
		ComponentInDeliveryComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ComponentInDeliveryComboBox.setBounds(40, 480, 430, 34);
		contentPane.add(ComponentInDeliveryComboBox);
		
		
		
		JButton ProviderInfoButton = new JButton("Інформація");
		ProviderInfoButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				provider_name_to_look = String.valueOf(ProviderComboBox.getSelectedItem());
				for(Provider provider : providers) 
				{
					provider_id_to_look = provider.getId();
					if(provider.getName().equals(provider_name_to_look))
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
		ProviderInfoButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ProviderInfoButton.setBounds(483, 121, 122, 34);
		contentPane.add(ProviderInfoButton);
		
		
		
		
		JButton SelectProviderButton = new JButton("Вибрати");
		SelectProviderButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				DeliveryInProviderComboBox.removeAllItems();
				
				provider_id_to_look = MethodsForFrames.getProviderIdByProviderName(provider_name_to_look, provider_id_to_look, ProviderComboBox, providers);
				
				try {
					DeliveriesInProvider = pd.getAllDeliveriesInProvider(provider_id_to_look);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				for(Delivery delivery : DeliveriesInProvider)
				{
					DeliveryInProviderComboBox.addItem(delivery.getDeliveryName());
				}

			}
		});
		SelectProviderButton.setBounds(40, 179, 97, 25);
		contentPane.add(SelectProviderButton);
		
		
		
		
		JButton SelectDeliveryButton = new JButton("Вибрати");
		SelectDeliveryButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ComponentInDeliveryComboBox.removeAllItems();
				
				
				delivery_name_to_look = String.valueOf(DeliveryInProviderComboBox.getSelectedItem());
				for(Delivery delivery : DeliveriesInProvider) 
				{
					delivery_id_to_look = delivery.getId();
					if(delivery.getDeliveryName().equals(delivery_name_to_look))
					{
						break;
					}
				}
				DeliveryComponentDao dcd = new DeliveryComponentDao();
				
				try {
					ComponentsInfoInDelivery = dcd.getAllFromDelivery(delivery_id_to_look);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for(DeliveryComponent delivery_component : ComponentsInfoInDelivery)
				{
					String component_name = null;
					try {
						component_name = dcd.getComponentNameById(delivery_id_to_look, delivery_component.getComponentId());
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
					String component_type = null;
					try {
						component_type = dcd.getComponentTypeById(delivery_id_to_look, delivery_component.getComponentId());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					String info = "Назва: " + component_name + 
							"  Тип: " + component_type +
							"  Кількість: " + delivery_component.getNumber();
					ComponentInDeliveryComboBox.addItem(info);
				}
			}
		});
		SelectDeliveryButton.setBounds(40, 340, 97, 25);
		contentPane.add(SelectDeliveryButton);

		
		
		JButton DeliveryInfoButton = new JButton("Інформація");
		DeliveryInfoButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				delivery_name_to_look = String.valueOf(DeliveryInProviderComboBox.getSelectedItem());
				for(Delivery delivery : DeliveriesInProvider) 
				{
					delivery_id_to_look = delivery.getId();
					if(delivery.getDeliveryName().equals(delivery_name_to_look))
					{
						break;
					}
				}
				InfoProvider.this.setVisible(false);
				try {
					new DeliveryInformation(InfoProvider.this).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		DeliveryInfoButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DeliveryInfoButton.setBounds(482, 293, 122, 34);
		contentPane.add(DeliveryInfoButton);
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				InfoProvider.this.setVisible(false);
				InfoProvider.this.dispose();
			}
		});
		btnBack.setBounds(597, 527, 97, 25);
		contentPane.add(btnBack);
		
	}
}
