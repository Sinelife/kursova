package domain;




public class User
{

	private int user_id;
	private String surname;
	private String name;
	private String login;
	private String password;
	private String role;
	
	User(int user_id, String surname,String name,String login,String password,String role)
	{
		this.user_id = user_id; 
		this.surname = surname; 
		this.name = name;
		this.login= login; 
		this.password = password;
		this.role = role;
	}
	
	
	public User() 
	{
		
	}


	public int getId()
	{	
		return user_id;
	}
	
	public void setId(int user_id)
	{	
		this.user_id = user_id;
	}
	
	public String getSurname() 
	{
		return surname;	
	}
	
	public void setSurname(String surname) 
	{
		this.surname = surname;	
	}
	
	public String getName() 
	{
		return name;	
	}
	
	public void setName(String name) 
	{
		this.name = name;	
	}
	
	public String getLogin() 
	{
		return login;
	}
	
	public void setLogin(String login) 
	{
		this.login = login;
	}
	
	public String getPassword() 
	{
		return password;
	}
	
	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	public String getRole() 
	{
		return role;
	}
	
	public void setRole(String role) 
	{
		this.role = role;
	}
}
