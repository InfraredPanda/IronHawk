package com.negafinity.ironhawk.entities;

import java.awt.Rectangle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.Physics;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.entities.weapons.Bullet;
import com.negafinity.ironhawk.entities.weapons.Warhead;
import com.negafinity.ironhawk.libs.Animation;

public class Bomber extends Enemy
{
	private boolean hasShot;
	private static final ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();

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

		c.moveTowardsPlayer(this);

		if (this.x == Game.player.x && this.y < Game.player.y && !this.hasShot)
		{
			c.addEntity(new Warhead(this.x + 16, this.y + 16, tex, c, game, true));
			this.hasShot = true;
			this.enableShootingInOneSec();
		}

		if (Physics.collision(this, Game.player))
		{
			Game.player.health -= 100;
		}

	}

	@Override
	public Rectangle getBounds()
	{
		return new Rectangle((int) x, (int) y, 64, 64);
	}

	public void enableShootingInOneSec()
	{
		Runnable task = new Runnable()
		{
			public void run()
			{
				if (hasShot)
				{
					hasShot = false;
				}
			}
		};
		worker.schedule(task, 1, TimeUnit.SECONDS);
	}
}
