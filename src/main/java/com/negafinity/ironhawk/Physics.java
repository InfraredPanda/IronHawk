package com.negafinity.ironhawk;

import com.negafinity.ironhawk.entities.EntityA;
import com.negafinity.ironhawk.entities.EntityB;

public class Physics
{
	public static boolean Collision(EntityA enta, EntityB entb)
	{

		if (enta.getBounds().intersects(entb.getBounds()))
		{
			return true;
		}

		return false;
	}
	
	public static boolean Collision(EntityA enta1, EntityA enta2)
	{

		if (enta1.getBounds().intersects(enta2.getBounds()))
		{
			return true;
		}

		return false;
	}
	
	public static boolean Collision(EntityB entb1, EntityB entb2)
	{

		if (entb1.getBounds().intersects(entb2.getBounds()))
		{
			return true;
		}

		return false;
	}

	public static boolean Collision(EntityB entb, EntityA enta)
	{
		if (entb.getBounds().intersects(enta.getBounds()))
		{
			return true;
		}

		return false;
	}

}
