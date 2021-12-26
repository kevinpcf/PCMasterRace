package model;

import java.io.Serializable;

public class UserBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public UserBean() 
	{
		username="";
		password="";
		nome="";
		cognome="";
		admin=false;
	}
	
	public String getUsername() 
	{
		return username;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	public String getNome() 
	{
		return nome;
	}
	public void setNome(String nome) 
	{
		this.nome = nome;
	}
	public String getCognome() 
	{
		return cognome;
	}
	public void setCognome(String cognome) 
	{
		this.cognome = cognome;
	}
	
	public boolean isAdmin() 
	{
		return admin;
	}

	public void setAdmin(boolean admin) 
	{
		this.admin = admin;
	}
	
	String password;
	String nome;
	String cognome;
	String username;
	boolean admin;
}
