package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import domain.Delivery;
import main.Main;

public class DeliveryDao 
{
	
	public void addDelivery(Delivery d) throws SQLException 
	{
		String sql = "INSERT INTO delivery (delivery_id, delivery_name, provider_id, startdate, paid) VALUES (?,?,?,?,?)";
		PreparedStatement stm = Main.conn.prepareStatement(sql);
		int i = -1;
		String sql_for_id = "SELECT MAX(delivery_id) from delivery";
		PreparedStatement statement_for_id = Main.conn.prepareStatement(sql_for_id);
		statement_for_id.executeQuery();
		ResultSet result = statement_for_id.getResultSet();
		if ((result != null) && (result.next())) {
			i = result.getInt(1);
		}
		stm.setInt(1, i + 1);
		stm.setString(2, d.getDeliveryName());
		stm.setInt(3, d.getProviderId());
		stm.setDate(4, d.getStartDate());
		stm.setBoolean(5, d.getPaid());
		stm.executeUpdate();
		JOptionPane.showMessageDialog(null, "Нове замовлення на постачання додано до бази данних!");
	}

	
	
	
	/**
	 * @throws SQLException
	 */
	public Delivery readDelivery(int key) throws SQLException 
	{
		String sql = "SELECT * FROM delivery WHERE delivery_id = ?";
		Delivery d = new Delivery();
		try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
		{
			stm.setInt(1, key);
			ResultSet rs = stm.executeQuery();
			rs.next();
			d.setId(rs.getInt("delivery_id"));
			d.setDeliveryName(rs.getString("delivery_name"));
			d.setProviderId(rs.getInt("provider_id"));
			d.setStartDate(rs.getDate("startdate"));
			d.setPaid(rs.getBoolean("paid"));
		}
		return d;
	}

	
	
	
	/**
	 * @throws SQLException
	 */
	public void updateDelivery(Delivery d) throws SQLException 
	{
		String sql = "update delivery set delivery_name = ?, provider_id = ?, startdate = ?, paid = ? where delivery_id = " + d.getId();
		PreparedStatement stm = Main.conn.prepareStatement(sql);
		stm.setInt(1, d.getProviderId());
		stm.setDate(2, d.getStartDate());
		stm.setBoolean(3, d.getPaid());
		stm.executeUpdate();
		JOptionPane.showMessageDialog(null, "Інформація про замовлення на постачання відредаговано!");
	}

	
	
	
	/**
	 * @throws SQLException
	 */
	public void delete(Delivery d) throws SQLException 
	{

	}
	
	
	

	public List<Delivery> getAll() throws SQLException 
	{
		String sql = "SELECT * FROM delivery;";
		List<Delivery> list = new ArrayList<Delivery>();
		try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
		{
			ResultSet rs = stm.executeQuery();
			while (rs.next()) 
			{
				Delivery d = new Delivery();
				d.setId(rs.getInt("delivery_id"));
				d.setDeliveryName(rs.getString("delivery_name"));
				d.setProviderId(rs.getInt("provider_id"));
				d.setStartDate(rs.getDate("startdate"));
				d.setPaid(rs.getBoolean("paid"));
				list.add(d);
			}
		}
		return list;
	}
}
