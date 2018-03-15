package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import domain.OrderDevice;
import main.Main;

public class OrderDeviceDao 
{
	 public void addOrderDevice(OrderDevice od) throws SQLException 
	    {
			String sql = "INSERT INTO order_device (order_id, device_id, number, price) VALUES (?,?,?,?)";
	 	  	PreparedStatement stm = Main.conn.prepareStatement(sql);
			stm.setInt(1, od.getOrderId());
	    	stm.setInt(2, od.getDeviceId());
	    	stm.setInt(3, od.getNumber());
	    	stm.setInt(4, od.getPrice());
	    	stm.executeUpdate();
	    	//JOptionPane.showMessageDialog (null, "Нового кліента додано до бази данних!" );
		}
	    
	    

	    /** @throws SQLException */
	    public OrderDevice readOrderDevice(int order_id, int device_id) throws SQLException 
	    {
	        String sql = "SELECT * FROM OrderDevice WHERE order_id = ? and device_id = ?";
	        OrderDevice od = new OrderDevice();
	        try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
	        {
	            stm.setInt(1, order_id);
	            stm.setInt(2, device_id);
	            ResultSet rs = stm.executeQuery();
	            rs.next();
	            od.setNumber(rs.getInt("number"));
	            od.setPrice(rs.getInt("price"));
	        }
	        return od;
		}
	    

	    
	    /** @throws SQLException */
	    public void updateOrderDevice(OrderDevice od) throws SQLException 
	    {
	    	String sql = "update order_device set device_id = ?, number = ?, price = ? where order_device_id = " +  od.getOrderId();
	    	PreparedStatement stm = Main.conn.prepareStatement(sql);
			stm.setInt(1, od.getOrderId());
	    	stm.setInt(2, od.getDeviceId());
	    	stm.setInt(3, od.getNumber());
	    	stm.setInt(4, od.getPrice());
	    	stm.executeUpdate();
	    	JOptionPane.showMessageDialog (null, "Інформація про прилад в замовленні відредагована!" ); 
		}

	    
	    
	    
	    /** @throws SQLException */ 
	    public void delete(OrderDevice d) throws SQLException 
	    {

		}

	    
	    
	   
	    public List<OrderDevice> getAll() throws SQLException 
	    {
	        String sql = "SELECT * FROM order_device;";
	        List<OrderDevice> list = new ArrayList<OrderDevice>();
	        try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
	        {
	            ResultSet rs = stm.executeQuery();
	            while (rs.next()) 
	            {
	                OrderDevice od = new OrderDevice();
	                od.setOrderId(rs.getInt("order_id"));
	                od.setDeviceId(rs.getInt("device_id"));
	                od.setNumber(rs.getInt("number"));
	                od.setPrice(rs.getInt("price"));
	                list.add(od);
	            }
	        }
	        return list;
	    }
	    
	    
	    public List<OrderDevice> getAllFromOrder(int order_id) throws SQLException 
	    {
	        String sql = "SELECT * FROM order_device WHERE order_id = " + order_id;
	        List<OrderDevice> list = new ArrayList<OrderDevice>();
	        try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
	        {
	            ResultSet rs = stm.executeQuery();
	            while (rs.next()) 
	            {
	                OrderDevice od = new OrderDevice();
	                od.setOrderId(rs.getInt("order_id"));
	                od.setDeviceId(rs.getInt("device_id"));
	                od.setNumber(rs.getInt("number"));
	                od.setPrice(rs.getInt("price"));
	                list.add(od);
	            }
	        }
	        return list;
	    }

	    
	    public String getDeviceNameById(int order_id, int device_id) throws SQLException
	    {
	    	String sql = "SELECT name FROM device WHERE device_id = " + device_id + " and device_id in "
	    			+ "(select device_id from order_device where order_id = " + order_id + ")";
			Statement s = Main.conn.createStatement();
	 	  	ResultSet rs = s.executeQuery(sql);
	 	  	String name = null;
	 	  	while (rs.next()) {
	 	  		name = rs.getString("name");
	 	  	}
			return name; 
	    }
	    
	    public String getSupplyVoltageById(int order_id, int device_id) throws SQLException
	    {
	    	String sql = "SELECT supply_voltage FROM device WHERE device_id = " + device_id + " and device_id in "
	    			+ "(select device_id from order_device where order_id = " + order_id + ")";
			Statement s = Main.conn.createStatement();
	 	  	ResultSet rs = s.executeQuery(sql);
	 	  	String supply_voltage = null;
	 	  	while (rs.next()) {
	 	  		supply_voltage = rs.getString("supply_voltage");
	 	  	}
			return supply_voltage; 
	    }
	    
	    public String getBorderRegulationTimeById(int order_id, int device_id) throws SQLException
	    {
	    	String sql = "SELECT border_regulation_time FROM device WHERE device_id = " + device_id + " and device_id in "
	    			+ "(select device_id from order_device where order_id = " + order_id + ")";
			Statement s = Main.conn.createStatement();
	 	  	ResultSet rs = s.executeQuery(sql);
	 	  	String border_regulation_time = null;
	 	  	while (rs.next()) {
	 	  		border_regulation_time = rs.getString("border_regulation_time");
	 	  	}
			return border_regulation_time; 
	    }
	    
}
