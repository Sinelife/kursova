package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import domain.Client;
import domain.Device;
import domain.Order;
import main.Main;

public class ClientDao
{
	 public void addClient(Client c) throws SQLException 
	    {
			String sql = "INSERT INTO client (client_id, name, phone, contactPIB, codeERPOU, codeTaxpayer) VALUES (?,?,?,?,?,?)";
	 	  	PreparedStatement stm = Main.conn.prepareStatement(sql);
			int i = -1;
			String sql_for_id = "SELECT MAX(client_id) from client";
			PreparedStatement statement_for_id = Main.conn.prepareStatement(sql_for_id);
			statement_for_id.executeQuery();
			ResultSet result = statement_for_id.getResultSet();
			if((result!=null) && (result.next()))
			{
				i = result.getInt(1);
			}
			stm.setInt(1,i+1);
	    	stm.setString(2, c.getName());
	    	stm.setString(3, c.getPhone());
	    	stm.setString(4, c.getContactPIB());
	    	stm.setString(5, c.getCodeERPOU());
	    	stm.setString(6, c.getCodeTaxpayer());
	    	stm.executeUpdate();
	    	JOptionPane.showMessageDialog (null, "Нового кліента додано до бази данних!" );
		}
	    
	    

	    /** @throws SQLException */
	    public Client readClient(int key) throws SQLException 
	    {
	        String sql = "SELECT * FROM client WHERE client_id = ?";
	        Client c = new Client();
	        try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
	        {
	            stm.setInt(1, key);
	            ResultSet rs = stm.executeQuery();
	            rs.next();
	            c.setId(rs.getInt("client_id"));
	            c.setName(rs.getString("name"));
	            c.setPhone(rs.getString("phone"));
	            c.setContactPIB(rs.getString("contactPIB"));
	            c.setCodeERPOU(rs.getString("codeERPOU"));
	            c.setCodeTaxpayer(rs.getString("codeTaxpayer"));
	        }
	        return c;
		}
	    

	    
	    /**@throws SQLException */
	    public void updateClient(Client c) throws SQLException 
	    {
	    	String sql = "update client set name = ?, phone = ?, contactPIB = ?, codeERPOU = ?, codeTaxpayer = ? where client_id = " +  c.getId();
	    	PreparedStatement stm = Main.conn.prepareStatement(sql);
	    	stm.setString(1, c.getName());
	    	stm.setString(2, c.getPhone());
	    	stm.setString(3, c.getContactPIB());
	    	stm.setString(4, c.getCodeERPOU());
	    	stm.setString(5, c.getCodeTaxpayer());
	    	stm.executeUpdate();
	    	JOptionPane.showMessageDialog (null, "Інформація про клієнта відредагована!" ); 
		}

	    
	    
	    
	    /** @throws SQLException */ 
	    public void delete(Client d) throws SQLException 
	    {

		}

	    
	    
	   
	    public List<Client> getAll() throws SQLException 
	    {
	        String sql = "SELECT * FROM client;";
	        List<Client> list = new ArrayList<Client>();
	        try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
	        {
	            ResultSet rs = stm.executeQuery();
	            while (rs.next()) 
	            {
	                Client c = new Client();
		            c.setId(rs.getInt("client_id"));
		            c.setName(rs.getString("name"));
		            c.setPhone(rs.getString("phone"));
		            c.setContactPIB(rs.getString("contactPIB"));
		            c.setCodeERPOU(rs.getString("codeERPOU"));
		            c.setCodeTaxpayer(rs.getString("codeTaxpayer"));
	                list.add(c);
	            }
	        }
	        return list;
	    }
	    
	    
	    public List<Device> getAllDevicesInClient(int client_id) throws SQLException
	    {
	    	String sql = "SELECT * FROM device where device_id in " + 
	    			"(select device_id from order_device where order_id in " + 
	    			"(select order_id from orders where client_id in " + 
	    			"(select client_id from client where client_id = " + client_id + ")))";
	        List<Device> list = new ArrayList<Device>();
	        try (PreparedStatement stm = Main.conn.prepareStatement(sql))
	        {
	            ResultSet rs = stm.executeQuery();
	            while (rs.next()) 
	            {
	                Device d = new Device();
	                d.setId(rs.getInt("device_id"));
	                d.setName(rs.getString("name"));
	                d.setSupplyVoltage(rs.getString("supply_voltage"));
	                d.setBorderRegulationTime(rs.getString("border_regulation_time"));
	                d.setRating(rs.getInt("rating"));
	                d.setDate(rs.getDate("date"));
	                list.add(d);
	            }
	        }
	        return list;
	    }
	    
