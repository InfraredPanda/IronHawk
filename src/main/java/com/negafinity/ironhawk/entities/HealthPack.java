package com.negafinity.ironhawk.entities;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.Physics;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.libs.Animation;

public class HealthPack extends Entity
{
	public HealthPack(double x, double y, Textures tex, Controller c, Game game)
	{
		super(x, y, tex, c, game);
		
		this.name = "Health Pack";
		anim = new Animation(5, tex.healthPack[0], tex.healthPack[1]);
	}

	@Override
	public void tick()
	{
		super.tick();
		
		if (Physics.collision(this, Game.player))
		{
			c.removeEntity(this);
			Game.player.health += 100;
		}
	}
}
