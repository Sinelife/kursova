package view_workerbuycomponent;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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


public class EditProvider extends JFrame {

	private JPanel contentPane;
	public static String name_to_edit;
	public static int id_to_edit;
	

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public EditProvider(JFrame parent) throws SQLException 
	{
		ProviderDao pd = new ProviderDao();
		List<Provider> providers = pd.getAll();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		comboBox.setBounds(40, 121, 437, 34);
		contentPane.add(comboBox);
		for(Provider provider : providers) 
		{
			comboBox.addItem(provider.getName());
		}
		
		JLabel lblNewLabel = new JLabel("Вибір постачальника для редагування.");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(46, 25, 559, 59);
		contentPane.add(lblNewLabel);
		
		
		JButton SelectButton = new JButton("Вибрати");
		SelectButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				name_to_edit = String.valueOf(comboBox.getSelectedItem());
				for(Provider provider : providers) 
				{
					id_to_edit = provider.getId();
					if(provider.getName().equals(name_to_edit))
					{
						break;
					}
				}
				EditProvider.this.setVisible(false);
				try {
					new EditProviderFrame(EditProvider.this).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		SelectButton.setBounds(46, 427, 97, 25);
		contentPane.add(SelectButton);
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				EditProvider.this.setVisible(false);
				EditProvider.this.dispose();
			}
		});
		btnBack.setBounds(489, 427, 97, 25);
		contentPane.add(btnBack);
	}
}
