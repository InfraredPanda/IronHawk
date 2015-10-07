package com.negafinity.ironhawk.entities.weapons;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.Physics;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.entities.Enemy;
import com.negafinity.ironhawk.entities.Entity;
import com.negafinity.ironhawk.libs.Animation;

public class Bomb extends Weapon
{
	public Animation animExplode;

	public Bomb(double x, double y, Textures tex, Controller c, Game game)
	{
		super(x, y, tex, c, game);

		this.name = "Bomb";
		anim = new Animation(5, tex.bomb[0], tex.bomb[1]);
		animExplode = new Animation(10, tex.bomb[1], tex.bomb[2], tex.bomb[3]);
	}

	@Override
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
					this.anim = this.animExplode;
					c.removeEntity(entity);
				}
			}
		}
	}

}
