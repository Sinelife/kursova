package domain;

public class Component
{

	private int component_id;
	private String type;
	private String name;
	private String technical_info;
	private int price;
	
	Component(int component_id, String type, String name, String technical_info, int price)
	{
		this.component_id = component_id; 
		this.type = type; 
		this.name = name;
		this.technical_info= technical_info; 
		this.price = price;
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
	
	public String getName() 
	{
		return name;	
	}
	
	public void setName(String name) 
	{
		this.name = name;	
	}
	
	public String getTechnicalInfo() 
	{
		return technical_info;
	}
	
	public void setTechnicalInfo(String technical_info) 
	{
		this.technical_info = technical_info;
	}

	public int getPrice() 
	{
		return price;
	}

	public void setPrice(int price) 
	{
		this.price = price;
	}
}
