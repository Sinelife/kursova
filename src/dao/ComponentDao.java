package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import domain.Component;
import domain.Device;
import main.Main;


public class ComponentDao 
{
	
    /** @throws SQLException */
    public void addComponent(Component c) throws SQLException 
    {
		String sql = "INSERT INTO component (component_id, type, name, technical_info) VALUES (?,?,?,?)";
 	  	PreparedStatement stm = Main.conn.prepareStatement(sql);
		int i = -1;
		String sql_for_id = "SELECT MAX(component_id) from component";
		PreparedStatement statement_for_id = Main.conn.prepareStatement(sql_for_id);
		statement_for_id.executeQuery();
		ResultSet result = statement_for_id.getResultSet();
		if((result!=null) && (result.next()))
		{
			i = result.getInt(1);
		}
		stm.setInt(1,i+1);
    	stm.setString(2, c.getType());
    	stm.setString(3, c.getName());
    	stm.setString(4, c.getTechnicalInfo());
    	stm.executeUpdate();
    	JOptionPane.showMessageDialog (null, "Новий компонент додано до бази данних!" );
	}
    
    

    /** @throws SQLException */
    public Component readComponent(int key) throws SQLException 
    {
        String sql = "SELECT * FROM component WHERE component_id = ?";
        Component c = new Component();
        try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
        {
            stm.setInt(1, key);
            ResultSet rs = stm.executeQuery();
            rs.next();
            c.setId(rs.getInt("component_id"));
            c.setType(rs.getString("type"));
            c.setName(rs.getString("name"));
            c.setTechnicalInfo(rs.getString("technical_info"));
        }
        return c;
	}
    

    
    /**@throws SQLException */
    public void updateComponent(Component c) throws SQLException 
    {
    	String sql = "update component set type = ?, name = ?, technical_info = ? where component_id = " +  c.getId();
    	PreparedStatement stm = Main.conn.prepareStatement(sql);
    	stm.setString(1, c.getType());
    	stm.setString(2, c.getName());
    	stm.setString(3, c.getTechnicalInfo());
    	stm.executeUpdate();
    	JOptionPane.showMessageDialog (null, "Інформація про компонент відредагована!" ); 
	}

    
    
    
    /** @throws SQLException */ 
    public void delete(Component c) throws SQLException 
    {
    	String sql = "DELETE FROM component WHERE component_id = " + c.getId();
    	try (Statement stm = Main.conn.createStatement())
    	{
            stm.executeUpdate(sql);
    	}
    	JOptionPane.showMessageDialog (null, "Компонент видалено з бази данних!" );
	}

    
    
   
    public List<Component> getAll() throws SQLException 
    {
        String sql = "SELECT * FROM component";
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
    
 
    public List<Component> getAllComponentsInDevice(int device_id) throws SQLException 
    {
        String sql = "SELECT * FROM component WHERE component_id in "
        		+ "(SELECT component_id FROM component_device WHERE device_id = " + device_id;
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
    
    
    public List<Device> getAllDeviceWhichHasComponent(int component_id) throws SQLException 
    {
        String sql = "SELECT * FROM device WHERE device_id in "
        		+ "(SELECT device_id FROM component_device WHERE component_id = " + component_id + ")";
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
    
    
    
}


