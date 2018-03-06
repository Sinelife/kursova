package domain;

import java.sql.Date;

;

public class Component
{

	private int component_id;
	private String type;
	private String unit_measurement;
	private String technical_info;
	
	Component(int component_id, String type,String unit_measurement,String technical_info)
	{
		this.component_id = component_id; 
		this.type = type; 
		this.unit_measurement = unit_measurement;
		this.technical_info= technical_info; 
	}
	
	
	public Component() 
	{
		
	}


	public int getId()
	{	
		return component_id;
	}
	
	public void setId(int component_id)
	{	
		this.component_id = component_id;
	}
	
	public String getType() 
	{
		return type;	
	}
	
	public void setType(String type) 
	{
		this.type = type;	
	}
	
	public String getUnitMeasurement() 
	{
		return unit_measurement;	
	}
	
	public void setUnitMeasurement(String unit_measurement) 
	{
		this.unit_measurement = unit_measurement;	
	}
	
	public String getTechnicalInfo() 
	{
		return technical_info;
	}
	
	public void setTechnicalInfo(String technical_info) 
	{
		this.technical_info = technical_info;
	}
}
