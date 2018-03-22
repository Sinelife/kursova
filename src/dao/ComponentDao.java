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
import domain.Specialization;
import main.Main;


public class ComponentDao 
{
	
    /** @throws SQLException */
    public void addComponent(Component c) throws SQLException 
    {
		String sql = "INSERT INTO component (component_id, type, name, technical_info, price) VALUES (?,?,?,?,?)";
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
    	stm.setInt(5, c.getPrice());
    	stm.executeUpdate();
    	JOptionPane.showMessageDialog (null, "����� ��������� ������ �� ���� ������!" );
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
            c.setPrice(rs.getInt("price"));
        }
        return c;
	}
    

    
    /**@throws SQLException */
    public void updateComponent(Component c) throws SQLException 
    {
    	String sql = "update component set type = ?, name = ?, technical_info = ?, price = ? where component_id = " +  c.getId();
    	PreparedStatement stm = Main.conn.prepareStatement(sql);
    	stm.setString(1, c.getType());
    	stm.setString(2, c.getName());
    	stm.setString(3, c.getTechnicalInfo());
    	stm.setInt(4, c.getPrice());
    	stm.executeUpdate();
    	JOptionPane.showMessageDialog (null, "���������� ��� ��������� ������������!" ); 
	}

    
    
    
    /** @throws SQLException */ 
    public void delete(Component c) throws SQLException 
    {
    	String sql = "DELETE FROM component WHERE component_id = " + c.getId();
    	try (Statement stm = Main.conn.createStatement())
    	{
            stm.executeUpdate(sql);
    	}
    	JOptionPane.showMessageDialog (null, "��������� �������� � ���� ������!" );
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
                c.setPrice(rs.getInt("price"));
                list.add(c);
            }
        }
        return list;
    }
    
    public List<Specialization> getAllSpecializations() throws SQLException
    {
    	String sql = "SELECT * FROM provider_specialization";
    	List<Specialization> list = new ArrayList<Specialization>();
        try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
        {
            ResultSet rs = stm.executeQuery();
            while (rs.next())
            {
                Specialization s = new Specialization();
                s.setSpecialization(rs.getString("specialization_name"));
                list.add(s);
            }
        }
        return list;
    }
    
}


