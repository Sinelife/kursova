package domain;

public class Provider 
{
	private int provider_id;
	private String name;
	private String phone;
	private String contactPIB;
	private String codeERPOU;
	private String codeTaxpayer;
	private String specialization;
	
	
	Provider(int provider_id, String name, String phone, String contactPIB, String codeERPOU, String codeTaxpayer, String specialization)
	{
		this.provider_id = provider_id;
		this.name = name;
		this.phone = phone;
		this.contactPIB = contactPIB;
		this.codeERPOU = codeERPOU;
		this.codeTaxpayer = codeTaxpayer;
		this.specialization = specialization;
	}
	
	
	public Provider()
	{
		
	}
	
	public int getId() 
	{
		return provider_id;
	}
	
	public void setId(int provider_id) 
	{
		this.provider_id = provider_id;
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

	public String getSpecialization() 
	{
		return specialization;
	}

	public void setSpecialization(String specialization) 
	{
		this.specialization = specialization;
	}

}
