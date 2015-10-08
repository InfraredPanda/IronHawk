package com.negafinity.ironhawk.entities.weapons;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.Physics;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.entities.Enemy;
import com.negafinity.ironhawk.entities.Entity;

public class Weapon extends Entity
{
	public int speed;
	
	public Weapon(double x, double y, Textures tex, Controller c, Game game)
	{
		super(x, y, tex, c, game);
	}
	public void tick()
	{
		super.tick();

		for (int i = 0; i < c.getEntities().size(); i++)
		{
			Entity entity = c.getEntities().get(i);

			if (entity instanceof Enemy)
			{
				if (Physics.collision(this, entity))
				{
					Enemy enemy = (Enemy) entity;
					enemy.enemyHealth = enemy.enemyHealth - 50;
					c.removeEntity(this);
				}
			}
		}
		
		y -= speed;
	}

}
