package com.negafinity.ironhawk.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.entities.Player;
import com.negafinity.ironhawk.libs.Animation;
import com.negafinity.ironhawk.states.Menu;

public class MouseInput implements MouseListener
{
	private Controller c;
	private Game game;
	private Textures tex;
	
	public MouseInput(Controller c, Game game)
	{
		this.c = c;
		this.game = game;
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
			if (locX >= Game.WIDTH / 2 + 120 && locX <= Game.WIDTH / 2 + 220)
			{
				if (locY >= 150 && locY <= 200)
				{
					// Hovering Over Play
					Menu.overPlayButton = true;
					System.out.println("works");
					return;
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
			if (clickX >= Game.WIDTH / 2 + 120 && clickX <= Game.WIDTH / 2 + 220)
			{
				if (clickY >= 150 && clickY <= 200)
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
			if (clickY < 348 && clickY > 147)
			{
				if (clickX <= Game.WIDTH / 2 + 160 && clickX >= 20)
				{
					if (Game.players.size() == 2)
						Game.players.remove(1);

					Game.State = Game.STATE.GAME;
				}
				else if (clickX < 627)
				{
					Game.multiplayerEnabled = true;
					Game.State = Game.STATE.GAME;
				}
			}
		}
		if (Game.State == Game.STATE.MENU)
		{
			if (clickX >= Game.WIDTH / 2 + 120 && clickX <= Game.WIDTH / 2 + 220)
			{
				if (clickY >= 250 && clickY <= 300)
				{
					// Pressed Help
					Game.State = Game.STATE.HELP;
				}
			}
		}
		if (Game.State == Game.STATE.MENU)
		{
			if (clickX >= Game.WIDTH / 2 + 120 && clickX <= Game.WIDTH / 2 + 220)
			{
				if (clickY >= 340 && clickY <= 400)
				{
					// Pressed Quit
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

					if (Game.multiplayerEnabled)
					{
						Player player = new Player(200, 200, tex, c, game);
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

					for (Player player : Game.players)
					{
						player.health = 200;
						player.rapidFire = false;
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
