package com.negafinity.ironhawk.screens;

import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.ScreenManager;

import java.awt.Graphics;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScreenStart extends Screen
{
	public boolean hasNotBeenCalled = true;

	private static final ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();

	public void showIronHawkIn10Sec()
	{
		Runnable task = new Runnable()
		{
			public void run()
			{
				if (Game.getGame().screenManager.currentScreen == ScreenManager.STATE.START)
				{
					Game.getGame().screenManager.currentScreen = ScreenManager.STATE.IRONHAWK;
				}
			}
		};
		worker.schedule(task, (long) 1.75, TimeUnit.SECONDS);
	}

	@Override
	public void render(Graphics g, Game game)
	{
		g.drawImage(Game.imageManager.negafinity, 0, 0, game);
	}

}
