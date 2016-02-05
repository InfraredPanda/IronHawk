package com.negafinity.ironhawk.utils;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.IronHawk;
import com.negafinity.ironhawk.entities.Player;
import com.negafinity.ironhawk.screens.ScreenStart;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class RenderManager
{
	private IronHawk game;

	public RenderManager(IronHawk game)
	{
		this.game = game;
	}

	public void render()
	{
		Controller c = game.c;
		BufferStrategy bufferedStrat = game.getBufferStrategy();

		if (bufferedStrat == null)
		{
			game.createBufferStrategy(3);
			return;
		}

		Graphics g = bufferedStrat.getDrawGraphics();
		g.drawImage(IronHawk.imageManager.image, 0, 0, game.getWidth(), game.getHeight(), game);
		g.drawImage(IronHawk.imageManager.background, 0, 0, game);

		switch (game.screenManager.currentScreen)
		{
			case GAME:
				c.render(g);

				for (Player player : IronHawk.players)
				{
					int moveFactor = 0;

					if (IronHawk.players.indexOf(player) > 0)
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
					g.drawString("Health of Player " + (IronHawk.players.indexOf(player) + 1) + ":", 20, 20 + moveFactor);
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
				g.drawString("Round", IronHawk.WIDTH + IronHawk.WIDTH - 80, 20);
				g.drawString(String.valueOf(IronHawk.roundNumber), IronHawk.WIDTH + IronHawk.WIDTH - 10, 20);

				if (IronHawk.gameStarting)
				{
					g.setColor(Color.white);
					g.drawString("Round Starts In: ", IronHawk.WIDTH - 35, 45);
					g.drawString(String.valueOf(IronHawk.timeToRound), IronHawk.WIDTH + 125, 45);
				}

				g.setColor(Color.red);
				g.drawString("Enemies", IronHawk.WIDTH - 15, 20);
				g.drawString(String.valueOf(IronHawk.enemyCount), IronHawk.WIDTH + 75, 20);
				break;
			case START:
				ScreenStart start = (ScreenStart) game.screenManager.screens.get(game.screenManager.currentScreen);

				if (start.hasNotBeenCalled)
				{
					start.hasNotBeenCalled = false;
					start.showIronHawkIn10Sec();
				}

				game.screenManager.screens.get(game.screenManager.currentScreen).render(g, game);
				break;
			case CHOICEMENU:
				game.screenManager.screens.get(game.screenManager.currentScreen).render(g, game);
				g.drawImage(ImageManager.icon64, 55, 200, game);
				g.drawImage(ImageManager.icon64, 355, 200, game);
				g.drawImage(IronHawk.imageManager.player2Sprite, 455, 200, game);
				break;
			default:
				game.screenManager.screens.get(game.screenManager.currentScreen).render(g, game);
				break;
		}
		
		g.dispose();
		bufferedStrat.show();
	}
}
