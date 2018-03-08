package domain;

import java.sql.Date;

public class Order 
{
	private int order_id;
	private int client_id;
	private Date startDate;
	private boolean paid;
	
	
	Order(int order_id, int client_id, Date startDate, boolean paid)
	{
		this.order_id = order_id;
		this.client_id = client_id;
		this.startDate = startDate;
		this.paid = paid;
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
	
	public boolean getPaid() 
	{
		return paid;
	}
	
	public void setPaid(boolean paid) 
	{
		this.paid = paid;
	}
}
