package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import domain.Client;
import main.Main;

public class ClientDao
{
	 public void addClient(Client c) throws SQLException 
	    {
			String sql = "INSERT INTO client (client_id, name, phone, contactPIB, codeERPOU, codeTaxpayer) VALUES (?,?,?,?,?,?)";
	 	  	PreparedStatement stm = Main.conn.prepareStatement(sql);
			int i = -1;
			String sql_for_id = "SELECT MAX(client_id) from client";
			PreparedStatement statement_for_id = Main.conn.prepareStatement(sql_for_id);
			statement_for_id.executeQuery();
			ResultSet result = statement_for_id.getResultSet();
			if((result!=null) && (result.next()))
			{
				i = result.getInt(1);
			}
			stm.setInt(1,i+1);
	    	stm.setString(2, c.getName());
	    	stm.setString(3, c.getPhone());
	    	stm.setString(4, c.getContactPIB());
	    	stm.setString(5, c.getCodeERPOU());
	    	stm.setString(6, c.getCodeTaxpayer());
	    	stm.executeUpdate();
	    	JOptionPane.showMessageDialog (null, "������ ������ ������ �� ���� ������!" );
		}
	    
	    

	    /** @throws SQLException */
	    public Client readClient(int key) throws SQLException 
	    {
	        String sql = "SELECT * FROM �lient WHERE �lient_id = ?";
	        Client c = new Client();
	        try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
	        {
	            stm.setInt(1, key);
	            ResultSet rs = stm.executeQuery();
	            rs.next();
	            c.setId(rs.getInt("�lient_id"));
	            c.setName(rs.getString("name"));
	            c.setPhone(rs.getString("phone"));
	            c.setContactPIB(rs.getString("contactPIB"));
	            c.setCodeERPOU(rs.getString("codeERPOU"));
	            c.setCodeTaxpayer(rs.getString("codeTaxpayer"));
	        }
	        return c;
		}
	    

	    
	    /**@throws SQLException */
	    public void updateClient(Client c) throws SQLException 
	    {
	    	String sql = "update client set name = ?, phone = ?, contactPIB = ?, codeERPOU = ?, codeTaxpayer = ? where client_id = " +  c.getId();
	    	PreparedStatement stm = Main.conn.prepareStatement(sql);
	    	stm.setString(1, c.getName());
	    	stm.setString(2, c.getPhone());
	    	stm.setString(3, c.getContactPIB());
	    	stm.setString(4, c.getCodeERPOU());
	    	stm.setString(5, c.getCodeTaxpayer());
	    	stm.executeUpdate();
	    	JOptionPane.showMessageDialog (null, "���������� ��� �볺��� ������������!" ); 
		}

	    
	    
	    
	    /** @throws SQLException */ 
	    public void delete(Client d) throws SQLException 
	    {

		}

	    
	    
	   
	    public List<Client> getAll() throws SQLException 
	    {
	        String sql = "SELECT * FROM client;";
	        List<Client> list = new ArrayList<Client>();
	        try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
	        {
	            ResultSet rs = stm.executeQuery();
	            while (rs.next()) 
	            {
	                Client c = new Client();
		            c.setId(rs.getInt("�lient_id"));
		            c.setName(rs.getString("name"));
		            c.setPhone(rs.getString("phone"));
		            c.setContactPIB(rs.getString("contactPIB"));
		            c.setCodeERPOU(rs.getString("codeERPOU"));
		            c.setCodeTaxpayer(rs.getString("codeTaxpayer"));
	                list.add(c);
	            }
	        }
	        return list;
	    }
}