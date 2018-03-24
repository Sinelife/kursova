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

import dao.OrderDao;
import domain.Order;
import domain.OrderDevice;
import main.MethodsForFrames;
import view.AuthorisationMenu;
import javax.swing.SwingConstants;

public class InfoOrder extends JFrame 
{

	private JPanel contentPane;
	public static int order_id_to_look;
	public static String order_name_to_look;

	public List<OrderDevice> DevicesInfoInOrder;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public InfoOrder(JFrame parent) throws SQLException 
	{
		OrderDao od = new OrderDao();
		List<Order> orders = od.getAllFromClient(ChooseClient.id_to_choose);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 784, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel lblNewLabel = new JLabel("Перегляд інформації про замовлення на купівлю");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setBounds(0, 25, 766, 59);
		contentPane.add(lblNewLabel);
		
		JLabel OrderLabel = new JLabel("Список замовлень на купівлю");
		OrderLabel.setHorizontalAlignment(SwingConstants.CENTER);
		OrderLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		OrderLabel.setBounds(12, 113, 724, 25);
		contentPane.add(OrderLabel);

		JComboBox<String> OrderComboBox = new JComboBox<String>();
		OrderComboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		OrderComboBox.setBounds(12, 149, 724, 34);
		contentPane.add(OrderComboBox);
		for(Order order : orders) 
		{
			OrderComboBox.addItem(order.getOrderName());
		}
		
		
		JComboBox<String> DeviceInOrderComboBox = new JComboBox<String>();
		DeviceInOrderComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DeviceInOrderComboBox.setBounds(12, 285, 724, 34);
		contentPane.add(DeviceInOrderComboBox);
		
		
		
		JButton SelectOrderButton = new JButton("ВИБРАТИ");
		SelectOrderButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				order_id_to_look = MethodsForFrames.getOrderIdByOrderName(order_name_to_look, order_id_to_look, OrderComboBox, orders);
				MethodsForFrames.getDeviceInfoFromOrder(order_id_to_look, DevicesInfoInOrder, DeviceInOrderComboBox);
			}
		});
		SelectOrderButton.setBounds(284, 205, 97, 25);
		contentPane.add(SelectOrderButton);
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				InfoOrder.this.setVisible(false);
				InfoOrder.this.dispose();
			}
		});
		btnBack.setBounds(639, 396, 97, 25);
		contentPane.add(btnBack);
	}

}
