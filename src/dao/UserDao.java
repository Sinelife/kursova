package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import domain.User;
import domain.User;
import main.Main;

public class UserDao 
{
	/** @throws SQLException */
    public void createUser(User u) throws SQLException 
    {
		String sql = "INSERT INTO user (user_id, surname, name, login, password, role) VALUES (?,?,?,?,?,?)";
 	  	PreparedStatement stm = Main.conn.prepareStatement(sql);
		int i = -1;
		String sql_for_id = "SELECT MAX(user_id) from user";
		PreparedStatement statement_for_id = Main.conn.prepareStatement(sql_for_id);
		statement_for_id.executeQuery();
		ResultSet result = statement_for_id.getResultSet();
		if((result!=null) && (result.next()))
		{
			i = result.getInt(1);
		}
		stm.setInt(1,i+1);
    	stm.setString(2, u.getSurname());
    	stm.setString(3, u.getName());
    	stm.setString(4, u.getLogin());
    	stm.setString(5, u.getPassword());
    	stm.setString(6, u.getRole());
    	stm.executeUpdate();
    	JOptionPane.showMessageDialog (null, "Ќового користувача додано до бази данних!" );
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
    public void update(User d) throws SQLException 
    {

	}

    
    
    
    /** @throws SQLException */ 
    public void delete(User d) throws SQLException 
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
