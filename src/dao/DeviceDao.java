package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import domain.Component;
import domain.ComponentDevice;
import domain.Device;
import main.Main;


public class DeviceDao 
{
	
    /** @throws SQLException */
    public void addDevice(Device d) throws SQLException 
    {
		String sql = "INSERT INTO device (device_id, name, supply_voltage, border_regulation_time,rating,date) VALUES (?,?,?,?,?,?)";
 	  	PreparedStatement stm = Main.conn.prepareStatement(sql);
		int i = -1;
		String sql_for_id = "SELECT MAX(device_id) from device";
		PreparedStatement statement_for_id = Main.conn.prepareStatement(sql_for_id);
		statement_for_id.executeQuery();
		ResultSet result = statement_for_id.getResultSet();
		if((result!=null) && (result.next()))
		{
			i = result.getInt(1);
		}
		stm.setInt(1,i+1);
    	stm.setString(2, d.getName());
    	stm.setString(3, d.getSupplyVoltage());
    	stm.setString(4, d.getBorderRegulationTime());
    	stm.setInt(5, d.getRating());
    	stm.setDate(6, d.getDate());
    	stm.executeUpdate();
    	JOptionPane.showMessageDialog (null, "����� ������ ������ �� ���� ������!" );
	}
    
    

    /** @throws SQLException */
    public Device readDevice(int key) throws SQLException 
    {
        String sql = "SELECT * FROM device WHERE device_id = ?";
        Device d = new Device();
        try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
        {
            stm.setInt(1, key);
            ResultSet rs = stm.executeQuery();
            rs.next();
            d.setId(rs.getInt("device_id"));
            d.setName(rs.getString("name"));
            d.setSupplyVoltage(rs.getString("supply_voltage"));
            d.setBorderRegulationTime(rs.getString("border_regulation_time"));
            d.setRating(rs.getInt("rating"));
            d.setDate(rs.getDate("date"));
        }
        return d;
	}
    

    
    /**@throws SQLException */
    public void updateDevice(Device d) throws SQLException 
    {
    	String sql = "update device set name = ?, supply_voltage = ?, border_regulation_time = ?, rating = ?, date = ? where device_id = " +  d.getId();
    	PreparedStatement stm = Main.conn.prepareStatement(sql);
    	stm.setString(1, d.getName());
    	stm.setString(2, d.getSupplyVoltage());
    	stm.setString(3, d.getBorderRegulationTime());
    	stm.setInt(4, d.getRating());
    	stm.setDate(5, d.getDate());
    	stm.executeUpdate();
    	JOptionPane.showMessageDialog (null, "���������� ��� ������ ������������!" ); 
	}

    
    
    
    /** @throws SQLException */ 
    public void delete(Device d) throws SQLException 
    {

	}

    
    
   
    public List<Device> getAll() throws SQLException 
    {
        String sql = "SELECT * FROM device;";
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
    
    
    public List<Device> getAllDeviceWhichHasComponent(int component_id) throws SQLException 
    {
        String sql = "SELECT * FROM device WHERE device_id in "
        		+ "(SELECT device_id FROM component_device WHERE component_id = " + component_id;
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
    
    
    public ComponentDevice readComponentInDevice(int device_id, int component_id) throws SQLException 
    {
        String sql = "SELECT * FROM component_device WHERE device_id = ?" + " and component_id = ?";
        ComponentDevice cd = new ComponentDevice();
        try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
        {
            stm.setInt(1, device_id);
            stm.setInt(2, component_id);
            ResultSet rs = stm.executeQuery();
            rs.next();
            cd.setDeviceId(rs.getInt("device_id"));
            cd.setComponentId(rs.getInt("component_id"));
            cd.setNumber(rs.getInt("number"));
        }
        return cd;
	}
    
    public ComponentDevice readSpecificationInfo(int device_id, int component_id) throws SQLException 
    {
        String sql = "SELECT device.name FROM component_device WHERE device_id = ?" + " and component_id = ?";
        ComponentDevice cd = new ComponentDevice();
        try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
        {
            stm.setInt(1, device_id);
            stm.setInt(2, component_id);
            ResultSet rs = stm.executeQuery();
            rs.next();
            cd.setDeviceId(rs.getInt("device_id"));
            cd.setComponentId(rs.getInt("component_id"));
            cd.setNumber(rs.getInt("number"));
        }
        return cd;
	}
    
    
    public void addComponentToDevice(ComponentDevice cd) throws SQLException 
    {
		String sql = "INSERT INTO component_device (device_id, component_id, number) VALUES (?,?,?)";
 	  	PreparedStatement stm = Main.conn.prepareStatement(sql);
 	  	stm.setInt(1, cd.getDeviceId());
		stm.setInt(2, cd.getComponentId());
    	stm.setInt(3, cd.getNumber());
    	stm.executeUpdate();
    	JOptionPane.showMessageDialog (null, "��������� ������ �� ������������ �������!" );
	}
    
    
    public void deleteComponentFromDevice(ComponentDevice cd) throws SQLException 
    {
		String sql = "DELETE FROM component_device WHERE device_id = " + cd.getDeviceId() + " and component_id = " + cd.getComponentId();
    	try (Statement stm = Main.conn.createStatement())
    	{
            stm.executeUpdate(sql);
    	}
    	JOptionPane.showMessageDialog (null, "��������� �������� � ������������ �������!" );
	}
    
    public void updateComponentInDevice(ComponentDevice cd) throws SQLException 
    {
    	String sql = "update component_device set number = ? where device_id = " + cd.getDeviceId() + " and component_id = " + cd.getComponentId();
    	PreparedStatement stm = Main.conn.prepareStatement(sql);
    	stm.setInt(1, cd.getNumber());
    	stm.executeUpdate();
    	JOptionPane.showMessageDialog (null, "���������� ��� ��������� � ������ ������������!" ); 
	}
    
    
    
}