	    public List<Order> getAllOrdersInClient(int client_id) throws SQLException
	    {
	    	String sql = "SELECT * FROM orders where client_id in "
	    			+ "(select client_id from client where client_id = " + client_id + ")";
	        List<Order> list = new ArrayList<Order>();
	        try (PreparedStatement stm = Main.conn.prepareStatement(sql))
	        {
	            ResultSet rs = stm.executeQuery();
	            while (rs.next()) 
	            {
	            	Order o = new Order();
	            	o.setId(rs.getInt("order_id"));
					o.setOrderName(rs.getString("order_name"));
					o.setClientId(rs.getInt("client_id"));
					o.setStartDate(rs.getDate("startdate"));
					o.setPaid(rs.getBoolean("paid"));
	                list.add(o);
	            }
	        }
	        return list;
	    }
	    
	    
	    
	    
	    
	    
	    
	    public int getOrderNumber() throws SQLException
	    {
	    	String sql = "SELECT COUNT(*) FROM orders";
	    	PreparedStatement stm = Main.conn.prepareStatement(sql);
	    	ResultSet rs = stm.executeQuery(sql);
	    	int result = 0;
	 	  	while(rs.next())
	 	  	{
	 	  		result = rs.getInt("COUNT(*)");
	 	  	}
	        return result;
	    }
	    
	    public int getPaidOrderNumber() throws SQLException
	    {
	    	String sql = "SELECT COUNT(*) FROM orders WHERE paid = 1";
	    	PreparedStatement stm = Main.conn.prepareStatement(sql);
	    	ResultSet rs = stm.executeQuery(sql);
	    	int result = 0;
	 	  	while(rs.next())
	 	  	{
	 	  		result = rs.getInt("COUNT(*)");
	 	  	}
	        return result;
	    }
	    
	    public int getShippedOrderNumber() throws SQLException
	    {
	    	String sql = "SELECT COUNT(*) FROM orders WHERE shipped = 1";
	    	PreparedStatement stm = Main.conn.prepareStatement(sql);
	    	ResultSet rs = stm.executeQuery(sql);
	    	int result = 0;
	 	  	while(rs.next())
	 	  	{
	 	  		result = rs.getInt("COUNT(*)");
	 	  	}
	        return result;
	    }
	    
	    public int getOrderNumberOfClient(int client_id) throws SQLException
	    {
	    	String sql = "SELECT COUNT(*) FROM orders WHERE client_id = " + client_id;
	    	PreparedStatement stm = Main.conn.prepareStatement(sql);
	    	ResultSet rs = stm.executeQuery(sql);
	    	int result = 0;
	 	  	while(rs.next())
	 	  	{
	 	  		result = rs.getInt("COUNT(*)");
	 	  	}
	        return result;
	    }
	    
	    public int getPaidOrderNumberOfClient(int client_id) throws SQLException
	    {
	    	String sql = "SELECT COUNT(*) FROM orders WHERE paid = 1 and client_id = " + client_id;
	    	PreparedStatement stm = Main.conn.prepareStatement(sql);
	    	ResultSet rs = stm.executeQuery(sql);
	    	int result = 0;
	 	  	while(rs.next())
	 	  	{
	 	  		result = rs.getInt("COUNT(*)");
	 	  	}
	        return result;
	    }
	    
