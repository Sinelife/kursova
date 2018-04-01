package main;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import dao.ClientDao;
import dao.ComponentDao;
import dao.DeliveryComponentDao;
import dao.DeliveryDao;
import dao.DeviceDao;
import dao.OrderDao;
import dao.OrderDeviceDao;
import dao.ProviderDao;
import dao.UserDao;
import domain.Client;
import domain.Component;
import domain.ComponentDevice;
import domain.Delivery;
import domain.DeliveryComponent;
import domain.Device;
import domain.Order;
import domain.OrderDevice;
import domain.Provider;
import domain.Specialization;
import domain.User;

public class MethodsForFrames 
{
	
	@SuppressWarnings("deprecation")
	public static String getCurrentDate()
	{
		java.util.Date date = new java.util.Date();
		String result = "";
		String day = String.valueOf(date.getDate());
		String year = String.valueOf(date.getYear() + 1900);
		String month = "";
		String s = String.valueOf(date.getMonth() + 1);
		if((Integer.valueOf(s)) < 10)
		{
			String s1 = "0";
			month = s1.concat(s);
		}
		else
		{
			month = s;
		} 
		result = year.concat("-").concat(month).concat("-").concat(day);
		return result;
	}
	
	
	
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
	
	
	
	
	
	//������ ��� ��������� ��Ĳ����� ��'���� ����� �'��
	
	//����� ��� ��������� �������� ������������� ����� ��'� �������������
	
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
	
	
	//����� ��� ��������� �������� ���������� ���������� ����� ��'� ���������� ����������
	
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

	
	//����� ��� ��������� �������� �볺��� ����� ��'� �볺���
	
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
	
	
	
	//����� ��� ��������� �������� ���������� �� ������ ����� ��'� ���������� �� ������
	
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
	
	
	
	//����� ��� ��������� �������� ������� ����� ��'� �������
	
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
	
	
	
	//����� ��� ��������� �������� ���������� ����� ��'� ����������
	
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
	
	
	
	//����� ��� ��������� �������� ����������� ����� ��'� � ������� �����������
	
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
	
	
	
	
	//������ ��� ������ � �����Բ��ֲ�� �������
	
	
	
	
	
	//����� ��� ��������� ���������� �� �������
	
