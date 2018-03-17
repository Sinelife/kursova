package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import domain.Component;
import domain.Delivery;
import domain.Provider;
import main.Main;

public class ProviderDao
{
	
	public void addProvider(Provider p) throws SQLException 
	{
		String sql = "INSERT INTO provider (provider_id, name, phone, contactPIB, codeERPOU, codeTaxpayer, specialization) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement stm = Main.conn.prepareStatement(sql);
		int i = -1;
		String sql_for_id = "SELECT MAX(provider_id) from provider";
		PreparedStatement statement_for_id = Main.conn.prepareStatement(sql_for_id);
		statement_for_id.executeQuery();
		ResultSet result = statement_for_id.getResultSet();
		if ((result != null) && (result.next())) {
			i = result.getInt(1);
		}
		stm.setInt(1, i + 1);
		stm.setString(2, p.getName());
		stm.setString(3, p.getPhone());
		stm.setString(4, p.getContactPIB());
		stm.setString(5, p.getCodeERPOU());
		stm.setString(6, p.getCodeTaxpayer());
		stm.setString(7, p.getSpecialization());
		stm.executeUpdate();
		JOptionPane.showMessageDialog(null, "������ ������������� ������ �� ���� ������!");
	}
	
	

	/**
	 * @throws SQLException
	 */
	public Provider readProvider(int key) throws SQLException 
	{
		String sql = "SELECT * FROM provider WHERE provider_id = ?";
		Provider p = new Provider();
		try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
		{
			stm.setInt(1, key);
			ResultSet rs = stm.executeQuery();
			rs.next();
			p.setId(rs.getInt("provider_id"));
			p.setName(rs.getString("name"));
			p.setPhone(rs.getString("phone"));
			p.setContactPIB(rs.getString("contactPIB"));
			p.setCodeERPOU(rs.getString("codeERPOU"));
			p.setCodeTaxpayer(rs.getString("codeTaxpayer"));
			p.setSpecialization(rs.getString("specialization"));
		}
		return p;
	}

	
	