	    public int getShippedOrderNumberOfClient(int client_id) throws SQLException
	    {
	    	String sql = "SELECT COUNT(*) FROM orders WHERE shipped = 1 and client_id = " + client_id;
	    	PreparedStatement stm = Main.conn.prepareStatement(sql);
	    	ResultSet rs = stm.executeQuery(sql);
	    	int result = 0;
	 	  	while(rs.next())
	 	  	{
	 	  		result = rs.getInt("COUNT(*)");
	 	  	}
	        return result;
	    }
	    
	    
	    
	    
	    
	    
	    public int getAllMoney() throws SQLException
	    {
	    	String sql = "select sum(price * order_device.number) " + 
	    				"from device,order_device,orders,client " + 
	    				"where device.device_id = order_device.device_id " + 
	    				"and order_device.order_id = orders.order_id " + 
	    				"and orders.client_id = client.client_id";
	    	PreparedStatement stm = Main.conn.prepareStatement(sql);
	    	ResultSet rs = stm.executeQuery(sql);
	    	int result = 0;
	 	  	while(rs.next())
	 	  	{
	 	  		result = rs.getInt("sum(price * order_device.number)");
	 	  	}
	        return result;
	    }
	      
	    public int getPaidMoney() throws SQLException
	    {
	    	String sql = "select sum(price * order_device.number) " + 
	    				"from device,order_device,orders,client " + 
	    				"where device.device_id = order_device.device_id " + 
	    				"and order_device.order_id = orders.order_id and orders.paid = 1 " + 
	    				"and orders.client_id = client.client_id";
	    	PreparedStatement stm = Main.conn.prepareStatement(sql);
	    	ResultSet rs = stm.executeQuery(sql);
	    	int result = 0;
	 	  	while(rs.next())
	 	  	{
	 	  		result = rs.getInt("sum(price * order_device.number)");
	 	  	}
	        return result;
	    }
	    
	    public int getShippedMoney() throws SQLException
	    {
	    	String sql = "select sum(price * order_device.number) " + 
	    				"from device,order_device,orders,client " + 
	    				"where device.device_id = order_device.device_id " + 
	    				"and order_device.order_id = orders.order_id and orders.shipped = 1 " + 
	    				"and orders.client_id = client.client_id";
	    	PreparedStatement stm = Main.conn.prepareStatement(sql);
	    	ResultSet rs = stm.executeQuery(sql);
	    	int result = 0;
	 	  	while(rs.next())
	 	  	{
	 	  		result = rs.getInt("sum(price * order_device.number)");
	 	  	}
	        return result;
	    }
	    
	    public int getAllMoneyOfClient(int client_id) throws SQLException
	    {
	    	String sql = "select sum(price * order_device.number) " + 
	    				"from device,order_device,orders,client " + 
	    				"where device.device_id = order_device.device_id " + 
	    				"and order_device.order_id = orders.order_id " + 
	    				"and orders.client_id = client.client_id " + 
	    				"and client.client_id = " + client_id;
	    	PreparedStatement stm = Main.conn.prepareStatement(sql);
	    	ResultSet rs = stm.executeQuery(sql);
	    	int result = 0;
	 	  	while(rs.next())
	 	  	{
	 	  		result = rs.getInt("sum(price * order_device.number)");
	 	  	}
	        return result;
	    }
	      
	    public int getPaidMoneyOfClient(int client_id) throws SQLException
	    {
	    	String sql = "select sum(price * order_device.number) " + 
	    				"from device,order_device,orders,client " + 
	    				"where device.device_id = order_device.device_id " + 
	    				"and order_device.order_id = orders.order_id and orders.paid = 1 " + 
	    				"and orders.client_id = client.client_id " + 
	    				"and client.client_id = " + client_id;
	    	PreparedStatement stm = Main.conn.prepareStatement(sql);
	    	ResultSet rs = stm.executeQuery(sql);
	    	int result = 0;
	 	  	while(rs.next())
	 	  	{
	 	  		result = rs.getInt("sum(price * order_device.number)");
	 	  	}
	        return result;
	    }
	    
	    public int getShippedMoneyOfClient(int client_id) throws SQLException
	    {
	    	String sql = "select sum(price * order_device.number) " + 
	    				"from device,order_device,orders,client " + 
	    				"where device.device_id = order_device.device_id " + 
	    				"and order_device.order_id = orders.order_id and orders.shipped = 1 " + 
	    				"and orders.client_id = client.client_id " + 
	    				"and client.client_id = " + client_id;
	    	PreparedStatement stm = Main.conn.prepareStatement(sql);
	    	ResultSet rs = stm.executeQuery(sql);
	    	int result = 0;
	 	  	while(rs.next())
	 	  	{
	 	  		result = rs.getInt("sum(price * order_device.number)");
	 	  	}
	        return result;
	    }
	    
	    
	    
	    
	    
