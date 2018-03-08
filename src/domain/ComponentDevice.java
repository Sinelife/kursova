package domain;

public class ComponentDevice 
{
	private int device_id;
	private int component_id;
	private int number;
	
	ComponentDevice(int device_id, int component_id, int number)
	{
		this.device_id = device_id;
		this.component_id = component_id; 
		this.number = number;
	}
	
	
	public ComponentDevice() 
	{
		
	}


	public int getComponentId()
	{	
		return component_id;
	}
	
	public void setComponentId(int component_id)
	{	
		this.component_id = component_id;
	}
	
	public int getDeviceId()
	{	
		return device_id;
	}
	
	public void setDeviceId(int device_id)
	{	
		this.device_id = device_id;
	}
	
	public int getNumber()
	{	
		return number;
	}
	
	public void setNumber(int number)
	{	
		this.number = number;
	}
}
