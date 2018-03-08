package domain;

import java.sql.Date;

public class Delivery 
{
	private int delivery_id;
	private int provider_id;
	private Date startDate;
	private boolean paid;
	
	
	Delivery(int delivery_id, int provider_id, Date startDate, boolean paid)
	{
		this.delivery_id = delivery_id;
		this.provider_id = provider_id;
		this.startDate = startDate;
		this.paid = paid;
	}
	
	
	Delivery()
	{
		
	}
	
	public int getDeliveryId() 
	{
		return delivery_id;
	}
	
	public void setDeliveryId(int delivery_id) 
	{
		this.delivery_id = delivery_id;
	}
	
	public int getProviderId() 
	{
		return provider_id;
	}
	
	public void setProviderId(int provider_id) 
	{
		this.provider_id = provider_id;
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