	    public int getDeviceNumber() throws SQLException
	    {
	    	String sql = "select sum(order_device.number) " + 
	    				"from order_device,orders,client " + 
	    				"where order_device.order_id = orders.order_id " + 
	    				"and orders.client_id = client.client_id";
	    	PreparedStatement stm = Main.conn.prepareStatement(sql);
	    	ResultSet rs = stm.executeQuery(sql);
	    	int result = 0;
	 	  	while(rs.next())
	 	  	{
	 	  		result = rs.getInt("sum(order_device.number)");
	 	  	}
	        return result;
	    }
	    
	    public int getPaidDeviceNumber() throws SQLException
	    {
	    	String sql = "select sum(order_device.number) " + 
	    				"from order_device,orders,client " + 
	    				"where order_device.order_id = orders.order_id " + 
	    				"and orders.client_id = client.client_id and orders.paid = 1";
	    	PreparedStatement stm = Main.conn.prepareStatement(sql);
	    	ResultSet rs = stm.executeQuery(sql);
	    	int result = 0;
	 	  	while(rs.next())
	 	  	{
	 	  		result = rs.getInt("sum(order_device.number)");
	 	  	}
	        return result;
	    }
	    
	    public int getShippedDeviceNumber() throws SQLException
	    {
	    	String sql = "select sum(order_device.number) " + 
	    				"from order_device,orders,client " + 
	    				"where order_device.order_id = orders.order_id " + 
	    				"and orders.client_id = client.client_id and orders.shipped = 1";
	    	PreparedStatement stm = Main.conn.prepareStatement(sql);
	    	ResultSet rs = stm.executeQuery(sql);
	    	int result = 0;
	 	  	while(rs.next())
	 	  	{
	 	  		result = rs.getInt("sum(order_device.number)");
	 	  	}
	        return result;
	    }
	    
	    public int getDeviceNumberOfClient(int client_id) throws SQLException
	    {
	    	String sql = "select sum(order_device.number) " + 
	    				"from order_device,orders,client " + 
	    				"where order_device.order_id = orders.order_id " + 
	    				"and orders.client_id = client.client_id " + 
	    				"and client.client_id = " + client_id;
	    	PreparedStatement stm = Main.conn.prepareStatement(sql);
	    	ResultSet rs = stm.executeQuery(sql);
	    	int result = 0;
	 	  	while(rs.next())
	 	  	{
	 	  		result = rs.getInt("sum(order_device.number)");
	 	  	}
	        return result;
	    }
	    
	    public int getPaidDeviceNumberOfClient(int client_id) throws SQLException
	    {
	    	String sql = "select sum(order_device.number) " + 
	    				"from order_device,orders,client " + 
	    				"where order_device.order_id = orders.order_id " + 
	    				"and orders.client_id = client.client_id and orders.paid = 1 " + 
	    				"and client.client_id = " + client_id;
	    	PreparedStatement stm = Main.conn.prepareStatement(sql);
	    	ResultSet rs = stm.executeQuery(sql);
	    	int result = 0;
	 	  	while(rs.next())
	 	  	{
	 	  		result = rs.getInt("sum(order_device.number)");
	 	  	}
	        return result;
	    }
	    
	    public int getShippedDeviceNumberOfClient(int client_id) throws SQLException
	    {
	    	String sql = "select sum(order_device.number) " + 
	    				"from order_device,orders,client " + 
	    				"where order_device.order_id = orders.order_id " + 
	    				"and orders.client_id = client.client_id and orders.shipped = 1 " + 
	    				"and client.client_id = " + client_id;
	    	PreparedStatement stm = Main.conn.prepareStatement(sql);
	    	ResultSet rs = stm.executeQuery(sql);
	    	int result = 0;
	 	  	while(rs.next())
	 	  	{
	 	  		result = rs.getInt("sum(order_device.number)");
	 	  	}
	        return result;
	    }
	    
}
