package com.negafinity.ironhawk.entities;

import com.negafinity.ironhawk.Game.STATE;

import java.awt.Graphics;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.Physics;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.libs.Animation;

public class RapidFire extends Entity
{
	private static final ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();
	private Animation animEffect;

	public RapidFire(double x, double y, Textures tex, Controller c, Game game)
	{
		super(x, y, tex, c, game);

		this.animEffect = new Animation(5, tex.player[0], tex.player[2]);
		this.anim = new Animation(5, tex.rapidFire[0], tex.rapidFire[1]);
		this.name = "Rapid Fire";
	}

	@Override
	public void render(Graphics g)
	{
		super.render(g);

		if (Physics.collision(this, Game.player))
		{
			c.removeEntity(this);
			Game.rapidFire = true;
			disableRapidFireIn10Sec();
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

	public void disableRapidFireIn10Sec()
	{
		Runnable task = new Runnable()
		{
			public void run()
			{
				if (Game.State == STATE.GAME)
				{
					Game.rapidFire = false;
				}
			}
		};
		worker.schedule(task, 10, TimeUnit.SECONDS);
	}
}
