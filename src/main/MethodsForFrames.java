package main;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import dao.DeviceDao;
import domain.Client;
import domain.Component;
import domain.ComponentDevice;
import domain.Delivery;
import domain.Device;
import domain.Order;
import domain.Provider;
import domain.User;

public class MethodsForFrames 
{
	public static void DateToString(Date d,JTextField t)
	{
		String res = null;
		if(d == null)
		{
			res = "";
			t.setText(res);
		}
		else
		{
			t.setText(String.valueOf(d));
		}
	}
	
	
	
	
	
	//МЕТОДИ ДЛЯ ОТРИМАННЯ АЙДІШНИКА ОБ'ЄКТА ЧЕРЕЗ І'МЯ
	
	//Метод для отримання айдішника постачальника через ім'я постачальника
	
	public static int getProviderIdByProviderName(String name, int id, JComboBox<String> ComboBox,List<Provider> providers)
	{
		name = String.valueOf(ComboBox.getSelectedItem());
		for(Provider provider : providers) 
		{
			id = provider.getId();
			if(provider.getName().equals(name))
			{
				break;
			}
		}
		return id;
	}
	
	
	//Метод для отримання айдішника замовлення постачання через ім'я замовлення постачання
	
	public static int getDeliveryIdByDeliveryName(String name, int id, JComboBox<String> ComboBox,List<Delivery> deliveries)
	{
		name = String.valueOf(ComboBox.getSelectedItem());
		for(Delivery delivery : deliveries) 
		{
			id = delivery.getId();
			if(delivery.getDeliveryName().equals(name))
			{
				break;
			}
		}
		return id;
	}

	
	//Метод для отримання айдішника клієнта через ім'я клієнта
	
	public static int getClientIdByClientName(String name, int id, JComboBox<String> ComboBox, List<Client> clients)
	{
		name = String.valueOf(ComboBox.getSelectedItem());
		for(Client client : clients) 
		{
			id = client.getId();
			if(client.getName().equals(name))
			{
				break;
			}
		}
		return id;
	}
	
	
	
	//Метод для отримання айдішника замовлення на купівлю через ім'я замовлення на купівлю
	
	public static int getOrderIdByOrderName(String name, int id, JComboBox<String> ComboBox, List<Order> orders)
	{
		name = String.valueOf(ComboBox.getSelectedItem());
		for(Order order : orders) 
		{
			id = order.getId();
			if(order.getOrderName().equals(name))
			{
				break;
			}
		}
		return id;
	}
	
	
	
	//Метод для отримання айдішника приладу через ім'я приладу
	
	public static int getDeviceIdByDeviceName(String name, int id, JComboBox<String> ComboBox,List<Device> devices)
	{
		name = String.valueOf(ComboBox.getSelectedItem());
		for(Device device : devices) 
		{
			id = device.getId();
			if(device.getName().equals(name))
			{
				break;
			}
		}
		return id;
	}
	
	
	
	//Метод для отримання айдішника компоненту через ім'я компоненту
	
	public static int getComponentIdByComponentName(String name, int id, JComboBox<String> ComboBox,List<Component> components)
	{
		name = String.valueOf(ComboBox.getSelectedItem());
		for(Component component : components) 
		{
			id = component.getId();
			if(component.getName().equals(name))
			{
				break;
			}
		}
		return id;
	}
	
	
	
	//Метод для отримання айдішника користувача через ім'я і прізвище користувача
	
	public static int getUsertIdByUserSurnameAndName(String surname_name, int id, JComboBox<String> ComboBox,List<User> users)
	{
		surname_name = String.valueOf(ComboBox.getSelectedItem());
		for(User user : users) 
		{
			id = user.getId();
			String record = user.getSurname() + " " + user.getName();
			if(record.equals(surname_name))
			{
				break;
			}
		}
		return id;
	}
	
	
	
	
	//МЕТОДИ ДЛЯ РОБОТИ З СПЕЦИФІКАЦІЄЮ ПРИЛАДУ
	
	//Метод для зміни вартості компонентів приладу через додавання нових приладів
	
	public static void changeComponentsCostBecauseAdd(int device_id, int component_id)
	{
		DeviceDao device_dao = new DeviceDao();
		Device d = null;
		try {
			d = device_dao.readDevice(device_id);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		int old_components_price = d.getComponentsPrice();
		int change_components_price = 0;
		try {
			change_components_price = device_dao.getComponentCostInDevice(device_id, component_id);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		int new_components_price = old_components_price + change_components_price;
		d.setComponentsPrice(new_components_price);
		d.setProfitPrice((d.getWorkPrice() + d.getComponentsPrice())/2);
		d.setSumPrice(d.getWorkPrice() + d.getComponentsPrice() + d.getProfitPrice());
		try {
			device_dao.updateDevice(d);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	
	//Метод для зміни вартості компонентів приладу через видалення приладів
	
	public static void changeComponentsCostBecauseDelete(int device_id, int component_id)
	{
		DeviceDao device_dao = new DeviceDao();
		Device d = null;
		try {
			d = device_dao.readDevice(device_id);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		int old_components_price = d.getComponentsPrice();
		int change_components_price = 0;
		try {
			change_components_price = device_dao.getComponentCostInDevice(device_id, component_id);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		int new_components_price = old_components_price - change_components_price;
		d.setComponentsPrice(new_components_price);
		d.setProfitPrice((d.getWorkPrice() + d.getComponentsPrice())/2);
		d.setSumPrice(d.getWorkPrice() + d.getComponentsPrice() + d.getProfitPrice());
		
		try {
			device_dao.updateDevice(d);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	
	
	
	
	//Метод для додавання компонентів до приладу
	
	public static void addComponentsToDevice(int device_id,int component_id, JTextField NumberAddField)
	{
		DeviceDao device_dao = new DeviceDao();
		ComponentDevice record = new ComponentDevice();
		record.setDeviceId(device_id);
		record.setComponentId(component_id);
		record.setNumber(Integer.valueOf(NumberAddField.getText()));
		try {
			device_dao.addComponentToDevice(record);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		changeComponentsCostBecauseAdd(device_id, component_id);
	}
	
	
	
	//Метод для видалення компонентів з приладу
	
	public static void deleteComponentsFromDevice(int device_id,int component_id)
	{
		DeviceDao device_dao = new DeviceDao();
		ComponentDevice record = null;
		try {
			record = device_dao.readComponentInDevice(device_id, component_id);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		changeComponentsCostBecauseDelete(device_id, component_id);
		try {
			device_dao.deleteComponentFromDevice(record);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	
	
	//Метод для зміни кількості компонентів в приладі
	
	public static void updateComponentsInDevice(int device_id, int component_id, JTextField NumberEditField)
	{
		DeviceDao device_dao = new DeviceDao();
		ComponentDevice record = null;
		try {
			record = device_dao.readComponentInDevice(device_id, component_id);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		changeComponentsCostBecauseDelete(device_id, component_id);
		
		record.setNumber(Integer.valueOf(NumberEditField.getText()));
		try {
			device_dao.updateComponentInDevice(record);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		changeComponentsCostBecauseAdd(device_id, component_id);
		
	}
	
	
	
}
