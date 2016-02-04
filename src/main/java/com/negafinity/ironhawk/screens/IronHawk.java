package com.negafinity.ironhawk.screens;

import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.Game.STATE;

import java.awt.Graphics;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class IronHawk extends Screen
{
	public boolean hasNotBeenCalled = true;

	private static final ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();

	public void showMenuIn10Sec()
	{
		Runnable task = new Runnable()
		{
			public void run()
			{
				if (Game.State == STATE.IRONHAWK)
				{
					Game.State = Game.STATE.MENU;
				}
			}
		};
		worker.schedule(task, 2, TimeUnit.SECONDS);
	}

	@Override
	public void render(Graphics g, Game game)
	{
		if (this.hasNotBeenCalled)
		{
			showMenuIn10Sec();
		}

		g.drawImage(game.ironhawkscreen, 0, 0, game);
	}

}
