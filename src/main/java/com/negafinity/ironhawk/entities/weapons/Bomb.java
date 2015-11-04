package com.negafinity.ironhawk.entities.weapons;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.Physics;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.entities.Enemy;
import com.negafinity.ironhawk.entities.Entity;
import com.negafinity.ironhawk.entities.Player;
import com.negafinity.ironhawk.libs.Animation;

public class Bomb extends Weapon
{
	public Animation animExplode;

	private static final ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();

	public Bomb(double x, double y, Textures tex, Controller c, Game game, Entity firer)
	{
		super(x, y, tex, c, game, firer);

		this.name = "Bomb";
		anim = new Animation(5, tex.bomb[0], tex.bomb[1]);
		animExplode = new Animation(10, tex.bomb[2], tex.bomb[3]);
	}

	public void removeInTwoSecconds()
	{
		final Entity entity = this;
		
		Runnable task = new Runnable()
		{
			public void run()
			{
				c.removeEntity(entity);
			}
		};
		worker.schedule(task, 2, TimeUnit.SECONDS);
	}

	@Override
	public void tick()
	{
		if (this.y < -1000 || this.y > 1000)
		{
			c.removeEntity(this);
		}

		
		for (int i = 0; i < c.getEntities().size(); i++)
		{
			Entity entity = c.getEntities().get(i);

			if (entity instanceof Enemy)
			{
				Enemy enemy = (Enemy) entity;

				if (Physics.collision(this, entity))
				{
					this.anim = this.animExplode;
					enemy.enemyHealth -= 100;
					removeInTwoSecconds();
				}
			}
		}
		
		anim.runAnimation();
	}

}
