package com.negafinity.ironhawk;

import com.negafinity.ironhawk.entities.Entity;

public class Physics
{
	public static boolean collision(Entity entityA, Entity entityB)
	{
		if (entityA.getBounds().intersects(entityB.getBounds()))
		{
			return true;
		}
		if (entityB.getBounds().intersects(entityA.getBounds()))
		{
			return true;
		}

		return false;
	}
}
