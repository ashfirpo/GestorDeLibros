package gestorDeLibros;

public class Usuario {

	//Variables del usuario
	private String user;
	private String pass;

	//Constructores
	public Usuario(String user, String pass) 
	{
		this.user=user;
		this.pass=pass;
	}
	
	public Usuario() 
	{
	}

	//Gets y sets
	public String getUser() 
	{
		return user;
	}

	public void setUser(String user) 
	{
		this.user = user;
	}

	public String getPass() 
	{
		return pass;
	}

	public void setPass(String pass) 
	{
		this.pass = pass;
	}

}
