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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class EditDeliveryComponent extends JFrame {

	private JPanel contentPane;

	public static int delivery_id_to_edit;
	public static String delivery_name_to_edit;

	List<Delivery> deliveries;
	private List<Component> ComponentsInDeliveryEdit = null;
	private List<Component> ComponentsInDeliveryDelete = null;
	private List<Component> ComponentsNotInDelivery = null;
	
	private JTextField NumberAddField;
	private JTextField NumberEditField;
	
	JComboBox<String> AddComboBox = new JComboBox<String>();
	JComboBox<String> DeleteComboBox = new JComboBox<String>();
	JComboBox<String> EditComboBox = new JComboBox<String>();
	
	DeliveryDao dd = new DeliveryDao();
	
	/**
	 * Create the frame.
	 * @throws SQLException
	 */
	public EditDeliveryComponent(JFrame parent) throws SQLException 
	{
		deliveries = dd.getAllFromProvider(ChooseProvider.id_to_choose);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 759, 576);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("����������� ���������� ����������");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(103, 13, 495, 59);
		contentPane.add(lblNewLabel);
		
		JComboBox<String> DeliveryComboBox = new JComboBox<String>();
		DeliveryComboBox.setFont(new Font("Tahoma", Font.PLAIN, 23));
		DeliveryComboBox.setBounds(39, 85, 559, 34);
		contentPane.add(DeliveryComboBox);
		for(Delivery delivery : deliveries) 
		{
			DeliveryComboBox.addItem(delivery.getDeliveryName());
		}
		
		
		JLabel AddLabel = new JLabel("���������");
		AddLabel.setBounds(39, 272, 141, 16);
		contentPane.add(AddLabel);
		
		JLabel DeleteLabel = new JLabel("���������");
		DeleteLabel.setBounds(275, 275, 141, 16);
		contentPane.add(DeleteLabel);
		
		JLabel EditLabel = new JLabel("�����������");
		EditLabel.setBounds(508, 275, 141, 16);
		contentPane.add(EditLabel);
		
		
		AddComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		AddComboBox.setBounds(39, 304, 141, 22);
		contentPane.add(AddComboBox);
		
		DeleteComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		DeleteComboBox.setBounds(275, 304, 141, 22);
		contentPane.add(DeleteComboBox);
		
		EditComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		EditComboBox.setBounds(508, 304, 141, 22);
		contentPane.add(EditComboBox);	
		MyItemListener actionListener = new MyItemListener();
		EditComboBox.addItemListener(actionListener);		
		
		
		JButton SelectButton = new JButton("�������");
		SelectButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				delivery_name_to_edit = String.valueOf(DeliveryComboBox.getSelectedItem());
				for(Delivery delivery : deliveries) 
				{
					if(delivery.getDeliveryName().equals(delivery_name_to_edit))
					{
						delivery_id_to_edit = delivery.getId();
					}
				}
				
				try {
					ComponentsInDeliveryEdit = dd.getAllComponentsInDelivery(delivery_id_to_edit);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					ComponentsInDeliveryDelete = dd.getAllComponentsInDelivery(delivery_id_to_edit);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					ComponentsNotInDelivery = dd.getAllComponentsNotInDelivery(delivery_id_to_edit);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
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
		});
		SelectButton.setBounds(39, 162, 97, 25);
		contentPane.add(SelectButton);
		
		
	
		
		NumberAddField = new JTextField();
		NumberAddField.setBounds(39, 372, 141, 25);
		contentPane.add(NumberAddField);
		NumberAddField.setColumns(10);
		
		JLabel NumberAddLabel = new JLabel("ʳ������");
		NumberAddLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		NumberAddLabel.setBounds(39, 342, 141, 28);
		contentPane.add(NumberAddLabel);
		
		NumberEditField = new JTextField();
		NumberEditField.setColumns(10);
		NumberEditField.setBounds(508, 372, 141, 25);
		contentPane.add(NumberEditField);
		
		JLabel NumberEditLabel = new JLabel("ʳ������");
		NumberEditLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		NumberEditLabel.setBounds(508, 342, 141, 28);
		contentPane.add(NumberEditLabel);
		
		
		
		JButton AddButton = new JButton("������");
		AddButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String component_name = String.valueOf(AddComboBox.getSelectedItem());
				int component_id = 0;
				for(Component component : ComponentsNotInDelivery) 
				{
					if(component.getName().equals(component_name))
					{
						component_id = component.getId();
					}
				}
				
				DeliveryComponent record = new DeliveryComponent();
				record.setDeliveryId(delivery_id_to_edit);;
				record.setComponentId(component_id);
				record.setNumber(Integer.valueOf(NumberAddField.getText()));
				try {
					dd.addComponentInDelivery(record);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		AddButton.setBounds(39, 433, 125, 28);
		contentPane.add(AddButton);
		
		
		
		JButton DeleteButton = new JButton("��������");
		DeleteButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String component_name = String.valueOf(DeleteComboBox.getSelectedItem());
				int component_id = 0;
				for(Component component : ComponentsInDeliveryDelete) 
				{
					if(component.getName().equals(component_name))
					{
						component_id = component.getId();
					}
				}
				DeliveryComponent record = null;
				try {
					record = dd.readComponentInDelivery(delivery_id_to_edit, component_id);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					dd.deleteComponentFromDelivery(record);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		DeleteButton.setBounds(275, 435, 125, 28);
		contentPane.add(DeleteButton);
		
		
		
		JButton EditButton = new JButton("����������");
		EditButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String component_name = String.valueOf(EditComboBox.getSelectedItem());
				int component_id = 0;
				for(Component component : ComponentsInDeliveryEdit) 
				{
					if(component.getName().equals(component_name))
					{
						component_id = component.getId();
					}
				}
				DeliveryComponent record = null;
				try {
					record = dd.readComponentInDelivery(delivery_id_to_edit, component_id);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				record.setNumber(Integer.valueOf(NumberEditField.getText()));
				try {
					dd.updateComponentInDelivery(record);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		EditButton.setBounds(508, 435, 125, 28);
		contentPane.add(EditButton);
		
		
		
		JButton btnNewButton = new JButton("����������");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				delivery_name_to_edit = String.valueOf(DeliveryComboBox.getSelectedItem());
				for(Delivery delivery : deliveries) 
				{
					delivery_id_to_edit = delivery.getId();
					if(delivery.getDeliveryName().equals(delivery_name_to_edit))
					{
						break;
					}
				}
				EditDeliveryComponent.this.setVisible(false);
				try {
					new DeliveryInformation(EditDeliveryComponent.this).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
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
		btnBack.setBounds(632, 491, 97, 25);
		contentPane.add(btnBack);
	}
	
	
	class MyItemListener implements ItemListener 
	{
		// This method is called only if a new item has been selected.
		public void itemStateChanged(ItemEvent evt) 
		{
			if (evt.getStateChange() == ItemEvent.SELECTED) {
				String component_name = String.valueOf(EditComboBox.getSelectedItem());
				int component_id = 0;
				for (Component component : ComponentsInDeliveryEdit) {
					if (component.getName().equals(component_name)) 
					{
						component_id = component.getId();
					}
				}
				DeliveryComponent record = null;
				try {
					record = dd.readComponentInDelivery(delivery_id_to_edit, component_id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				NumberEditField.setText(String.valueOf(record.getNumber()));

			} else if (evt.getStateChange() == ItemEvent.DESELECTED) {
				NumberEditField.setText("");
			}
		}
	}
}
