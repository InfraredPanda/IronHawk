package com.negafinity.ironhawk;

import com.negafinity.ironhawk.entities.Player;
import com.negafinity.ironhawk.screens.Start;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class RenderManager
{
	private Game game;

	public RenderManager(Game game)
	{
		this.game = game;
	}

	public void render()
	{
		Controller c = game.c;
		ScreenManager screenManager = game.screenManager;
		BufferStrategy bufferedStrat = game.getBufferStrategy();

		if (bufferedStrat == null)
		{
			game.createBufferStrategy(3);
			return;
		}

		Graphics g = bufferedStrat.getDrawGraphics();
		g.drawImage(Game.imageManager.image, 0, 0, game.getWidth(), game.getHeight(), game);
		g.drawImage(Game.imageManager.background, 0, 0, game);

		switch (screenManager.currentScreen)
		{
			case GAME:
				c.render(g);

				for (Player player : Game.players)
				{
					int moveFactor = 0;

					if (Game.players.indexOf(player) > 0)
					{
						moveFactor = 120;
					}

					player.render(g);

					Font fnt0 = new Font("arial", Font.BOLD, 20);
					g.setFont(fnt0);

					if (player.health / 2 == 0)
					{
						g.setColor(Color.red);
						g.fillRect(5, 5, 200, 50);
					}

					Color healthBarColor = Color.green;

					if (player.health / 2 <= 100 && player.health / 2 >= 60)
					{
						healthBarColor = Color.green;
					}
					else if (player.health / 2 >= 40 && player.health / 2 < 60)
					{
						healthBarColor = Color.yellow;
					}
					else
					{
						healthBarColor = Color.red;
					}

					g.setColor(healthBarColor);
					g.fillRect(5, 5 + moveFactor, player.health, 50);

					g.setColor(Color.white);
					g.drawString("Health of Player " + (Game.players.indexOf(player) + 1) + ":", 20, 20 + moveFactor);
					g.setColor(Color.gray);
					g.drawString(String.valueOf(player.health / 2), 20, 40 + moveFactor);

					g.setColor(Color.white);
					g.drawRect(5, 5 + moveFactor, 200, 50);

					if (player.bombCount > 0)
					{
						g.setColor(Color.white);
						g.drawString("Bombs", 500 - moveFactor, 475);
						g.drawString(String.valueOf(player.bombCount), 575 - moveFactor, 475);
					}

					if (player.missleCount > 0)
					{
						g.setColor(Color.white);
						g.drawString("Missles ", 250 - moveFactor, 475);
						g.drawString(String.valueOf(player.missleCount), 325 - moveFactor, 475);
					}
				}

				g.setColor(Color.white);
				g.drawString("Round", Game.WIDTH + Game.WIDTH - 80, 20);
				g.drawString(String.valueOf(Game.roundNumber), Game.WIDTH + Game.WIDTH - 10, 20);

				if (Game.gameStarting)
				{
					g.setColor(Color.white);
					g.drawString("Round Starts In: ", Game.WIDTH - 35, 45);
					g.drawString(String.valueOf(Game.timeToRound), Game.WIDTH + 125, 45);
				}

				g.setColor(Color.red);
				g.drawString("Enemies", Game.WIDTH - 15, 20);
				g.drawString(String.valueOf(Game.enemyCount), Game.WIDTH + 75, 20);
			case MENU:
				screenManager.screens.get(ScreenManager.STATE.MENU).render(g, game);
			case GAMEOVER:
				screenManager.screens.get(ScreenManager.STATE.GAMEOVER).render(g, game);
			case HELP:
				screenManager.screens.get(ScreenManager.STATE.HELP).render(g, game);
			case CONTROLS:
				screenManager.screens.get(ScreenManager.STATE.CONTROLS).render(g, game);
			case LOGIN:
				screenManager.screens.get(ScreenManager.STATE.LOGIN).render(g, game);
			case IRONHAWK:
				screenManager.screens.get(ScreenManager.STATE.IRONHAWK).render(g, game);
			case START:
				Start start = (Start) screenManager.screens.get(ScreenManager.STATE.START);

				if (start.hasNotBeenCalled)
				{
					start.hasNotBeenCalled = false;
					start.showIronHawkIn10Sec();
				}

				screenManager.screens.get(ScreenManager.STATE.START).render(g, game);
			case CHOICEMENU:
				screenManager.screens.get(ScreenManager.STATE.CHOICEMENU).render(g, game);
				g.drawImage(ImageManager.icon64, 55, 200, game);
				g.drawImage(ImageManager.icon64, 355, 200, game);
				g.drawImage(Game.imageManager.player2Sprite, 455, 200, game);
				// break
				g.dispose();
				bufferedStrat.show();
		}
	}
}
