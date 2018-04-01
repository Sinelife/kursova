package view_workerbuycomponent;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dao.DeliveryDao;
import domain.Component;
import domain.Delivery;
import domain.DeliveryComponent;
import main.MethodsForFrames;
import view.AuthorisationMenu;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

public class EditDeliveryComponent extends JFrame {

	private JPanel contentPane;

	public static int delivery_id_to_edit;
	public static String delivery_name_to_edit;

	List<Delivery> deliveries;
	private static List<Component> ComponentsInDeliveryEdit = null;
	private static List<Component> ComponentsInDeliveryDelete = null;
	private static List<Component> ComponentsNotInDelivery = null;
	
	private JTextField NumberAddField;
	private JTextField NumberEditField;
	
	static JComboBox<String> AddComboBox = new JComboBox<String>();
	static JComboBox<String> DeleteComboBox = new JComboBox<String>();
	static JComboBox<String> EditComboBox = new JComboBox<String>();
	
	private int component_id;
	private String component_name;
	
	static DeliveryDao dd = new DeliveryDao();
	
	/**
	 * Create the frame.
	 * @throws SQLException
	 */
	public EditDeliveryComponent(JFrame parent) throws SQLException 
	{
		deliveries = dd.getAllFromProviderNotPaid(ChooseProvider.id_to_choose);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 759, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AuthorisationMenu.setColorOfFrame(contentPane, AuthorisationMenu.user_role);
		
		
		JLabel lblNewLabel = new JLabel("Редагування замовлення постачання");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(0, 13, 741, 59);
		contentPane.add(lblNewLabel);
		
		JComboBox<String> DeliveryComboBox = new JComboBox<String>();
		DeliveryComboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		DeliveryComboBox.setBounds(39, 85, 559, 34);
		contentPane.add(DeliveryComboBox);
		for(Delivery delivery : deliveries) 
		{
			DeliveryComboBox.addItem(delivery.getDeliveryName());
		}
		
		
		JLabel AddLabel = new JLabel("Додавання");
		AddLabel.setBounds(39, 244, 170, 16);
		contentPane.add(AddLabel);
		
		JLabel DeleteLabel = new JLabel("Видалення");
		DeleteLabel.setBounds(275, 247, 170, 16);
		contentPane.add(DeleteLabel);
		
		JLabel EditLabel = new JLabel("Редагування");
		EditLabel.setBounds(508, 247, 170, 16);
		contentPane.add(EditLabel);
		
		
		AddComboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		AddComboBox.setBounds(39, 276, 170, 22);
		contentPane.add(AddComboBox);
		
		DeleteComboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		DeleteComboBox.setBounds(275, 276, 170, 22);
		contentPane.add(DeleteComboBox);
		
		EditComboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		EditComboBox.setBounds(508, 276, 170, 22);
		contentPane.add(EditComboBox);	
		MyItemListener actionListener = new MyItemListener();
		EditComboBox.addItemListener(actionListener);		
		
		
		JButton SelectButton = new JButton("ВИБРАТИ");
		SelectButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				delivery_id_to_edit = MethodsForFrames.getDeliveryIdByDeliveryName(delivery_name_to_edit, delivery_id_to_edit, DeliveryComboBox, deliveries);		
				outputAllComponentsComboBoxes();
			}
		});
		SelectButton.setBounds(259, 152, 97, 25);
		contentPane.add(SelectButton);
		
		
	
		
		NumberAddField = new JTextField();
		NumberAddField.setBounds(39, 344, 170, 25);
		contentPane.add(NumberAddField);
		NumberAddField.setColumns(10);
		
		JLabel NumberAddLabel = new JLabel("Кількість");
		NumberAddLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		NumberAddLabel.setBounds(39, 314, 170, 28);
		contentPane.add(NumberAddLabel);
		
		NumberEditField = new JTextField();
		NumberEditField.setColumns(10);
		NumberEditField.setBounds(508, 344, 170, 25);
		contentPane.add(NumberEditField);
		
		JLabel NumberEditLabel = new JLabel("Кількість");
		NumberEditLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		NumberEditLabel.setBounds(508, 314, 170, 28);
		contentPane.add(NumberEditLabel);
		
		
		
		JButton AddButton = new JButton("Додати");
		AddButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				component_id = MethodsForFrames.getComponentIdByComponentName(component_name, component_id, AddComboBox, ComponentsNotInDelivery);
				try {
					MethodsForFrames.addComponentsInDelivery(delivery_id_to_edit, component_id, NumberAddField);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		AddButton.setBounds(39, 405, 125, 28);
		contentPane.add(AddButton);
		
		
		
		JButton DeleteButton = new JButton("Видалити");
		DeleteButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				component_id = MethodsForFrames.getComponentIdByComponentName(component_name, component_id, DeleteComboBox, ComponentsInDeliveryDelete);
				try {
					MethodsForFrames.deleteComponentsFromDelivery(delivery_id_to_edit, component_id);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		DeleteButton.setBounds(275, 407, 125, 28);
		contentPane.add(DeleteButton);
		
		
		
		JButton EditButton = new JButton("Редагувати");
		EditButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				component_id = MethodsForFrames.getComponentIdByComponentName(component_name, component_id, EditComboBox, ComponentsInDeliveryEdit);
				try {
					MethodsForFrames.updateComponentsInDelivery(delivery_id_to_edit, component_id, NumberEditField);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		EditButton.setBounds(508, 407, 125, 28);
		contentPane.add(EditButton);
		
		
		
		JButton btnNewButton = new JButton("Інформація");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				delivery_id_to_edit = MethodsForFrames.getDeliveryIdByDeliveryName(delivery_name_to_edit, delivery_id_to_edit, DeliveryComboBox, deliveries);			
				ProviderMenu.delivery_information_check = 2;
				EditDeliveryComponent.this.setVisible(false);
				try {
					new DeliveryInformation(EditDeliveryComponent.this).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(610, 85, 119, 34);
		contentPane.add(btnNewButton);
		
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				EditDeliveryComponent.this.setVisible(false);
				EditDeliveryComponent.this.dispose();
			}
		});
		btnBack.setBounds(632, 492, 97, 25);
		contentPane.add(btnBack);
	}
	
	
	public static void outputAllComponentsComboBoxes()
	{
		
		try {
			ComponentsInDeliveryEdit = dd.getAllComponentsInDelivery(delivery_id_to_edit);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			ComponentsInDeliveryDelete = dd.getAllComponentsInDelivery(delivery_id_to_edit);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			ComponentsNotInDelivery = dd.getAllComponentsNotInDelivery(delivery_id_to_edit);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		AddComboBox.removeAllItems();
		DeleteComboBox.removeAllItems();
		EditComboBox.removeAllItems();
		
		for(Component component : ComponentsNotInDelivery)
		{
			AddComboBox.addItem(component.getName());
		}
		for(Component component : ComponentsInDeliveryDelete)
		{
			DeleteComboBox.addItem(component.getName());
		}
		for(Component component : ComponentsInDeliveryEdit)
		{
			EditComboBox.addItem(component.getName());
		}
	}
	
	
	
	class MyItemListener implements ItemListener 
	{
		// This method is called only if a new item has been selected.
		public void itemStateChanged(ItemEvent evt) 
		{
			if (evt.getStateChange() == ItemEvent.SELECTED) {
				String component_name = String.valueOf(EditComboBox.getSelectedItem());
				int component_id = 0;
				component_id = MethodsForFrames.getComponentIdByComponentName(component_name, component_id, EditComboBox, ComponentsInDeliveryEdit);

				DeliveryComponent record = null;
				try {
					record = dd.readComponentInDelivery(delivery_id_to_edit, component_id);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				NumberEditField.setText(String.valueOf(record.getNumber()));

			} else if (evt.getStateChange() == ItemEvent.DESELECTED) {
				NumberEditField.setText("");
			}
		}
	}
}
