package com.negafinity.ironhawk.utils;

public class User
{
	private String UUID;
	private String username;
	private String password;
	private int highscore;

	public User(String UUID, String username, String password, int highscore)
	{
		this.UUID = UUID;
		this.username = username;
		this.password = password;
		this.highscore = highscore;
	}

	public String getUUID()
	{
		return UUID;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}

	public double getHighscore()
	{
		return this.highscore;
	}

	public void setHighscore(int highscore)
	{
		this.highscore = highscore;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}

	public void setUUID(String UUID)
	{
		this.UUID = UUID;
	}
}
