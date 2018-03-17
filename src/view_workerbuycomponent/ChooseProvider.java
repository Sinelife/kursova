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

public class ChooseProvider extends JFrame {

	private JPanel contentPane;
	public static String name_to_choose;
	public static int id_to_choose;


	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public ChooseProvider() throws SQLException 
	{
		ProviderDao pd = new ProviderDao();
		List<Provider> providers = pd.getAll();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 598, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel lblNewLabel = new JLabel("���� ������������� ��� �������� ������ � ���");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(22, 29, 546, 59);
		contentPane.add(lblNewLabel);


		JComboBox<String> ProviderComboBox = new JComboBox<String>();
		ProviderComboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		ProviderComboBox.setBounds(40, 121, 427, 34);
		contentPane.add(ProviderComboBox);
		for(Provider provider : providers) 
		{
			ProviderComboBox.addItem(provider.getName());
		}
		
		
		
		
		JButton SelectButton = new JButton("�������");
		SelectButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				id_to_choose = MethodsForFrames.getProviderIdByProviderName(name_to_choose, id_to_choose, ProviderComboBox, providers);
				
				name_to_choose = ProviderComboBox.getSelectedItem().toString();
				ChooseProvider.this.setVisible(false);
				new DeliveryMenu().setVisible(true);
				
			}
		});
		SelectButton.setBounds(40, 311, 118, 25);
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
		btnBack.setBounds(471, 311, 97, 25);
		contentPane.add(btnBack);
	}

}
