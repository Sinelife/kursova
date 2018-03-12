package domain;

import java.sql.Date;

public class Delivery 
{
	private int delivery_id;
	private String delivery_name;
	private int provider_id;
	private Date startDate;
	private boolean paid;
	
	
	Delivery(int delivery_id, String delivery_name, int provider_id, Date startDate, boolean paid)
	{
		this.delivery_id = delivery_id;
		this.setDeliveryName(delivery_name);
		this.provider_id = provider_id;
		this.startDate = startDate;
		this.paid = paid;
	}
	
	
	public Delivery()
	{
		
	}
	
	public int getId() 
	{
		return delivery_id;
	}
	
	public void setId(int delivery_id) 
	{
		this.delivery_id = delivery_id;
	}
	
	public String getDeliveryName()
	{
		return delivery_name;
	}

	public void setDeliveryName(String delivery_name) 
	{
		this.delivery_name = delivery_name;
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
