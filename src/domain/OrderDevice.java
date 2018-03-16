package domain;

public class OrderDevice 
{
	private int order_id;
	private int device_id;
	private int number;
	
	OrderDevice(int order_id, int device_id, int number)
	{
		this.order_id = order_id;
		this.device_id = device_id;;
		this.number = number;
	}
	
	public OrderDevice()
	{
		
	}

	public int getOrderId() 
	{
		return order_id;
	}

	public void setOrderId(int order_id) 
	{
		this.order_id = order_id;
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
