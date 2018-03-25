package view_director;

import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.DeliveryDao;
import dao.ProviderDao;
import domain.Delivery;
import domain.Provider;
import main.MethodsForFrames;
import view.AuthorisationMenu;
import view_workerbuycomponent.DeliveryInformation;
import view_workerbuycomponent.ProviderInformation;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class DeliveryDepartmentDirectorMenu extends JFrame {

	private JPanel contentPane;
	private JTextField DeliveryNumberField;
	private JTextField PaidDeliveryNumberField;
	private JTextField ShippedDeliveryNumberField;
	private JTextField ComponentNumberField;
	private JTextField PaidComponentNumberField;
	private JTextField ShippedComponentNumberField;
	private JTextField AllMoneyField;
	private JTextField PaidMoneyField;
	private JTextField ShippedMoneyField;

	public static int provider_id_to_look;
	public static String provider_name_to_look;
	public static int delivery_id_to_look;
	public static String delivery_name_to_look;
	
	private static int deliveryNumber;
	private static int paidDeliveryNumber;
	private static int shippedDeliveryNumber;
	private static int allMoney;
	private static int paidMoney;
	private static int shippedMoney;
	private static int componentNumber;
	private static int paidComponentNumber;
	private static int shippedComponentNumber;
	
	private static List<Delivery> deliveriesInProvider;
	private static List<Provider> providers;
	
	
	static JComboBox<String> ProviderComboBox;
	JComboBox<String> DeliveryInProviderComboBox;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public DeliveryDepartmentDirectorMenu(JFrame parent) throws SQLException 
	{
		ProviderDao pd = new ProviderDao();
		providers = pd.getAll();
		DeliveryDao dd = new DeliveryDao();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 812, 628);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel ProviderLabel = new JLabel("Список постачальників");
		ProviderLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ProviderLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ProviderLabel.setBounds(38, 13, 546, 25);
		contentPane.add(ProviderLabel);
		
		ProviderComboBox = new JComboBox<String>();
		ProviderComboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		ProviderComboBox.setBounds(38, 51, 546, 34);
		contentPane.add(ProviderComboBox);
		for(Provider provider : providers) 
		{
			ProviderComboBox.addItem(provider.getName());
		}
		
		JLabel DeliveryInProviderLabel = new JLabel("Список замовлень постачання");
		DeliveryInProviderLabel.setHorizontalAlignment(SwingConstants.CENTER);
		DeliveryInProviderLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		DeliveryInProviderLabel.setBounds(38, 425, 546, 25);
		contentPane.add(DeliveryInProviderLabel);
		
		DeliveryInProviderComboBox = new JComboBox<String>();
		DeliveryInProviderComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		DeliveryInProviderComboBox.setBounds(38, 463, 546, 34);
		contentPane.add(DeliveryInProviderComboBox);
		
		
		
		JButton SelectButton = new JButton("ВИБРАТИ");
		SelectButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				provider_id_to_look = MethodsForFrames.getProviderIdByProviderName(provider_name_to_look, provider_id_to_look, ProviderComboBox,providers);
				
				DeliveryInProviderComboBox.removeAllItems();
				try {
					deliveriesInProvider = dd.getAllFromProvider(provider_id_to_look);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				for(Delivery delivery : deliveriesInProvider) 
				{
					DeliveryInProviderComboBox.addItem(delivery.getDeliveryName());
				}
				
				
				
				try {
					deliveryNumber = pd.getDeliveryNumberOfProvider(provider_id_to_look);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				DeliveryNumberField.setText(String.valueOf(deliveryNumber));
				
				try {
					paidDeliveryNumber = pd.getPaidDeliveryNumberOfProvider(provider_id_to_look);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PaidDeliveryNumberField.setText(String.valueOf(paidDeliveryNumber));
				
				try {
					shippedDeliveryNumber = pd.getShippedDeliveryNumberOfProvider(provider_id_to_look);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ShippedDeliveryNumberField.setText(String.valueOf(shippedDeliveryNumber));
				
				
				
				try {
					allMoney = pd.getAllMoneyOfProvider(provider_id_to_look);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				AllMoneyField.setText(String.valueOf(allMoney));
				
				try {
					paidMoney = pd.getPaidMoneyOfProvider(provider_id_to_look);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PaidMoneyField.setText(String.valueOf(paidMoney));
				
				try {
					shippedMoney = pd.getShippedMoneyOfProvider(provider_id_to_look);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ShippedMoneyField.setText(String.valueOf(shippedMoney));
				
				
				
				try {
					componentNumber = pd.getComponentNumberOfProvider(provider_id_to_look);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ComponentNumberField.setText(String.valueOf(componentNumber));
				
				try {
					paidComponentNumber = pd.getPaidComponentNumberOfProvider(provider_id_to_look);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PaidComponentNumberField.setText(String.valueOf(paidComponentNumber));
				
				try {
					shippedComponentNumber = pd.getShippedComponentNumberOfProvider(provider_id_to_look);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ShippedComponentNumberField.setText(String.valueOf(shippedComponentNumber));
			}
		});
		SelectButton.setBounds(38, 109, 97, 25);
		contentPane.add(SelectButton);
				
				
				
				
		JButton InfoButton = new JButton("Інформація");
		InfoButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				provider_id_to_look = MethodsForFrames.getProviderIdByProviderName(provider_name_to_look, provider_id_to_look, ProviderComboBox,providers);
				
				DeliveryDepartmentDirectorMenu.this.setVisible(false);
				try {
					new ProviderInformation(DeliveryDepartmentDirectorMenu.this).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		InfoButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		InfoButton.setBounds(596, 54, 122, 34);
		contentPane.add(InfoButton);

		
		
		
		JButton SelectAllButton = new JButton("ВИБРАТИ ВСІХ");
		SelectAllButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				DeliveryInProviderComboBox.removeAllItems();
				try {
					deliveriesInProvider = dd.getAll();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				for(Delivery delivery : deliveriesInProvider) 
				{
					DeliveryInProviderComboBox.addItem(delivery.getDeliveryName());
				}
				
				
				try {
					deliveryNumber = pd.getDeliveryNumber();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				DeliveryNumberField.setText(String.valueOf(deliveryNumber));
				
				try {
					paidDeliveryNumber = pd.getPaidDeliveryNumber();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PaidDeliveryNumberField.setText(String.valueOf(paidDeliveryNumber));
				
				try {
					shippedDeliveryNumber = pd.getShippedDeliveryNumber();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ShippedDeliveryNumberField.setText(String.valueOf(shippedDeliveryNumber));
				
				
				
				try {
					allMoney = pd.getAllMoney();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				AllMoneyField.setText(String.valueOf(allMoney));
				
				try {
					paidMoney = pd.getPaidMoney();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PaidMoneyField.setText(String.valueOf(paidMoney));
				
				try {
					shippedMoney = pd.getShippedMoney();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ShippedMoneyField.setText(String.valueOf(shippedMoney));
				
				
				
				try {
					componentNumber = pd.getComponentNumber();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ComponentNumberField.setText(String.valueOf(componentNumber));
				
				try {
					paidComponentNumber = pd.getPaidComponentNumber();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PaidComponentNumberField.setText(String.valueOf(paidComponentNumber));
				
				try {
					shippedComponentNumber = pd.getShippedComponentNumber();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ShippedComponentNumberField.setText(String.valueOf(shippedComponentNumber));
			}
		});
		SelectAllButton.setBounds(171, 109, 135, 25);
		contentPane.add(SelectAllButton);

		
		
		
		JLabel DeliveryNumberLabel = new JLabel("Кількість замовлень");
		DeliveryNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		DeliveryNumberLabel.setBounds(38, 156, 198, 25);
		contentPane.add(DeliveryNumberLabel);
		
		JLabel PaidDeliveryNumberLabel = new JLabel("Кількість оплачених замовлень");
		PaidDeliveryNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PaidDeliveryNumberLabel.setBounds(265, 156, 198, 25);
		contentPane.add(PaidDeliveryNumberLabel);
		
		JLabel ShippedDeliveryNumberLabel = new JLabel("Кількість відвантажених замовлень");
		ShippedDeliveryNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ShippedDeliveryNumberLabel.setBounds(506, 160, 230, 25);
		contentPane.add(ShippedDeliveryNumberLabel);
		
		JLabel ComponentNumberLabel = new JLabel("Кількість компонентів");
		ComponentNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ComponentNumberLabel.setBounds(38, 253, 198, 25);
		contentPane.add(ComponentNumberLabel);
		
		JLabel PaidComponentNumberLabel = new JLabel("Кількість куплених компонентів");
		PaidComponentNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PaidComponentNumberLabel.setBounds(265, 253, 198, 25);
		contentPane.add(PaidComponentNumberLabel);
		
		JLabel ShippedComponentNumberLabel = new JLabel("Кількість відвантажених компонентів");
		ShippedComponentNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ShippedComponentNumberLabel.setBounds(506, 257, 230, 25);
		contentPane.add(ShippedComponentNumberLabel);
		
		JLabel SummaryMoneyLabel = new JLabel("Загальна вартість замовлень");
		SummaryMoneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		SummaryMoneyLabel.setBounds(38, 348, 198, 25);
		contentPane.add(SummaryMoneyLabel);
		
		JLabel PaidMoneyLabel = new JLabel("Вартість оплачених замовлень");
		PaidMoneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PaidMoneyLabel.setBounds(265, 348, 198, 25);
		contentPane.add(PaidMoneyLabel);
		
		JLabel ShippedMoneyLabel = new JLabel("Вартість відвантажених замовлень");
		ShippedMoneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ShippedMoneyLabel.setBounds(506, 352, 230, 25);
		contentPane.add(ShippedMoneyLabel);
		
		DeliveryNumberField = new JTextField();
		DeliveryNumberField.setHorizontalAlignment(SwingConstants.CENTER);
		DeliveryNumberField.setBounds(38, 185, 198, 22);
		contentPane.add(DeliveryNumberField);
		DeliveryNumberField.setColumns(10);
		
		PaidDeliveryNumberField = new JTextField();
		PaidDeliveryNumberField.setHorizontalAlignment(SwingConstants.CENTER);
		PaidDeliveryNumberField.setColumns(10);
		PaidDeliveryNumberField.setBounds(265, 185, 198, 22);
		contentPane.add(PaidDeliveryNumberField);
		
		ShippedDeliveryNumberField = new JTextField();
		ShippedDeliveryNumberField.setHorizontalAlignment(SwingConstants.CENTER);
		ShippedDeliveryNumberField.setColumns(10);
		ShippedDeliveryNumberField.setBounds(506, 185, 230, 22);
		contentPane.add(ShippedDeliveryNumberField);
		
		ComponentNumberField = new JTextField();
		ComponentNumberField.setHorizontalAlignment(SwingConstants.CENTER);
		ComponentNumberField.setColumns(10);
		ComponentNumberField.setBounds(38, 282, 198, 22);
		contentPane.add(ComponentNumberField);
		
		PaidComponentNumberField = new JTextField();
		PaidComponentNumberField.setHorizontalAlignment(SwingConstants.CENTER);
		PaidComponentNumberField.setColumns(10);
		PaidComponentNumberField.setBounds(265, 282, 198, 22);
		contentPane.add(PaidComponentNumberField);
		
		ShippedComponentNumberField = new JTextField();
		ShippedComponentNumberField.setHorizontalAlignment(SwingConstants.CENTER);
		ShippedComponentNumberField.setColumns(10);
		ShippedComponentNumberField.setBounds(506, 282, 230, 22);
		contentPane.add(ShippedComponentNumberField);
		
		AllMoneyField = new JTextField();
		AllMoneyField.setHorizontalAlignment(SwingConstants.CENTER);
		AllMoneyField.setColumns(10);
		AllMoneyField.setBounds(38, 377, 198, 22);
		contentPane.add(AllMoneyField);
		
		PaidMoneyField = new JTextField();
		PaidMoneyField.setHorizontalAlignment(SwingConstants.CENTER);
		PaidMoneyField.setColumns(10);
		PaidMoneyField.setBounds(265, 377, 198, 22);
		contentPane.add(PaidMoneyField);
		
		ShippedMoneyField = new JTextField();
		ShippedMoneyField.setHorizontalAlignment(SwingConstants.CENTER);
		ShippedMoneyField.setColumns(10);
		ShippedMoneyField.setBounds(506, 377, 230, 22);
		contentPane.add(ShippedMoneyField);
	
		
		
		
		JButton DeliveryInfoButton = new JButton("Інформація");
		DeliveryInfoButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				delivery_id_to_look = MethodsForFrames.getDeliveryIdByDeliveryName(delivery_name_to_look, delivery_id_to_look, DeliveryInProviderComboBox, deliveriesInProvider);;
				
				DeliveryDepartmentDirectorMenu.this.setVisible(false);
				try {
					new DeliveryInformation(DeliveryDepartmentDirectorMenu.this).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		DeliveryInfoButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DeliveryInfoButton.setBounds(596, 463, 122, 34);
		contentPane.add(DeliveryInfoButton);
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				DeliveryDepartmentDirectorMenu.this.setVisible(false);
				DeliveryDepartmentDirectorMenu.this.dispose();
			}
		});
		btnBack.setBounds(685, 543, 97, 25);
		contentPane.add(btnBack);
	}

	
	
	

}
