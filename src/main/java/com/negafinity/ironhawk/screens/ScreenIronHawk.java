package com.negafinity.ironhawk.screens;

import com.negafinity.ironhawk.IronHawk;
import com.negafinity.ironhawk.utils.ScreenManager;

import java.awt.Graphics;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScreenIronHawk extends Screen
{
	public boolean hasNotBeenCalled = true;

	private static final ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();

	public void showMenuIn10Sec()
	{
		Runnable task = new Runnable()
		{
			public void run()
			{
				if (IronHawk.getGame().screenManager.currentScreen == ScreenManager.STATE.IRONHAWK)
				{
					IronHawk.getGame().screenManager.currentScreen = ScreenManager.STATE.MENU;
				}
			}
		};
		worker.schedule(task, 2, TimeUnit.SECONDS);
	}

	@Override
	public void render(Graphics g, IronHawk game)
	{
		if (this.hasNotBeenCalled)
		{
			showMenuIn10Sec();
		}

		g.drawImage(IronHawk.imageManager.ironhawkscreen, 0, 0, game);
	}

}
