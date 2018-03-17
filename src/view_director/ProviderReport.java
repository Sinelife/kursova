package view_director;

import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ProviderDao;
import domain.Provider;
import view.AuthorisationMenu;
import view_workerbuycomponent.ProviderInformation;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProviderReport extends JFrame {

	private JPanel contentPane;
	private JTextField OrderNumberField;
	private JTextField PaidOrderNumberField;
	private JTextField ShippedOrderNumberField;
	private JTextField ComponentNumberField;
	private JTextField PaidComponentNumberField;
	private JTextField ShippedComponentNumberField;
	private JTextField AllMoneyField;
	private JTextField PaidMoneyField;
	private JTextField ShippedMoneyField;

	public static int provider_id_to_look;
	public static String provider_name_to_look;
	
	private static int orderNumber;
	private static int paidOrderNumber;
	private static int shippedOrderNumber;
	private static int allMoney;
	private static int paidMoney;
	private static int shippedMoney;
	private static int componentNumber;
	private static int paidComponentNumber;
	private static int shippedComponentNumber;
	


	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public ProviderReport(JFrame parent) throws SQLException 
	{
		ProviderDao pd = new ProviderDao();
		List<Provider> providers = pd.getAll();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 784, 612);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel ProviderLabel = new JLabel("Список постачальників");
		ProviderLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ProviderLabel.setBounds(38, 13, 198, 25);
		contentPane.add(ProviderLabel);
		
		JComboBox<String> ProviderComboBox = new JComboBox<String>();
		ProviderComboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		ProviderComboBox.setBounds(38, 51, 430, 34);
		contentPane.add(ProviderComboBox);
		for(Provider provider : providers) 
		{
			ProviderComboBox.addItem(provider.getName());
		}
		
		
		
		JButton SelectButton = new JButton("Вибрати");
		SelectButton.addActionListener(new ActionListener() 
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
				try {
					orderNumber = pd.getOrderNumberOfProvider(provider_id_to_look);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				OrderNumberField.setText(String.valueOf(orderNumber));
				
				try {
					paidOrderNumber = pd.getPaidOrderNumberOfProvider(provider_id_to_look);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PaidOrderNumberField.setText(String.valueOf(paidOrderNumber));
				
				
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
			}
		});
		
				
				
				
				
				JButton InfoButton = new JButton("Інформація");
				InfoButton.addActionListener(new ActionListener() 
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
						ProviderReport.this.setVisible(false);
						try {
							new ProviderInformation(ProviderReport.this).setVisible(true);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
					}
				});
				InfoButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
				InfoButton.setBounds(491, 51, 122, 34);
				contentPane.add(InfoButton);
		SelectButton.setBounds(38, 109, 97, 25);
		contentPane.add(SelectButton);

		
		JLabel OrderNumberLabel = new JLabel("Кількість замовлень");
		OrderNumberLabel.setBounds(38, 156, 198, 25);
		contentPane.add(OrderNumberLabel);
		
		JLabel PaidOrderNumberLabel = new JLabel("Кількість оплачених замовлень");
		PaidOrderNumberLabel.setBounds(265, 156, 198, 25);
		contentPane.add(PaidOrderNumberLabel);
		
		JLabel ShippedOrderNumberLabel = new JLabel("Кількість відвантажених замовлень");
		ShippedOrderNumberLabel.setBounds(506, 160, 230, 25);
		contentPane.add(ShippedOrderNumberLabel);
		
		JLabel ComponentNumberLabel = new JLabel("Кількість компонентів");
		ComponentNumberLabel.setBounds(38, 253, 198, 25);
		contentPane.add(ComponentNumberLabel);
		
		JLabel PaidComponentNumberLabel = new JLabel("Кількість куплених компонентів");
		PaidComponentNumberLabel.setBounds(265, 253, 198, 25);
		contentPane.add(PaidComponentNumberLabel);
		
		JLabel ShippedComponentNumberLabel = new JLabel("Кількість відвантажених компонентів");
		ShippedComponentNumberLabel.setBounds(506, 257, 230, 25);
		contentPane.add(ShippedComponentNumberLabel);
		
		JLabel SummaryMoneyLabel = new JLabel("Загальна вартість замовлень");
		SummaryMoneyLabel.setBounds(38, 348, 198, 25);
		contentPane.add(SummaryMoneyLabel);
		
		JLabel PaidMoneyLabel = new JLabel("Вартість оплачених замовлень");
		PaidMoneyLabel.setBounds(265, 348, 198, 25);
		contentPane.add(PaidMoneyLabel);
		
		JLabel ShippedMoneyLabel = new JLabel("Вартість відвантажених замовлень");
		ShippedMoneyLabel.setBounds(506, 352, 230, 25);
		contentPane.add(ShippedMoneyLabel);
		
		OrderNumberField = new JTextField();
		OrderNumberField.setBounds(38, 185, 198, 22);
		contentPane.add(OrderNumberField);
		OrderNumberField.setColumns(10);
		
		PaidOrderNumberField = new JTextField();
		PaidOrderNumberField.setColumns(10);
		PaidOrderNumberField.setBounds(265, 185, 198, 22);
		contentPane.add(PaidOrderNumberField);
		
		ShippedOrderNumberField = new JTextField();
		ShippedOrderNumberField.setColumns(10);
		ShippedOrderNumberField.setBounds(506, 185, 230, 22);
		contentPane.add(ShippedOrderNumberField);
		
		ComponentNumberField = new JTextField();
		ComponentNumberField.setColumns(10);
		ComponentNumberField.setBounds(38, 282, 198, 22);
		contentPane.add(ComponentNumberField);
		
		PaidComponentNumberField = new JTextField();
		PaidComponentNumberField.setColumns(10);
		PaidComponentNumberField.setBounds(265, 282, 198, 22);
		contentPane.add(PaidComponentNumberField);
		
		ShippedComponentNumberField = new JTextField();
		ShippedComponentNumberField.setColumns(10);
		ShippedComponentNumberField.setBounds(506, 282, 230, 22);
		contentPane.add(ShippedComponentNumberField);
		
		AllMoneyField = new JTextField();
		AllMoneyField.setColumns(10);
		AllMoneyField.setBounds(38, 377, 198, 22);
		contentPane.add(AllMoneyField);
		
		PaidMoneyField = new JTextField();
		PaidMoneyField.setColumns(10);
		PaidMoneyField.setBounds(265, 377, 198, 22);
		contentPane.add(PaidMoneyField);
		
		ShippedMoneyField = new JTextField();
		ShippedMoneyField.setColumns(10);
		ShippedMoneyField.setBounds(506, 377, 230, 22);
		contentPane.add(ShippedMoneyField);
		
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				ProviderReport.this.setVisible(false);
				ProviderReport.this.dispose();
			}
		});
		btnBack.setBounds(597, 527, 97, 25);
		contentPane.add(btnBack);
	}

}