	public static void addComponentsToDevice(int device_id,int component_id, JTextField NumberAddField) throws SQLException
	{
		DeviceDao dd = new DeviceDao();
		ComponentDevice record = new ComponentDevice();
		record.setDeviceId(device_id);
		record.setComponentId(component_id);
		record.setNumber(Integer.valueOf(NumberAddField.getText()));
		try {
			dd.addComponentToDevice(record);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		Device d = dd.readDevice(device_id);
		dd.updateDevice(d, false);
		
		OrderDao od = new OrderDao();
		List<Order> orders = od.getAllNotPaid();
		for(Order order : orders)
		{
			od.updateOrder(order, false);
		}

	}
	
	
	
	//����� ��� ��������� ���������� � �������
	
	public static void deleteComponentsFromDevice(int device_id,int component_id) throws SQLException
	{
		DeviceDao dd = new DeviceDao();
		ComponentDevice record = null;
		try {
			record = dd.readComponentInDevice(device_id, component_id);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			dd.deleteComponentFromDevice(record);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		Device d = dd.readDevice(device_id);
		dd.updateDevice(d, false);
		
		OrderDao od = new OrderDao();
		List<Order> orders = od.getAllNotPaid();
		for(Order order : orders)
		{
			od.updateOrder(order, false);
		}

	}
	
	
	
	//����� ��� ���� ������� ���������� � ������
	
	public static void updateComponentsInDevice(int device_id, int component_id, JTextField NumberEditField) throws SQLException
	{
		DeviceDao dd = new DeviceDao();
		ComponentDevice record = null;
		try {
			record = dd.readComponentInDevice(device_id, component_id);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		record.setNumber(Integer.valueOf(NumberEditField.getText()));
		try {
			dd.updateComponentInDevice(record);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}		
		
		Device d = dd.readDevice(device_id);
		dd.updateDevice(d, false);
		
		OrderDao od = new OrderDao();
		List<Order> orders = od.getAllNotPaid();
		for(Order order : orders)
		{
			od.updateOrder(order, false);
		}

	}
	
	
	
	
	//����� ��� ������ ���������� ������������
	
	public static void getSpecificationInfo(int device_id, List<Component> components, JComboBox<String> ComponentInfoComboBox)
	{
		DeviceDao dd = new DeviceDao();
		ComponentDao cd = new ComponentDao();
		ComponentInfoComboBox.removeAllItems();
		try {
			components = dd.getAllComponentsInDevice(device_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String info[] = new String[100];
		int i = 0 ;
		ComponentDevice c_d = null;
		Component comp = null;
		for(Component component : components)
		{
			try {
				c_d = dd.readComponentInDevice(device_id, component.getId());
				comp = cd.readComponent(component.getId());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			info[i] = "�����: " + comp.getName() + "    ���: " + comp.getType() + "    ʳ������:" + c_d.getNumber();
			ComponentInfoComboBox.addItem(info[i]);
			i++;
		}
	}
	
	
	
	
	
	
	//������ ��� ��������� в���� ��'��Ҳ� ������������
	
	//����� ��� ��������� ������ ����������

	public static void addComponent(JComboBox<String> TypeComboBox, JTextField NameField,
			JTextField TechnicalInfoField, JTextField PriceField) throws SQLException 
	{
		ComponentDao cd = new ComponentDao();
		Component c = new Component();
		c.setType(TypeComboBox.getSelectedItem().toString());
		c.setName(NameField.getText());
		c.setTechnicalInfo(TechnicalInfoField.getText());
		c.setPrice(Integer.valueOf(PriceField.getText()));
		try {
			cd.addComponent(c);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	
	//����� ��� ��������� ������ �������
	
	public static void addDevice(JTextField NameField, JTextField SupplyVoltageField,
			JTextField BorderRegulationTimeField, JTextField RatingField, JTextField StartDateField,
			JTextField WorkPriceField) throws SQLException 
	{
		DeviceDao dd = new DeviceDao();
		Device d = new Device();
		d.setName(NameField.getText());
		d.setSupplyVoltage(SupplyVoltageField.getText());
		d.setBorderRegulationTime(BorderRegulationTimeField.getText());
		d.setRating(Integer.valueOf(RatingField.getText()));
		d.setDate(Date.valueOf(StartDateField.getText()));
		d.setWorkPrice(Integer.valueOf(WorkPriceField.getText()));
		d.setSumPrice(dd.getSumPrice(d));
		try {
			dd.addDevice(d);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}


	
	//����� ��� ��������� ������ �������������
	
	public static void addProvider(JTextField NameField, JTextField PhoneField,
			JTextField ContactPIBField, JTextField CodeERPOUField, JTextField CodeTaxpayerField,
			JTextField SpecializationField) 
	{
		ProviderDao pd = new ProviderDao();
		Provider p = new Provider();
		p.setName(NameField.getText());
		p.setPhone(PhoneField.getText());
		p.setContactPIB(ContactPIBField.getText());
		p.setCodeERPOU(CodeERPOUField.getText());
		p.setCodeTaxpayer(CodeTaxpayerField.getText());
		p.setSpecialization(SpecializationField.getText());
		try {
			pd.addProvider(p);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	
	//����� ��� ��������� ������ ���������� ����������
	
	public static void addDelivery(String provider_name, int provider_id, JTextField StartDateField) 
	{
		DeliveryDao dd = new DeliveryDao();
		Delivery d = new Delivery();
		ProviderDao pd = new ProviderDao();
		
		d.setProviderId(provider_id);
		try {
			d.setDeliveryName("���������� ������������� " + provider_name + " �" + (pd.getDeliveryNumberOfProvider(provider_id) + 1));
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		d.setStartDate(Date.valueOf(StartDateField.getText()));
		try {
			dd.addDelivery(d);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	
	//����� ��� ��������� ������ �볺���
	
	public static void addClient(JTextField NameField, JTextField PhoneField,
			JTextField ContactPIBField, JTextField CodeERPOUField, JTextField CodeTaxpayerField) 
	{
		ClientDao cd = new ClientDao();
		Client c = new Client();
		c.setName(NameField.getText());
		c.setPhone(PhoneField.getText());
		c.setContactPIB(ContactPIBField.getText());
		c.setCodeERPOU(CodeERPOUField.getText());
		c.setCodeTaxpayer(CodeTaxpayerField.getText());
		try {
			cd.addClient(c);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	
	//����� ��� ��������� ������ ���������� ����������
	
	public static void addOrder(String client_name, int client_id, JTextField StartDateField) 
	{
		OrderDao od = new OrderDao();
		ClientDao cd = new ClientDao();
		Order o = new Order();
		o.setClientId(client_id);
		try {
			o.setOrderName("���������� �볺��� " + client_name + " �" + (cd.getOrderNumberOfClient(client_id) + 1));
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		o.setStartDate(Date.valueOf(StartDateField.getText()));
		try {
			od.addOrder(o);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	
	//����� ��� ��������� ������ �����������
	
	public static void addUser(JTextField SurnameField, JTextField NameField, JTextField LoginField,
			JTextField PasswordField, JTextField RoleField) 
	{
		UserDao ud = new UserDao();
		User u = new User();
		u.setSurname(SurnameField.getText());
		u.setName(NameField.getText());
		u.setLogin(LoginField.getText());
		u.setPassword(PasswordField.getText());
		u.setRole(RoleField.getText());
		try {
			ud.addUser(u);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	
	
	
	
	//������ ��� ��������� в���� ��'��Ҳ� ������������
	
	//����� ��� ��������� ����������
	
	public static void updateComponent(Component c, JTextField NameField,
			JTextField TechnicalInfoField, JTextField PriceField) throws SQLException 
	{
		ComponentDao cd = new ComponentDao();
		c.setName(NameField.getText());
		c.setTechnicalInfo(TechnicalInfoField.getText());
		c.setPrice(Integer.valueOf(PriceField.getText()));
		try {
			cd.updateComponent(c);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		DeviceDao dd = new DeviceDao();
		List<Device> devices = dd.getAllDeviceWhichHasComponent(c.getId());
		for(Device device : devices)
		{
			device.setSumPrice(dd.getSumPrice(device));
			dd.updateDevice(device, false);
		}
		OrderDao order_dao = new OrderDao();
		List<Order> orders = order_dao.getAllNotPaid();
		for(Order order : orders)
		{
			order_dao.updateOrder(order, false);
		}
		DeliveryDao delivery_dao = new DeliveryDao();
		List<Delivery> deliveries = delivery_dao.getAllNotPaid();
		for(Delivery delivery : deliveries)
		{
			delivery_dao.updateDelivery(delivery, false);
		}
	}
	
	
	//����� ��� ��������� �������
	
	public static void updateDevice(Device d, JTextField NameField, JTextField SupplyVoltageField,
			JTextField BorderRegulationTimeField, JTextField RatingField, JTextField StartDateField,
			JTextField WorkPriceField) throws SQLException 
	{
		DeviceDao dd = new DeviceDao();
		d.setName(NameField.getText());
		d.setSupplyVoltage(SupplyVoltageField.getText());
		d.setBorderRegulationTime(BorderRegulationTimeField.getText());
		d.setRating(Integer.valueOf(RatingField.getText()));
		d.setDate(Date.valueOf(StartDateField.getText()));
		d.setWorkPrice(Integer.valueOf(WorkPriceField.getText()));
		d.setSumPrice(dd.getSumPrice(d));
		try {
			dd.updateDevice(d, true);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		OrderDao od = new OrderDao();
		List<Order> orders = od.getAllNotPaidWhichHasDevice(d.getId());
		for(Order order : orders)
		{
			od.updateOrder(order, false);
		}
	}
	
	
	//����� ��� ��������� �볺���
	
	public static void updateClient(Client c, JTextField NameField, JTextField PhoneField,
			JTextField ContactPIBField, JTextField CodeERPOUField, JTextField CodeTaxpayerField) throws SQLException 
	{
		ClientDao cd = new ClientDao();
		c.setName(NameField.getText());
		c.setPhone(PhoneField.getText());
		c.setContactPIB(ContactPIBField.getText());
		c.setCodeERPOU(CodeERPOUField.getText());
		c.setCodeTaxpayer(CodeTaxpayerField.getText());
		try {
			cd.updateClient(c);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		OrderDao od = new OrderDao();
		List<Order> orders = od.getAllFromClient(c.getId());
		int i = 1;
		for(Order order : orders)
		{
			order.setOrderName("���������� �볺��� " + c.getName() + " �" + i);
			od.updateOrder(order, false);
			i++;
		}
	}
	
	
	//����� ��� ��������� ���������� �� ������
	
	public static void updateOrder(Order o, JCheckBox ShippedCheckBox) 
	{
		OrderDao od = new OrderDao();
		o.setShipped(ShippedCheckBox.isSelected());
		try {
			od.updateOrder(o,true);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	
	//����� ��� �������� ���������� �� ������ �� ����������
	
	public static void makeOrderPaid(Order o) 
	{
		OrderDao od = new OrderDao();
		o.setPaid(true);
		String date = getCurrentDate();
		o.setEndDate(Date.valueOf(date));
		try {
			od.makeOrderPaid(o,true);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	
	//����� ��� ��������� �������������
	
	public static void updateProvider(Provider p, JTextField NameField, JTextField PhoneField,
			JTextField ContactPIBField, JTextField CodeERPOUField, JTextField CodeTaxpayerField,
			JTextField SpecializationField) throws SQLException 
	{
		ProviderDao pd = new ProviderDao();
		p.setName(NameField.getText());
		p.setPhone(PhoneField.getText());
		p.setContactPIB(ContactPIBField.getText());
		p.setCodeERPOU(CodeERPOUField.getText());
		p.setCodeTaxpayer(CodeTaxpayerField.getText());
		p.setSpecialization(SpecializationField.getText());
		try {
			pd.updateProvider(p);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		DeliveryDao dd = new DeliveryDao();
		List<Delivery> deliveries = dd.getAllFromProvider(p.getId());
		int i = 1;
		for(Delivery delivery : deliveries)
		{
			delivery.setDeliveryName("���������� ������������� " + p.getName() + " �" + i);
			dd.updateDelivery(delivery, false);
			i++;
		}
		
	}
	
	
	//����� ��� ��������� ���������� ����������
	
	public static void updateDelivery(Delivery d, JCheckBox ShippedCheckBox) 
	{
		DeliveryDao dd = new DeliveryDao();
		d.setShipped(ShippedCheckBox.isSelected());
		try {
			dd.updateDelivery(d, true);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	
	//����� ��� �������� ���������� �� ���������� �� ����������
	
	public static void makeDeliveryPaid(Delivery d) 
	{
		DeliveryDao dd = new DeliveryDao();
		d.setPaid(true);
		String date = getCurrentDate();
		d.setEndDate(Date.valueOf(date));
		try {
			dd.makeDeliveryPaid(d,true);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	
	//����� ��� ��������� �����������
	
	public static void updateUser(User u, JTextField SurnameField, JTextField NameField, JTextField LoginField,
			JTextField PasswordField, JTextField RoleField) 
	{
		UserDao ud = new UserDao();
		u.setSurname(SurnameField.getText());
		u.setName(NameField.getText());
		u.setLogin(LoginField.getText());
		u.setPassword(PasswordField.getText());
		u.setRole(RoleField.getText());
		try {
			ud.updateUser(u);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	
	
	
	
	//������ ��� ������ � ��������� � ��������Ͳ
	
	//����� ��� ��������� ������� �� ����������
	
	public static void addDevicesInOrder(int order_id, int device_id, JTextField NumberAddField) throws SQLException
	{
		OrderDao od = new OrderDao();
		OrderDevice record = new OrderDevice();
		record.setOrderId(order_id);
		record.setDeviceId(device_id);
		record.setNumber(Integer.valueOf(NumberAddField.getText()));
		try {
			od.addDeviceInOrder(record);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		Order o = od.readOrder(order_id);
		od.updateOrder(o, false);
	}
	
	
	//����� ��� ��������� ������� � ����������
	
	public static void deleteDevicesFromOrder(int order_id, int device_id) throws SQLException
	{
		OrderDao od = new OrderDao();
		OrderDevice record = null;
		try {
			record = od.readDeviceInOrder(order_id, device_id);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			od.deleteDeviceFromOrder(record);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		Order o = od.readOrder(order_id);
		od.updateOrder(o, false);
	}
	
	
	//����� ��� ����������� ������� ������� � ���������
	
	public static void updateDevicesInOrder(int order_id, int device_id, JTextField NumberEditField) throws SQLException
	{
		OrderDao od = new OrderDao();
		OrderDevice record = null;
		try {
			record = od.readDeviceInOrder(order_id, device_id);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		record.setNumber(Integer.valueOf(NumberEditField.getText()));
		try {
			od.updateDeviceInOrder(record);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		Order o = od.readOrder(order_id);
		od.updateOrder(o, false);
	}
	
	
	
	

	
	//����� ��� ��������� ��� ��������� ������� �볺���
	
	public static List<Order> getAllOrdersInClient(int client_id, List<Order> OrdersInClient, JComboBox<String> OrderInClientComboBox)
	{
		ClientDao cd = new ClientDao();
		OrderInClientComboBox.removeAllItems();
		try {
			OrdersInClient = cd.getAllOrdersInClient(client_id);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		for(Order order : OrdersInClient)
		{
			OrderInClientComboBox.addItem(order.getOrderName());
		}
		return OrdersInClient;
	}
	
	
	
	//����� ��� ��������� ���������� ��� �� ������� � ���������
	
	public static void getDeviceInfoFromOrder(int order_id, List<OrderDevice> DevicesInfoInOrder, JComboBox<String> DeviceInOrderComboBox)
	{
		DeviceInOrderComboBox.removeAllItems();
		OrderDeviceDao odd = new OrderDeviceDao();
		try {
			DevicesInfoInOrder = odd.getAllFromOrder(order_id);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		for(OrderDevice order_device : DevicesInfoInOrder)
		{
			String device_name = null;
			try {
				device_name = odd.getDeviceNameById(order_id, order_device.getDeviceId());
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			String device_supply_voltage = null;
			try {
				device_supply_voltage = odd.getSupplyVoltageById(order_id, order_device.getDeviceId());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			String border_regulation_time = null;
			try {
				border_regulation_time = odd.getBorderRegulationTimeById(order_id, order_device.getDeviceId());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			String info = "�����: " + device_name + 
					"  ������� ��������: " + device_supply_voltage +
					"  ������� ����������� ����: " + border_regulation_time +
					"  ʳ������: " + order_device.getNumber();
			DeviceInOrderComboBox.addItem(info);
		}
	}
	
	
	
	
	
	
	
	
	// ������ ��� ������ � ������������ � ��������Ͳ ����������

	// ����� ��� ��������� ���������� �� ����������

	public static void addComponentsInDelivery(int delivery_id, int component_id, JTextField NumberAddField) throws SQLException {
		DeliveryDao dd = new DeliveryDao();
		DeliveryComponent record = new DeliveryComponent();
		record.setDeliveryId(delivery_id);
		record.setComponentId(component_id);
		record.setNumber(Integer.valueOf(NumberAddField.getText()));
		try {
			dd.addComponentInDelivery(record);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		Delivery d = dd.readDelivery(delivery_id);
		dd.updateDelivery(d, false);
	}
		
		
	// ����� ��� ��������� ���������� � ����������

	public static void deleteComponentsFromDelivery(int delivery_id, int component_id) throws SQLException {
		DeliveryDao dd = new DeliveryDao();
		DeliveryComponent record = null;
		try {
			record = dd.readComponentInDelivery(delivery_id, component_id);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			dd.deleteComponentFromDelivery(record);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		Delivery d = dd.readDelivery(delivery_id);
		dd.updateDelivery(d, false);
	}

	// ����� ��� ����������� ������� ���������� � ���������

	public static void updateComponentsInDelivery(int delivery_id, int component_id, JTextField NumberEditField) throws SQLException {
		DeliveryDao dd = new DeliveryDao();
		DeliveryComponent record = null;
		try {
			record = dd.readComponentInDelivery(delivery_id, component_id);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		record.setNumber(Integer.valueOf(NumberEditField.getText()));
		try {
			dd.updateComponentInDelivery(record);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		Delivery d = dd.readDelivery(delivery_id);
		dd.updateDelivery(d, false);
	}

	// ����� ��� ��������� ��� ��������� ������� �������������

	public static List<Delivery> getAllDeliveriesInProvider(int provider_id, List<Delivery> DeliveriesInProvider,
			JComboBox<String> DeliveryInProviderComboBox) {
		ProviderDao pd = new ProviderDao();
		DeliveryInProviderComboBox.removeAllItems();
		try {
			DeliveriesInProvider = pd.getAllDeliveriesInProvider(provider_id);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		for (Delivery delivery : DeliveriesInProvider) {
			DeliveryInProviderComboBox.addItem(delivery.getDeliveryName());
		}
		return DeliveriesInProvider;
	}

		
		
		//����� ��� ��������� ���������� ��� �� ���������� � ���������
		
	public static void getComponentInfoFromDelivery(int delivery_id, List<DeliveryComponent> ComponentsInfoInDelivery,
			JComboBox<String> ComponentInDeliveryComboBox) {
		ComponentInDeliveryComboBox.removeAllItems();
		DeliveryComponentDao dcd = new DeliveryComponentDao();

		try {
			ComponentsInfoInDelivery = dcd.getAllFromDelivery(delivery_id);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		for (DeliveryComponent delivery_component : ComponentsInfoInDelivery) {
			String component_name = null;
			try {
				component_name = dcd.getComponentNameById(delivery_id, delivery_component.getComponentId());
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			String component_type = null;
			try {
				component_type = dcd.getComponentTypeById(delivery_id, delivery_component.getComponentId());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			String info = "�����: " + component_name + "  ���: " + component_type + "  ʳ������: "
					+ delivery_component.getNumber();
			ComponentInDeliveryComboBox.addItem(info);
		}
	}
	
	
	
	
	//����� ������ � JComboBox ��� ������������, �� �� ������������
	
	public static JComboBox<String> specializationInProvider(Provider p) throws SQLException
	{
		ComponentDao cd = new ComponentDao();
		JComboBox<String> in = new JComboBox<String>();
		JComboBox<String> notin = new JComboBox<String>();
		List<Specialization> specializationList = cd.getAllSpecializations();
		String sp = p.getSpecialization();
		String word = "";
		String word1 = "";
		for(Specialization specialization : specializationList)
		{
			word = specialization.getSpecialization();
			word1 = word + ", ";
			if(sp.toLowerCase().contains(word1.toLowerCase()))
			{
				in.addItem(specialization.getSpecialization());
			}
			else
			{
				notin.addItem(specialization.getSpecialization());
			}
		}
		return in;
	}
	
	
	//����� ������ � JComboBox ��� ������������, �� �� �� ������������
	
	public static JComboBox<String> specializationNotInProvider(Provider p) throws SQLException
	{
		ComponentDao cd = new ComponentDao();
		JComboBox<String> in = new JComboBox<String>();
		JComboBox<String> notin = new JComboBox<String>();
		List<Specialization> specializationList = cd.getAllSpecializations();
		String sp = p.getSpecialization();
		String word = "";
		String word1 = "";
		for(Specialization specialization : specializationList)
		{
			word = specialization.getSpecialization();
			word1 = word + ", ";
			if(sp.toLowerCase().contains(word1.toLowerCase()))
			{
				in.addItem(specialization.getSpecialization());
			}
			else
			{
				notin.addItem(specialization.getSpecialization());
			}
		}
		return notin;
	}
	
	
	
	
	
	
	
	//������ ��� ��������� ����Ҳ�
	
	//����� ��� ��������� ��� �������,�� ������ �� � ����������, �� ������ �������
	
	public static void getAllDeviceWhichHasAllComponentsWhichHasChosenDevice(int device_id, List<Device> devicesAllInChoosen,
			JComboBox<String> DeviceAllInChoosenComboBox) 
	{
		DeviceDao dd = new DeviceDao();
		DeviceAllInChoosenComboBox.removeAllItems();
		try {
			devicesAllInChoosen = dd.getAllDeviceWhichHasAllComponentsWhichHasChosenDevice(device_id);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		for(Device device : devicesAllInChoosen)
		{
			DeviceAllInChoosenComboBox.addItem(device.getName());
		}
	}
	
	
	//����� ��� ��������� ��� �������,�� ������ �� � ����������, �� �� ������ �������
	
	public static void getAllDeviceWhichHasAllComponentsWhichHasNotChosenDevice(int device_id, List<Device> devicesAllNotInChoosen,
			JComboBox<String> DeviceAllNotInChoosenComboBox) 
	{
		DeviceDao dd = new DeviceDao();
		DeviceAllNotInChoosenComboBox.removeAllItems();
		try {
			devicesAllNotInChoosen = dd.getAllDeviceWhichHasAllComponentsWhichHasNotChosenDevice(device_id);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		for(Device device : devicesAllNotInChoosen)
		{
			DeviceAllNotInChoosenComboBox.addItem(device.getName());
		}
	}
	
	
	
	//����� ��� ��������� ��� �������,�� ������ ����� ���� ���������, �� ������ �������
	
	public static void getAllDeviceWhichHasAtLeastOneComponentWhichHasChosenDevice(int device_id,
			List<Device> devicesOneInChoosen, JComboBox<String> DeviceOneInChoosenComboBox) 
	{
		DeviceDao dd = new DeviceDao();
		DeviceOneInChoosenComboBox.removeAllItems();
		try {
			devicesOneInChoosen = dd.getAllDeviceWhichHasAtLeastOneComponentWhichHasChosenDevice(device_id);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		for(Device device : devicesOneInChoosen)
		{
			DeviceOneInChoosenComboBox.addItem(device.getName());
		}
	}
	
	
	
	//����� ��� ��������� ��� �������,�� ������ �� � ����� � ����������, �� ������ �������
	
	public static void getAllDeviceWhichHasOnlyAllComponentsWhichHasChosenDevice(int device_id, List<Device> devicesOnlyAllInChoosen,
			JComboBox<String> DeviceOnlyAllInChoosenComboBox) 
	{
		DeviceDao dd = new DeviceDao();
		DeviceOnlyAllInChoosenComboBox.removeAllItems();
		try {
			devicesOnlyAllInChoosen = dd.getAllDeviceWhichHasOnlyAllComponentsWhichHasChosenDevice(device_id);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		for(Device device : devicesOnlyAllInChoosen)
		{
			DeviceOnlyAllInChoosenComboBox.addItem(device.getName());
		}
	}
	
	
}
