package com.negafinity.ironhawk.entities;

import java.awt.Graphics;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.Physics;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.Game.STATE;
import com.negafinity.ironhawk.libs.Animation;

public class HealthPack extends Entity
{
	private Animation animEffect;
	private static final ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();

	public HealthPack(double x, double y, Textures tex, Controller c, Game game)
	{
		super(x, y, tex, c, game);

		this.name = "Health Pack";
		this.animEffect = new Animation(5, tex.player[0], tex.player[2]);
		;
		this.anim = new Animation(5, tex.healthPack[0], tex.healthPack[1]);
	}

	@Override
	public void render(Graphics g)
	{
		super.render(g);

		if (Physics.collision(this, Game.player))
		{
			Game.player.anim = this.animEffect;
			this.changeAnimationIn1Sec();
			this.c.removeEntity(this);
			Game.player.health += 100;
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
