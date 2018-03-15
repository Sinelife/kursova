package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import domain.ComponentDevice;
import domain.Device;
import domain.Order;
import domain.OrderDevice;
import main.Main;

public class OrderDao 
{
	
	public void addOrder(Order o) throws SQLException 
	{
		String sql = "INSERT INTO orders (order_id, order_name, client_id, startdate, paid) VALUES (?,?,?,?,?)";
		PreparedStatement stm = Main.conn.prepareStatement(sql);
		int i = -1;
		String sql_for_id = "SELECT MAX(order_id) from orders";
		PreparedStatement statement_for_id = Main.conn.prepareStatement(sql_for_id);
		statement_for_id.executeQuery();
		ResultSet result = statement_for_id.getResultSet();
		if ((result != null) && (result.next())) {
			i = result.getInt(1);
		}
		stm.setInt(1, i + 1);
		stm.setString(2, o.getOrderName());
		stm.setInt(3, o.getClientId());
		stm.setDate(4, o.getStartDate());
		stm.setBoolean(5, o.getPaid());
		stm.executeUpdate();
		JOptionPane.showMessageDialog(null, "Нове замовлення на купівлю додано до бази данних!");
	}

	
	
	
	/**
	 * @throws SQLException
	 */
	public Order readOrder(int key) throws SQLException 
	{
		String sql = "SELECT * FROM orders WHERE order_id = ?";
		Order o = new Order();
		try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
		{
			stm.setInt(1, key);
			ResultSet rs = stm.executeQuery();
			rs.next();
			o.setId(rs.getInt("order_id"));
			o.setOrderName(rs.getString("order_name"));
			o.setClientId(rs.getInt("client_id"));
			o.setStartDate(rs.getDate("startdate"));
			o.setPaid(rs.getBoolean("paid"));
		}
		return o;
	}

	
	
	
	/**
	 * @throws SQLException
	 */
	public void updateOrder(Order o) throws SQLException 
	{
		String sql = "update orders set order_name = ?, client_id = ?, startdate = ?, paid = ? where order_id = " + o.getId();
		PreparedStatement stm = Main.conn.prepareStatement(sql);
		stm.setString(1, o.getOrderName());
		stm.setInt(2, o.getClientId());
		stm.setDate(3, o.getStartDate());
		stm.setBoolean(4, o.getPaid());
		stm.executeUpdate();
		JOptionPane.showMessageDialog(null, "Інформація про замовлення на купівлю відредаговано!");
	}

	
	
	
	/**
	 * @throws SQLException
	 */
	public void delete(Order d) throws SQLException 
	{

	}
	
	
	

	public List<Order> getAll() throws SQLException 
	{
		String sql = "SELECT * FROM orders;";
		List<Order> list = new ArrayList<Order>();
		try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
		{
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
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
	
	
	public List<Order> getAllFromClient(int client_id) throws SQLException 
	{
		String sql = "SELECT * FROM orders WHERE client_id = " + client_id;
		List<Order> list = new ArrayList<Order>();
		try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
		{
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
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
	
	
	public List<Device> getAllDevicesInOrder(int order_id) throws SQLException 
	{
		String sql = "SELECT * FROM device WHERE device_id IN"
				+ "(SELECT device_id FROM order_device WHERE order_id = " + order_id + ")";
		List<Device> list = new ArrayList<Device>();
		try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
		{
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
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
	
	public List<Device> getAllDevicesNotInOrder(int order_id) throws SQLException 
	{
		String sql = "SELECT * FROM device WHERE device_id IN"
				+ "(SELECT device_id FROM order_device WHERE order_id <> " + order_id + ")";
		List<Device> list = new ArrayList<Device>();
		try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
		{
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
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
	
	
	public void addDeviceInOrder(OrderDevice od) throws SQLException 
	{
		String sql = "INSERT INTO order_device (order_id, device_id, number, price) VALUES (?,?,?,?)";
		PreparedStatement stm = Main.conn.prepareStatement(sql);
		stm.setInt(1, od.getOrderId());
		stm.setInt(2, od.getDeviceId());
		stm.setInt(3, od.getNumber());
		stm.setInt(4, od.getPrice());
		stm.executeUpdate();
		JOptionPane.showMessageDialog(null, "Прилад додано до замовлення на купівлю!");
	}

	public void updateDeviceInOrder(OrderDevice od) throws SQLException 
	{
		String sql = "update order_device set number = ?, price = ? where order_id = "
				+ od.getOrderId() + " and device_id = " + od.getDeviceId();
		PreparedStatement stm = Main.conn.prepareStatement(sql);
		stm.setInt(1, od.getNumber());
		stm.setInt(2, od.getPrice());
		stm.executeUpdate();
		JOptionPane.showMessageDialog(null, "Інформацію про прилад в замовлення на купівлю відредаговано!");
	}
	
    public void deleteDeviceFromOrder(OrderDevice od) throws SQLException 
    {
		String sql = "DELETE FROM order_device WHERE order_id = " + od.getOrderId() + " and device_id = " + od.getDeviceId();
    	try (Statement stm = Main.conn.createStatement())
    	{
            stm.executeUpdate(sql);
    	}
    	JOptionPane.showMessageDialog (null, "Прилад видалено з замовлення на купівлю!" );
	}
    
    public OrderDevice readDeviceInOrder(int order_id, int device_id) throws SQLException 
    {
        String sql = "SELECT * FROM order_device WHERE order_id = ? and device_id = ?";
        OrderDevice od = new OrderDevice();
        try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
        {
            stm.setInt(1, order_id);
            stm.setInt(2, device_id);
            ResultSet rs = stm.executeQuery();
            rs.next();
            od.setOrderId(rs.getInt("order_id"));
            od.setDeviceId(rs.getInt("device_id"));
            od.setNumber(rs.getInt("number"));
            od.setPrice(rs.getInt("price"));
        }
        return od;
	}
}
