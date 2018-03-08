package domain;

public class DeliveryComponent 
{
	private int delivery_id;
	private int component_id;
	private int number;
	private int price;
	
	DeliveryComponent(int delivery_id, int component_id, int number, int price)
	{
		this.delivery_id = delivery_id;
		this.component_id = component_id;;
		this.number = number;
		this.price = price;
	}
	
	DeliveryComponent()
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

	public int getComponentId() 
	{
		return component_id;
	}

	public void setComponentId(int component_id) 
	{
		this.component_id = component_id;
	}

	public int getNumber() 
	{
		return number;
	}

	public void setNumber(int number) 
	{
		this.number = number;
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
