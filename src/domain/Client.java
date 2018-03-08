package domain;

public class Client 
{
	private int client_id;
	private String name;
	private String phone;
	private String contactPIB;
	private String codeERPOU;
	private String codeTaxpayer;
	
	
	Client(int client_id, String name, String phone, String contactPIB, String codeERPOU, String codeTaxpayer)
	{
		this.client_id = client_id;
		this.name = name;
		this.phone = phone;
		this.contactPIB = contactPIB;
		this.codeERPOU = codeERPOU;
		this.codeTaxpayer = codeTaxpayer;
	}
	
	
	public Client()
	{
		
	}
	
	public int getId() 
	{
		return client_id;
	}
	
	public void setId(int client_id) 
	{
		this.client_id = client_id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getPhone() 
	{
		return phone;
	}
	
	public void setPhone(String phone) 
	{
		this.phone = phone;
	}
	
	public String getContactPIB() 
	{
		return contactPIB;
	}
	
	public void setContactPIB(String contactPIB) 
	{
		this.contactPIB = contactPIB;
	}
	
	public String getCodeERPOU() 
	{
		return codeERPOU;
	}
	
	public void setCodeERPOU(String codeERPOU) 
	{
		this.codeERPOU = codeERPOU;
	}
	
	public String getCodeTaxpayer() 
	{
		return codeTaxpayer;
	}
	
	public void setCodeTaxpayer(String codeTaxpayer) 
	{
		this.codeTaxpayer = codeTaxpayer;
	}

}
