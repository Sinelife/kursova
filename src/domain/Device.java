package domain;

import java.sql.Date;



public class Device
{

	private int device_id;
	private String name;
	private String supply_voltage;
	private String border_regulation_time;
	private int rating;
	private Date date;
	private int work_price;
	private int components_price;
	private int profit_price;
	private int sum_price;
	
	
	Device(int device_id, String name,String supply_voltage,String border_regulation_time,int rating,Date date, int work_price, int components_price, int profit_price, int sum_price)
	{
		this.device_id = device_id; 
		this.name = name; 
		this.supply_voltage = supply_voltage;
		this.border_regulation_time= border_regulation_time; 
		this.rating = rating;
		this.date = date;
		this.work_price = work_price;
		this.components_price = components_price;
		this.profit_price = profit_price;
		this.sum_price = sum_price;
	}
	
	
	public Device() 
	{
		
	}


	public int getId()
	{	
		return device_id;
	}
	
	public void setId(int device_id)
	{	
		this.device_id = device_id;
	}
	
	public String getName() 
	{
		return name;	
	}
	
	public void setName(String name) 
	{
		this.name = name;	
	}
	
	public String getSupplyVoltage() 
	{
		return supply_voltage;	
	}
	
	public void setSupplyVoltage(String supply_voltage) 
	{
		this.supply_voltage = supply_voltage;	
	}
	
	public String getBorderRegulationTime() 
	{
		return border_regulation_time;
	}
	
	public void setBorderRegulationTime(String border_regulation_time) 
	{
		this.border_regulation_time = border_regulation_time;
	}
	
	public int getRating() 
	{
		return rating;
	}
	
	public void setRating(int rating) 
	{
		this.rating = rating;
	}
	
	public Date getDate() 
	{
		return date;
	}
	
	public void setDate(Date date) 
	{
		this.date = date;
	}

	public int getWorkPrice() 
	{
		return work_price;
	}

	public void setWorkPrice(int work_price) 
	{
		this.work_price = work_price;
	}


	public int getComponentsPrice() 
	{
		return components_price;
	}


	public void setComponentsPrice(int components_price) 
	{
		this.components_price = components_price;
	}


	public int getProfitPrice() 
	{
		return profit_price;
	}


	public void setProfitPrice(int profit_price) 
	{
		this.profit_price = profit_price;
	}


	public int getSumPrice() 
	{
		return sum_price;
	}


	public void setSumPrice(int sum_price) 
	{
		this.sum_price = sum_price;
	}
}
