package domain;

public class Specialization 
{
	private String specialization;

	
	Specialization(String specialization)
	{
		this.specialization = specialization;
	}
	
	public Specialization()
	{
		
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