	/**
	 * @throws SQLException
	 */
	public void updateProvider(Provider p) throws SQLException {
		String sql = "update Provider set name = ?, phone = ?, contactPIB = ?, codeERPOU = ?, codeTaxpayer = ?, specialization = ? where Provider_id = "
				+ p.getId();
		PreparedStatement stm = Main.conn.prepareStatement(sql);
		stm.setString(1, p.getName());
		stm.setString(2, p.getPhone());
		stm.setString(3, p.getContactPIB());
		stm.setString(4, p.getCodeERPOU());
		stm.setString(5, p.getCodeTaxpayer());
		stm.setString(6, p.getSpecialization());
		stm.executeUpdate();
		JOptionPane.showMessageDialog(null, "���������� ��� ������������� ������������!");
	}

	
	
	
	
	
	/**
	 * @throws SQLException
	 */
	public void delete(Provider d) throws SQLException {

	}

	
	
	
	
	
	public List<Provider> getAll() throws SQLException 
	{
		String sql = "SELECT * FROM provider;";
		List<Provider> list = new ArrayList<Provider>();
		try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
		{
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Provider p = new Provider();
				p.setId(rs.getInt("provider_id"));
				p.setName(rs.getString("name"));
				p.setPhone(rs.getString("phone"));
				p.setContactPIB(rs.getString("contactPIB"));
				p.setCodeERPOU(rs.getString("codeERPOU"));
				p.setCodeTaxpayer(rs.getString("codeTaxpayer"));
				p.setSpecialization(rs.getString("specialization"));
				list.add(p);
			}
		}
		return list;
	}
	
	
    public List<Component> getAllComponentsInProvider(int provider_id) throws SQLException
    {
    	String sql = "SELECT * FROM component where component_id in " + 
    			"(select component_id from delivery_component where delivery_id in " + 
    			"(select delivery_id from delivery where provider_id in " + 
    			"(select provider_id from provider where provider_id = " + provider_id + ")))";
        List<Component> list = new ArrayList<Component>();
        try (PreparedStatement stm = Main.conn.prepareStatement(sql))
        {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) 
            {
            	Component c = new Component();
				c.setId(rs.getInt("component_id"));
                c.setType(rs.getString("type"));
                c.setName(rs.getString("name"));
                c.setTechnicalInfo(rs.getString("technical_info"));
                list.add(c);
            }
        }
        return list;
    }
    
    public List<Delivery> getAllDeliveriesInProvider(int provider_id) throws SQLException
    {
    	String sql = "SELECT * FROM delivery where provider_id in "
    			+ "(select provider_id from provider where provider_id = " + provider_id + ")";
        List<Delivery> list = new ArrayList<Delivery>();
        try (PreparedStatement stm = Main.conn.prepareStatement(sql))
        {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) 
            {
            	Delivery d = new Delivery();
            	d.setId(rs.getInt("delivery_id"));
				d.setDeliveryName(rs.getString("delivery_name"));
				d.setProviderId(rs.getInt("provider_id"));
				d.setStartDate(rs.getDate("startdate"));
				d.setPaid(rs.getBoolean("paid"));
                list.add(d);
            }
        }
        return list;
    }
    
    
    
    public int getOrderNumber() throws SQLException
    {
    	String sql = "SELECT COUNT(*) FROM delivery";
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
    	String sql = "SELECT COUNT(*) FROM delivery WHERE paid = 1";
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
    	String sql = "SELECT COUNT(*) FROM delivery WHERE shipped = 1";
    	PreparedStatement stm = Main.conn.prepareStatement(sql);
    	ResultSet rs = stm.executeQuery(sql);
    	int result = 0;
 	  	while(rs.next())
 	  	{
 	  		result = rs.getInt("COUNT(*)");
 	  	}
        return result;
    }
    
    public int getOrderNumberOfProvider(int provider_id) throws SQLException
    {
    	String sql = "SELECT COUNT(*) FROM delivery WHERE provider_id = " + provider_id;
    	PreparedStatement stm = Main.conn.prepareStatement(sql);
    	ResultSet rs = stm.executeQuery(sql);
    	int result = 0;
 	  	while(rs.next())
 	  	{
 	  		result = rs.getInt("COUNT(*)");
 	  	}
        return result;
    }
    
    public int getPaidOrderNumberOfProvider(int provider_id) throws SQLException
    {
    	String sql = "SELECT COUNT(*) FROM delivery WHERE paid = 1 and provider_id = " + provider_id;
    	PreparedStatement stm = Main.conn.prepareStatement(sql);
    	ResultSet rs = stm.executeQuery(sql);
    	int result = 0;
 	  	while(rs.next())
 	  	{
 	  		result = rs.getInt("COUNT(*)");
 	  	}
        return result;
    }
    
    public int getShippedOrderNumberOfProvider(int provider_id) throws SQLException
    {
    	String sql = "SELECT COUNT(*) FROM delivery WHERE shipped = 1 and provider_id = " + provider_id;
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
    	String sql = "select sum(price * delivery_component.number) " + 
    				"from component,delivery_component,delivery,provider " + 
    				"where component.component_id = delivery_component.component_id " + 
    				"and delivery_component.delivery_id = delivery.delivery_id " + 
    				"and delivery.provider_id = provider.provider_id";
    	PreparedStatement stm = Main.conn.prepareStatement(sql);
    	ResultSet rs = stm.executeQuery(sql);
    	int result = 0;
 	  	while(rs.next())
 	  	{
 	  		result = rs.getInt("sum(price * delivery_component.number)");
 	  	}
        return result;
    }
      
    public int getPaidMoney() throws SQLException
    {
    	String sql = "select sum(price * delivery_component.number) " + 
    				"from component,delivery_component,delivery,provider " + 
    				"where component.component_id = delivery_component.component_id " + 
    				"and delivery_component.delivery_id = delivery.delivery_id and delivery.paid = 1 " + 
    				"and delivery.provider_id = provider.provider_id";
    	PreparedStatement stm = Main.conn.prepareStatement(sql);
    	ResultSet rs = stm.executeQuery(sql);
    	int result = 0;
 	  	while(rs.next())
 	  	{
 	  		result = rs.getInt("sum(price * delivery_component.number)");
 	  	}
        return result;
    }
    
    public int getShippedMoney() throws SQLException
    {
    	String sql = "select sum(price * delivery_component.number) " + 
    				"from component,delivery_component,delivery,provider " + 
    				"where component.component_id = delivery_component.component_id " + 
    				"and delivery_component.delivery_id = delivery.delivery_id and delivery.shipped = 1 " + 
    				"and delivery.provider_id = provider.provider_id";
    	PreparedStatement stm = Main.conn.prepareStatement(sql);
    	ResultSet rs = stm.executeQuery(sql);
    	int result = 0;
 	  	while(rs.next())
 	  	{
 	  		result = rs.getInt("sum(price * delivery_component.number)");
 	  	}
        return result;
    }
    
    public int getAllMoneyOfProvider(int provider_id) throws SQLException
    {
    	String sql = "select sum(price * delivery_component.number) " + 
    				"from component,delivery_component,delivery,provider " + 
    				"where component.component_id = delivery_component.component_id " + 
    				"and delivery_component.delivery_id = delivery.delivery_id " + 
    				"and delivery.provider_id = provider.provider_id " + 
    				"and provider.provider_id = " + provider_id;
    	PreparedStatement stm = Main.conn.prepareStatement(sql);
    	ResultSet rs = stm.executeQuery(sql);
    	int result = 0;
 	  	while(rs.next())
 	  	{
 	  		result = rs.getInt("sum(price * delivery_component.number)");
 	  	}
        return result;
    }
      
    public int getPaidMoneyOfProvider(int provider_id) throws SQLException
    {
    	String sql = "select sum(price * delivery_component.number) " + 
    				"from component,delivery_component,delivery,provider " + 
    				"where component.component_id = delivery_component.component_id " + 
    				"and delivery_component.delivery_id = delivery.delivery_id and delivery.paid = 1 " + 
    				"and delivery.provider_id = provider.provider_id " + 
    				"and provider.provider_id = " + provider_id;
    	PreparedStatement stm = Main.conn.prepareStatement(sql);
    	ResultSet rs = stm.executeQuery(sql);
    	int result = 0;
 	  	while(rs.next())
 	  	{
 	  		result = rs.getInt("sum(price * delivery_component.number)");
 	  	}
        return result;
    }
    
    public int getShippedMoneyOfProvider(int provider_id) throws SQLException
    {
    	String sql = "select sum(price * delivery_component.number) " + 
    				"from component,delivery_component,delivery,provider " + 
    				"where component.component_id = delivery_component.component_id " + 
    				"and delivery_component.delivery_id = delivery.delivery_id and delivery.shipped = 1 " + 
    				"and delivery.provider_id = provider.provider_id " + 
    				"and provider.provider_id = " + provider_id;
    	PreparedStatement stm = Main.conn.prepareStatement(sql);
    	ResultSet rs = stm.executeQuery(sql);
    	int result = 0;
 	  	while(rs.next())
 	  	{
 	  		result = rs.getInt("sum(price * delivery_component.number)");
 	  	}
        return result;
    }
    
    
    
    
    
    public int getComponentNumber() throws SQLException
    {
    	String sql = "select sum(delivery_component.number) " + 
    				"from delivery_component,delivery,provider " + 
    				"where delivery_component.delivery_id = delivery.delivery_id " + 
    				"and delivery.provider_id = provider.provider_id";
    	PreparedStatement stm = Main.conn.prepareStatement(sql);
    	ResultSet rs = stm.executeQuery(sql);
    	int result = 0;
 	  	while(rs.next())
 	  	{
 	  		result = rs.getInt("sum(delivery_component.number)");
 	  	}
        return result;
    }
    
    public int getPaidComponentNumber() throws SQLException
    {
    	String sql = "select sum(delivery_component.number) " + 
    				"from delivery_component,delivery,provider " + 
    				"where delivery_component.delivery_id = delivery.delivery_id " + 
    				"and delivery.provider_id = provider.provider_id and delivery.paid = 1";
    	PreparedStatement stm = Main.conn.prepareStatement(sql);
    	ResultSet rs = stm.executeQuery(sql);
    	int result = 0;
 	  	while(rs.next())
 	  	{
 	  		result = rs.getInt("sum(delivery_component.number)");
 	  	}
        return result;
    }
    
    public int getShippedComponentNumber() throws SQLException
    {
    	String sql = "select sum(delivery_component.number) " + 
    				"from delivery_component,delivery,provider " + 
    				"where delivery_component.delivery_id = delivery.delivery_id " + 
    				"and delivery.provider_id = provider.provider_id and delivery.shipped = 1";
    	PreparedStatement stm = Main.conn.prepareStatement(sql);
    	ResultSet rs = stm.executeQuery(sql);
    	int result = 0;
 	  	while(rs.next())
 	  	{
 	  		result = rs.getInt("sum(delivery_component.number)");
 	  	}
        return result;
    }
    
    public int getComponentNumberOfProvider(int provider_id) throws SQLException
    {
    	String sql = "select sum(delivery_component.number) " + 
    				"from delivery_component,delivery,provider " + 
    				"where delivery_component.delivery_id = delivery.delivery_id " + 
    				"and delivery.provider_id = provider.provider_id " + 
    				"and provider.provider_id = " + provider_id;
    	PreparedStatement stm = Main.conn.prepareStatement(sql);
    	ResultSet rs = stm.executeQuery(sql);
    	int result = 0;
 	  	while(rs.next())
 	  	{
 	  		result = rs.getInt("sum(delivery_component.number)");
 	  	}
        return result;
    }
    
    public int getPaidComponentNumberOfProvider(int provider_id) throws SQLException
    {
    	String sql = "select sum(delivery_component.number) " + 
    				"from delivery_component,delivery,provider " + 
    				"where delivery_component.delivery_id = delivery.delivery_id " + 
    				"and delivery.provider_id = provider.provider_id and delivery.paid = 1 " + 
    				"and provider.provider_id = " + provider_id;
    	PreparedStatement stm = Main.conn.prepareStatement(sql);
    	ResultSet rs = stm.executeQuery(sql);
    	int result = 0;
 	  	while(rs.next())
 	  	{
 	  		result = rs.getInt("sum(delivery_component.number)");
 	  	}
        return result;
    }
    
    public int getShippedComponentNumberOfProvider(int provider_id) throws SQLException
    {
    	String sql = "select sum(delivery_component.number) " + 
    				"from delivery_component,delivery,provider " + 
    				"where delivery_component.delivery_id = delivery.delivery_id " + 
    				"and delivery.provider_id = provider.provider_id and delivery.shipped = 1 " + 
    				"and provider.provider_id = " + provider_id;
    	PreparedStatement stm = Main.conn.prepareStatement(sql);
    	ResultSet rs = stm.executeQuery(sql);
    	int result = 0;
 	  	while(rs.next())
 	  	{
 	  		result = rs.getInt("sum(delivery_component.number)");
 	  	}
        return result;
    }
}
