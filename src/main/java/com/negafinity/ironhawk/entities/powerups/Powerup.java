package com.negafinity.ironhawk.entities.powerups;

import java.awt.Graphics;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.Physics;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.entities.Entity;
import com.negafinity.ironhawk.libs.Animation;

public class Powerup extends Entity
{
	private static final ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();
	public Animation animEffect;

	public Powerup(double x, double y, Textures tex, Controller c, Game game)
	{
		super(x, y, tex, c, game);

		this.animEffect = new Animation(5, tex.player[0], tex.player[2]);
	}

	@Override
	public void render(Graphics g)
	{
		super.render(g);

		if (Physics.collision(this, Game.player))
		{
			c.removeEntity(this);
			Game.player.anim = this.animEffect;
			this.changeAnimationIn1Sec();
		}
	}

	public void changeAnimationIn1Sec()
	{
		Runnable task = new Runnable()
		{
			public void run()
			{
				if (Game.player.anim.equals(animEffect))
				{
					Game.player.anim = Game.player.defaultAnim;
				}
			}
		};
		worker.schedule(task, 1, TimeUnit.SECONDS);
	}
}
