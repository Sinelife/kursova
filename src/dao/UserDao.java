package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import domain.Device;
import domain.User;
import main.Main;

public class UserDao 
{
	/** @throws SQLException */
    public void createUser(Device d) throws SQLException 
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
    	JOptionPane.showMessageDialog (null, "Новий прилад додано до бази данних!" );
	}
    
    

    /** @throws SQLException */
    public User readUser(int key) throws SQLException 
    {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        User u = new User();
        try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
        {
            stm.setInt(1, key);
            ResultSet rs = stm.executeQuery();
            rs.next();
            u.setId(rs.getInt("user_id"));
            u.setSurname(rs.getString("surname"));
            u.setName(rs.getString("name"));
            u.setLogin(rs.getString("login"));
            u.setPassword(rs.getString("password"));
            u.setRole(rs.getString("role"));
        }
        return u;
	}
    

    
    /**@throws SQLException */
    public void update(Device d) throws SQLException 
    {

	}

    
    
    
    /** @throws SQLException */ 
    public void delete(Device d) throws SQLException 
    {

	}

    
    
    
    public List<User> getAll() throws SQLException 
    {
        String sql = "SELECT * FROM user;";
        List<User> list = new ArrayList<User>();
        try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
        {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) 
            {
                User u = new User();
                u.setId(rs.getInt("user_id"));
                u.setSurname(rs.getString("surname"));
                u.setName(rs.getString("name"));
                u.setLogin(rs.getString("login"));
                u.setPassword(rs.getString("password"));
                u.setRole(rs.getString("role"));
                list.add(u);
            }
        }
        return list;
    }
}
