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

import dao.DeliveryDao;
import domain.Delivery;
import domain.DeliveryComponent;
import main.MethodsForFrames;
import view.AuthorisationMenu;
import javax.swing.SwingConstants;

public class InfoDelivery extends JFrame 
{

	private JPanel contentPane;
	public static int delivery_id_to_look;

	public List<DeliveryComponent> ComponentsInfoInDelivery;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public InfoDelivery(JFrame parent) throws SQLException 
	{
		DeliveryDao od = new DeliveryDao();
		List<Delivery> deliveries = od.getAllFromProvider(ChooseProvider.id_to_choose);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 899, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel lblNewLabel = new JLabel("Перегляд інформації про замовлення постачання");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setBounds(0, 25, 766, 59);
		contentPane.add(lblNewLabel);
		
		JLabel DeliveryLabel = new JLabel("Список замовлень постачання");
		DeliveryLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		DeliveryLabel.setBounds(12, 113, 724, 25);
		contentPane.add(DeliveryLabel);

		JComboBox<String> DeliveryComboBox = new JComboBox<String>();
		DeliveryComboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		DeliveryComboBox.setBounds(12, 149, 724, 34);
		contentPane.add(DeliveryComboBox);
		for(Delivery delivery : deliveries) 
		{
			DeliveryComboBox.addItem(delivery.getDeliveryName());
		}
		
		
		JComboBox<String> ComponentInDeliveryComboBox = new JComboBox<String>();
		ComponentInDeliveryComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ComponentInDeliveryComboBox.setBounds(12, 285, 724, 34);
		contentPane.add(ComponentInDeliveryComboBox);
		
		
		
		JButton SelectDeliveryButton = new JButton("ВИБРАТИ");
		SelectDeliveryButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				delivery_id_to_look = MethodsForFrames.getDeliveryIdByDeliveryName(DeliveryComboBox, deliveries);
				MethodsForFrames.getComponentInfoFromDelivery(delivery_id_to_look, ComponentsInfoInDelivery, ComponentInDeliveryComboBox);
			}
		});
		SelectDeliveryButton.setBounds(316, 208, 97, 25);
		contentPane.add(SelectDeliveryButton);
		
		
		
		JButton DeliveryInfoButton = new JButton("Інформація");
		DeliveryInfoButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				delivery_id_to_look = MethodsForFrames.getDeliveryIdByDeliveryName(DeliveryComboBox, deliveries);
				ProviderMenu.delivery_information_check = 3;
				InfoDelivery.this.setVisible(false);
				try {
					new DeliveryInformation(InfoDelivery.this).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		DeliveryInfoButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DeliveryInfoButton.setBounds(748, 149, 122, 34);
		contentPane.add(DeliveryInfoButton);
		
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				InfoDelivery.this.setVisible(false);
				InfoDelivery.this.dispose();
			}
		});
		btnBack.setBounds(773, 379, 97, 25);
		contentPane.add(btnBack);
	}

}
