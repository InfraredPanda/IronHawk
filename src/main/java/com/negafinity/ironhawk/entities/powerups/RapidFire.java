package com.negafinity.ironhawk.entities.powerups;

import java.awt.Graphics;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.Game.STATE;
import com.negafinity.ironhawk.Physics;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.entities.Player;
import com.negafinity.ironhawk.libs.Animation;

public class RapidFire extends Powerup
{
	private static final ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();

	public RapidFire(double x, double y, Textures tex, Controller c, Game game)
	{
		super(x, y, tex, c, game);

		this.anim = new Animation(5, tex.rapidFire[0], tex.rapidFire[1]);
		this.name = "Rapid Fire";
	}

	@Override
	public void render(Graphics g)
	{
		super.render(g);

		for(Player player : Game.players)
		{
			if (Physics.collision(this, player))
			{
				player.rapidFire = true;
				this.disableRapidFireIn10Sec(player);
			}
		}
	}

	public void disableRapidFireIn10Sec(Player player)
	{
		final Player target = player;
		
		Runnable task = new Runnable()
		{
			public void run()
			{
				if (Game.State == STATE.GAME)
				{
					target.rapidFire = false;
				}
			}
		};
		worker.schedule(task, 10, TimeUnit.SECONDS);
	}
}
