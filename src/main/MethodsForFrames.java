package main;

import java.sql.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import domain.Client;
import domain.Component;
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
}
