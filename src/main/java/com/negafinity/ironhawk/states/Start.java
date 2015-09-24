package com.negafinity.ironhawk.states;

import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.Game.STATE;

import java.awt.Graphics;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Start
{
	public boolean hasNotBeenCalled = true;

	private static final ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();

	public void showMenuIn10Sec()
	{
		Runnable task = new Runnable()
		{
			public void run()
			{
				if(Game.State == STATE.START)
				{
					Game.State = Game.STATE.MENU;	
				}
			}
		};
		worker.schedule(task, 3, TimeUnit.SECONDS);
	}

	public void render(Graphics g, Game game)
	{
		g.drawImage(game.negafinity, 0, 0, game);
	}

}
