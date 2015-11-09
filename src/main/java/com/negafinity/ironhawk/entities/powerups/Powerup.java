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
import com.negafinity.ironhawk.entities.Player;
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

		for (Player player : Game.players)
		{
			if (Physics.collision(this, player))
			{
				c.removeEntity(this);
				if (Game.players.size() == 2 && Game.players.get(1) == player)
				{
					this.animEffect = new Animation(5, tex.player2[0], tex.player2[2]);
				}
				player.anim = this.animEffect;
				this.changeAnimationIn1Sec(player);
			}
		}
	}

	public void changeAnimationIn1Sec(Player player)
	{
		final Player target = player;

		Runnable task = new Runnable()
		{
			public void run()
			{
				if (target.anim.equals(animEffect))
				{
					if (Game.players.size() == 2 && target == Game.players.get(1))
					{
						target.defaultAnim = new Animation(5, tex.player2[0], tex.player2[1]);
					}
					target.anim = target.defaultAnim;
				}
			}
		};
		worker.schedule(task, 1, TimeUnit.SECONDS);
	}
}
