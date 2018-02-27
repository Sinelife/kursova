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
import main.Main;


public class ComponentDao 
{
	
    /** @throws SQLException */
    public void create(Component c) throws SQLException 
    {
		String sql = "INSERT INTO component (component_id, type, unit_measurement, technical_info) VALUES (?,?,?,?)";
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
    	stm.setString(3, c.getUnitMeasurement());
    	stm.setString(4, c.getTechnicalInfo());
    	stm.executeUpdate();
    	JOptionPane.showMessageDialog (null, "Новий компонент додано до бази данних!" );
	}
    
    

    /** @throws SQLException */
    public Component read(int key) throws SQLException 
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
            c.setUnitMeasurement(rs.getString("unit_measurement"));
            c.setTechnicalInfo(rs.getString("technical_info"));
        }
        return c;
	}
    

    
    /**@throws SQLException */
    public void update(Component c) throws SQLException 
    {
    	String sql = "update component set type = ?, unit_measurement = ?, technical_info = ? where component_id = " +  c.getId();
    	PreparedStatement stm = Main.conn.prepareStatement(sql);
    	stm.setString(1, c.getType());
    	stm.setString(2, c.getUnitMeasurement());
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
        String sql = "SELECT * FROM сomponent;";
        List<Component> list = new ArrayList<Component>();
        try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
        {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) 
            {
                Component c = new Component();
				c.setId(rs.getInt("component_id"));
                c.setType(rs.getString("type"));
                c.setUnitMeasurement(rs.getString("unit_measurement"));
                c.setTechnicalInfo(rs.getString("technical_info"));
                list.add(c);
            }
        }
        return list;
    }
}


