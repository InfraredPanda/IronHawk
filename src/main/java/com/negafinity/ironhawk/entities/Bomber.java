package com.negafinity.ironhawk.entities;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.Physics;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.libs.Animation;

public class Bomber extends Enemy
{
	public Bomber(double x, double y, Textures tex, Controller c, Game game, int enemyHealth)
	{
		super(x, y, tex, c, game, enemyHealth);

		this.name = "B-29 Bomber";
		this.speed = 0;
		this.enemyHealth = 500;
		anim = new Animation(5, tex.bomber[0], tex.bomber[1]);

	}

	@Override
	public void tick()
	{
		super.tick();

		if (this.x == 640)
		{
			x--;
		}
		if (this.x == 0)
		{
			x++;
		}
		if (Physics.collision(this, Game.player))
		{
			Game.player.health -= 100;
		}
		

	}
}
