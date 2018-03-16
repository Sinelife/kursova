package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import domain.Delivery;
import domain.DeliveryComponent;
import domain.Component;
import main.Main;

public class DeliveryDao 
{
	
	public void addDelivery(Delivery d) throws SQLException 
	{
		String sql = "INSERT INTO delivery (delivery_id, delivery_name, provider_id, startdate, paid) VALUES (?,?,?,?,?)";
		PreparedStatement stm = Main.conn.prepareStatement(sql);
		int i = -1;
		String sql_for_id = "SELECT MAX(delivery_id) from delivery";
		PreparedStatement statement_for_id = Main.conn.prepareStatement(sql_for_id);
		statement_for_id.executeQuery();
		ResultSet result = statement_for_id.getResultSet();
		if ((result != null) && (result.next())) {
			i = result.getInt(1);
		}
		stm.setInt(1, i + 1);
		stm.setString(2, d.getDeliveryName());
		stm.setInt(3, d.getProviderId());
		stm.setDate(4, d.getStartDate());
		stm.setBoolean(5, d.getPaid());
		stm.executeUpdate();
		JOptionPane.showMessageDialog(null, "Нове замовлення на постачання додано до бази данних!");
	}

	
	
	
	/**
	 * @throws SQLException
	 */
	public Delivery readDelivery(int key) throws SQLException 
	{
		String sql = "SELECT * FROM delivery WHERE delivery_id = ?";
		Delivery d = new Delivery();
		try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
		{
			stm.setInt(1, key);
			ResultSet rs = stm.executeQuery();
			rs.next();
			d.setId(rs.getInt("delivery_id"));
			d.setDeliveryName(rs.getString("delivery_name"));
			d.setProviderId(rs.getInt("provider_id"));
			d.setStartDate(rs.getDate("startdate"));
			d.setPaid(rs.getBoolean("paid"));
		}
		return d;
	}

	
	
	
	/**
	 * @throws SQLException
	 */
	public void updateDelivery(Delivery d) throws SQLException 
	{
		String sql = "update delivery set delivery_name = ?, provider_id = ?, startdate = ?, paid = ? where delivery_id = " + d.getId();
		PreparedStatement stm = Main.conn.prepareStatement(sql);
		stm.setString(1, d.getDeliveryName());
		stm.setInt(2, d.getProviderId());
		stm.setDate(3, d.getStartDate());
		stm.setBoolean(4, d.getPaid());
		stm.executeUpdate();
		JOptionPane.showMessageDialog(null, "Інформація про замовлення на постачання відредаговано!");
	}

	
	
	
	/**
	 * @throws SQLException
	 */
	public void delete(Delivery d) throws SQLException 
	{

	}
	
	
	

	public List<Delivery> getAll() throws SQLException 
	{
		String sql = "SELECT * FROM delivery;";
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
	
	
	public List<Delivery> getAllFromProvider(int provider_id) throws SQLException 
	{
		String sql = "SELECT * FROM delivery WHERE provider_id = " + provider_id;
		List<Delivery> list = new ArrayList<Delivery>();
		try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
		{
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
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
	
	
	public List<Component> getAllComponentsInDelivery(int delivery_id) throws SQLException 
	{
		String sql = "SELECT * FROM component WHERE component_id IN"
				+ "(SELECT component_id FROM delivery_component WHERE delivery_id = " + delivery_id + ")";
		List<Component> list = new ArrayList<Component>();
		try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
		{
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
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
	
	public List<Component> getAllComponentsNotInDelivery(int delivery_id) throws SQLException 
	{
		String sql = "SELECT * FROM component WHERE component_id NOT IN"
				+ "(SELECT component_id FROM delivery_component WHERE delivery_id = " + delivery_id + ")";
		List<Component> list = new ArrayList<Component>();
		try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
		{
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
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
	
	
	public void addComponentInDelivery(DeliveryComponent dc) throws SQLException 
	{
		String sql = "INSERT INTO delivery_component (delivery_id, component_id, number) VALUES (?,?,?)";
		PreparedStatement stm = Main.conn.prepareStatement(sql);
		stm.setInt(1, dc.getDeliveryId());
		stm.setInt(2, dc.getComponentId());
		stm.setInt(3, dc.getNumber());
		stm.executeUpdate();
		JOptionPane.showMessageDialog(null, "Компонент додано до замовлення постачання!");
	}
	

	public void updateComponentInDelivery(DeliveryComponent dc) throws SQLException 
	{
		String sql = "update delivery_component set number = ? where delivery_id = "
				+ dc.getDeliveryId() + " and component_id = " + dc.getComponentId();
		PreparedStatement stm = Main.conn.prepareStatement(sql);
		stm.setInt(1, dc.getNumber());
		stm.executeUpdate();
		JOptionPane.showMessageDialog(null, "Інформацію про компонент в замовленні постачання відредаговано!");
	}
	
	
    public void deleteComponentFromDelivery(DeliveryComponent dc) throws SQLException 
    {
		String sql = "DELETE FROM delivery_component WHERE delivery_id = " + dc.getDeliveryId() + " and component_id = " + dc.getComponentId();
    	try (Statement stm = Main.conn.createStatement())
    	{
            stm.executeUpdate(sql);
    	}
    	JOptionPane.showMessageDialog (null, "Компонент видалено з замовлення постачання!" );
	}
    
    
    public DeliveryComponent readComponentInDelivery(int delivery_id, int component_id) throws SQLException 
    {
        String sql = "SELECT * FROM delivery_component WHERE delivery_id = ? and component_id = ?";
        DeliveryComponent dc = new DeliveryComponent();
        try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
        {
            stm.setInt(1, delivery_id);
            stm.setInt(2, component_id);
            ResultSet rs = stm.executeQuery();
            rs.next();
            dc.setDeliveryId(rs.getInt("delivery_id"));
            dc.setComponentId(rs.getInt("component_id"));
            dc.setNumber(rs.getInt("number"));
        }
        return dc;
	}
}
