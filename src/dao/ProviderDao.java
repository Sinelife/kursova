package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import domain.Provider;
import main.Main;

public class ProviderDao
{
	
	public void addProvider(Provider p) throws SQLException 
	{
		String sql = "INSERT INTO provider (provider_id, name, phone, contactPIB, codeERPOU, codeTaxpayer, specialization) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement stm = Main.conn.prepareStatement(sql);
		int i = -1;
		String sql_for_id = "SELECT MAX(provider_id) from provider";
		PreparedStatement statement_for_id = Main.conn.prepareStatement(sql_for_id);
		statement_for_id.executeQuery();
		ResultSet result = statement_for_id.getResultSet();
		if ((result != null) && (result.next())) {
			i = result.getInt(1);
		}
		stm.setInt(1, i + 1);
		stm.setString(2, p.getName());
		stm.setString(3, p.getPhone());
		stm.setString(4, p.getContactPIB());
		stm.setString(5, p.getCodeERPOU());
		stm.setString(6, p.getCodeTaxpayer());
		stm.setString(7, p.getSpecialization());
		stm.executeUpdate();
		JOptionPane.showMessageDialog(null, "������ ������������� ������ �� ���� ������!");
	}
	
	

	/**
	 * @throws SQLException
	 */
	public Provider readProvider(int key) throws SQLException 
	{
		String sql = "SELECT * FROM provider WHERE provider_id = ?";
		Provider p = new Provider();
		try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
		{
			stm.setInt(1, key);
			ResultSet rs = stm.executeQuery();
			rs.next();
			p.setId(rs.getInt("provider_id"));
			p.setName(rs.getString("name"));
			p.setPhone(rs.getString("phone"));
			p.setContactPIB(rs.getString("contactPIB"));
			p.setCodeERPOU(rs.getString("codeERPOU"));
			p.setCodeTaxpayer(rs.getString("codeTaxpayer"));
			p.setSpecialization(rs.getString("specialization"));
		}
		return p;
	}

	
	
	/**
	 * @throws SQLException
	 */
	public void updateProvider(Provider p) throws SQLException {
		String sql = "update Provider set name = ?, phone = ?, contactPIB = ?, codeERPOU = ?, codeTaxpayer = ?, specialization = ? where Provider_id = "
				+ p.getId();
		PreparedStatement stm = Main.conn.prepareStatement(sql);
		stm.setString(1, p.getName());
		stm.setString(2, p.getPhone());
		stm.setString(3, p.getContactPIB());
		stm.setString(4, p.getCodeERPOU());
		stm.setString(5, p.getCodeTaxpayer());
		stm.setString(6, p.getSpecialization());
		stm.executeUpdate();
		JOptionPane.showMessageDialog(null, "���������� ��� ������������� ������������!");
	}

	
	
	
	
	
	/**
	 * @throws SQLException
	 */
	public void delete(Provider d) throws SQLException {

	}

	
	
	
	
	
	public List<Provider> getAll() throws SQLException 
	{
		String sql = "SELECT * FROM provider;";
		List<Provider> list = new ArrayList<Provider>();
		try (PreparedStatement stm = Main.conn.prepareStatement(sql)) 
		{
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Provider p = new Provider();
				p.setId(rs.getInt("provider_id"));
				p.setName(rs.getString("name"));
				p.setPhone(rs.getString("phone"));
				p.setContactPIB(rs.getString("contactPIB"));
				p.setCodeERPOU(rs.getString("codeERPOU"));
				p.setCodeTaxpayer(rs.getString("codeTaxpayer"));
				p.setSpecialization(rs.getString("specialization"));
				list.add(p);
			}
		}
		return list;
	}
}