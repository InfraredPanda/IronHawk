package com.negafinity.ironhawk.entities;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.IronHawk;
import com.negafinity.ironhawk.Physics;
import com.negafinity.ironhawk.Textures;

import java.util.Random;

public class Enemy extends Entity
{
	public int speed;
	public int enemyHealth;

	public Enemy(double x, double y, Textures tex, Controller c, IronHawk game, int enemyHealth)
	{
		super(x, y, tex, c, game);

		Random r = new Random();
		this.speed = r.nextInt(5) + 1;
		this.enemyHealth = enemyHealth;
	}

	@Override
	public void tick()
	{
		super.tick();

		y += speed;
		applySpeedMultiplier();

		for (Player player : IronHawk.players)
		{
			if (Physics.collision(this, player))
			{
				this.enemyHealth -= 50;
				player.health -= 40;
			}
		}

		if (this.enemyHealth <= 0)
		{
			c.removeEntity(this);
		}
	}

	public void applySpeedMultiplier()
	{
		if (y > (IronHawk.HEIGHT * IronHawk.SCALE))
		{
			Random r = new Random();
			speed = r.nextInt(5) + 1;
			x = r.nextInt(640);
			y = -10;
		}
	}
}
