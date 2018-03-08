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
	
	Device(int device_id, String name,String supply_voltage,String border_regulation_time,int rating,Date date)
	{
		this.device_id = device_id; 
		this.name = name; 
		this.supply_voltage = supply_voltage;
		this.border_regulation_time= border_regulation_time; 
		this.rating = rating;
		this.date = date;
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
}
