package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import domain.DeliveryComponent;
import main.Main;

public class DeliveryComponentDao 
{
	 public void addDeliveryComponent(DeliveryComponent dc) throws SQLException 
	    {
			String sql = "INSERT INTO delivery_component (delivery_id, component_id, number) VALUES (?,?,?)";
	 	  	PreparedStatement stm = Main.conn.prepareStatement(sql);
			stm.setInt(1, dc.getDeliveryId());
	    	stm.setInt(2, dc.getComponentId());
	    	stm.setInt(3, dc.getNumber());
	    	stm.executeUpdate();
	    	//JOptionPane.showMessageDialog (null, "Нового кліента додано до бази данних!" );
		}
	    
	    

	    /** @throws SQLException */
	    public DeliveryComponent readDeliveryComponent(int delivery_id, int component_id) throws SQLException 
	    {
	        String sql = "SELECT * FROM delivery_component WHERE delivery_id = ? and component_id = ?";
	        DeliveryComponent dc = new DeliveryComponent();
	        try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
	        {
	            stm.setInt(1, delivery_id);
	            stm.setInt(2, component_id);
	            ResultSet rs = stm.executeQuery();
	            rs.next();
	            dc.setNumber(rs.getInt("number"));
	        }
	        return dc;
		}
	    

	    
	    /** @throws SQLException */
	    public void updateDeliveryComponent(DeliveryComponent dc) throws SQLException 
	    {
	    	String sql = "update delivery_component set component_id = ?, number = ? where delivery_component_id = " +  dc.getDeliveryId();
	    	PreparedStatement stm = Main.conn.prepareStatement(sql);
			stm.setInt(1, dc.getDeliveryId());
	    	stm.setInt(2, dc.getComponentId());
	    	stm.setInt(3, dc.getNumber());
	    	stm.executeUpdate();
	    	JOptionPane.showMessageDialog (null, "Інформація про компонент в замовленні постачання відредагована!" ); 
		}

	    
	    
	    
	    /** @throws SQLException */ 
	    public void delete(DeliveryComponent dc) throws SQLException 
	    {

		}

	    
	    
	   
	    public List<DeliveryComponent> getAllFromDelivery(int delivery_id) throws SQLException 
	    {
	        String sql = "SELECT * FROM delivery_component WHERE delivery_id = " + delivery_id;
	        List<DeliveryComponent> list = new ArrayList<DeliveryComponent>();
	        try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
	        {
	            ResultSet rs = stm.executeQuery();
	            while (rs.next()) 
	            {
	                DeliveryComponent dc = new DeliveryComponent();
	                dc.setDeliveryId(rs.getInt("delivery_id"));
	                dc.setComponentId(rs.getInt("component_id"));
	                dc.setNumber(rs.getInt("number"));
	                list.add(dc);
	            }
	        }
	        return list;
	    }

	    
	    public String getComponentNameById(int delivery_id, int component_id) throws SQLException
	    {
	    	String sql = "SELECT name FROM component WHERE component_id = " + component_id + " and component_id in "
	    			+ "(select component_id from delivery_component where delivery_id = " + delivery_id + ")";
			Statement s = Main.conn.createStatement();
	 	  	ResultSet rs = s.executeQuery(sql);
	 	  	String name = null;
	 	  	while (rs.next()) {
	 	  		name = rs.getString("name");
	 	  	}
			return name; 
	    }
	    
	    
	    public String getComponentTypeById(int delivery_id, int component_id) throws SQLException
	    {
	    	String sql = "SELECT type FROM component WHERE component_id = " + component_id + " and component_id in "
	    			+ "(select component_id from delivery_component where delivery_id = " + delivery_id + ")";
			Statement s = Main.conn.createStatement();
	 	  	ResultSet rs = s.executeQuery(sql);
	 	  	String type = null;
	 	  	while (rs.next()) {
	 	  		type = rs.getString("type");
	 	  	}
			return type; 
	    }
}
