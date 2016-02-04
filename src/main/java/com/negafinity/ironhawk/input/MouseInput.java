package com.negafinity.ironhawk.input;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.IronHawk;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.entities.Player;
import com.negafinity.ironhawk.libs.Animation;
import com.negafinity.ironhawk.screens.ScreenIronHawk;
import com.negafinity.ironhawk.utils.ScreenManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener
{
	private Controller c;
	private IronHawk game;
	private Textures tex;

	public MouseInput(Controller c, IronHawk game, Textures tex)
	{
		this.c = c;
		this.game = game;
		this.tex = tex;
	}

	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		;
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		int locX = e.getX();
		int locY = e.getY();

		// Play Button
		if (game.screenManager.currentScreen == ScreenManager.STATE.MENU)
		{
			if (locX >= 225 && locX <= 430)
			{
				if (locY >= 195 && locY <= 250)
				{

				}
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		;
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		int clickX = e.getX();
		int clickY = e.getY();

		// Play Button
		if (game.screenManager.currentScreen == ScreenManager.STATE.MENU)
		{
			if (clickX >= 225 && clickX <= 430)
			{
				if (clickY >= 195 && clickY <= 250)
				{
					// Pressed Play
					// Picking 1 or 2 Players
					game.screenManager.currentScreen = ScreenManager.STATE.CHOICEMENU;
					return;
				}
			}
		}
		
		if (game.screenManager.currentScreen == ScreenManager.STATE.CHOICEMENU)
		{
			if (clickX >= 5 && clickX <= 105)
			{
				if (clickY >= 5 && clickY <= 55)
				{
					// Pressed Back
					game.screenManager.currentScreen = ScreenManager.STATE.HELP;
				}
			}
		}

		if (game.screenManager.currentScreen == ScreenManager.STATE.CHOICEMENU)
		{
			if (clickY < 348 && clickY > 147)
			{
				if (clickX <= IronHawk.WIDTH / 2 + 160 && clickX >= 20)
				{
					// Picked SP
					if (IronHawk.players.size() == 2)
					{
						IronHawk.players.remove(1);
					}

					if (IronHawk.players.get(0).getUser() != null)
					{
						game.screenManager.currentScreen = ScreenManager.STATE.GAME;
					}
					else
					{
						game.screenManager.currentScreen = ScreenManager.STATE.LOGIN;
					}
				}
				else if (clickX < 627)
				{
					IronHawk.multiplayerEnabled = true;

					if (IronHawk.players.get(0).getUser() != null && IronHawk.players.get(1).getUser() != null)
					{
						game.screenManager.currentScreen = ScreenManager.STATE.GAME;
					}
					else
					{
						game.screenManager.currentScreen = ScreenManager.STATE.LOGIN;
					}
				}
			}
		}

		if (game.screenManager.currentScreen == ScreenManager.STATE.LOGIN)
		{
			if (clickX >= 5 && clickX <= 105)
			{
				if (clickY >= 5 && clickY <= 55)
				{
					// Pressed Back
					game.screenManager.currentScreen = ScreenManager.STATE.CHOICEMENU;
				}
			}
		}

		if (game.screenManager.currentScreen == ScreenManager.STATE.MENU)
		{
			if (clickX >= 225 && clickX <= 430)
			{
				if (clickY >= 260 && clickY <= 305)
				{
					// Pressed Help
					game.screenManager.currentScreen = ScreenManager.STATE.HELP;
				}
			}
		}
		
		if (game.screenManager.currentScreen == ScreenManager.STATE.MENU)
		{
			if (clickX >= 225 && clickX <= 430)
			{
				if (clickY >= 320 && clickY <= 375)
				{
					// Pressed Quit
					game.dataManager.saveUsers();
					System.exit(1);
				}
			}
		}

		if (game.screenManager.currentScreen == ScreenManager.STATE.IRONHAWK)
		{
			ScreenIronHawk ironHawk = (ScreenIronHawk) game.screenManager.screens.get(ScreenManager.STATE.IRONHAWK);

			if (!ironHawk.hasNotBeenCalled)
			{
				game.screenManager.currentScreen = ScreenManager.STATE.MENU;
			}
		}

		if (game.screenManager.currentScreen == ScreenManager.STATE.START)
		{
			game.screenManager.currentScreen = ScreenManager.STATE.IRONHAWK;
			ScreenIronHawk ironHawk = (ScreenIronHawk) game.screenManager.screens.get(ScreenManager.STATE.IRONHAWK);

			if (ironHawk.hasNotBeenCalled)
			{
				ironHawk.hasNotBeenCalled = false;
				ironHawk.showMenuIn10Sec();
			}
		}

		if (game.screenManager.currentScreen == ScreenManager.STATE.HELP)
		{
			if (clickX >= 5 && clickX <= 105)
			{
				if (clickY >= 5 && clickY <= 55)
				{
					// Pressed Back
					game.screenManager.currentScreen = ScreenManager.STATE.MENU;
				}
			}
		}

		if (game.screenManager.currentScreen == ScreenManager.STATE.HELP)
		{
			if (clickX >= 450 && clickX <= 600)
			{
				if (clickY >= 5 && clickY <= 55)
				{
					// Pressed Back
					game.screenManager.currentScreen = ScreenManager.STATE.CONTROLS;
				}
			}
		}

		if (game.screenManager.currentScreen == ScreenManager.STATE.CONTROLS)
		{
			if (clickX >= 5 && clickX <= 105)
			{
				if (clickY >= 5 && clickY <= 55)
				{
					// Pressed Back
					game.screenManager.currentScreen = ScreenManager.STATE.HELP;
				}
			}
		}

		// Quit Button
		if (game.screenManager.currentScreen == ScreenManager.STATE.GAME || game.screenManager.currentScreen == ScreenManager.STATE.GAMEOVER)
		{
			if (clickX >= IronHawk.WIDTH / 2 + 120 && clickX <= IronHawk.WIDTH / 2 + 220)
			{
				if (clickY >= 350 && clickY <= 400)
				{
					// Pressed Quit
					System.exit(1);
				}
			}
		}

		if (game.screenManager.currentScreen == ScreenManager.STATE.GAMEOVER)
		{
			if (clickX >= IronHawk.WIDTH / 2 + 120 && clickX <= IronHawk.WIDTH / 2 + 250)
			{
				if (clickY >= 250 && clickY <= 300)
				{
					// Pressed Play Again
					game.entities.clear();
					IronHawk.players.clear();

					if (IronHawk.multiplayerEnabled)
					{
						Player player = new Player(200, 200, tex, c, game);
						player.anim = player.defaultAnim;
						Player player2 = new Player(250, 200, tex, c, game);
						player2.anim = new Animation(5, tex.player2[0], tex.player2[1]);

						IronHawk.players.add(player);
						IronHawk.players.add(player2);
					}
					else
					{
						Player player = new Player(200, 200, tex, c, game);
						IronHawk.players.add(player);
					}

					IronHawk.roundNumber = 0;
					IronHawk.enemyCount = 10;
					game.screenManager.currentScreen = ScreenManager.STATE.GAME;
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		;
	}
}
