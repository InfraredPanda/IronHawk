package com.negafinity.ironhawk.screens;

import com.negafinity.ironhawk.IronHawk;
import com.negafinity.ironhawk.utils.ScreenManager;

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
				if (IronHawk.getGame().screenManager.currentScreen == ScreenManager.STATE.START)
				{
					IronHawk.getGame().screenManager.currentScreen = ScreenManager.STATE.IRONHAWK;
				}
			}
		};
		worker.schedule(task, (long) 1.75, TimeUnit.SECONDS);
	}

	@Override
	public void render(Graphics g, IronHawk game)
	{
		g.drawImage(IronHawk.imageManager.negafinity, 0, 0, game);
	}

}
