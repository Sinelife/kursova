package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import domain.Order;
import main.Main;

public class OrderDao 
{
	
	public void addOrder(Order o) throws SQLException 
	{
		String sql = "INSERT INTO orders (order_id, order_name, client_id, startdate, paid) VALUES (?,?,?,?,?)";
		PreparedStatement stm = Main.conn.prepareStatement(sql);
		int i = -1;
		String sql_for_id = "SELECT MAX(order_id) from order";
		PreparedStatement statement_for_id = Main.conn.prepareStatement(sql_for_id);
		statement_for_id.executeQuery();
		ResultSet result = statement_for_id.getResultSet();
		if ((result != null) && (result.next())) {
			i = result.getInt(1);
		}
		stm.setInt(1, i + 1);
		stm.setString(2, o.getOrderName());
		stm.setInt(3, o.getClientId());
		stm.setDate(4, o.getStartDate());
		stm.setBoolean(5, o.getPaid());
		stm.executeUpdate();
		JOptionPane.showMessageDialog(null, "���� ���������� �� ������ ������ �� ���� ������!");
	}

	
	
	
	/**
	 * @throws SQLException
	 */
	public Order readOrder(int key) throws SQLException 
	{
		String sql = "SELECT * FROM orders WHERE order_id = ?";
		Order o = new Order();
		try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
		{
			stm.setInt(1, key);
			ResultSet rs = stm.executeQuery();
			rs.next();
			o.setId(rs.getInt("order_id"));
			o.setOrderName(rs.getString("order_name"));
			o.setClientId(rs.getInt("client_id"));
			o.setStartDate(rs.getDate("startdate"));
			o.setPaid(rs.getBoolean("paid"));
		}
		return o;
	}

	
	
	
	/**
	 * @throws SQLException
	 */
	public void updateOrder(Order o) throws SQLException 
	{
		String sql = "update orders set order_name = ?, client_id = ?, startdate = ?, paid = ? where order_id = " + o.getId();
		PreparedStatement stm = Main.conn.prepareStatement(sql);
		stm.setString(1, o.getOrderName());
		stm.setInt(2, o.getClientId());
		stm.setDate(3, o.getStartDate());
		stm.setBoolean(4, o.getPaid());
		stm.executeUpdate();
		JOptionPane.showMessageDialog(null, "���������� ��� ���������� �� ������ ������������!");
	}

	
	
	
	/**
	 * @throws SQLException
	 */
	public void delete(Order d) throws SQLException 
	{

	}
	
	
	

	public List<Order> getAll() throws SQLException 
	{
		String sql = "SELECT * FROM orders;";
		List<Order> list = new ArrayList<Order>();
		try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
		{
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Order o = new Order();
				o.setId(rs.getInt("order_id"));
				o.setOrderName(rs.getString("order_name"));
				o.setClientId(rs.getInt("client_id"));
				o.setStartDate(rs.getDate("startdate"));
				o.setPaid(rs.getBoolean("paid"));
				list.add(o);
			}
		}
		return list;
	}
}
