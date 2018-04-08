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
import domain.Delivery;
import domain.DeliveryComponent;
import javax.swing.SwingConstants;

public class InfoProvider extends JFrame {

	private JPanel contentPane;
	public static int provider_id_to_look;
	public static int delivery_id_to_look;

	public List<Delivery> DeliveriesInProvider;
	public List<DeliveryComponent> ComponentsInfoInDelivery;
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public InfoProvider(JFrame parent) throws SQLException 
	{
		ProviderDao pd = new ProviderDao();
		List<Provider> providers = pd.getAll();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 724, 651);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		
		JLabel lblNewLabel = new JLabel("Перегляд інформації про постачальника");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(0, 11, 706, 59);
		contentPane.add(lblNewLabel);
		
		JLabel ProviderLabel = new JLabel("Список постачальників");
		ProviderLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ProviderLabel.setBounds(71, 83, 198, 25);
		contentPane.add(ProviderLabel);
		
		
		JComboBox<String> ProviderComboBox = new JComboBox<String>();
		ProviderComboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		ProviderComboBox.setBounds(71, 121, 430, 34);
		contentPane.add(ProviderComboBox);
		for(Provider provider : providers) 
		{
			ProviderComboBox.addItem(provider.getName());
		}
		
		JLabel DeliveryLabel = new JLabel("Список замовлень на постачання обраного постачальника");
		DeliveryLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		DeliveryLabel.setBounds(71, 259, 447, 25);
		contentPane.add(DeliveryLabel);

		
		JComboBox<String> DeliveryInProviderComboBox = new JComboBox<String>();
		DeliveryInProviderComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DeliveryInProviderComboBox.setBounds(71, 293, 430, 34);
		contentPane.add(DeliveryInProviderComboBox);
		
		JLabel ComponentInfoLabel = new JLabel("Інформація про компоненти в замовленні постачання");
		ComponentInfoLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ComponentInfoLabel.setBounds(71, 448, 447, 25);
		contentPane.add(ComponentInfoLabel);
		
		
		JComboBox<String> ComponentInDeliveryComboBox = new JComboBox<String>();
		ComponentInDeliveryComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ComponentInDeliveryComboBox.setBounds(71, 480, 430, 34);
		contentPane.add(ComponentInDeliveryComboBox);
		
		
		
		JButton ProviderInfoButton = new JButton("Інформація");
		ProviderInfoButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				provider_id_to_look = MethodsForFrames.getProviderIdByProviderName(ProviderComboBox, providers);
				InfoProvider.this.setVisible(false);
				try {
					new ProviderInformation(InfoProvider.this).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		ProviderInfoButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ProviderInfoButton.setBounds(514, 121, 122, 34);
		contentPane.add(ProviderInfoButton);
		
		
		
		
		JButton SelectProviderButton = new JButton("Вибрати");
		SelectProviderButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				provider_id_to_look = MethodsForFrames.getProviderIdByProviderName(ProviderComboBox, providers);
				DeliveriesInProvider = MethodsForFrames.getAllDeliveriesInProvider(provider_id_to_look, DeliveriesInProvider, DeliveryInProviderComboBox);

			}
		});
		SelectProviderButton.setBounds(71, 179, 97, 25);
		contentPane.add(SelectProviderButton);
		
		
		
		
		JButton SelectDeliveryButton = new JButton("Вибрати");
		SelectDeliveryButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				delivery_id_to_look = MethodsForFrames.getDeliveryIdByDeliveryName(DeliveryInProviderComboBox, DeliveriesInProvider);
				MethodsForFrames.getComponentInfoFromDelivery(delivery_id_to_look, ComponentsInfoInDelivery, ComponentInDeliveryComboBox);
			}
		});
		SelectDeliveryButton.setBounds(71, 340, 97, 25);
		contentPane.add(SelectDeliveryButton);

		
		
		JButton DeliveryInfoButton = new JButton("Інформація");
		DeliveryInfoButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				delivery_id_to_look = MethodsForFrames.getDeliveryIdByDeliveryName(DeliveryInProviderComboBox, DeliveriesInProvider);
				ProviderMenu.delivery_information_check = 1;
				InfoProvider.this.setVisible(false);
				try {
					new DeliveryInformation(InfoProvider.this).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		DeliveryInfoButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DeliveryInfoButton.setBounds(513, 293, 122, 34);
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
		btnBack.setBounds(579, 555, 97, 25);
		contentPane.add(btnBack);
		
	}
}
