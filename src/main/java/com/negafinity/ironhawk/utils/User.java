package com.negafinity.ironhawk.utils;

public class User
{
	private int highscore;
	private String UUID;
	
	public User(String UUID, int highscore)
	{
		this.UUID = UUID;
		this.highscore = highscore;
	}
	
	public String getUUID()
	{
		return UUID;
	}
	
	public double getHighscore()
	{
		return this.highscore;
	}
	
	public void setHighscore(int highscore)
	{
		this.highscore = highscore;
	}
	
	public void setUUID(String uUID)
	{
		UUID = uUID;
	}
}

