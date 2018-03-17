package domain;

import java.sql.Date;

public class Order 
{
	private int order_id;
	private String order_name;
	private int client_id;
	private Date startDate;
	private boolean paid;
	private boolean shipped;
	
	
	Order(int order_id, String order_name, int client_id, Date startDate, boolean paid, boolean shipped)
	{
		this.order_id = order_id;
		this.order_name = order_name;
		this.client_id = client_id;
		this.startDate = startDate;
		this.paid = paid;
		this.shipped = shipped;
	}
	
	
	public Order()
	{
		
	}
	
	public int getId() 
	{
		return order_id;
	}
	
	public void setId(int order_id) 
	{
		this.order_id = order_id;
	}
	
	public String getOrderName() 
	{
		return order_name;
	}

	public void setOrderName(String order_name) 
	{
		this.order_name = order_name;
	}
	
	public int getClientId() 
	{
		return client_id;
	}
	
	public void setClientId(int client_id) 
	{
		this.client_id = client_id;
	}
	
	public Date getStartDate() 
	{
		return startDate;
	}
	
	public void setStartDate(Date startDate) 
	{
		this.startDate = startDate;
	}
	
	public boolean isPaid() 
	{
		return paid;
	}
	
	public void setPaid(boolean paid) 
	{
		this.paid = paid;
	}

	public boolean isShipped() 
	{
		return shipped;
	}

	public void setShipped(boolean shipped) 
	{
		this.shipped = shipped;
	}
}
