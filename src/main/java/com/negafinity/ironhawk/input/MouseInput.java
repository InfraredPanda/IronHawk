package com.negafinity.ironhawk.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.entities.Player;
import com.negafinity.ironhawk.libs.Animation;

public class MouseInput implements MouseListener
{
	private Controller c;
	private Game game;
	private Textures tex;

	public MouseInput(Controller c, Game game, Textures tex)
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
		if (Game.State == Game.STATE.MENU)
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
		if (Game.State == Game.STATE.MENU)
		{
			if (clickX >= 225 && clickX <= 430)
			{
				if (clickY >= 195 && clickY <= 250)
				{
					// Pressed Play
					// Picking 1 or 2 Players
					Game.State = Game.STATE.CHOICEMENU;
					return;
				}
			}
		}
		if (Game.State == Game.STATE.CHOICEMENU)
		{
			if (clickX >= 5 && clickX <= 105)
			{
				if (clickY >= 5 && clickY <= 55)
				{
					// Pressed Back
					Game.State = Game.STATE.HELP;
				}
			}
		}

		if (Game.State == Game.STATE.CHOICEMENU)
		{
			if (clickY < 348 && clickY > 147)
			{
				if (clickX <= Game.WIDTH / 2 + 160 && clickX >= 20)
				{
					// Picked SP
					if (Game.players.size() == 2)
					{
						Game.players.remove(1);
					}

					if (Game.players.get(0).getUser() != null)
					{
						Game.State = Game.STATE.GAME;
					}
					else
					{
						Game.State = Game.STATE.LOGIN;
					}
				}
				else if (clickX < 627)
				{
					Game.multiplayerEnabled = true;

					if (Game.players.get(0).getUser() != null && Game.players.get(1).getUser() != null)
					{
						Game.State = Game.STATE.GAME;
					}
					else
					{
						Game.State = Game.STATE.LOGIN;
					}
				}
			}
		}

		if (Game.State == Game.STATE.LOGIN)
		{
			if (clickX >= 5 && clickX <= 105)
			{
				if (clickY >= 5 && clickY <= 55)
				{
					// Pressed Back
					Game.State = Game.STATE.CHOICEMENU;
				}
			}
		}

		if (Game.State == Game.STATE.MENU)
		{
			if (clickX >= 225 && clickX <= 430)
			{
				if (clickY >= 260 && clickY <= 305)
				{
					// Pressed Help
					Game.State = Game.STATE.HELP;
				}
			}
		}
		if (Game.State == Game.STATE.MENU)
		{
			if (clickX >= 225 && clickX <= 430)
			{
				if (clickY >= 320 && clickY <= 375)
				{
					// Pressed Quit
					Game.saveUsers();
					System.exit(1);
				}
			}
		}

		if (Game.State == Game.STATE.IRONHAWK)
		{
			if (!(Game.ironhawk.hasNotBeenCalled))
			{
				Game.State = Game.STATE.MENU;
			}
		}

		if (Game.State == Game.STATE.START)
		{
			Game.State = Game.STATE.IRONHAWK;

			if (Game.ironhawk.hasNotBeenCalled)
			{
				Game.ironhawk.hasNotBeenCalled = false;
				Game.ironhawk.showMenuIn10Sec();
			}
		}

		if (Game.State == Game.STATE.HELP)
		{
			if (clickX >= 5 && clickX <= 105)
			{
				if (clickY >= 5 && clickY <= 55)
				{
					// Pressed Back
					Game.State = Game.STATE.MENU;
				}
			}
		}

		if (Game.State == Game.STATE.HELP)
		{
			if (clickX >= 450 && clickX <= 600)
			{
				if (clickY >= 5 && clickY <= 55)
				{
					// Pressed Back
					Game.State = Game.STATE.CONTROLS;
				}
			}
		}
		if (Game.State == Game.STATE.CONTROLS)
		{
			if (clickX >= 5 && clickX <= 105)
			{
				if (clickY >= 5 && clickY <= 55)
				{
					// Pressed Back
					Game.State = Game.STATE.HELP;
				}
			}
		}

		// Quit Button
		if (Game.State == Game.STATE.GAME || Game.State == Game.STATE.GAMEOVER)
		{
			if (clickX >= Game.WIDTH / 2 + 120 && clickX <= Game.WIDTH / 2 + 220)
			{
				if (clickY >= 350 && clickY <= 400)
				{
					// Pressed Quit
					System.exit(1);
				}
			}
		}
		if (Game.State == Game.STATE.GAMEOVER)
		{
			if (clickX >= Game.WIDTH / 2 + 120 && clickX <= Game.WIDTH / 2 + 250)
			{
				if (clickY >= 250 && clickY <= 300)
				{
					// Pressed Play Again
					game.entities.clear();
					Game.players.clear();

					if (Game.multiplayerEnabled)
					{
						Player player = new Player(200, 200, tex, c, game);
						player.anim = player.defaultAnim;
						Player player2 = new Player(250, 200, tex, c, game);
						player2.anim = new Animation(5, tex.player2[0], tex.player2[1]);

						Game.players.add(player);
						Game.players.add(player2);
					}
					else
					{
						Player player = new Player(200, 200, tex, c, game);
						Game.players.add(player);
					}

					Game.roundNumber = 0;
					Game.enemyCount = 10;
					Game.State = Game.STATE.GAME;
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
